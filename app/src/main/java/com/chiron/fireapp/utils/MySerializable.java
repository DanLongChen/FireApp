package com.chiron.fireapp.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class MySerializable {
    public void setSerializable() throws IOException, ClassNotFoundException {
        Worm worm = new Worm(1);
        //TODO 以硬盘作为介质
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("F:\\worm.txt"));
        outputStream.writeObject("String\n");
        outputStream.writeObject(worm);
        outputStream.flush();
        outputStream.close();

        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("F:\\worm.txt"));
        String temp = (String)inputStream.readObject();
        Worm tempWorm = (Worm)inputStream.readObject();
        System.out.println(temp);
        System.out.println(tempWorm);

        //TODO 以内存作为介质
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream outputStream1 =new ObjectOutputStream(byteArrayOutputStream);
        outputStream1.writeObject(worm);
        outputStream1.flush();
        ObjectInputStream inputStream1 = new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
        Worm tempWorm1= (Worm) inputStream1.readObject();
        System.out.println(tempWorm1);
    }
}

class Data implements Externalizable {
    private int number;
    private transient String name;//对属性关闭序列化

    public Data(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public Data() {
        super();
    }

    @Override
    public String toString() {
        return super.toString()+" "+number+" "+name;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(number);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        number = in.readInt();
    }
}

class Worm implements Serializable {
    private int i = 0;
    private Data[] data={new Data(0,"Data1"),new Data(1,"Data2")};
    private static final Long serialVersionUID = 123456L;

    public Worm(int i) {
        this.i = i;
    }

    @Override
    public String toString() {
        return super.toString()+" : "+i+" "+data[0].toString();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(data);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        data=(Data[])objectInputStream.readObject();
    }
}

