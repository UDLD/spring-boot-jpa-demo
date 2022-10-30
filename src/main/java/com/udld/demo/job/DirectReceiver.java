package com.udld.demo.job;

import java.util.Map;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "TestDirectQueue") //监听的队列名称 TestDirectQueue
public class DirectReceiver {

  @RabbitHandler
  public void getMessage(Map testMessage) {
    System.out.println("DirectReceiver消费者收到消息  : " + testMessage.toString());
  }

}