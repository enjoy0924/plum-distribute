package com.plum.cas.scheduler;

import org.apache.shiro.session.mgt.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.*;

/**
 * 用来做周期的session清理和状态
 */

public class PersistSessionValidationScheduler implements SessionValidationScheduler, Runnable {

    /** Private internal log instance. */
    private static final Logger log = LoggerFactory.getLogger(PersistSessionValidationScheduler.class);

    /** 会话过期验证管理器，这里可以在spring配置文件里面配置，目前采用的是默认的 */
    ValidatingSessionManager sessionManager;

    /** 周期调用线程 */
    private ScheduledExecutorService service;

    /** 默认Session清理间隔*/
    private long interval = DefaultSessionManager.DEFAULT_SESSION_VALIDATION_INTERVAL;

    /** 当前是否启用 */
    private boolean enabled = false;

    public PersistSessionValidationScheduler() {
        super();
    }

    public ValidatingSessionManager getSessionManager() {
        return sessionManager;
    }

    public void setSessionManager(ValidatingSessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    /**
     * Creates a single thread {@link ScheduledExecutorService} to smsvalidate sessions at fixed intervals
     * and enables this scheduler. The executor is created as a daemon thread to allow JVM to shut down
     */
    //TODO Implement an integration test to test for jvm exit as part of the standalone example
    // (so we don't have to change the unit test execution model for the core module)
    public void enableSessionValidation() {
        if (this.interval > 0l) {
            this.service = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
                public Thread newThread(Runnable r) {
                    Thread thread = new Thread(r);
                    thread.setDaemon(true);
                    return thread;
                }
            });
            this.service.scheduleAtFixedRate(this, interval, interval, TimeUnit.MILLISECONDS);
            this.enabled = true;
        }
    }

    public void run() {
        /**
         * 这里暂时这样写到时候会进行更改的
         */
        //sessionManager.validateSessions();
        log.debug("Unimplemented session smsvalidate service");
    }

    public void disableSessionValidation() {
        this.service.shutdownNow();
        this.enabled = false;
    }
}
