package com.kykj.demo.factory;

/**
 * 工厂生产的一个对象
 *
 * @author chenxinnunu@gmail.com
 * @date 2019/4/3 11:51
 */
public class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("这是一个Square");
    }
}
