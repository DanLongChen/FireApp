package com.chiron.fireapp.utils;

import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class ThreadPool{
    private static ThreadPoolExecutor pool = new ThreadPoolExecutor(10,20,10,
            TimeUnit.SECONDS, new SynchronousQueue<>(), new MyThreadFactory(), new MyPolicy());
    private static ScheduledExecutorService schedulePoll = Executors.newScheduledThreadPool(10);

    public static void executeTask(Runnable task){
        //可以通过execute或者submit来提交任务，submit可以返回任务执行的结果
        pool.execute(task);
    }

    public static void scheduleExecute(Runnable task, ScheduleType type){
        switch(type){
            case ONCE:
                schedulePoll.schedule(task,10,TimeUnit.SECONDS);
                break;
            case FIXEDRATE:
                schedulePoll.scheduleAtFixedRate(task,0,10,TimeUnit.SECONDS);
                break;
            case FIXEDDELAY:
                schedulePoll.scheduleWithFixedDelay(task,0,10,TimeUnit.SECONDS);
            default:
                break;
        }
    }
}

enum ScheduleType{
    ONCE,
    FIXEDRATE,
    FIXEDDELAY
}

class MyThreadFactory implements ThreadFactory {
    private static final AtomicInteger poolNumber = new AtomicInteger(1);
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final ThreadGroup group;
    private String namePre;

    public MyThreadFactory() {
        SecurityManager s = System.getSecurityManager();
        if(s!=null){
            group = s.getThreadGroup();
        }else{
            group = Thread.currentThread().getThreadGroup();
        }
        //定义前缀
        namePre = "fireapp - pool - "+poolNumber.getAndIncrement()+" - thread -";
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(group, r,namePre+" "+threadNumber.getAndIncrement(),0);
        if(thread.isDaemon()){
            thread.setDaemon(false);
        }
        if(thread.getPriority()!=Thread.NORM_PRIORITY){
            thread.setPriority(Thread.NORM_PRIORITY);
        }
        return thread;
    }
}

class MyPolicy implements RejectedExecutionHandler {
    public MyPolicy() {
    }

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        if(!executor.isShutdown()){
            r.run();
            executor.getQueue().poll();
        }
    }
}
