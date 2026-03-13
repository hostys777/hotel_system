package com.hotel.repository;

import com.hotel.model.Reservation;
import com.hotel.model.Customer;
import com.hotel.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 预订数据访问接口
 */
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByCustomer(Customer customer);
    List<Reservation> findByRoom(Room room);
    
    /**
     * 统计指定客户的预订数量
     */
    long countByCustomerId(Long customerId);
}
