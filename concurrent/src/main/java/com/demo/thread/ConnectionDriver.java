package com.demo.thread;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.concurrent.TimeUnit;

/**
 * @author chenxin
 * @date 2019/08/12
 * java.sql.connection只是一个接口，最终的实现是由数据库驱动提供方来实现的，这里仅仅是一个示例，所以通过动态代理构造一个Connection
 */
public class ConnectionDriver {
    static class ConnectionHandler implements InvocationHandler{

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equals("commit")) {
                TimeUnit.MILLISECONDS.sleep(100);
            }
            return null;
        }
    }
    //创建一个Connection的代理，在commit是休眠100毫秒
  /*  public static final Connection creatConnection() {
        return (Connection) Proxy.newProxyInstance(ConnectionDriver.class.getClassLoader(),
                new Class<>[] {Connection.class}, new ConnectionHandler());
    }*/

}
