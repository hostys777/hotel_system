package com.hotel.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @Column
    private String email;

    @Column
    private String phone;

    @Column
    private boolean active = true;

    // 构造函数
    public User() {}

    public User(String username, String password, String fullName, UserRole role) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    // 权限检查方法
    public boolean hasPermission(String permission) {
        if (role == null) return false;
        
        switch (role) {
            case ADMIN:
                return true; // 管理员拥有所有权限
            case MANAGER:
                return !permission.equals("USER_MANAGEMENT"); // 经理不能管理用户
            case FRONT_DESK:
                return permission.equals("ROOM_MANAGEMENT") || 
                       permission.equals("CUSTOMER_MANAGEMENT") || 
                       permission.equals("RESERVATION_MANAGEMENT") || 
                       permission.equals("FOOD_ORDER_MANAGEMENT") || 
                       permission.equals("PARKING_MANAGEMENT");
            default:
                return false;
        }
    }

    public boolean canManageUsers() {
        return role == UserRole.ADMIN;
    }

    public boolean canManageEmployees() {
        return role == UserRole.ADMIN || role == UserRole.MANAGER;
    }
}
