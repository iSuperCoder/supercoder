package com.supercoder.singleton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

class StaticInnerClassSingletonTest {
    @Test
    public void reflectInvokeCons() throws Exception {
        Assertions.assertThrows(InvocationTargetException.class, () -> {
            Class clazz = Class.forName(StaticInnerClassSingleton.class.getName());
            Constructor<StaticInnerClassSingleton> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            StaticInnerClassSingleton instance = constructor.newInstance();
        }, "通过反射成功调用了构造方法，不符合预期");

    }

}