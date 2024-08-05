package com.idsmanager.idp.notify.plugin.sms.demo;

import com.idsmanager.idp.notify.sms.infrastructure.SmsMsgEngineMetadata;
import org.junit.jupiter.api.Test;

/**
 * 2021/9/13 5:30 PM
 *
 * @author Shengzhao Li
 */
class IdpNotifyPluginTest {

    @Test
    public void test() {
        SmsMsgEngineMetadata metadata = (SmsMsgEngineMetadata)new SmsDemoEngine();
        //要先配置插件，查询插件配置
    }

}