package com.example.monitordashboardbackend.demos.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MemoryMonitor {
    private static final Logger logger = LoggerFactory.getLogger(MemoryMonitor.class);
    private static final String PROC_MEMINFO = "/proc/meminfo";
    private static long freeMemory;
    private static long totalMemory;

    public static double getMemoryUsage() {
        try(BufferedReader reader = new BufferedReader(new FileReader(PROC_MEMINFO))){
            String line;
            while((line = reader.readLine()) != null) {
               if(line.startsWith("MemTotal:")) {
                   totalMemory = Long.parseLong(line.split("\\s+")[1]);
               }
               else if(line.startsWith("MemFree:")) {
                    freeMemory = Long.parseLong(line.split("\\s+")[1]);
               }
            }
            return (totalMemory - freeMemory) * 100.0 / totalMemory;
        }
        catch (IOException e) {
            logger.error("获取MEM使用率失败", e);
            return -1.0;
        }
    }

    public static long getFreeMemory() {
        return freeMemory;
    }

    public static long getTotalMemory() {
        return totalMemory;
    }

}
