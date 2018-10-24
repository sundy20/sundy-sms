package com.sundy.sms.service.mongodb.service.impl;

import com.sundy.sms.service.mongodb.model.EmailLog;
import com.sundy.sms.service.mongodb.model.SmsLog;
import com.sundy.sms.service.mongodb.service.SmsLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

/**
 * Created on 2017/12/11
 *
 * @author plus.wang
 * @description
 */
@Component
public class SmsLogServiceImpl implements SmsLogService {

    @Autowired
    MongoOperations mongoOperations;

    @Override
    public void insertSmsLog(SmsLog smsLog) {
        mongoOperations.save(smsLog);
    }

    @Override
    public SmsLog findSmsLogByPhone(String phone) {

        Query query = new Query(Criteria.where("phone").is(phone));

        query.with(new Sort(Sort.Direction.DESC, "_id"));

        query.limit(1);

        return mongoOperations.findOne(query, SmsLog.class);
    }

    @Override
    public void insertEmailLog(EmailLog emailLog) {
        mongoOperations.save(emailLog);
    }

    @Override
    public EmailLog findEmailLogByEmail(String email) {

        Query query = new Query(Criteria.where("email").is(email));

        query.with(new Sort(Sort.Direction.DESC, "_id"));

        query.limit(1);

        return mongoOperations.findOne(query, EmailLog.class);
    }
}
