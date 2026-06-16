package com.example.travelappproject.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "FLIGHT")
public class FlightEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "hangBay")
    public String hangBay;

    @ColumnInfo(name = "logoHangBay")
    public String logoHangBay;

    @ColumnInfo(name = "diemDi")
    public String diemDi;

    @ColumnInfo(name = "diemDen")
    public String diemDen;

    @ColumnInfo(name = "sanBayDi")
    public String sanBayDi;

    @ColumnInfo(name = "sanBayDen")
    public String sanBayDen;

    // 4. Thời gian bay
    @ColumnInfo(name = "gioDi")
    public String gioDi;

    @ColumnInfo(name = "gioDen")
    public String gioDen;

    @ColumnInfo(name = "ngayDi")
    public String ngayDi;

    @ColumnInfo(name = "thoiGianBay")
    public String thoiGianBay;

    // 5. Giá tiền
    @ColumnInfo(name = "giaGoc")
    public String giaGoc;

    @ColumnInfo(name = "giaGiam")
    public String giaGiam;

    // 6. Tiện ích và Hạng ghế
    @ColumnInfo(name = "hanhLy")
    public String hanhLy;

    @ColumnInfo(name = "hangGhe")
    public String hangGhe;

    // Constructor
    public FlightEntity(String hangBay, String logoHangBay, String diemDi, String diemDen,
                        String sanBayDi, String sanBayDen, String gioDi, String gioDen,
                        String ngayDi, String thoiGianBay, String giaGoc, String giaGiam,
                        String hanhLy, String hangGhe) {
        this.hangBay = hangBay;
        this.logoHangBay = logoHangBay;
        this.diemDi = diemDi;
        this.diemDen = diemDen;
        this.sanBayDi = sanBayDi;
        this.sanBayDen = sanBayDen;
        this.gioDi = gioDi;
        this.gioDen = gioDen;
        this.ngayDi = ngayDi;
        this.thoiGianBay = thoiGianBay;
        this.giaGoc = giaGoc;
        this.giaGiam = giaGiam;
        this.hanhLy = hanhLy;
        this.hangGhe = hangGhe;
    }
}