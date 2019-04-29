package com.kykj.demo.proxy;

/**
 * 代理类
 *
 * @author chenxinnunu@gmail.com
 * @date 2019/4/4 16:38
 */
public class ProxyImage implements Image {

    private RellImage rellImage;
    private String fileName;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if (rellImage == null) {
            rellImage = new RellImage(fileName);
        }
        rellImage.display();
    }

}
