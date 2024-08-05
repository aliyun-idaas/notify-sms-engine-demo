package com.idsmanager.idp.notify.plugin.sms.demo;

import com.alibaba.fastjson.JSON;
import com.idsmanager.idp.notify.commons.NotifyErrorCode;
import com.idsmanager.idp.notify.commons.infrastructure.ApiResult;
import com.idsmanager.idp.notify.commons.infrastructure.dto.msg.RContentBasedMsgSendReq;
import com.idsmanager.idp.notify.commons.infrastructure.dto.msg.TemplateBasedMsgSendReq;
import com.idsmanager.idp.notify.logger.NotifyPluginLogger;
import com.idsmanager.idp.notify.sms.entity.SmsMsgTemplate;
import com.idsmanager.idp.notify.sms.infrastructure.SmsMsgSender;
import com.idsmanager.micro.commons.web.filter.RIDHolder;
import org.apache.commons.lang.StringUtils;

import static org.ietf.jgss.GSSException.FAILURE;

/**
 * 2024/5/17
 *
 * @author lvz
 */
public class SmsDemoSender extends SmsMsgSender {

    private String apiUsername;

    private String apiPassword;

    private String smsUrl;

    public ApiResult sendMessage(String message, String... phoneNumbers) {
        if (StringUtils.isEmpty(message) || phoneNumbers == null) {
            NotifyPluginLogger.warn("Param checking failed, message: {}, phoneNumbers: {}", message, phoneNumbers);
            return ApiResultHelper.success(NotifyErrorCode.UNKNOWN_ERROR, "message is empty or phoneNumber is null");
        }

        //  短信发送实现逻辑，方法名可以自己定义，这里以sendPost为例
        String result = sendPost(smsUrl, phoneNumbers);
        //解析 返回结果
        SmsResponse sendReq = JSON.parseObject(result, SmsResponse.class);
        if (!sendReq.isSuccess()) {
            NotifyPluginLogger.debug("[{}] Send mas fail response  response text [{}]", RIDHolder.id(), sendReq);
            return ApiResultHelper.error(FAILURE, sendReq);

        }
        NotifyPluginLogger.debug("短信发送成功！！！message: {}, phoneNumbers: {}", message, phoneNumbers);
        return ApiResultHelper.success(NotifyErrorCode.SUCCESS, "发送短信成功");
    }

    private String sendPost(String smsUrl, String[] phoneNumbers) {
        return "{\"msg\":\"发送短信失败,密码错误！！！\",\"code\":\"InvalidUsrOrPwd\",\"success\":false}";
        //具体调用发送短信逻辑，请自行实现
    }

    /**
     * 根据模板发送消息
     *
     * @param req
     * @param content
     * @param template
     * @return ApiResult
     */
    @Override
    public ApiResult sendMsgByTemplate(TemplateBasedMsgSendReq req, String content, SmsMsgTemplate template) {
        return sendMessage(content, req.getReceiver().getRecipients());
    }

    /**
     * 直接发送原生消息
     *
     * @param req example: {"subject":"","content":"尊敬的用户wceshi，您本次的验证码是：133554，
     *            2分钟内有效。如非本人操作，请忽略本短信并联系管理员。","mimeType":"text","receiver":{"userUniqueId":"86-18888888888","userDisplayName":"86-18888888888",
     *            "recipients":["86-18888888888"],"params":{"code":"133554","minutes":2,"username":"wceshi"}},"options":{"instanceId":"wceshi",
     *            "businessId":"general_sms_code","externalId":"general_sms_code"},"defaultTenantId":"0000000000000000","systemId":"0000000000000000",
     *            "tenantId":"c43bd59e4a03351d527cda65023dbca7wpnnBYyqcfd"}
     * @return
     */
    @Override
    public ApiResult sendMsgByRContent(RContentBasedMsgSendReq req) {
        return sendMessage(req.getContent(), req.getReceiver().getRecipients());
    }

    public String getApiUsername() {
        return apiUsername;
    }

    public SmsDemoSender setApiUsername(String apiUsername) {
        this.apiUsername = apiUsername;
        return this;
    }

    public String getApiPassword() {
        return apiPassword;
    }

    public SmsDemoSender setApiPassword(String apiPassword) {
        this.apiPassword = apiPassword;
        return this;
    }

    public String getSmsUrl() {
        return smsUrl;
    }

    public SmsDemoSender setSmsUrl(String smsUrl) {
        this.smsUrl = smsUrl;
        return this;
    }
}
