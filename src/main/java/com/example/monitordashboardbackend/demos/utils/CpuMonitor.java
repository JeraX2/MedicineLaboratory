package com.example.monitordashboardbackend.demos.utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CpuMonitor {
    private static final Logger logger = LoggerFactory.getLogger(CpuMonitor.class);
    private static final String PROC_STAT = "/proc/stat";
    private static long[] prevCpuTimes = new long[7];
    private static long sum(long[] arr) {
        long sum = 0;
        for (long num : arr) {
            sum += num;
        }
        return sum;
    }
    public static double getCpuUsage() {
        try(BufferedReader reader = new BufferedReader(new FileReader(PROC_STAT))) {
            String line = reader.readLine();
            if(line == null || !line.startsWith("cpu ")) {
                return -1.0;
            }
            String[] parts = line.split("\\s+");
            long[] currCpuTimes = new long[7];
            for(int i = 1; i <= 7; i++) {
                currCpuTimes[i-1] = Long.parseLong(parts[i]);
            }

            long totalTimeDiff = sum(currCpuTimes) - sum(prevCpuTimes);
            long idleTimeDiff = currCpuTimes[3] - prevCpuTimes[3];
            prevCpuTimes = currCpuTimes;

            if(totalTimeDiff == 0)
                return 0.0;
            return 100.0 * (totalTimeDiff - idleTimeDiff) / totalTimeDiff;
        } catch (IOException | NumberFormatException e) {
            logger.error("获取CPU使用率失败", e);
            return -1.0;
        }
    }
}
