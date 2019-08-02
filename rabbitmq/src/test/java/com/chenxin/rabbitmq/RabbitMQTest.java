package com.chenxin.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenxin
 * @date 2019/08/02
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class RabbitMQTest {

    @Autowired
    private TestSendMessage testSendMessage;

    @Test
    public void send() throws InterruptedException {
        Map<String, String> map = new HashMap<>();
        map.put("message", "测试一下");
        String coent = JsonUtils.toJSONString(map);
        testSendMessage.sendCoreMessage(coent);
        Thread.sleep(300000);
    }
}
