package com.kykj.demo.command;

/**
 * 请求类
 *
 * @author chenxinnunu@gmail.com
 * @date 2019/4/4 19:11
 */
public class Stock {
    private String name = "ABC";
    private int quantity = 10;

    public void buy() {
        System.out.println("Stock [ Name: " + name +
                "Quantity:" + quantity + " ]bought ");
    }

    public void sell() {
        System.out.println("Stock [ Name: " + name +
                "Quantity:" + quantity + " ]sold ");
    }
}
