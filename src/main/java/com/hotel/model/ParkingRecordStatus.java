package com.hotel.model;

public enum ParkingRecordStatus {
    ACTIVE("进行中", "车辆正在停车"),
    COMPLETED("已完成", "停车记录已完成"),
    CANCELLED("已取消", "停车记录已取消"),
    OVERDUE("超时", "停车时间超时");
    
    private final String description;
    private final String details;
    
    ParkingRecordStatus(String description, String details) {
        this.description = description;
        this.details = details;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getDetails() {
        return details;
    }
}
