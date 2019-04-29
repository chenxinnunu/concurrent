package com.kykj.demo.singleton;

/**
 * 单例模式
 *
 * @author chenxinnunu@gmail.com
 * @date 2019/4/4 11:38
 */
public class Singleton {
    /**
     * 懒汉式，多线程不安全，一般情况下不建议使用
     */
  /*  private static Singleton instance;
    private Singleton(){}
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }*/
    /**
     * 懒汉式，加了锁，多线程安全，但是效率低，一般情况下不建议使用
     */
/*    private static Singleton instance;

    private Singleton() {
    }

    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }*/
    /**
     * 饿汉式，多线程安全，没有加锁，执行效率高，类加载时就初始化，浪费内存，建议使用这种方式
     */
    private static Singleton instance = new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance() {
        return instance;
    }
    /**
     * 双重锁/双重校验锁模式，采用双锁机制，多线程安全，高性能，如果有特殊要求可以使用这种方式
     */
 /*   private volatile static Singleton singleton;
    private Singleton() {}
    public static Singleton getSingleton() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }*/

    /**
     * 登记式/静态内部类模式，能达到双检锁方式一样的功效，要明确实现lazy loading效果时，才会使用这种方式
     */
/*    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }
    private Singleton() {}
    public static final Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }*/
    /**
     * 枚举，这种方式还没有被广泛采用，但这是实现单例模式的最佳方式，它更简洁，自动支持序列化机制，绝对防止多次实例化，
     * 如果涉及到反序列化创建对象时，可以尝试使用这种方式
     */
   /* public enum Singleton {
        INSTANCE;
        public void whateverMethod() {
        }
    }*/
}
