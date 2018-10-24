package com.sundy.sms.share.dto;

import java.io.Serializable;
import java.util.Map;

public class EmailRequestDTO implements Serializable {

    private static final long serialVersionUID = 3826838176926648421L;

    private String email;

    private String templateNo;

    private Map<String, Object> params;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTemplateNo() {
        return templateNo;
    }

    public void setTemplateNo(String templateNo) {
        this.templateNo = templateNo;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
