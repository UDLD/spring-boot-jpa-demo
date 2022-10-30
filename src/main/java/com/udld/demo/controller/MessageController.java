package com.udld.demo.controller;

import com.udld.demo.job.DirectReceiver;
import com.udld.demo.util.RespGenerate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

  @Autowired
  RabbitTemplate rabbitTemplate; //使用RabbitTemplate,这提供了接收/发送等等方法

  @Autowired
  DirectReceiver directReceiver;

  @RequestMapping("/sendDirectMessage")
  public String sendDirectMessage() {
    String messageId = String.valueOf(UUID.randomUUID());
    String messageData = "test message, hello!";
    String createTime = LocalDateTime.now()
        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    Map<String, Object> map = new HashMap<>();
    map.put("messageId", messageId);
    map.put("messageData", messageData);
    map.put("createTime", createTime);
    //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
    rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting", map);
    return "ok";
  }






}
