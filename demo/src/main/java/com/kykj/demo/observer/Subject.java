package com.kykj.demo.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察者
 *
 * @author chenxinnunu@gmail.com
 * @date 2019/4/4 16:03
 */
public class Subject {
    /**
     * 状态改变
     */
    private List<Observer> observers = new ArrayList<>();

    public void setMsg(String msg) {
        notifyAll(msg);
    }

    /**
     * 订阅
     */
    public void addAttach(Observer observer) {
        observers.add(observer);
    }

    /**
     * 通知所有订阅的观察者
     */
    private void notifyAll(String msg) {
        for (Observer observer : observers) {
            observer.update(msg);
        }
    }
}
