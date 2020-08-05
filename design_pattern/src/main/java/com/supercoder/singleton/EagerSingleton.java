package com.supercoder.singleton;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * 饿汉式单例模式
 * 1. 在类加载时立即创建实例，饿汉式的单例模式的关键点，JVM类加载机制是线程安全的，不需要考虑并发安全问题。
 * 2. 私有化构造函数并确保反射调用时不生成新对象
 * 3. 对外公开实例获取方法
 * 4. 实现readResolve方法，确保反序列化时不产生新对象（可选）
 */
public class EagerSingleton implements Serializable {

    private static final EagerSingleton instance = new EagerSingleton();

    private EagerSingleton() {
        if (instance != null) {
            throw new RuntimeException("请使用getInstance()获取EagerSingleton类实例");
        }
    }

    public static EagerSingleton getInstance() {
        return instance;
    }

    private Object readResolve() throws ObjectStreamException {
        return instance;
    }
}
