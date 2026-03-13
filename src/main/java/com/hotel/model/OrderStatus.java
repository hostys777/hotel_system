package com.hotel.model;

/**
 * 订单状态枚举
 */
public enum OrderStatus {
    PENDING("待确认"),
    CONFIRMED("已确认"),
    PREPARING("制作中"),
    READY("已就绪"),
    SERVED("已上菜"),
    CANCELLED("已取消"),
    PAID("已支付");
    
    private final String description;
    
    OrderStatus(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
}
