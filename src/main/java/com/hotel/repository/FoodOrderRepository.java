package com.hotel.repository;

import com.hotel.model.FoodOrder;
import com.hotel.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 餐饮订单数据访问接口
 */
@Repository
public interface FoodOrderRepository extends JpaRepository<FoodOrder, Long> {
    
    /**
     * 根据客户查找订单
     */
    List<FoodOrder> findByCustomerId(Long customerId);
    
    /**
     * 根据服务员查找订单
     */
    List<FoodOrder> findByEmployeeId(Long employeeId);
    
    /**
     * 根据桌号查找订单
     */
    List<FoodOrder> findByTableNumber(String tableNumber);
    
    /**
     * 根据状态查找订单
     */
    List<FoodOrder> findByStatus(OrderStatus status);
    
    /**
     * 根据创建时间范围查找订单
     */
    List<FoodOrder> findByCreateTimeBetween(LocalDateTime startTime, LocalDateTime endTime);
    
    /**
     * 根据客户和状态查找订单
     */
    List<FoodOrder> findByCustomerIdAndStatus(Long customerId, OrderStatus status);
    
    /**
     * 统计指定客户的订单数量
     */
    long countByCustomerId(Long customerId);
    
    /**
     * 统计指定员工的订单数量
     */
    long countByEmployeeId(Long employeeId);
}
