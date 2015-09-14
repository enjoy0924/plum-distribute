package com.plum.jpush;

import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

import com.plum.core.common.AbstractLifeBean;
//import org.apache.activemq.store.kahadb.disk.util.DiskBenchmark;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;

/**
 *
 * Created by G_dragon on 2015/8/27.
 */
public class JPushClient extends AbstractLifeBean {

    private static final Logger logger = LoggerFactory.getLogger(JPushClient.class);

    private String password;
    private String key;
    private int maxRetryTimes;

    protected cn.jpush.api.JPushClient jpushClient;

    /**
     * 内部调用通知函数
     *
     * @param payload
     * @return
     */
    private String notify(PushPayload payload) {
        try {
            PushResult result = jpushClient.sendPush(payload);
            if (result != null) {
                //DiskBenchmark.Report(String.valueOf(result.msg_id));
                return result.toString();
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return null;
    }

    public String notifyMessage(String msg, String... topic){

        PushPayload payload = new PushPayload.Builder()
                .setPlatform(Platform.all())
                .setAudience(Audience.tag(topic))
                .setNotification(Notification.alert(msg))
                .build();

        return notify(payload);
    }

    @Override
    protected void onBootstrap(ApplicationEvent event) {

        try {
            jpushClient = new cn.jpush.api.JPushClient(password, key, maxRetryTimes);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    protected void onShutdown(ApplicationEvent event) {

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getMaxRetryTimes() {
        return maxRetryTimes;
    }

    public void setMaxRetryTimes(int maxRetryTimes) {
        this.maxRetryTimes = maxRetryTimes;
    }
}
