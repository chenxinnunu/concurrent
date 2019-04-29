package com.kykj.demo.factory;

/**
 * 工厂模式的换一个对象
 *
 * @author chenxinnunu@gmail.com
 * @date 2019/4/3 11:51
 */
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("这是一个Circle");
    }
}
