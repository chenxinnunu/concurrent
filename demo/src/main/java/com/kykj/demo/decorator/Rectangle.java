package com.kykj.demo.decorator;

/**
 * @author chenxin
 * @date 2019/07/25
 * 实现接口的实现类
 */
public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Rectangle");
    }
}
