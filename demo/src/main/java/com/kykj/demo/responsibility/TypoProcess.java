package com.kykj.demo.responsibility;

/**
 * 责任链中的一个
 *
 * @author chenxinnunu@gmail.com
 * @date 2019/4/3 10:45
 */
public class TypoProcess implements ProcessTest {
    @Override
    public void doProcess(String msg) {
        System.out.println(msg + "错别字修改");
    }
}
