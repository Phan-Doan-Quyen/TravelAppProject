package com.example.travelappproject.database;

import androidx.room.Dao;
import androidx.room.Query;
import java.util.List;

@Dao
public interface TourDAO {
    // Lấy tất cả Tour (cho màn hình danh sách ban đầu)
    @Query("SELECT * FROM TOUR")
    List<TourEntity> getAllTours();

    // Lọc Tour theo Thành phố (Ví dụ: bấm vào ô Đà Nẵng)
    @Query("SELECT * FROM TOUR WHERE ThanhPho = :city")
    List<TourEntity> getToursByCity(String city);

    // Lọc Tour theo Danh mục (Ví dụ: bấm vào ô Sân chơi)
    @Query("SELECT * FROM TOUR WHERE DanhMuc = :category")
    List<TourEntity> getToursByCategory(String category);

    // Lọc Tour kết hợp cả 2 (Nếu người dùng chọn cả Thành phố và Danh mục)
    @Query("SELECT * FROM TOUR WHERE ThanhPho = :city AND DanhMuc = :category")
    List<TourEntity> getToursByFilter(String city, String category);
}