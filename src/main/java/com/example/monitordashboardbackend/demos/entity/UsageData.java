package com.example.monitordashboardbackend.demos.entity;


public class UsageData {
    public double cpuUsage;
    public double memoryUsage;

    public UsageData(double cpuUsage, double memoryUsage) {
        this.cpuUsage = cpuUsage;
        this.memoryUsage = memoryUsage;
    }
}