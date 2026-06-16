package com.example.travelappproject.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface AccountDAO {
    // Dùng để Đăng ký tài khoản
    @Insert
    void insertAccount(AccountEntity account);

    // Dùng để Đăng nhập (Kiểm tra xem có tài khoản nào khớp email và mật khẩu không)
    @Query("SELECT * FROM ACCOUNT WHERE Email = :emailInput AND MatKhau = :passInput")
    AccountEntity checkLogin(String emailInput, String passInput);
}