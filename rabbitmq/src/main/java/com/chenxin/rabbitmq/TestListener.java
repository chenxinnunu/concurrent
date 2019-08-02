package com.chenxin.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenxin
 * @date 2019/08/02
 */
public class TestListener implements ChannelAwareMessageListener {
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        try {
        System.out.println(message);
       // channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        System.out.println(1 / 0);
        } catch (Exception e) {
            acknowledgeMessage(message, channel);
        }
    }

    private void acknowledgeMessage(Message message, Channel channel) {
        String json = new String(message.getBody());
        Map<String, Object> map = JsonUtils.parseJSONObject(json);
        if (map.get("times") != null && (map.get("times") instanceof Integer)) {
            int times = (Integer) map.get("times");
            if (times++ >= 5) {
                System.out.println("消息重发停止,请进行人工处理.");
                return;
            }
            System.out.println("消息重发第" + times + "次");
            map.put("times", times);
        } else {
            System.out.println("消息重发第一次");
            int times = 1;
            map.put("times", times);
        }
        try {
            //因为设置的是auto模式，不知道这个方法是干嘛的，可能为了方式消息还未被确认，手动确认一次
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            //重新发一次消息，参数分别为交换器，路由键，消息持久化，消息体
            channel.basicPublish(message.getMessageProperties().getReceivedExchange(),
                    message.getMessageProperties().getReceivedRoutingKey(), MessageProperties.PERSISTENT_TEXT_PLAIN,
                    JsonUtils.toJSONByte(map));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
