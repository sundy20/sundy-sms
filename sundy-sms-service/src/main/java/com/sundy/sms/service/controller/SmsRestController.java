package com.sundy.sms.service.controller;

import com.sundy.sms.share.Result;
import com.sundy.sms.share.dto.EmailRequestDTO;
import com.sundy.sms.share.dto.PhoneRequestDTO;
import com.sundy.sms.share.dto.SmsResultDTO;
import com.sundy.sms.share.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2017/12/20
 *
 * @author plus.wang
 * @description 发送短信邮件控制器
 */
@RestController
public class SmsRestController {

    @Autowired
    private SmsService smsService;

    @RequestMapping("/rest/sms/phone")
    public Result<SmsResultDTO> sendMsg(@RequestBody PhoneRequestDTO phoneRequestDTO) {

        return smsService.sendMsg(phoneRequestDTO);
    }

    @RequestMapping("/rest/sms/email")
    public Result<SmsResultDTO> sendEmail(@RequestBody EmailRequestDTO emailRequestDTO) {

        return smsService.sendEmail(emailRequestDTO);
    }

}
