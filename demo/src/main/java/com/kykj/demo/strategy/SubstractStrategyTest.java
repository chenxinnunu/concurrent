package com.kykj.demo.strategy;

/**
 * @author chenxinnunu@gmail.com
 * @date 2019/4/3 10:19
 */
public class SubstractStrategyTest implements StrategyTest {
    @Override
    public int doOperation(int a, int b) {
        return a - b;
    }
}
