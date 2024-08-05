/*
 * Copyright (c) 2016 BeiJing JZYT Technology Co. Ltd
 * www.idsmanager.com
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * BeiJing JZYT Technology Co. Ltd ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with BeiJing JZYT Technology Co. Ltd.
 */
package com.idsmanager.idp.notify.plugin.sms.demo;

import com.idsmanager.micro.commons.utils.httpclient.HttpResponseHandler;
import com.idsmanager.micro.commons.utils.httpclient.IDSHttpResponse;

public class SmsResponse implements HttpResponseHandler<String> {
    /**响应状态码*/
    private String code;

    private String msg;
    /**是否成功 true成功 false失败*/
    private boolean success;

    public String getCode() {
        return code;
    }

    public SmsResponse setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public SmsResponse setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public SmsResponse setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    @Override
    public String handleResponse(IDSHttpResponse response) {
        return null;
    }
}