package com.chiron.fireapp.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Reflect {
    public static void reflect() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, NoSuchFieldException, InvocationTargetException {
//        Class<? extends AA> fansheClass = (Class<? extends AA>)Class.forName("TestFuXi.Fanshe");
        Class<? extends AA> fanShe = Fanshe.class;
        System.out.println(fanShe.getName());
        Constructor[] constructors = fanShe.getDeclaredConstructors();
        Constructor constructor = fanShe.getConstructor(String.class);
        //可以直接newInstance，调用默认构造，想要调用其他构造可以使用constructor
        Fanshe f = (Fanshe) fanShe.newInstance();
        System.out.println(f.getName());

        //TODO constructor
        constructor.setAccessible(true);
        Fanshe f1 = (Fanshe) constructor.newInstance("haha");
        System.out.println(f1.getName());

        //TODO FIeld
        Field field = fanShe.getDeclaredField("name");
        field.setAccessible(true);
        field.set(f1, "hello world");
        System.out.println(f1.getName());

        //TODO method
        Method method = fanShe.getMethod("setName", String.class);
        method.setAccessible(true);
        method.invoke(f1, "i am method");
        System.out.println(f1.getName());

        //TODO 打印出实现的接口或者继承的类
        for (Class i : fanShe.getInterfaces()) {
            System.out.println(i.getName());
        }

    }
}

interface AA {
}

interface BB {
}

class Fanshe implements AA, BB {
    private String name = "Fanshe";

    public Fanshe() {
    }

    public Fanshe(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private void showName(String name) {
        this.name = name;
        System.out.println(name);
    }
}
