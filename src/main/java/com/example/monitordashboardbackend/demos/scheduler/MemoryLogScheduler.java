package com.example.monitordashboardbackend.demos.scheduler;

import com.example.monitordashboardbackend.demos.utils.MemoryMonitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MemoryLogScheduler {
    private static final Logger logger = LoggerFactory.getLogger("MEMORY_LOGGER");

    @Scheduled(fixedRate = 5000)
    public void logMemoryUsage() {
        System.out.println("定时内存监控任务已触发");
        double memoryUsage = MemoryMonitor.getMemoryUsage();
        long freeMemory = MemoryMonitor.getFreeMemory();
        long totalMemory = MemoryMonitor.getTotalMemory();
        if (memoryUsage >= 0) {
            String formattedUsage = String.format("%.2f", memoryUsage);
            String formattedUsed = String.format("%.2f", ((double)totalMemory - (double)freeMemory) / 1024);
            String formattedMax = String.format("%.2f", (double)totalMemory / 1024);
            logger.info("内存使用量: {} MB / {} MB ({}%)", formattedUsed, formattedMax, formattedUsage);
        }
    }
}