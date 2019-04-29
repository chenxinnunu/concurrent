package com.kykj.demo.strategy;

/**
 * @author chenxinnunu@gmail.com
 * @date 2019/4/3 10:23
 */
public class Context {
    private StrategyTest strategyTest;
    public Context(StrategyTest strategyTest) {
        this.strategyTest = strategyTest;
    }
    public int executeStrategyTest(int a, int b) {
        return strategyTest.doOperation(a, b);
    }
}
