package com.supercoder.singleton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.lang.reflect.Constructor;

class LazySingletonTest {

    /**
     * 测试反射调用情况下是否会创建出不同实例
     *
     * @throws Exception
     */
    @Test
    public void reflectInvokeCons() throws Exception {
        Class clazz = Class.forName(LazySingleton.class.getName());
        Constructor<LazySingleton> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        LazySingleton instance = constructor.newInstance();
        LazySingleton instance1 = LazySingleton.getInstance();
        Assertions.assertEquals(instance, instance1, "通过反射成功调用了构造方法，并获得一个新的实例，不符合预期");
    }

    /**
     * 测试反序列化时是否会出现不同实例
     *
     * @throws Exception
     */
    @Test
    public void deserialize() throws Exception {
        LazySingleton instance = LazySingleton.getInstance();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(instance);
        byte[] bytes = baos.toByteArray();
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
        Object instance1 = ois.readObject();
        Assertions.assertEquals(instance, instance1, "反序列化后产生了一个新的实例，不符合预期");
    }
}