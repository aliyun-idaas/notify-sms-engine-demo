package com.idsmanager.idp.notify.plugin.sms.demo;

import com.idsmanager.idp.notify.infrastructure.InvalidConfigException;
import com.idsmanager.idp.notify.logger.NotifyPluginLogger;
import com.idsmanager.idp.notify.sms.infrastructure.SMS;
import com.idsmanager.idp.notify.sms.infrastructure.SmsMsgEngineMetadata;
import com.idsmanager.idp.notify.sms.infrastructure.SmsMsgSender;
import com.idsmanager.idp.notify.util.JsonFiledSecurityUtils;
import com.idsmanager.micro.commons.web.filter.RIDHolder;
import net.sf.json.JSONObject;
import org.apache.commons.io.IOUtils;

import java.io.IOException;

/**
 * 2024/5/17
 *
 * @author user
 * @since 1.0
 */
@SMS
public class SmsDemoEngine extends SmsMsgEngineMetadata {
    /**
     * 网关类型 唯一标识
     */
    public static final String MINOR_TYPE = "SMS_DEMO";
    /**
     * 需要加密的字段，定义在下面ENCRYPT_FIELDS数组的字段，会进行加密处理
     */
    private static final String[] ENCRYPT_FIELDS = {"apiPassword"};
    /**
     * 需要校验非空字段，定义在下面KEYS数组的字段如果为空，则抛出异常
     */
    private static final String[] KEYS = new String[] {"apiUsername", "apiPassword", "smsUrl"};


    /**
     * 插件图标 byte[] 数据
     */
    private byte[] logoAsBytes;

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

    /**
     * 需要加密的字段数组
     */
    @Override
    public String[] encryptFields() {
        return ENCRYPT_FIELDS;
    }

    @Override
    public SmsMsgSender build(String json) throws InvalidConfigException {
        //解析和验证参数
        JSONObject config = doValidate(json);
        //解密加密的参数
        JsonFiledSecurityUtils.decryptField(config, encryptFields());
        //构建发送器,这里需要组装自己的参数
        SmsDemoSender sender = new SmsDemoSender();
        sender.setApiUsername(config.optString("apiUsername"))
                .setApiPassword(config.optString("apiPassword"))
                .setSmsUrl(config.getString("smsUrl"));
        return sender;
    }

    private JSONObject doValidate(String json) throws InvalidConfigException {
        JSONObject config = null;
        try {
            config = JSONObject.fromObject(json);
        } catch (Throwable e) {
            throw new InvalidConfigException("解析配置失败，无效JSON字符串");
        }
        if (KEYS!=null && KEYS.length>0){
            for (String key : KEYS) {
                ensureParamNotNull(key, config.optString(key));
            }
        }

        return config;
    }
    /**
     * 每个短信插件的logo图片文件，
     * 图片要求尺寸 200x200, 大小不超过50KB/, png 或 jpg
     * 图片文件名与插件ID一致，放在 resources 目录中
     *
     * @return logo
     * @throws IOException IOException
     */
    @Override
    public byte[] logoAsBytes()  {
        if (this.logoAsBytes == null) {
            try {
                this.logoAsBytes = IOUtils.toByteArray(this.getClass().getClassLoader().getResourceAsStream("SMS_DEMO.png"));
            } catch (IOException e) {
                NotifyPluginLogger.debug("[{}]-Initialed SmsDemoEngine logo from: SMS_DEMO.png", RIDHolder.id());
                return new byte[0];
            }
        }
        return this.logoAsBytes;
    }
}
