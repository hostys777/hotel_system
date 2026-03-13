package com.hotel.model;

import javax.persistence.*;

/**
 * 客户实体类
 */
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;                // 客户ID
    
    @Column(name = "name", nullable = false)
    private String name;            // 姓名
    
    @Column(name = "phone")
    private String phone;           // 电话
    
    @Column(name = "email")
    private String email;           // 邮箱
    
    @Column(name = "id_card")
    private String idCard;          // 身份证号
    
    @Column(name = "address")
    private String address;         // 地址
    
    public Customer() {}
    
    public Customer(String name, String phone, String email, String idCard, String address) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.idCard = idCard;
        this.address = address;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getIdCard() {
        return idCard;
    }
    
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
}
