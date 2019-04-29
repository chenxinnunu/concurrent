package com.kykj.demo;

import com.kykj.demo.test.EmailEntity;
import com.kykj.demo.test.EmailEvent;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

/**
 * 发布事件
 * @author chenxinnunu@gmail.com
 * @date 2019/4/2 10:50
 */

public class EmailTest extends ApplicationTest {
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Test
    public void event() {
        EmailEntity emailEntity = new EmailEntity();
        emailEntity.setAddress("杭州市萧山区");
        emailEntity.setText("大帅哥你好");
        eventPublisher.publishEvent(new EmailEvent(emailEntity));
    }
}
