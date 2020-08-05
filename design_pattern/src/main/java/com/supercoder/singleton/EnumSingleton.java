package com.supercoder.singleton;

/**
 * 枚举-单例模式，枚举元素本身就是单例的，由JVM保证。无需处理反射和反序列情况下的漏洞。虽然没有延迟加载的特性，但不失为一种简洁高效的方法。
 */
public enum EnumSingleton {
    INSTANCE;

    public void singletonOperation() {
        //TODO something
        System.out.println("执行操作...");
    }
}
