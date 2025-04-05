package com.example.monitordashboardbackend.demos.scheduler;

import com.example.monitordashboardbackend.demos.utils.CpuMonitor;
import com.example.monitordashboardbackend.demos.utils.MemoryMonitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LogScheduler {
    private static final Logger logger = LoggerFactory.getLogger("SYSTEM_LOGGER");

    @Scheduled(fixedRate = 5000)
    public void logSystemUsage() {
        System.out.println("定时系统监控任务已触发");
        double cpuUsage = CpuMonitor.getCpuUsage();
        double memoryUsage = MemoryMonitor.getMemoryUsage();
        long freeMemory = MemoryMonitor.getFreeMemory();
        long totalMemory = MemoryMonitor.getTotalMemory();

        if (cpuUsage >= 0 && memoryUsage >= 0) {
            String formattedCpuUsage = String.format("%.2f", cpuUsage);
            String formattedMemoryUsage = String.format("%.2f", memoryUsage);
            String formattedUsedMemory = String.format("%.2f", ((double) totalMemory - (double) freeMemory) / 1024);
            String formattedMaxMemory = String.format("%.2f", (double) totalMemory / 1024);

            logger.info("current CPU usage: {}%, memory usage: {} MB / {} MB ({}%)",
                    formattedCpuUsage, formattedUsedMemory, formattedMaxMemory, formattedMemoryUsage);
        }
    }
}