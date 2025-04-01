package com.example.monitordashboardbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Set;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;

@SpringBootApplication
@MapperScan(value = "com.example.monitordashboardbackend.demos.mapper")
@EnableScheduling
public class MonitorDashboardBackendApplication {

    public static void main(String[] args) {
        String logDir = System.getProperty("user.home") + "/.monitor_logs";
        try {
            Files.createDirectories(Paths.get(logDir));
            Files.setPosixFilePermissions(Paths.get(logDir), Set.of(PosixFilePermission.OWNER_READ, PosixFilePermission.OWNER_WRITE, PosixFilePermission.OWNER_EXECUTE));
        } catch (IOException e) {
            System.err.println("Could not create log directory: " + logDir);
        }
        SpringApplication.run(MonitorDashboardBackendApplication.class, args);
    }

}
