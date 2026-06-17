package com.example.travelappproject.database;

import androidx.room.Dao;
import androidx.room.Query;
import java.util.List;

@Dao
public interface RoomDAO {
    // Lọc ra tất cả các phòng thuộc về 1 khách sạn cụ thể
    @Query("SELECT * FROM ROOM WHERE hotelId = :hId")
    List<RoomEntity> getRoomsByHotelId(int hId);

    @Query("SELECT * FROM ROOM WHERE hotelId = :hId AND soNguoi = :capacity")
    List<RoomEntity> getRoomsByCapacity(int hId, String capacity);
}