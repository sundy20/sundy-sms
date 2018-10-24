package com.sundy.sms.service;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableDubbo(scanBasePackages = "com.sundy.sms")
@MapperScan("com.sundy.sms.dao")
@EnableTransactionManagement(proxyTargetClass = true)
@SpringBootApplication(scanBasePackages = "com.sundy.sms")
public class SundySmsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SundySmsServiceApplication.class, args);
    }
}
