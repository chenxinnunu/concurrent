package com.kykj.demo.decorator;

/**
 * @author chenxin
 * @date 2019/07/25
 * 创建实现Shape的抽象装饰类
 */
public abstract class ShapeDecorator implements Shape{
    protected Shape decoratedShape;

    public ShapeDecorator(Shape decoratedShape){
        this.decoratedShape = decoratedShape;
    }
    @Override
    public void draw(){
        decoratedShape.draw();
    }
}
