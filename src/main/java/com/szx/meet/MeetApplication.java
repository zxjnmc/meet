package com.szx.meet;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.szx.meet.mapper"})
public class MeetApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeetApplication.class, args);
    }

}
