package com.kykj.demo.test;

/**
 * 定义一个ApplicationContext类，其对象是一个Spring容器事件
 *
 * @author chenxinnunu@gmail.com
 * @date 2019/4/2 10:20
 */
public class EmailEvent extends AbstractEvent<EmailEntity>{
    public EmailEvent(EmailEntity source) {
        super(source);
    }
}
