package com.plum.jpush;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by G_dragon on 2015/8/27.
 */
public class JPushMessageQueueImpl {

    private static final Logger logger = LoggerFactory.getLogger(JPushMessageQueueImpl.class);

    private JPushClient jPushClient;

    public JPushClient getjPushClient() {
        return jPushClient;
    }

    public void setjPushClient(JPushClient jPushClient) {
        this.jPushClient = jPushClient;
    }

    //@Override
    public boolean notifyMessage(String message, String... topic) {
        if(null == jPushClient)
            return false;

        String result = jPushClient.notifyMessage(message, topic);
        logger.debug(result);
        return true;
    }
}
