/**
 * Copyright (c) 2005-2016, cnfuelteam (fuelteam@163.com)"
 * <p>
 * Licensed under The MIT License (MIT)
 */
package com.sundy.sms.service.domain;

import java.util.Date;

/**
 * 描述： 短信模板表（sms_template）
 * <p>
 * 作者： plus
 * 时间： 2017-12-20 13:16:13
 */
public class SmsTemplate {

    private Long id;

    /**
     * 1:短信 2:邮件
     */
    private Long type;

    /**
     * 模板号
     */
    private String templateNo;

    /**
     * 标题
     */
    private String title;

    /**
     * 短信内容
     */
    private String content;

    /**
     * 谁发送的
     */
    private String sendFrom;

    /**
     * 创建日期
     */
    private Date createAt;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 更新日期
     */
    private Date updateAt;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 是否删除  Y N
     */
    private String deleted;

    /**
     * 备注
     */
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getTemplateNo() {
        return templateNo;
    }

    public void setTemplateNo(String templateNo) {
        this.templateNo = templateNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendFrom() {
        return sendFrom;
    }

    public void setSendFrom(String sendFrom) {
        this.sendFrom = sendFrom;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}