package com.kykj.demo.test;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 事件监听器,根据事件类型匹配
 *
 * @author chenxinnunu@gmail.com
 * @date 2019/4/2 10:24
 */
@Component
public class EmailEventHandle {
    /**
     * 该方法在容器发生事件时自动触发
     */
    @Async
    @EventListener(EmailEvent.class)
    public void onApplicationEvent(EmailEvent event) {
        EmailEntity load = event.getLoad();
        String address = load.getAddress();
        String text = load.getText();
        System.out.println("发送的邮件地址：" + address + ";" + "发送的邮件内容：" + text);
    }
}
