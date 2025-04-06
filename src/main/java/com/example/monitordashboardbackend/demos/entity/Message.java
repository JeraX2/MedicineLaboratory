package com.example.monitordashboardbackend.demos.entity;

import java.time.LocalDateTime;

public class Message<T> {
    private LocalDateTime timestamp;
    private T data;

    public Message(LocalDateTime timestamp, T data) {
        this.timestamp = timestamp;
        this.data = data;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}