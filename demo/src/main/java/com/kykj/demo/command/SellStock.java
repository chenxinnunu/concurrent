package com.kykj.demo.command;

/**
 * 实现Order接口的实体类
 *
 * @author chenxinnunu@gmail.com
 * @date 2019/4/4 19:18
 */
public class SellStock implements Order {
    private Stock abcStock;

    public SellStock(Stock abcStock) {
        this.abcStock = abcStock;
    }

    @Override
    public void execute() {
        abcStock.sell();
    }
}
