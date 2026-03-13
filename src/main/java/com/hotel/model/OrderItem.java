package com.hotel.model;

import javax.persistence.*;

/**
 * 订单项实体类
 */
@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "order_id")
    private FoodOrder order;            // 所属订单
    
    @ManyToOne
    @JoinColumn(name = "dish_id")
    private Dish dish;                  // 菜品
    
    @Column(name = "quantity", nullable = false)
    private Integer quantity;           // 数量
    
    @Column(name = "unit_price", nullable = false)
    private java.math.BigDecimal unitPrice;  // 单价
    
    @Column(name = "total_price", nullable = false)
    private java.math.BigDecimal totalPrice; // 小计
    
    public OrderItem() {}
    
    public OrderItem(FoodOrder order, Dish dish, Integer quantity) {
        this.order = order;
        this.dish = dish;
        this.quantity = quantity;
        this.unitPrice = dish.getPrice();
        this.totalPrice = unitPrice.multiply(java.math.BigDecimal.valueOf(quantity));
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public FoodOrder getOrder() {
        return order;
    }
    
    public void setOrder(FoodOrder order) {
        this.order = order;
    }
    
    public Dish getDish() {
        return dish;
    }
    
    public void setDish(Dish dish) {
        this.dish = dish;
    }
    
    public Integer getQuantity() {
        return quantity;
    }
    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
        if (this.unitPrice != null) {
            this.totalPrice = this.unitPrice.multiply(java.math.BigDecimal.valueOf(quantity));
        }
    }
    
    public java.math.BigDecimal getUnitPrice() {
        return unitPrice;
    }
    
    public void setUnitPrice(java.math.BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
        if (this.quantity != null) {
            this.totalPrice = unitPrice.multiply(java.math.BigDecimal.valueOf(this.quantity));
        }
    }
    
    public java.math.BigDecimal getTotalPrice() {
        return totalPrice;
    }
    
    public void setTotalPrice(java.math.BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
