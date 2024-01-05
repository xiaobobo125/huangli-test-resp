package com.huangli.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author lxb
 * @version 1.0
 * @title SpringBootApplication
 * @description
 * @create 2023/1/5
 */
@SpringBootApplication
@MapperScan("com.huangli.web.**.mapper")
public class HuangLiWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(HuangLiWebApplication.class, args);
    }
}
