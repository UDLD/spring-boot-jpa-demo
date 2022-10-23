package com.udld.demo;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing
@EnableEncryptableProperties
@EnableAsync        // 使Async生效
@EnableScheduling   // 开启对定时任务的支持
@ServletComponentScan(basePackages = "com.udld.demo.jwt")
@EnableDubbo(scanBasePackages = {"com.udld.demo.dubbo"})
@PropertySources({
    @PropertySource(value = "classpath:/provider-config.properties"),
    @PropertySource(value = "classpath:/application.properties")
})
public class DemoApplication {

  public static void main(String[] args) {

    SpringApplication.run(DemoApplication.class, args);

  }

}
