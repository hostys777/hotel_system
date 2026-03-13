package com.hotel.model;

/**
 * 支付方式枚举
 */
public enum PaymentMethod {
    CASH("现金支付"),
    CARD("刷卡支付"),
    ROOM_CHARGE("挂房账"),
    ONLINE("在线支付");
    
    private final String description;
    
    PaymentMethod(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
}
