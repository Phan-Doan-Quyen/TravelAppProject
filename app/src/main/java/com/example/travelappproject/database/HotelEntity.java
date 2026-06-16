package com.example.travelappproject.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// Tên bảng trong ảnh đã đổi thành HOTEL
@Entity(tableName = "HOTEL")
public class HotelEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "hotelId")
    public int id;

    @ColumnInfo(name = "tenKhachSan")
    public String tenKhachSan;

    @ColumnInfo(name = "hinhAnh")
    public String hinhAnh;

    @ColumnInfo(name = "giaSale")
    public String giaSale;

    @ColumnInfo(name = "moTa")
    public String moTa;

    @ColumnInfo(name = "diaChi")
    public String diaChi;

    @ColumnInfo(name = "soSao")
    public String soSao;

    @ColumnInfo(name = "thanhPho")
    public String thanhPho;

    // Constructor đã thêm đầy đủ các trường mới
    public HotelEntity(String tenKhachSan, String hinhAnh, String giaSale,
                       String moTa, String diaChi, String soSao, String thanhPho) {
        this.tenKhachSan = tenKhachSan;
        this.hinhAnh = hinhAnh;
        this.giaSale = giaSale;
        this.moTa = moTa;
        this.diaChi = diaChi;
        this.soSao = soSao;
        this.thanhPho = thanhPho;
    }
}