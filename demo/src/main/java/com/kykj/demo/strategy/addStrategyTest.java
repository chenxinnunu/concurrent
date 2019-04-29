package com.kykj.demo.strategy;

/**
 * 接口实现
 *
 * @author chenxinnunu@gmail.com
 * @date 2019/4/3 10:17
 */
public class addStrategyTest implements StrategyTest {
    @Override
    public int doOperation(int a, int b) {
        return a + b;
    }
}
