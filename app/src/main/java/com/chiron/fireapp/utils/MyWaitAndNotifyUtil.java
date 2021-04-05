package com.chiron.fireapp.utils;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyWaitAndNotifyUtil {
    public void ReaderAndWriter() throws InterruptedException {
        MyCondition condition = new MyCondition();
        Reader reader = new Reader.Builder().condition(condition).build();
        Writer writer = new Writer.Builder().condition(condition).build();

        Thread add = new Thread(writer, "add");
        Thread get = new Thread(reader, "get");
        get.start();
        add.start();
        add.join();
        get.join();
    }

    //TODO 另外的方式，synchronized+lock实现
    public void SynchronizedLock() throws InterruptedException {
        boolean flag = true;
        Lock lock = new ReentrantLock(false);
        Thread wait = new Thread(new Wait(flag, lock), "wait Thread");
        Thread notify = new Thread(new Notify(flag, lock), "notify Thread");
        wait.start();
        TimeUnit.SECONDS.sleep(2);
        notify.start();
    }
}

class Reader implements Runnable {
    private MyCondition condition;

    private Reader(Builder builder) {
        this.condition = builder.condition;
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println(condition.get());
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class Builder {
        private MyCondition condition;

        public Builder() {
        }

        public Builder condition(MyCondition val) {
            condition = val;
            return this;
        }

        public Reader build() {
            return new Reader(this);
        }
    }
}

class Writer implements Runnable {
    private MyCondition condition;

    private Writer(Builder builder) {
        this.condition = builder.condition;
    }

    @Override
    public void run() {

        try {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
                TimeUnit.SECONDS.sleep(5);
                condition.add(System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class Builder {
        private MyCondition condition;

        public Builder() {
        }

        public Builder condition(MyCondition val) {
            condition = val;
            return this;
        }

        public Writer build() {
            return new Writer(this);
        }
    }
}

class MyCondition {
    private Lock lock = new ReentrantLock(false);
    private Condition full = lock.newCondition();
    private Condition empty = lock.newCondition();
    private static long[] queue = new long[10];
    private volatile int count = 0, readIndex = 0, writeIndex = 0;

    public void add(long i) {
        lock.lock();
        try {
            while (count == queue.length) {
                full.await();
            }
            queue[writeIndex] = i;
            if (writeIndex + 1 == queue.length) {
                writeIndex = 0;
            } else {
                ++writeIndex;
            }
            ++count;
            empty.signal();//signal之后在finally中释放锁资源，从empty同步队列中的线程才能获取到锁
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public long get() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                empty.await();
            }
            long a = queue[readIndex];
            if (readIndex + 1 == queue.length) {
                readIndex = 0;
            } else {
                ++readIndex;
            }
            --count;
            full.signal();
            return a;
        } finally {
            lock.unlock();
        }
    }
}

class Wait implements Runnable {
    public static boolean flag;
    private Lock lock;

    public Wait(boolean flag, Lock lock) {
        this.flag = flag;
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            System.out.println("wait flag:" + flag);
            while (flag) {
                try {
                    System.out.println("still waiting");
                    lock.wait();// 从方法返回，执行下一步
                    System.out.println("do the next");
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            System.out.println("notified! doSomething else");
        }
    }
}

class Notify implements Runnable {
    private boolean flag;
    private Lock lock;

    public Notify(boolean flag, Lock lock) {
        super();
        this.flag = flag;
        this.lock = lock;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        synchronized (lock) {
            lock.notify();// 通知不会释放lock锁，只有当调用notify的线程释放lock锁，另外的线程才能从wait方法返回
            // notify方法只是将线程从等待队列移入同步队列，从wait状态返回的前提是获取调用对象的锁
            Wait.flag = false;
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        synchronized (lock) {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("keep the lock don not let the wait() return");// 继续持有锁，阻止线程从wait方法返回
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}



