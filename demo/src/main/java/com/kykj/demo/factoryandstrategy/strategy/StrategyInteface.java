package com.kykj.demo.factoryandstrategy.strategy;


import com.kykj.demo.factoryandstrategy.factory.FactoryInterface;

/**
 * @author chenxinnunu@gmail.com
 * @date 2019/4/3 13:42
 */
public interface StrategyInteface {
    FactoryInterface get();
}
