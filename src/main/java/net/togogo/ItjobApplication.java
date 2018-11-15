package net.togogo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("net.togogo.mapper")
public class ItjobApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItjobApplication.class, args);
    }
}
