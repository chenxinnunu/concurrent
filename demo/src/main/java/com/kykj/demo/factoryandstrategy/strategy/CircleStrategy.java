package com.kykj.demo.factoryandstrategy.strategy;

import com.kykj.demo.factoryandstrategy.factory.CircleImpl;
import com.kykj.demo.factoryandstrategy.factory.FactoryInterface;

/**
 * @author chenxinnunu@gmail.com
 * @date 2019/4/3 13:46
 */
public class CircleStrategy implements StrategyInteface {
    @Override
    public FactoryInterface get() {
        return new CircleImpl();
    }
}
