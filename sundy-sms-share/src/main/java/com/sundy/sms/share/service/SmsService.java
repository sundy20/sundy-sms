package com.sundy.sms.share.service;


import com.sundy.sms.share.Result;
import com.sundy.sms.share.dto.EmailRequestDTO;
import com.sundy.sms.share.dto.GetuiRequestDTO;
import com.sundy.sms.share.dto.PhoneRequestDTO;
import com.sundy.sms.share.dto.SmsResultDTO;

/**
 * 发送短信 邮件服务 个推消息接口
 */
public interface SmsService {


    /**
     * @param phoneRequestDTO
     * @description 发送短信
     */
    Result<SmsResultDTO> sendMsg(PhoneRequestDTO phoneRequestDTO);

    /**
     * @param emailRequestDTO
     * @description 发送邮件
     */
    Result<SmsResultDTO> sendEmail(EmailRequestDTO emailRequestDTO);

    /**
     * 发送个推消息
     *
     * @param getuiRequestDTO
     * @return
     */
    Result<SmsResultDTO> sendGetuiMsg(GetuiRequestDTO getuiRequestDTO);

}
