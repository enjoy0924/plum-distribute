
package com.plum.core.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class DynamicallySizedThreadPoolExecutor extends ThreadPoolExecutor
{
    private static Log logger = LogFactory.getLog(DynamicallySizedThreadPoolExecutor.class);
    
    private final ReentrantLock lock = new ReentrantLock();
    private int realCorePoolSize;
    
    public DynamicallySizedThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
            BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler)
    {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
        this.realCorePoolSize = corePoolSize;
    }

    public DynamicallySizedThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
            BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler)
    {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        this.realCorePoolSize = corePoolSize;
    }

    public DynamicallySizedThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
            BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory)
    {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
        this.realCorePoolSize = corePoolSize;
    }

    public DynamicallySizedThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
            BlockingQueue<Runnable> workQueue)
    {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        this.realCorePoolSize = corePoolSize;
    }
    
    @Override
    public void setCorePoolSize(int corePoolSize)
    {
        this.realCorePoolSize = corePoolSize;
        super.setCorePoolSize(corePoolSize);
    }

    @Override
    public void execute(Runnable command)
    {
        // Do we want to add another thread?
        int threadCount = getPoolSize();
        if(logger.isDebugEnabled())
        {
            logger.debug("Current pool size is " + threadCount + ", real core=" + realCorePoolSize +  
                    ", current core=" + getCorePoolSize() + ", max=" + getMaximumPoolSize());
        }
        
        if(threadCount < getMaximumPoolSize())
        {
            // We're not yet at the full thread count
            
            // Does the queue size warrant adding one?
            // (If there are more than the maximum pool size of jobs pending,
            //  it's time to add another thread)
            int queueSize = getQueue().size() + 1;// New job not yet added
            if(queueSize >= getMaximumPoolSize())
            {
                lock.lock();
                int currentCoreSize = getCorePoolSize();
                if(currentCoreSize < getMaximumPoolSize()) 
                {
                    super.setCorePoolSize(currentCoreSize+1);
                    
                    if(logger.isInfoEnabled())
                    {
                        logger.info("Increased pool size to " + getCorePoolSize() + " from " +
                                currentCoreSize + " due to queue size of " + queueSize);
                    }
                }
                lock.unlock();
            }
        }
        
        // Now run the actual work
        super.execute(command);
    }
     
    @Override
    protected void afterExecute(Runnable r, Throwable t)
    {
        // If the queue is looking empty, allow the pool to
        //  get rid of idle threads when it wants to
        int threadCount = getPoolSize();
        if(threadCount == getMaximumPoolSize() && threadCount > realCorePoolSize) 
        {
            int queueSize = getQueue().size();
            int currentCoreSize = getCorePoolSize();
            if(queueSize < 2 && currentCoreSize > realCorePoolSize)
            {
                // Almost out of work, allow the pool to reduce threads when
                //  required. Double checks the sizing inside a lock to avoid
                //  race conditions taking us below the real core size.
                lock.lock();
                currentCoreSize = getCorePoolSize();
                if(currentCoreSize > realCorePoolSize) 
                {
                    super.setCorePoolSize(currentCoreSize-1);
                    
                    if(logger.isInfoEnabled())
                    {
                        logger.info("Decreased pool size to " + getCorePoolSize() + " from " +
                                currentCoreSize + " (real core size is " + realCorePoolSize + 
                                ") due to queue size of " + queueSize);
                    }
                }
                lock.unlock();
            }
        }
    }
}
