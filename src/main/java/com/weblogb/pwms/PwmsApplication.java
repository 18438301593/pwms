package com.weblogb.pwms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
* @author: Jiajiajia
* @Date: 2021/10/27
* @Description: 启动程序
*/
@SpringBootApplication
@MapperScan("com.weblogb.pwms.mapper")
public class PwmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(PwmsApplication.class,args);
    }
}
