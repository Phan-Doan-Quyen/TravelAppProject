package com.example.travelappproject.view.flight;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast; // THÊM THƯ VIỆN TOAST
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.travelappproject.R;
import com.google.android.material.button.MaterialButton;

// THÊM THƯ VIỆN CSDL
import com.example.travelappproject.database.AppDatabase;
import com.example.travelappproject.database.AccountEntity;

public class plane_SignIn_Activity extends AppCompatActivity {
    MaterialButton btn;
    EditText firtname;
    EditText email;
    EditText password;
    EditText password_confirm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plane_singin);

        btn=findViewById(R.id.btn_singin);
        firtname=findViewById(R.id.Firt_name);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        password_confirm=findViewById(R.id.password_confirm);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(plane_SignIn_Activity.this);
                builder.setTitle("Thông báo")
                        .setMessage("Bạn có chắc chắn muốn tạo tài khoản không ?")
                        .setNegativeButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // ==========================================
                                // XỬ LÝ LƯU TÀI KHOẢN VÀO CSDL KHI BẤM "CÓ"
                                // ==========================================
                                String name = firtname.getText().toString().trim();
                                String mail = email.getText().toString().trim();
                                String pass = password.getText().toString().trim();
                                String passConfirm = password_confirm.getText().toString().trim();

                                // Kiểm tra trống
                                if (name.isEmpty() || mail.isEmpty() || pass.isEmpty() || passConfirm.isEmpty()) {
                                    Toast.makeText(plane_SignIn_Activity.this, "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                // Kiểm tra mật khẩu khớp
                                if (!pass.equals(passConfirm)) {
                                    Toast.makeText(plane_SignIn_Activity.this, "Mật khẩu xác nhận không khớp!", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                // Thêm vào CSDL
                                AppDatabase db = AppDatabase.getDatabase(plane_SignIn_Activity.this);
                                AccountEntity newAccount = new AccountEntity(name, mail, pass);
                                db.accountDAO().insertAccount(newAccount);

                                Toast.makeText(plane_SignIn_Activity.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();

                                Intent m = new Intent(plane_SignIn_Activity.this, plane_LogIn_Activity.class);
                                startActivity(m);
                                finish();
                            }
                        })
                        .setPositiveButton("Không", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setCancelable(false);

                AlertDialog dialog =builder.create();
                dialog.show();
            }
        });

        firtname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    firtname.setText("");
                    firtname.setHintTextColor(getResources().getColor(R.color.white));
                }
            }
        });

        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    email.setText("");
                    email.setHintTextColor(getResources().getColor(R.color.white));
                }
            }
        });

        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    password.setHint("");
                    password.setHintTextColor(getResources().getColor(R.color.white));
                }
            }
        });

        password_confirm.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    password_confirm.setHint("");
                    password_confirm.setHintTextColor(getResources().getColor(R.color.white));
                }
            }
        });
        TextView tvChuyenDangNhap = findViewById(R.id.tv_chuyen_sang_dang_nhap);
        tvChuyenDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(plane_SignIn_Activity.this, plane_LogIn_Activity.class);
                startActivity(intent);
                finish(); // Tắt màn hình đăng ký hiện tại
            }
        });
    }
}