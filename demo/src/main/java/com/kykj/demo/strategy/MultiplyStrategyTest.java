package com.kykj.demo.strategy;

/**
 * 乘
 *
 * @author chenxinnunu@gmail.com
 * @date 2019/4/3 10:20
 */
public class MultiplyStrategyTest implements StrategyTest {
    @Override
    public int doOperation(int a, int b) {
        return  a * b;
    }
}
