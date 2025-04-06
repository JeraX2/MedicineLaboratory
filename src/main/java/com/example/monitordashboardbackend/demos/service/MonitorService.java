package com.example.monitordashboardbackend.demos.service;

import com.example.monitordashboardbackend.demos.entity.Message;
import com.example.monitordashboardbackend.demos.entity.UsageData;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MonitorService {
    private static final String FILE_PATH = "F:/monitor-backend/system_usage.log";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final Pattern PATTERN = Pattern.compile("(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}) - current CPU usage: (\\d+\\.?\\d*)%, memory usage: \\d+\\.?\\d* MB / \\d+\\.?\\d* MB \\((\\d+\\.?\\d*)%\\)");

    public List<Message<UsageData>> getMonitorData() {
        //System.out.println("ex");
        List<Message<UsageData>> messages = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                Matcher matcher = PATTERN.matcher(line);
                if (matcher.find()) {
                    LocalDateTime timestamp = LocalDateTime.parse(matcher.group(1), FORMATTER);
                    double cpuUsage = Double.parseDouble(matcher.group(2));
                    double memoryUsage = Double.parseDouble(matcher.group(3));
                    UsageData usageData = new UsageData(cpuUsage, memoryUsage);
                    messages.add(new Message<>(timestamp, usageData));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println("ex");
        return messages;
    }
}
