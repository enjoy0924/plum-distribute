package com.plum.core.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class TraceableThreadFactory implements ThreadFactory
{
    private static final AtomicInteger factoryNumber = new AtomicInteger(1);
    private static List<ThreadGroup> activeThreadGroups = Collections.synchronizedList(new ArrayList<ThreadGroup>(1));
    
    /**
     * Get a list of thread groups registered by the factory.
     * 
     * @return      Returns a snapshot of thread groups
     */
    public static List<ThreadGroup> getActiveThreadGroups()
    {
        return activeThreadGroups;
    }
    
    private final ThreadGroup group;
    private String namePrefix;
    private final AtomicInteger threadNumber;
    private boolean threadDaemon;
    private int threadPriority;
    

    public TraceableThreadFactory()
    {
        this.group = new ThreadGroup("TraceableThreadGroup-" + factoryNumber.getAndIncrement());
        TraceableThreadFactory.activeThreadGroups.add(this.group);
        
        this.namePrefix = "TraceableThread-" + factoryNumber.getAndIncrement() + "-thread-";
        this.threadNumber = new AtomicInteger(1);
        
        this.threadDaemon = true;
        this.threadPriority = Thread.NORM_PRIORITY;
    }

    /**
     * @param daemon            <tt>true</tt> if all threads created must be daemon threads
     */
    public void setThreadDaemon(boolean daemon)
    {
        this.threadDaemon = daemon;
    }

    /**
     * 
     * @param threadPriority    the threads priority from 1 (lowest) to 10 (highest)
     */
    public void setThreadPriority(int threadPriority)
    {
        this.threadPriority = threadPriority;
    }

    public Thread newThread(Runnable r)
    {
        Thread thread = new Thread(
                group,
                r,
                namePrefix + threadNumber.getAndIncrement(),
                0);
        thread.setDaemon(threadDaemon);
        thread.setPriority(threadPriority);
        
        return thread;
    }
    
    public void setNamePrefix(String namePrefix)
    {
    	this.namePrefix = namePrefix;
    }
    
    public String getNamePrefix()
    {
    	return this.namePrefix;
    }
    
}
