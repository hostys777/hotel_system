package com.hotel.model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 房间实体类
 */
@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @Column(name = "room_number")
    private String roomNumber;      // 房间号
    
    @Enumerated(EnumType.STRING)
    @Column(name = "room_type")
    private RoomType roomType;      // 房间类型
    
    @Column(name = "price")
    private BigDecimal price;       // 价格
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private RoomStatus status;      // 房间状态
    
    @Column(name = "description")
    private String description;     // 房间描述
    
    public Room() {}
    
    public Room(String roomNumber, RoomType roomType, BigDecimal price, String description) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.price = price;
        this.status = RoomStatus.AVAILABLE;
        this.description = description;
    }
    
    // Getters and Setters
    public String getRoomNumber() {
        return roomNumber;
    }
    
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
    
    public RoomType getRoomType() {
        return roomType;
    }
    
    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public RoomStatus getStatus() {
        return status;
    }
    
    public void setStatus(RoomStatus status) {
        this.status = status;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
}
