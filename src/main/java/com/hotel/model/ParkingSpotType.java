package com.hotel.model;

public enum ParkingSpotType {
    STANDARD("标准车位", "适合普通轿车"),
    COMPACT("紧凑车位", "适合小型车"),
    LARGE("大型车位", "适合SUV、商务车"),
    DISABLED("无障碍车位", "专为残障人士设计"),
    VIP("VIP车位", "贵宾专用车位"),
    ELECTRIC("充电车位", "配备充电桩的车位");
    
    private final String description;
    private final String details;
    
    ParkingSpotType(String description, String details) {
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
