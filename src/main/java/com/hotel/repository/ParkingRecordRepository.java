package com.hotel.repository;

import com.hotel.model.ParkingRecord;
import com.hotel.model.ParkingRecordStatus;
import com.hotel.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingRecordRepository extends JpaRepository<ParkingRecord, Long> {
    
    // 根据客户查找停车记录
    List<ParkingRecord> findByCustomer(Customer customer);
    
    // 根据状态查找停车记录
    List<ParkingRecord> findByStatus(ParkingRecordStatus status);
    
    // 根据车牌号查找停车记录
    List<ParkingRecord> findByLicensePlate(String licensePlate);
    
    // 查找进行中的停车记录
    @Query("SELECT p FROM ParkingRecord p WHERE p.status = 'ACTIVE'")
    List<ParkingRecord> findActiveRecords();
    
    // 根据时间范围查找停车记录
    List<ParkingRecord> findByEntryTimeBetween(LocalDateTime startTime, LocalDateTime endTime);
    
    // 查找特定客户的进行中记录
    @Query("SELECT p FROM ParkingRecord p WHERE p.customer = ?1 AND p.status = 'ACTIVE'")
    Optional<ParkingRecord> findActiveRecordByCustomer(Customer customer);
    
    // 根据停车位查找进行中的记录
    @Query("SELECT p FROM ParkingRecord p WHERE p.parkingSpot.id = ?1 AND p.status = 'ACTIVE'")
    Optional<ParkingRecord> findActiveRecordBySpot(Long spotId);
    
    // 统计今日停车记录数
    @Query("SELECT COUNT(p) FROM ParkingRecord p WHERE p.entryTime >= ?1 AND p.entryTime < ?2")
    Long countTodayRecords(LocalDateTime startOfDay, LocalDateTime endOfDay);
    
    // 统计今日收入
    @Query("SELECT COALESCE(SUM(p.totalFee), 0) FROM ParkingRecord p WHERE p.entryTime >= ?1 AND p.entryTime < ?2 AND p.status = 'COMPLETED'")
    Double getTodayRevenue(LocalDateTime startOfDay, LocalDateTime endOfDay);
    
    // 查找超时记录
    @Query("SELECT p FROM ParkingRecord p WHERE p.status = 'ACTIVE' AND p.entryTime < ?1")
    List<ParkingRecord> findOverdueRecords(LocalDateTime cutoffTime);
    
    /**
     * 统计指定客户的停车记录数量
     */
    long countByCustomerId(Long customerId);
}
