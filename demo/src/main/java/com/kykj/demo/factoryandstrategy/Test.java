package com.kykj.demo.factoryandstrategy;

import com.kykj.demo.factoryandstrategy.strategy.CircleStrategy;
import com.kykj.demo.factoryandstrategy.strategy.RectangleStrategy;
import com.kykj.demo.factoryandstrategy.strategy.SquareStrategy;

/**
 * @author chenxinnunu@gmail.com
 * @date 2019/4/3 13:38
 */
public class Test {
    public static void main(String[] args) {

        new CircleStrategy().get().drow();

        new RectangleStrategy().get().drow();

        //获取 Square 的对象，并调用它的 draw 方法
        new SquareStrategy().get().drow();

    }
}
