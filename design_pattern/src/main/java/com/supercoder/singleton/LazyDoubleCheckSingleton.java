package com.supercoder.singleton;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * 懒汉-双重检查锁
 */
public class LazyDoubleCheckSingleton implements Serializable {
    private static LazyDoubleCheckSingleton instance = null;

    private LazyDoubleCheckSingleton() {
        if (instance != null) {
            throw new RuntimeException("请使用getInstance()获取LazyDoubleCheckSingleton类实例");
        } else {
            instance = this;
        }
    }

    /**
     * 这里使用同步块的方式来避免并发安全问题。与LazySingleton中的同步方法相比，锁的粒度变小了，资源访问效率会更高。
     * 家庭作业：双重检查锁如何保证线程安全的？
     * @return
     */
    public static LazyDoubleCheckSingleton getInstance() {
        if (null == instance) {
            synchronized (LazyDoubleCheckSingleton.class) {
                if (null == instance) {
                    synchronized (LazyDoubleCheckSingleton.class) {
                        if (instance == null) {
                            instance = new LazyDoubleCheckSingleton();
                        }
                    }
                }
            }
        }
        return instance;
    }

    private Object readResolve() throws ObjectStreamException {
        return instance;
    }
}
