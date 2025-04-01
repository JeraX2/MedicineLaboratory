package com.example.monitordashboardbackend.demos.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "servers")
public class Server {
    @TableId(value = "server_id", type = IdType.AUTO)
    private int id;

    @TableField("server_name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Server{" +
                "serverId=" + id +
                ", serverName='" + name + '\'' +
                '}';
    }
}
