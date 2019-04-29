package com.kykj.demo.proxy;

/**
 * 测试类
 *
 * @author chenxinnunu@gmail.com
 * @date 2019/4/4 16:40
 */
public class Main {
    public static void main(String[] args) {
        Image image = new ProxyImage("名称");
        //图像从磁盘加载
        image.display();
        System.out.println("");
        //图像不需要从磁盘加载
        image.display();
    }
}
