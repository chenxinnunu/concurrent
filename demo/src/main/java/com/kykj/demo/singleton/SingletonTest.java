package com.kykj.demo.singleton;

/**
 * 使用场景：
 * 1、要求生产唯一序列号。
 * 2、WEB 中的计数器，不用每次刷新都在数据库里加一次，用单例先缓存起来。
 * 3、创建的一个对象需要消耗的资源过多，比如 I/O 与数据库的连接等。
 * @author chenxinnunu@gmail.com
 * @date 2019/4/4 14:15
 */
public class SingletonTest {
    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
    }
}
