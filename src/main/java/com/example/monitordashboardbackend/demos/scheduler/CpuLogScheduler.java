package com.example.monitordashboardbackend.demos.scheduler;

import com.example.monitordashboardbackend.demos.utils.CpuMonitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;

@Component
public class CpuLogScheduler {
    private static final Logger logger = LoggerFactory.getLogger("CPU_LOGGER");

    @Scheduled(fixedRate = 5000)
    public void logCpuUsage() {
        System.out.println("定时CPU监控任务已触发");
        double cpuUsage = CpuMonitor.getCpuUsage();
        if (cpuUsage >= 0) {
            String formatted = String.format("%.2f", cpuUsage);
            logger.info("当前 CPU 使用率: {}%", formatted);
        }
    }
}
