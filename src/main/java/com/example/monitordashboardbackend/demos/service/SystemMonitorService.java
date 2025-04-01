package com.example.monitordashboardbackend.demos.service;

import com.example.monitordashboardbackend.demos.entity.*;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.driver.linux.proc.CpuInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;

import java.time.LocalDateTime;
@Service
public class SystemMonitorService {
    public MonitorData getMonitorData() {
        //获取当前时间戳
        LocalDateTime timestamp = LocalDateTime.now();
        MonitorData monitorData = new MonitorData();
        monitorData.setTimestamp(timestamp);
        monitorData.setCpuUsage(getCpuUsage());
        monitorData.setMemoryUsage(getMemoryUsage());
        return monitorData;
    }

    //获取当前CPU使用率
    private double getCpuUsage( ) {
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hardware = systemInfo.getHardware();
        CentralProcessor processor = hardware.getProcessor();
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        //等待一段时间
        try {
            Thread.sleep(2000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return processor.getSystemCpuLoadBetweenTicks(prevTicks) * 100.0;

    }

    //获取内存使用率
    private double getMemoryUsage() {
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hardware = systemInfo.getHardware();
        GlobalMemory memory = hardware.getMemory();

        return (memory.getTotal() - memory.getAvailable()) * 100.0 / memory.getTotal();
    }

}
