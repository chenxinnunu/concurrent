package com.kykj.demo.factoryandstrategy.strategy;

import com.kykj.demo.factoryandstrategy.factory.RectangleImpl;
import com.kykj.demo.factoryandstrategy.factory.FactoryInterface;

/**
 * @author chenxinnunu@gmail.com
 * @date 2019/4/3 13:48
 */
public class RectangleStrategy implements StrategyInteface {
    @Override
    public FactoryInterface get() {
        return new RectangleImpl();
    }
}
