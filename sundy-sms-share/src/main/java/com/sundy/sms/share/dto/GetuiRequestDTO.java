package com.sundy.sms.share.dto;

import java.io.Serializable;
import java.util.Map;

/**
 * @author plus.wang
 * @description 个推请求消息
 * @date 2018/8/7
 */
public class GetuiRequestDTO implements Serializable {

    private static final long serialVersionUID = 3826838176926648421L;

    /**
     * 必填
     */
    private Long userId;

    /**
     * 必填
     */
    private String appRefer;

    /**
     * 个推clientId
     */
    private String clientId;

    /**
     * 标题 必填
     */
    private String title;

    /**
     * 内容 必填
     */
    private String text;

    /**
     * 透传信息内容
     */
    private Map<String, Object> content;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Map<String, Object> getContent() {
        return content;
    }

    public void setContent(Map<String, Object> content) {
        this.content = content;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAppRefer() {
        return appRefer;
    }

    public void setAppRefer(String appRefer) {
        this.appRefer = appRefer;
    }
}
