package com.kykj.demo.observer;

/**
 * 第二个观察者
 *
 * @author chenxinnunu@gmail.com
 * @date 2019/4/4 16:01
 */
public class SObserver extends Observer {
    @Override
    public void update(String msg) {
        System.out.println(SObserver.class.getName() + ":" + msg);
    }
}
