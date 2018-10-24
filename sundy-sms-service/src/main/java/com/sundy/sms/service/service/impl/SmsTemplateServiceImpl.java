/**
 * Copyright (c) 2005-2016, cnfuelteam (fuelteam@163.com)"
 * <p>
 * Licensed under The MIT License (MIT)
 */
package com.sundy.sms.service.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sundy.sms.service.dao.SmsTemplateDao;
import com.sundy.sms.service.domain.SmsTemplate;
import com.sundy.sms.service.service.SmsTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述： 短信模板表（sms_template） 接口实现
 * <p>
 * 作者： plus
 * 时间： 2017-12-19 19:15:13
 */
@Service
public class SmsTemplateServiceImpl implements SmsTemplateService {

    @Autowired
    private SmsTemplateDao smsTemplateDao;

    @Override
    public PageInfo<SmsTemplate> findPage(int pageNum, int pageSize, Map<String, Object> params) {
        PageHelper.startPage(pageNum, pageSize);
        List<SmsTemplate> list = smsTemplateDao.findByParams(params);
        PageInfo<SmsTemplate> page = new PageInfo<SmsTemplate>(list);
        return page;
    }

    @Override
    public List<SmsTemplate> findAll() {
        Map<String, Object> params = new HashMap<String, Object>();
        return smsTemplateDao.findByParams(params);
    }

    @Override
    public SmsTemplate findByTemplateNo(String templateNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("templateNo", templateNo);
        List<SmsTemplate> list = smsTemplateDao.findByParams(params);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public SmsTemplate get(Long id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        List<SmsTemplate> list = smsTemplateDao.findByParams(params);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public int save(SmsTemplate smsTemplate) {
        smsTemplate.setCreateAt(new Date());
        return smsTemplateDao.save(smsTemplate);
    }

    @Override
    public int update(SmsTemplate smsTemplate) {
        smsTemplate.setUpdateAt(new Date());
        return smsTemplateDao.update(smsTemplate);
    }

}

