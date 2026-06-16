package com.example.travelappproject.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HotelDAO {

    @Query("SELECT * FROM HOTEL")
    List<HotelEntity> getAllHotels();

    // Sửa KHACHSAN thành HOTEL (Nếu bạn có dùng hàm lọc theo thành phố)
    @Query("SELECT * FROM HOTEL WHERE thanhPho = :selectedCity")
    List<HotelEntity> getHotelsByCity(String selectedCity);

    @Insert
    void insert(HotelEntity hotel);
}