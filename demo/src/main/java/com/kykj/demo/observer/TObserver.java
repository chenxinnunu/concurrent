package com.kykj.demo.observer;

/**
 * 第三个观察者
 *
 * @author chenxinnunu@gmail.com
 * @date 2019/4/4 16:02
 */
public class TObserver extends Observer {
    @Override
    public void update(String msg) {
        System.out.println(TObserver.class.getName() + ":" + msg);
    }
}
