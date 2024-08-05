package com.idsmanager.idp.notify.plugin.sms.demo;

import com.alibaba.fastjson.JSON;
import com.idsmanager.idp.notify.commons.NotifyErrorCode;
import com.idsmanager.idp.notify.commons.infrastructure.ApiResult;

public class ApiResultHelper {
    public static ApiResult success(String result) {
        return new ApiResult(NotifyErrorCode.SUCCESS, result);
    }

    public static ApiResult error(int errorCode, Object result) {
        return new ApiResult(errorCode, JSON.toJSONString(result));
    }

    public static ApiResult error(int errorCode, Object result, Exception e) {
        return new ApiResult(errorCode, String.format("【%s】:%s", JSON.toJSONString(result), e.getMessage()));
    }

    public static ApiResult success(int success, String msg) {
        return new ApiResult(success, msg);
    }
}
