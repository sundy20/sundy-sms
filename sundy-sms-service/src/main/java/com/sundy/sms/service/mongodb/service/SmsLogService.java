package com.sundy.sms.service.mongodb.service;


import com.sundy.sms.service.mongodb.model.EmailLog;
import com.sundy.sms.service.mongodb.model.SmsLog;

/**
 * Created on 2017/12/11
 *
 * @author plus.wang
 * @description 操作底层mongodb数据看
 */
public interface SmsLogService {

    public void insertSmsLog(SmsLog smsLog);

    public SmsLog findSmsLogByPhone(String phone);

    public void insertEmailLog(EmailLog emailLog);

    public EmailLog findEmailLogByEmail(String email);

}
