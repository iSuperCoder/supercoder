package com.supercoder.singleton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

class EagerSingletonTest {
    /**
     * 测试反射调用
     *
     * @throws Exception
     */
    @Test
    public void reflectInvokeCons() throws Exception {
        Assertions.assertThrows(InvocationTargetException.class, () -> {
            Class clazz = Class.forName(EagerSingleton.class.getName());
            Constructor<EagerSingleton> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            EagerSingleton instance = constructor.newInstance();
        }, "通过反射成功调用了构造方法，不符合预期");
    }

    /**
     * 测试反序列化时是否会出现不同实例
     *
     * @throws Exception
     */
    @Test
    public void deserialize() throws Exception {
        EagerSingleton instance = EagerSingleton.getInstance();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(instance);
        byte[] bytes = baos.toByteArray();
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
        Object instance1 = ois.readObject();
        Assertions.assertEquals(instance, instance1, "反序列化后产生了一个新的实例，不符合预期");
    }
}