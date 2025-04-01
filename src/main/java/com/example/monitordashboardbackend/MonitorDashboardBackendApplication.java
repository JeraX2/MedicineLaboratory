package com.example.monitordashboardbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.example.monitordashboardbackend.demos.mapper")
public class MonitorDashboardBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonitorDashboardBackendApplication.class, args);
    }

}
