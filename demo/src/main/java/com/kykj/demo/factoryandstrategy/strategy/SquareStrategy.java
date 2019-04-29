package com.kykj.demo.factoryandstrategy.strategy;

import com.kykj.demo.factoryandstrategy.factory.FactoryInterface;
import com.kykj.demo.factoryandstrategy.factory.SquareImpl;

/**
 * @author chenxinnunu@gmail.com
 * @date 2019/4/3 13:49
 */
public class SquareStrategy implements StrategyInteface {
    @Override
    public FactoryInterface get() {
        return new SquareImpl();
    }
}
