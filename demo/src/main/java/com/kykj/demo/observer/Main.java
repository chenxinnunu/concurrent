package com.kykj.demo.observer;

/**
 * 测试类
 *
 * @author chenxinnunu@gmail.com
 * @date 2019/4/4 16:08
 */
public class Main {
    public static void main(String[] args) {
        FObserver fObserver = new FObserver();
        SObserver sObserver = new SObserver();
        TObserver tObserver = new TObserver();
        Subject subject = new Subject();
        subject.addAttach(fObserver);
        subject.addAttach(sObserver);
        subject.addAttach(tObserver);
        subject.setMsg("msg change");
    }
}
