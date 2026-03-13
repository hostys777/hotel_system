package com.hotel.repository;

import com.hotel.model.Room;
import com.hotel.model.RoomStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 房间数据访问接口
 */
@Repository
public interface RoomRepository extends JpaRepository<Room, String> {
    List<Room> findByStatus(RoomStatus status);
}
