package com.plum.core.utils.transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * 数据库事物提交前后拦截器工具类
 *
 * Created by Andy on 2015/9/8.
 */
public class TransactionSynchronizerUtils {
    private final static Logger logger = LoggerFactory.getLogger(TransactionSynchronizerUtils.class);

    public static void afterCommit(final Runnable runnable) {
        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
                @Override
                public void afterCommit() {
                    runnable.run();
                }
            });
        } else {
            //directly call
            runnable.run();
        }
    }

    /**
     * after commit or rollback
     */
    public static void afterCompletion(final Runnable runnable) {
        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
                @Override
                public void afterCompletion(int status) {

                    if(TransactionSynchronization.STATUS_COMMITTED == status){
                        logger.debug("This transaction has committed");
                    }else if(TransactionSynchronization.STATUS_ROLLED_BACK == status){
                        logger.debug("This transaction has rolled back");
                    }else if(TransactionSynchronization.STATUS_UNKNOWN == status){
                        logger.debug("This transaction has an unknown status");
                    }else{
                        logger.debug("unknown status: " + status);
                    }

                    runnable.run();
                }
            });
        } else {
            //directly call
            runnable.run();
        }
    }

//    @SuppressWarnings("unchecked")
//    public static <T> Future<T> afterCompletion(final Callable<T> callable) {
//        final TransactionFuture<T> future = new TransactionFuture<>();
//        if (TransactionSynchronizationManager.isActualTransactionActive()) {
//            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
//                @Override
//                public void afterCompletion(int status) {
//                    try {
//                        future.put(callable.call());
//                    } catch (Exception e) {
//                        logger.error("error calling callable.", e);
//                        future.cancel(true);
//                    }
//                }
//            });
//        } else {
//            //directly call
//            try {
//                future.put(callable.call());
//            } catch (Exception e) {
//                logger.error("error calling callable.", e);
//                future.cancel(true);
//            }
//        }
//        return future;
//    }
}