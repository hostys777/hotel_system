package com.hotel.model;

public enum UserRole {
    ADMIN("系统管理员", "拥有所有权限，可以管理用户和所有业务模块"),
    MANAGER("经理", "可以管理员工和所有业务模块，但不能管理用户"),
    FRONT_DESK("前台", "可以管理房间、客户、预订、餐饮订单和停车，但不能管理员工和用户");

    private final String displayName;
    private final String description;

    UserRole(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }

    public String getLevel() {
        switch (this) {
            case ADMIN:
                return "高级";
            case MANAGER:
                return "中级";
            case FRONT_DESK:
                return "初级";
            default:
                return "未知";
        }
    }
}
