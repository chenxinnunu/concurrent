package com.kykj.demo.jdkproxy;

/**
 * 真实对象
 *
 * @author chenxinnunu@gmail.com
 * @date 2019/4/4 17:04
 */
public class RealSubject implements Subject {
    @Override
    public int sellBook() {
        System.out.println("买书");
        return 1;
    }

    @Override
    public String speak() {
        System.out.println("说话");
        return "张三";
    }
}
