package com.example.travelappproject.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HotelDAO {

    @Query("SELECT * FROM HOTEL")
    List<HotelEntity> getAllHotels();

    @Query("SELECT * FROM HOTEL WHERE thanhPho = :selectedCity")
    List<HotelEntity> getHotelsByCity(String selectedCity);

    @Query("SELECT * FROM HOTEL WHERE soSao = :starRating")
    List<HotelEntity> getHotelsByStar(String starRating);

    @Query("SELECT * FROM HOTEL WHERE hotelId = :hId")
    HotelEntity getHotelById(int hId);

    @Insert
    void insert(HotelEntity hotel);
}