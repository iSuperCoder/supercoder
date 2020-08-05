package com.supercoder.singleton;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * 使用静态内部类实现单例模式
 * 1.只有在调用getInstance()时，JVM才会加载InstanceHolder这个静态内部类，JVM加载类的过程是线程安全的。
 * 2.当InstanceHolder被JVM加载时，会实例化instance，且final关键字使instance不可被重新赋值。
 * 3.并发安全且具有延迟加载特性。
 */
public class StaticInnerClassSingleton implements Serializable {
    /**
     * 静态内部类的static属性持有对象实例
     */
    private static class InstanceHolder {
        private static final StaticInnerClassSingleton instance = new StaticInnerClassSingleton();
    }

    /**
     * 使用反射调用构造方法时，会触发InstanceHolder类加载，并在类加载之后再一次调用构造方法，外层的反射调用会执行到抛出异常的逻辑。
     * 在这个过程中只有一个实例产生。
     */
    private StaticInnerClassSingleton() {
        if (InstanceHolder.instance != null) {
            throw new RuntimeException("请使用getInstance()获取StaticInnerClassSingleton类实例");
        }
    }

    public static StaticInnerClassSingleton getInstance() {
        return InstanceHolder.instance;
    }

    private Object readResolve() throws ObjectStreamException {
        return InstanceHolder.instance;
    }
}
