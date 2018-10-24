package com.sundy.sms.service.message;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.sundy.sms.service.config.AliSmsMobileProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 阿里大于短信
 *
 * @author lonyee
 */
@Component
public class AliSmsClient {

    @Autowired
    private AliSmsMobileProperties aliSmsMobileProperties;

    //设置10秒超时
    private static final String TIME_OUT = "10000";

    /**
     * 发送短信
     */
    public SendSmsResponse send(String mobiles, String templateNO, String jsonContent) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", TIME_OUT);
        System.setProperty("sun.net.client.defaultReadTimeout", TIME_OUT);

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", aliSmsMobileProperties.getAppKey(), aliSmsMobileProperties.getAppSecret());
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", aliSmsMobileProperties.getProduct(), aliSmsMobileProperties.getDomain());
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //使用post提交
        request.setMethod(MethodType.POST);
        //必填:待发送手机号
        request.setPhoneNumbers(mobiles);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(aliSmsMobileProperties.getSign());
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templateNO);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam(jsonContent);

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        return sendSmsResponse;
    }

}
