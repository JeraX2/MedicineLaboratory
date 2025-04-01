package com.example.monitordashboardbackend.demos.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.monitordashboardbackend.demos.entity.MonitorData;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CpuAndMemoryUsageMapper extends BaseMapper<MonitorData> {

}
