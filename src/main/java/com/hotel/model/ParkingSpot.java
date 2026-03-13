package com.hotel.model;

import javax.persistence.*;

@Entity
@Table(name = "parking_spots")
public class ParkingSpot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String spotNumber; // 停车位编号，如 A01, B02
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ParkingSpotType spotType; // 停车位类型
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ParkingSpotStatus status; // 停车位状态
    
    @Column
    private String location; // 位置描述，如 "地下车库A区"
    
    @Column
    private String description; // 描述信息
    
    @Column(nullable = false)
    private Double hourlyRate; // 每小时费用
    
    @Column
    private Boolean isReserved = false; // 是否被预订
    
    // 构造函数
    public ParkingSpot() {}
    
    public ParkingSpot(String spotNumber, ParkingSpotType spotType, String location, Double hourlyRate) {
        this.spotNumber = spotNumber;
        this.spotType = spotType;
        this.location = location;
        this.hourlyRate = hourlyRate;
        this.status = ParkingSpotStatus.AVAILABLE;
        this.isReserved = false;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getSpotNumber() {
        return spotNumber;
    }
    
    public void setSpotNumber(String spotNumber) {
        this.spotNumber = spotNumber;
    }
    
    public ParkingSpotType getSpotType() {
        return spotType;
    }
    
    public void setSpotType(ParkingSpotType spotType) {
        this.spotType = spotType;
    }
    
    public ParkingSpotStatus getStatus() {
        return status;
    }
    
    public void setStatus(ParkingSpotStatus status) {
        this.status = status;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Double getHourlyRate() {
        return hourlyRate;
    }
    
    public void setHourlyRate(Double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
    
    public Boolean getIsReserved() {
        return isReserved;
    }
    
    public void setIsReserved(Boolean isReserved) {
        this.isReserved = isReserved;
    }
}
