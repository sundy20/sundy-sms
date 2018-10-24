/**
 * Copyright (c) 2005-2016, cnfuelteam (fuelteam@163.com)"
 * <p>
 * Licensed under The MIT License (MIT)
 */
package com.sundy.sms.service.service;

import com.github.pagehelper.PageInfo;
import com.sundy.sms.service.domain.SmsTemplate;

import java.util.List;
import java.util.Map;

/**
 * 描述： 短信模板表（sms_template） 接口
 * <p>
 * 作者： plus
 * 时间： 2017-12-19 19:15:13
 */
public interface SmsTemplateService {

    public PageInfo<SmsTemplate> findPage(int pageNum, int pageSize, Map<String, Object> params);

    public List<SmsTemplate> findAll();

    public SmsTemplate findByTemplateNo(String templateNo);

    public SmsTemplate get(Long id);

    public int save(SmsTemplate smsTemplate);

    public int update(SmsTemplate smsTemplate);

}