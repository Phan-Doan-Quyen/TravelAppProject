package com.example.travelappproject.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "ROOM",
        foreignKeys = @ForeignKey(
                entity = HotelEntity.class,
                parentColumns = "hotelId",
                childColumns = "hotelId",
                onDelete = ForeignKey.CASCADE
        ),
        indices = {@Index("hotelId")}
)
public class RoomEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "roomid")
    public int id;

    @ColumnInfo(name = "tenPhong")
    public String tenPhong;

    @ColumnInfo(name = "soNguoi")
    public String soNguoi;

    @ColumnInfo(name = "giaTien")
    public String giaTien;

    @ColumnInfo(name = "hinhAnh")
    public String hinhAnh;

    @ColumnInfo(name = "hotelId")
    public int hotelId;

    public RoomEntity(String tenPhong, String soNguoi, String giaTien, String hinhAnh, int hotelId) {
        this.tenPhong = tenPhong;
        this.soNguoi = soNguoi;
        this.giaTien = giaTien;
        this.hinhAnh = hinhAnh;
        this.hotelId = hotelId;
    }
}