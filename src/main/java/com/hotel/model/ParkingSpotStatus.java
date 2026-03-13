package com.hotel.model;

public enum ParkingSpotStatus {
    AVAILABLE("可用", "车位空闲"),
    OCCUPIED("占用", "车位被占用"),
    MAINTENANCE("维护中", "车位正在维护"),
    RESERVED("已预订", "车位已被预订"),
    OUT_OF_ORDER("故障", "车位出现故障");
    
    private final String description;
    private final String details;
    
    ParkingSpotStatus(String description, String details) {
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
