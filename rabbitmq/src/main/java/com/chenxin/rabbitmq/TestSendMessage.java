package com.chenxin.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chenxin
 * @date 2019/08/02
 */
@Service
public class TestSendMessage {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendCoreMessage(final String content) {
        amqpTemplate.convertAndSend("TestExchanger", "TestBinding", content);
    }
}