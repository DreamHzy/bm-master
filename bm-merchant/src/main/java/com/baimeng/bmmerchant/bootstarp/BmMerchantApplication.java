package com.baimeng.bmmerchant.bootstarp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * spring-boot 主启动程序
 *
 * @author terrfly
 * @site https://www.jeequan.com
 * @date 2021-04-27 15:50
 */
@SpringBootApplication
@EnableScheduling
@MapperScan({"com.baimeng.bmmerchant.mapper.**", "com.baimeng.bmservice.mapper.**"})  //Mybatis mapper接口路径
@ComponentScan(basePackages = "com.baimeng.*")   //由于MainApplication没有在项目根目录， 需要配置basePackages属性使得成功扫描所有Spring组件；
@Configuration
public class BmMerchantApplication {

    public static void main(String[] args) {
        SpringApplication.run(BmMerchantApplication.class, args);

    }



}
