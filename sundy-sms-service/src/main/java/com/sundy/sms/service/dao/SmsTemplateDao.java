/**
 * Copyright (c) 2005-2016, cnfuelteam (fuelteam@163.com)"
 * <p>
 * Licensed under The MIT License (MIT)
 */
package com.sundy.sms.service.dao;

import com.sundy.sms.service.domain.SmsTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 描述： 操作 短信模板表（sms_template）
 *
 * 作者： plus
 * 时间： 2017-12-19 19:15:13
 */
@Repository
public interface SmsTemplateDao {

    public SmsTemplate findById(Long id);

    public List<SmsTemplate> findByIds(List<Long> ids);

    public List<SmsTemplate> findByExample(SmsTemplate smsTemplate);

    public int count(SmsTemplate smsTemplate);

    public List<SmsTemplate> findByParams(Map<String, Object> params);

    public int countByParams(Map<String, Object> params);

    public List<SmsTemplate> findAll();

    public int save(SmsTemplate smsTemplate);

    public int update(SmsTemplate smsTemplate);

    public int delete(Long id);

    public int saveOrUpdate(SmsTemplate smsTemplate);

    public int batchSave(List<SmsTemplate> smsTemplateList);

    public int batchUpdate(List<SmsTemplate> smsTemplateList);

    public int batchDelete(List<Long> ids);

}