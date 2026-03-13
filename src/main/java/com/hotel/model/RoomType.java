package com.hotel.model;

/**
 * 房间类型枚举
 */
public enum RoomType {
    SINGLE("单人间"),
    DOUBLE("双人间"),
    SUITE("套房"),
    DELUXE("豪华间"),
    PRESIDENTIAL("总统套房");
    
    private final String description;
    
    RoomType(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
}
