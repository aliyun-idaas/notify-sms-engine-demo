package com.idsmanager.idp.notify.sms.demo;

import com.idsmanager.idp.notify.commons.NotifyErrorCode;
import com.idsmanager.idp.notify.commons.infrastructure.ApiResult;
import com.idsmanager.idp.notify.commons.infrastructure.dto.msg.RContentBasedMsgSendReq;
import com.idsmanager.idp.notify.commons.infrastructure.dto.msg.TemplateBasedMsgSendReq;
import com.idsmanager.idp.notify.sms.domain.SmsMsgTemplate;
import com.idsmanager.idp.notify.sms.infrastructure.SmsMsgSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 2021/3/8
 *
 * @author lanyu
 */
public class SMSSender extends SmsMsgSender {

    private static final Logger LOG = LoggerFactory.getLogger(SMSSender.class);

    private String apiUsername;

    private String apiPassword;

    private String smsUrl;

    public ApiResult sendMessage(String message, String... phoneNumbers) {
//        return ApiResult.SUCCESS;
        return new ApiResult(NotifyErrorCode.SEND_UNKNOWN_ERROR, "发送短信失败");
    }

    /**
     * 根据模板发送消息 (暂时没有地方会用到)
     * @param req
     * @param content
     * @param template
     * @return
     */
    @Override
    public ApiResult sendMsgByTemplate(TemplateBasedMsgSendReq req, String content, SmsMsgTemplate template) {
        return sendMessage(content, req.getReceiver().getRecipients());
    }

    /**
     * 直接发送原生消息
     * @param req example: {"subject":"","content":"尊敬的用户wceshi，您本次的验证码是：133554， 2分钟内有效。如非本人操作，请忽略本短信并联系管理员。","mimeType":"text","receiver":{"userUniqueId":"86-18888888888","userDisplayName":"86-18888888888","recipients":["86-18888888888"],"params":{"code":"133554","minutes":2,"username":"wceshi"}},"options":{"instanceId":"wceshi","businessId":"general_sms_code","externalId":"general_sms_code"},"defaultTenantId":"0000000000000000","systemId":"0000000000000000","tenantId":"c43bd59e4a03351d527cda65023dbca7wpnnBYyqcfd"}
     * @return
     */
    @Override
    public ApiResult sendMsgByRContent(RContentBasedMsgSendReq req) {
        return sendMessage(req.getContent(), req.getReceiver().getRecipients());
    }

    public void setApiUsername(String apiUsername) {
        this.apiUsername = apiUsername;
    }

    public void setApiPassword(String apiPassword) {
        this.apiPassword = apiPassword;
    }

    public void setSmsUrl(String smsUrl) {
        this.smsUrl = smsUrl;
    }
}
