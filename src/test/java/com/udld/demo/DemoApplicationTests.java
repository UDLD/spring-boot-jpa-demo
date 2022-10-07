package com.udld.demo;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {

  @Test
  void contextLoads() {
  }

  @Autowired
  StringEncryptor stringEncryptor;

  @Test
  public void encryptPwd() {
    String result = stringEncryptor.encrypt("aine1314");
    System.out.println(result);
  }
}
