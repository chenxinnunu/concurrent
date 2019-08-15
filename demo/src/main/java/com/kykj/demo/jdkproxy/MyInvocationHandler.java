package com.kykj.demo.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Date;

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
        before();
        Object invoke = method.invoke(realSubject, args);
        after();
        return invoke;
    }

    // 调用invoke方法之前执行
    private void before() {
        System.out.println(String.format("log start time [%s] ", new Date()));
    }

    // 调用invoke方法之后执行
    private void after() {
        System.out.println(String.format("log end time [%s] ", new Date()));
    }

}
