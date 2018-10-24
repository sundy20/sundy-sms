package com.sundy.sms.share.dto;

import java.io.Serializable;

public class SmsResultDTO implements Serializable {

    private static final long serialVersionUID = 5823613884672987382L;

    private Boolean success;

    private String message;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
