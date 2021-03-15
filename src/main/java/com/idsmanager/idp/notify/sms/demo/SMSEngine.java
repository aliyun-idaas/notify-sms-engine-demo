package com.idsmanager.idp.notify.sms.demo;

import com.idsmanager.idp.notify.infrastructure.InvalidConfigException;
import com.idsmanager.idp.notify.sms.infrastructure.SMS;
import com.idsmanager.idp.notify.sms.infrastructure.SmsMsgEngineMetadata;
import com.idsmanager.idp.notify.sms.infrastructure.SmsMsgSender;
import net.sf.json.JSONObject;

/**
 * 2021/3/8
 *
 * @author lanyu
 */
@SMS
public class SMSEngine extends SmsMsgEngineMetadata {

    public static final String MINOR_TYPE = "SMS_DEMO";

    @Override
    public String name() {
        return "DEMO短信网关";
    }

    @Override
    public final String minorType() {
        return MINOR_TYPE;
    }

    @Override
    public void validate(String json) throws InvalidConfigException {
        doValidate(json);
    }

    @Override
    public SmsMsgSender build(String json) throws InvalidConfigException {
        JSONObject config = doValidate(json);
        SMSSender sender = new SMSSender();
        sender.setApiUsername(config.optString("apiUsername"));
        sender.setApiPassword(config.optString("apiPassword"));
        sender.setSmsUrl(config.getString("smsUrl"));
        return sender;
    }

    private JSONObject doValidate(String json) throws InvalidConfigException {
        JSONObject config = null;
        try {
            config = JSONObject.fromObject(json);
        } catch (Throwable e) {
            throw new InvalidConfigException("解析配置失败，无效JSON字符串");
        }
        String[] keys = new String[]{"apiUsername", "apiPassword", "smsUrl"};
        for (String key : keys) {
            ensureParamNotNull(key, config.optString(key));
        }
        return config;
    }

}
