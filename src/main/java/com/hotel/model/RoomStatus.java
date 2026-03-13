package com.hotel.model;

/**
 * 房间状态枚举
 */
public enum RoomStatus {
    AVAILABLE("可用"),
    OCCUPIED("已入住"),
    MAINTENANCE("维护中"),
    RESERVED("已预订");
    
    private final String description;
    
    RoomStatus(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
}
