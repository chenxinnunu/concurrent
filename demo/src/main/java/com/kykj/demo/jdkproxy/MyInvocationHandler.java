package com.kykj.demo.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 定义一个处理器
 *
 * @author chenxinnunu@gmail.com
 * @date 2019/4/4 17:05
 */
public class MyInvocationHandler implements InvocationHandler {
    /**
     * 因为要处理真实角色，所以要把真实角色传进来
     */
    Subject realSubject;

    public MyInvocationHandler(Subject realSubject) {
        this.realSubject = realSubject;
    }

    /**
     * @param proxy  代理类
     * @param method 正在调用的方法
     * @param args   方法的参数
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("调用代理类");
        if (method.getName().equals("sellBook")) {
            int invoke = (int) method.invoke(realSubject, args);
            System.out.println("调用的是卖书的方法");
            return invoke;
        } else {
            String invoke = (String) method.invoke(realSubject, args);
            System.out.println("调用的是说话的方法");
            return invoke;
        }
    }
}
