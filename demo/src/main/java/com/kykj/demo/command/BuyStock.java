package com.kykj.demo.command;

/**
 * 实现Order接口的实体类
 *
 * @author chenxinnunu@gmail.com
 * @date 2019/4/4 19:16
 */
public class BuyStock implements Order {
    private Stock abcStock;

    public BuyStock(Stock abcStock) {
        this.abcStock = abcStock;
    }

    @Override
    public void execute() {
        abcStock.buy();
    }
}
