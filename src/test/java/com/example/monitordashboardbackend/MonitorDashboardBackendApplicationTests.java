package com.example.monitordashboardbackend;

import com.example.monitordashboardbackend.demos.entity.MonitorData;
import com.example.monitordashboardbackend.demos.service.SystemMonitorService;
import com.example.monitordashboardbackend.demos.entity.Server;
import com.example.monitordashboardbackend.demos.mapper.CpuAndMemoryUsageMapper;
import com.example.monitordashboardbackend.demos.mapper.ServerMapper;
import com.example.monitordashboardbackend.demos.service.SystemMonitorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootTest
class MonitorDashboardBackendApplicationTests {
    @Autowired
    private SystemMonitorService monitorService;

    @Autowired
    private ServerMapper serverMapper;

    @Autowired
    private CpuAndMemoryUsageMapper cpuAndMemoryUsageMapper;

    @Test
    public void monitorSystem() {
        MonitorData monitorData = monitorService.getMonitorData();
        monitorData.setTimestamp(monitorData.getTimestamp());
        monitorData.setCpuUsage(monitorData.getCpuUsage());
        monitorData.setMemoryUsage(monitorData.getMemoryUsage());
        monitorData.setServerId(0);
        cpuAndMemoryUsageMapper.insert(monitorData);
    }
    @Test
    void serverInsert() {
        Server server = new Server();
        server.setName("test");
        serverMapper.insert(server);
    }

}
