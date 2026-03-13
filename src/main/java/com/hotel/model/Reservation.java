package com.hotel.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 预订实体类
 */
@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;                // 预订ID
    
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;      // 客户
    
    @ManyToOne
    @JoinColumn(name = "room_number")
    private Room room;              // 房间
    
    @Column(name = "check_in_date")
    private LocalDate checkInDate;  // 入住日期
    
    @Column(name = "check_out_date")
    private LocalDate checkOutDate; // 退房日期
    
    @Column(name = "total_amount")
    private BigDecimal totalAmount; // 总金额
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ReservationStatus status; // 预订状态
    
    @Column(name = "create_time")
    private LocalDateTime createTime; // 创建时间
    
    @Column(name = "notes")
    private String notes;           // 备注
    
    public Reservation() {}
    
    public Reservation(Customer customer, Room room, LocalDate checkInDate, 
                      LocalDate checkOutDate, BigDecimal totalAmount, String notes) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalAmount = totalAmount;
        this.status = ReservationStatus.CONFIRMED;
        this.createTime = LocalDateTime.now();
        this.notes = notes;
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
    
    public Room getRoom() {
        return room;
    }
    
    public void setRoom(Room room) {
        this.room = room;
    }
    
    public LocalDate getCheckInDate() {
        return checkInDate;
    }
    
    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }
    
    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }
    
    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
    
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public ReservationStatus getStatus() {
        return status;
    }
    
    public void setStatus(ReservationStatus status) {
        this.status = status;
    }
    
    public LocalDateTime getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
}
