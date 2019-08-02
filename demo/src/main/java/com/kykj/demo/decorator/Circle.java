package com.kykj.demo.decorator;

/**
 * @author chenxin
 * @date 2019/07/25
 * 实现接口的实体类
 */
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Circle");
    }
}
