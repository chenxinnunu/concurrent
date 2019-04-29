package com.kykj.demo.proxy;

/**
 * 接口实现
 *
 * @author chenxinnunu@gmail.com
 * @date 2019/4/4 16:34
 */
public class RellImage implements Image {

    private String fileName;

    public RellImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk(fileName);
    }

    @Override
    public void display() {
        System.out.println("Displaying" + fileName);
    }

    private void loadFromDisk(String fileName) {
        System.out.println("loading" + fileName);
    }
}
