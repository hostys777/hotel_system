package com.hotel.repository;

import com.hotel.model.ParkingSpot;
import com.hotel.model.ParkingSpotStatus;
import com.hotel.model.ParkingSpotType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {
    
    // 根据状态查找停车位
    List<ParkingSpot> findByStatus(ParkingSpotStatus status);
    
    // 根据类型查找停车位
    List<ParkingSpot> findBySpotType(ParkingSpotType spotType);
    
    // 根据编号查找停车位
    Optional<ParkingSpot> findBySpotNumber(String spotNumber);
    
    // 查找可用的停车位
    @Query("SELECT p FROM ParkingSpot p WHERE p.status = 'AVAILABLE' AND p.isReserved = false")
    List<ParkingSpot> findAvailableSpots();
    
    // 根据位置查找停车位
    List<ParkingSpot> findByLocationContaining(String location);
    
    // 查找特定类型的可用停车位
    @Query("SELECT p FROM ParkingSpot p WHERE p.spotType = ?1 AND p.status = 'AVAILABLE' AND p.isReserved = false")
    List<ParkingSpot> findAvailableSpotsByType(ParkingSpotType spotType);
    
    // 统计各状态的停车位数量
    @Query("SELECT p.status, COUNT(p) FROM ParkingSpot p GROUP BY p.status")
    List<Object[]> countByStatus();
    
    // 统计各类型的停车位数量
    @Query("SELECT p.spotType, COUNT(p) FROM ParkingSpot p GROUP BY p.spotType")
    List<Object[]> countByType();
}
