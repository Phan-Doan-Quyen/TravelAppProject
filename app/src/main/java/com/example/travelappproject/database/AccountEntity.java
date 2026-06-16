package com.example.travelappproject.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ACCOUNT")
public class AccountEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "AccountId")
    public int accountId;

    @ColumnInfo(name = "HoTen")
    public String hoTen;

    @ColumnInfo(name = "Email")
    public String email;

    @ColumnInfo(name = "MatKhau")
    public String matKhau;

    public AccountEntity(String hoTen, String email, String matKhau) {
        this.hoTen = hoTen;
        this.email = email;
        this.matKhau = matKhau;
    }
}