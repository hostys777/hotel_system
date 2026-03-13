package com.hotel.model;

import javax.persistence.*;

/**
 * 员工实体类
 */
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name", nullable = false)
    private String name;                // 员工姓名
    
    @Column(name = "employee_number", unique = true, nullable = false)
    private String employeeNumber;      // 工号
    
    @Column(name = "phone")
    private String phone;               // 电话
    
    @Column(name = "position")
    private String position;            // 职位
    
    @Column(name = "is_active")
    private Boolean isActive;           // 是否在职
    
    public Employee() {}
    
    public Employee(String name, String employeeNumber, String phone, String position) {
        this.name = name;
        this.employeeNumber = employeeNumber;
        this.phone = phone;
        this.position = position;
        this.isActive = true;
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
    
    public String getEmployeeNumber() {
        return employeeNumber;
    }
    
    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getPosition() {
        return position;
    }
    
    public void setPosition(String position) {
        this.position = position;
    }
    
    public Boolean getIsActive() {
        return isActive;
    }
    
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}