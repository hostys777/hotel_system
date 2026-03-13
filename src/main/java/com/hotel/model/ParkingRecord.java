package com.hotel.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "parking_records")
public class ParkingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer; // 客户
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parking_spot_id", nullable = false)
    private ParkingSpot parkingSpot; // 停车位
    
    @Column(nullable = false)
    private String licensePlate; // 车牌号
    
    @Column(nullable = false)
    private LocalDateTime entryTime; // 入场时间
    
    @Column
    private LocalDateTime exitTime; // 出场时间
    
    @Column
    private Integer durationMinutes; // 停车时长（分钟）
    
    @Column(precision = 10, scale = 2)
    private BigDecimal totalFee; // 总费用
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ParkingRecordStatus status; // 记录状态
    
    @Column
    private String notes; // 备注
    
    @Column
    private String vehicleType; // 车辆类型
    
    @Column
    private String vehicleColor; // 车辆颜色
    
    // 构造函数
    public ParkingRecord() {}
    
    public ParkingRecord(Customer customer, ParkingSpot parkingSpot, String licensePlate) {
        this.customer = customer;
        this.parkingSpot = parkingSpot;
        this.licensePlate = licensePlate;
        this.entryTime = LocalDateTime.now();
        this.status = ParkingRecordStatus.ACTIVE;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Customer getCustomer() {
        return customer;
    }
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }
    
    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }
    
    public String getLicensePlate() {
        return licensePlate;
    }
    
    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
    
    public LocalDateTime getEntryTime() {
        return entryTime;
    }
    
    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }
    
    public LocalDateTime getExitTime() {
        return exitTime;
    }
    
    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }
    
    public Integer getDurationMinutes() {
        return durationMinutes;
    }
    
    public void setDurationMinutes(Integer durationMinutes) {
        this.durationMinutes = durationMinutes;
    }
    
    public BigDecimal getTotalFee() {
        return totalFee;
    }
    
    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }
    
    public ParkingRecordStatus getStatus() {
        return status;
    }
    
    public void setStatus(ParkingRecordStatus status) {
        this.status = status;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    public String getVehicleType() {
        return vehicleType;
    }
    
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
    
    public String getVehicleColor() {
        return vehicleColor;
    }
    
    public void setVehicleColor(String vehicleColor) {
        this.vehicleColor = vehicleColor;
    }
}
