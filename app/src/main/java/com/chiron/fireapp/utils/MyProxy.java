package com.chiron.fireapp.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyProxy {
    public static void proxy(){
        Person student = new Student("aaa");
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(student);
//        Class<?> proxyClass = Proxy.getProxyClass(Person.class.getClassLoader(),new Class<?>[]{Person.class});
//        Constructor<?> constructor = proxyClass.getConstructor(InvocationHandler.class);
//        Person proxy = (Person)constructor.newInstance(myInvocationHandler);
        //TODO 三步可以简化为一步
        Person proxy = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(),new Class<?>[]{Person.class},myInvocationHandler);
        int money = proxy.giveMoney();
        System.out.println(money);
    }
}
interface Person{
    int giveMoney();
}

class Student implements Person{
    String name;

    public Student(String name) {
        this.name = name;
    }

    @Override
    public int giveMoney() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name+"交钱50");
        return 50;
    }
}

class MonitorUtil{
    private static ThreadLocal<Long> time = new ThreadLocal<>();

    public static void start(){
        time.set(System.currentTimeMillis());
    }

    public static void finish(){
        System.out.println("方法耗时："+(System.currentTimeMillis()-time.get())+" ms");
    }
}

class MyInvocationHandler<T> implements InvocationHandler {
    T target;

    public MyInvocationHandler(T target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理类执行："+method.getName());
        MonitorUtil.start();
        Object result = method.invoke(target,args);
        MonitorUtil.finish();
        //想要有返回值的话可以进行return，否则return null即可
        return result;
    }
}

