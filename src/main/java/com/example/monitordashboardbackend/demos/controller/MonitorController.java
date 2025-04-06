package com.example.monitordashboardbackend.demos.controller;


import com.example.monitordashboardbackend.demos.entity.Message;
import com.example.monitordashboardbackend.demos.entity.UsageData;
import com.example.monitordashboardbackend.demos.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MonitorController {
    @Autowired
    private MonitorService monitorService;

    @GetMapping("/monitor")
    public List<Message<?>> getMonitorData() {
        List<Message<UsageData>> originalList = monitorService.getMonitorData();
        List<Message<?>> resultList = new ArrayList<>(originalList);
        return resultList;
    }
}