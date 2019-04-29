package com.kykj.demo.command;

import java.util.ArrayList;
import java.util.List;

/**
 * 命令调用类
 *
 * @author chenxinnunu@gmail.com
 * @date 2019/4/4 19:19
 */
public class Broker {
    private List<Order> orderList = new ArrayList<>();
    public void takeOrder(Order order) {
        orderList.add(order);
    }
    public void placeOrders() {
        for (Order order : orderList) {
            order.execute();
        }
        orderList.clear();
    }
}
