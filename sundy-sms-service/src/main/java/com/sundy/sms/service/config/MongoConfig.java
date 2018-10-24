package com.sundy.sms.service.config;

/**
 * Created on 2017/12/11
 *
 * @author plus.wang
 * @description fastjson 序列化
 */

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class MongoConfig {

    /**
     * 修改默认转换为fastjson,解决mongodb显示时间差8小时（Jackson序列化时间问题，数据没问题）
     */
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastConverter.setFastJsonConfig(fastJsonConfig);
        return new HttpMessageConverters(fastConverter);
    }

    @Bean
    public JavaMailSender javaMailSender() {
        return new JavaMailSenderImpl();
    }

    @Bean
    public ExecutorService singleThreadPool() {

        return Executors.newSingleThreadExecutor();
    }
}
