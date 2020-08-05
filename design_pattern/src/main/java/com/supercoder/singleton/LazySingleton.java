package com.supercoder.singleton;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * 懒汉式单例模式
 * 1. 在调用getInstance()时才创建实例是懒汉式单例模式的关键点，也是必须考虑并发安全问题的原因。
 * 2. 私有化构造函数并确保反射调用时不生成新对象
 * 3. 对外公开实例获取方法
 * 4. 实现readResolve方法，确保反序列化时不产生新对象（可选）
 */
public class LazySingleton implements Serializable {
    private static LazySingleton instance;

    private LazySingleton() {
        if (instance != null) {
            throw new RuntimeException("请使用getInstance()获取LazySingleton类实例");
        } else {
            instance = this;
        }
    }

    public static synchronized LazySingleton getInstance() {
        if (null == instance) {
            instance = new LazySingleton();
        }
        return instance;
    }

    private Object readResolve() throws ObjectStreamException {
        return instance;
    }
}
