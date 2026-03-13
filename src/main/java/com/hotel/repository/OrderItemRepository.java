package com.hotel.repository;

import com.hotel.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单项数据访问接口
 */
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    
    /**
     * 根据订单ID查找订单项
     */
    List<OrderItem> findByOrderId(Long orderId);
    
    /**
     * 根据菜品ID查找订单项
     */
    List<OrderItem> findByDishId(Long dishId);
}
