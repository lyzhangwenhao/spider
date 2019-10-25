package com.tom.spider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan(value = "com.tom.sprider.mapper")
public class Sprider03Application {

    public static void main(String[] args) {
        SpringApplication.run(Sprider03Application.class, args);
    }

}
