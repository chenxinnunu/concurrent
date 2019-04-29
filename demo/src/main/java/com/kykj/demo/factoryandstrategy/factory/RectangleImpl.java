package com.kykj.demo.factoryandstrategy.factory;

/**
 * @author chenxinnunu@gmail.com
 * @date 2019/4/3 13:36
 */
public class RectangleImpl implements FactoryInterface {
    @Override
    public void drow() {
        System.out.println("这是一个Rectangle");
    }
}
