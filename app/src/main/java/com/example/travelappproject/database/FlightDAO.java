package com.example.travelappproject.database;

import androidx.room.Dao;
import androidx.room.Query;
import java.util.List;

@Dao
public interface FlightDAO {

    // Lấy toàn bộ chuyến bay (Dùng cho lúc test DB)
    @Query("SELECT * FROM FLIGHT")
    List<FlightEntity> getAllFlights();

    // Lọc chuyến bay dựa trên Nơi đi, Nơi đến và Ngày bay
    @Query("SELECT * FROM FLIGHT WHERE diemDi LIKE '%' || :diemDi || '%' AND diemDen LIKE '%' || :diemDen || '%' AND ngayDi LIKE '%' || :ngayDi || '%'")
    List<FlightEntity> searchFlights(String diemDi, String diemDen, String ngayDi);

    // Lọc chuyến bay kèm theo Hạng ghế (Nâng cao hơn chút nếu người dùng chọn ghế)
    @Query("SELECT * FROM FLIGHT WHERE diemDi LIKE '%' || :diemDi || '%' AND diemDen LIKE '%' || :diemDen || '%' AND hangGhe = :hangGhe")
    List<FlightEntity> searchFlightsWithClass(String diemDi, String diemDen, String hangGhe);
}