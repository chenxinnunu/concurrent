package com.demo.thread.simpleconnectionpool;

import java.sql.Connection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author chenxin
 * @date 2019/08/12
 * 使用等待超时机制构造的简单的数据库连接池示例，模拟从连接池中获取、使用和释放连接的过程，
 * 客户端获取连接的过程设定为等待超时模式，也就是在1000毫秒内如果无妨获得可用连接，将会给客户端返回一个null
 */
public class ConnectionPool {
    //通过双向队列维护连接，
    private LinkedList<Connection> pool = new LinkedList<>();

    //通过构造函数初始化连接的最大上线
    public ConnectionPool(int initialSize) {
        if (initialSize > 0) {
            for (int i = 0; i < initialSize; i++) {
                pool.addLast(ConnectionDriver.creatConnection());
            }
        }
    }

    //当连接使用完时，需要将连接放回连接池
    public void releaseConnection(Connection connection) {
        if (connection != null) {
            synchronized (pool) {
                //连接释放后需要进行通知，这样其他消费者能够感知到连接池中已经归还了一个连接
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }

    //调用方需要先调用这个方法，指定在多少毫秒内获取连接会超时
    public Connection fetchConnection(long mills) throws InterruptedException {
        synchronized (pool) {
            //完全超时，就是没有超时时间，获取不到连接的线程会一直等待。
            if (mills <= 0) {
                while (pool.isEmpty()) {
                    pool.wait();
                }
                return pool.removeFirst();
            } else {
                long future = System.currentTimeMillis() + mills;
                long remaining = mills;
                while (pool.isEmpty() && remaining > 0) {
                    pool.wait();
                    remaining = future - System.currentTimeMillis();
                }
                Connection result = null;
                if (!pool.isEmpty()) {
                    result = pool.removeFirst();
                }
                return result;
            }
        }
    }
}
