package com.kykj.demo.jdkproxy;

import java.lang.reflect.Proxy;

/**
 * 测试类
 *
 * @author chenxinnunu@gmail.com
 * @date 2019/4/4 17:13
 */
public class Main {
    public static void main(String[] args) {
        //真实对象
        Subject realSubject = new RealSubject();
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(realSubject);
        //代理对象
        Subject proxyClass = (Subject) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{Subject.class}, myInvocationHandler);
        proxyClass.sellBook();
        proxyClass.speak();

    }
}
