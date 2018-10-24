package com.sundy.sms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.sundy.sms.service.domain.SmsTemplate;
import com.sundy.sms.service.getui.GtPushComponent;
import com.sundy.sms.service.message.AliSmsClient;
import com.sundy.sms.service.message.EmailClient;
import com.sundy.sms.service.mongodb.model.EmailLog;
import com.sundy.sms.service.mongodb.model.SmsLog;
import com.sundy.sms.service.mongodb.service.SmsLogService;
import com.sundy.sms.service.service.SmsTemplateService;
import com.sundy.sms.share.Result;
import com.sundy.sms.share.ResultCode;
import com.sundy.sms.share.dto.EmailRequestDTO;
import com.sundy.sms.share.dto.GetuiRequestDTO;
import com.sundy.sms.share.dto.PhoneRequestDTO;
import com.sundy.sms.share.dto.SmsResultDTO;
import com.sundy.sms.share.service.SmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.util.HtmlUtils;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * @author plus
 */
@Service(version = "1.0.0", protocol = {"dubbo"})
public class SmsServiceImpl implements SmsService {

    private static final Logger log = LoggerFactory.getLogger(SmsServiceImpl.class);

    private static final String REGIST_TEMPLATE_NO = "c06c63d2ec544c008c4e";

    private static final String RESET_TEMPLATE_NO = "31dc91bc321d4c3fb585";

    @Autowired
    private SmsLogService smsLogService;

    @Autowired
    private SmsTemplateService smsTemplateService;

    @Autowired
    private AliSmsClient aliSmsClient;

    @Autowired
    private EmailClient emailClient;

    @Autowired
    private ExecutorService executorService;

    @Autowired
    private GtPushComponent gtPushComponent;

    /**
     * @param phoneRequestDTO 发送短信请求实体
     * @description 发送短信
     */
    @Override
    public Result<SmsResultDTO> sendMsg(PhoneRequestDTO phoneRequestDTO) {

        Result<SmsResultDTO> smsResultDTOResult = Result.failure();

        if (null != phoneRequestDTO) {

            String templateNo = phoneRequestDTO.getTemplateNo();

            Map<String, Object> params = phoneRequestDTO.getParams();

            SmsLog smsLog = new SmsLog();

            smsLog.setSendDate(new Date());

            smsLog.setTemplateNo(templateNo);

            smsLog.setPhone(phoneRequestDTO.getMobile());

            try {

                String jsonContent = JSON.toJSONString(params);

                SendSmsResponse sendSmsResponse = aliSmsClient.send(phoneRequestDTO.getMobile(), templateNo, jsonContent);

                if (sendSmsResponse.getCode() != null && "OK".equals(sendSmsResponse.getCode())) {

                    smsLog.setContent(jsonContent);

                    smsLog.setResult(sendSmsResponse.getMessage());

                    smsResultDTOResult.code(ResultCode.SUCCESS.getCode());

                    log.info("SmsServiceImpl.sendMsg alidayu success result : " + sendSmsResponse.getMessage());

                } else {

                    log.info("SmsServiceImpl.sendMsg alidayu failure result : " + sendSmsResponse.getMessage());

                    if ("SMS_119091568".equals(templateNo)) {

                        templateNo = REGIST_TEMPLATE_NO;

                    } else if ("SMS_119091571".equals(templateNo)) {

                        templateNo = RESET_TEMPLATE_NO;
                    }
                    //TODO 阿里大于发送失败后使用第三方渠道发送

                }

            } catch (Exception e) {

                log.error("SmsServiceImpl.sendMsg error message : " + e.getMessage(), e);

                smsLog.setErrorMessage(e.getMessage());
            }

            executorService.submit(() -> {
                try {
                    smsLogService.insertSmsLog(smsLog);
                } catch (Exception e) {
                    log.error("SmsServiceImpl.sendMsg insertMsgLog error: {} logContent: {}", e.getMessage(), JSON.toJSONString(smsLog), e);

                }
            });
        }

        return smsResultDTOResult;
    }

    /**
     * @param emailRequestDTO 发送邮件请求实体
     * @description 发送邮件
     */
    @Override
    public Result<SmsResultDTO> sendEmail(EmailRequestDTO emailRequestDTO) {

        Result<SmsResultDTO> smsResultDTOResult = Result.failure();

        if (null != emailRequestDTO) {

            String templateNo = emailRequestDTO.getTemplateNo();

            SmsTemplate smsTemplate = smsTemplateService.findByTemplateNo(templateNo);

            Map<String, Object> params = emailRequestDTO.getParams();

            params.put("title", smsTemplate.getTitle());

            String contentTemplate = smsTemplate.getContent();

            String content = getContent(contentTemplate, params);

            EmailLog smsLog = new EmailLog();

            smsLog.setEmail(emailRequestDTO.getEmail());

            smsLog.setSendFrom(smsTemplate.getSendFrom());

            smsLog.setContent(JSON.toJSONString(params));

            smsLog.setSendDate(new Date());

            smsLog.setTemplateNo(templateNo);

            try {

                emailClient.sendHtml(emailRequestDTO.getEmail(), smsTemplate.getSendFrom(), smsTemplate.getTitle(), HtmlUtils.htmlUnescape(content));

                smsLog.setResult("success");

                smsResultDTOResult.code(ResultCode.SUCCESS.getCode());

            } catch (Exception e) {

                log.error("SmsServiceImpl.sendEmail error message : " + e.getMessage(), e);

                smsLog.setErrorMessage(e.getMessage());
            }

            executorService.submit(() -> {
                try {
                    smsLogService.insertEmailLog(smsLog);
                } catch (Exception e) {
                    log.error("SmsServiceImpl.sendEmail insertEmailLog error: {} logContent: {}", e.getMessage(), JSON.toJSONString(smsLog), e);
                }
            });
        }

        return smsResultDTOResult;
    }

    /**
     * 发送个推消息
     *
     * @param getuiRequestDTO
     * @return
     */
    @Override
    public Result<SmsResultDTO> sendGetuiMsg(GetuiRequestDTO getuiRequestDTO) {

        Result<SmsResultDTO> smsResultDTOResult = Result.failure();

        if (StringUtils.isEmpty(getuiRequestDTO.getAppRefer()) || null == getuiRequestDTO.getUserId()) {

            return smsResultDTOResult;
        }

        try {

            if ("ios".equalsIgnoreCase(getuiRequestDTO.getAppRefer()) && !StringUtils.isEmpty(getuiRequestDTO.getClientId())) {

                gtPushComponent.pushMessageToSingleIOS(getuiRequestDTO.getClientId(), getuiRequestDTO.getTitle(), getuiRequestDTO.getText(), getuiRequestDTO.getContent());

            } else {

                gtPushComponent.pushMessageToSingleAndroid(getuiRequestDTO.getClientId(), getuiRequestDTO.getTitle(), getuiRequestDTO.getText(), getuiRequestDTO.getContent());
            }

        } catch (Exception e) {

            log.error("SmsServiceImpl.sendGetuiMsg error message : " + e.getMessage(), e);
        }

        return smsResultDTOResult;
    }

    private String getContent(String content, Map<String, Object> stringObjectMap) {

        StringBuilder stringBuilder = new StringBuilder();

        for (String key : stringObjectMap.keySet()) {

            content = content.replace("${" + key + "}", stringObjectMap.get(key).toString());
        }

        stringBuilder.append(content);

        return stringBuilder.toString();
    }
}
