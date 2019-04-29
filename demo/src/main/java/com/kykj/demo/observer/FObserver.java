package com.kykj.demo.observer;

/**
 * 第一个观察者
 *
 * @author chenxinnunu@gmail.com
 * @date 2019/4/4 15:51
 */
public class FObserver extends Observer {
    @Override
    public void update(String msg) {
        System.out.println(FObserver.class.getName() + ":" + msg);
    }
}
