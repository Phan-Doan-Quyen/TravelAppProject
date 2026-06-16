package com.example.travelappproject.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "TOUR")
public class TourEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "TourId")
    public int tourId;

    @ColumnInfo(name = "TenTour")
    public String tenTour;

    @ColumnInfo(name = "DiaChi")
    public String diaChi;

    @ColumnInfo(name = "ThanhPho")
    public String thanhPho;

    @ColumnInfo(name = "DanhMuc")
    public String danhMuc;

    @ColumnInfo(name = "SoNguoi")
    public String soNguoi;

    @ColumnInfo(name = "GiaTien")
    public String giaTien;

    @ColumnInfo(name = "GiaSale")
    public String giaSale;

    @ColumnInfo(name = "SoSao")
    public String soSao;

    @ColumnInfo(name = "Time")
    public String time;

    @ColumnInfo(name = "Mota")
    public String mota;

    // Cột bổ sung để load ảnh lên giao diện
    @ColumnInfo(name = "HinhAnh")
    public String hinhAnh;

    public TourEntity(String tenTour, String diaChi, String thanhPho, String danhMuc,
                      String soNguoi, String giaTien, String giaSale, String soSao,
                      String time, String mota, String hinhAnh) {
        this.tenTour = tenTour;
        this.diaChi = diaChi;
        this.thanhPho = thanhPho;
        this.danhMuc = danhMuc;
        this.soNguoi = soNguoi;
        this.giaTien = giaTien;
        this.giaSale = giaSale;
        this.soSao = soSao;
        this.time = time;
        this.mota = mota;
        this.hinhAnh = hinhAnh;
    }
}