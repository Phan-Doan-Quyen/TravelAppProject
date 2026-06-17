package com.example.travelappproject.view.flight;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast; // THÊM THƯ VIỆN TOAST

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.travelappproject.R;
import com.example.travelappproject.view.hotel.hotel_MainHome_Activity;
import com.google.android.material.button.MaterialButton;

// THÊM THƯ VIỆN CSDL
import com.example.travelappproject.database.AppDatabase;
import com.example.travelappproject.database.AccountEntity;

public class plane_LogIn_Activity extends AppCompatActivity {
    MaterialButton btn;
    EditText editTextEmail ;
    EditText editTextPassword;
    ImageView check;
    TextView account;
    boolean isChecked = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plane_login);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.EditTextPassword);
        check=findViewById(R.id.check_remember);
        btn = findViewById(R.id.btn_login);
        account=findViewById(R.id.new_account);

        // Cập nhật padding cho View khi có Window Insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main0), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isChecked==false){
                    check.setBackgroundResource(R.drawable.checkbox_with_check);
                    isChecked=true;
                }
                else if(isChecked==true){
                    check.setBackgroundResource(R.drawable.border);
                    isChecked=false;
                }
            }
        });

        editTextEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    editTextEmail.setText("");
                    editTextEmail.setHintTextColor(getResources().getColor(R.color.white));
                }
            }
        });

        editTextPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    editTextPassword.setHint("");
                }
            }
        });

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(plane_LogIn_Activity.this, plane_SignIn_Activity.class);
                startActivity(m);
            }
        });

        // ==========================================
        // KHỞI TẠO BUTTON VÀ XỬ LÝ ĐĂNG NHẬP VỚI CSDL
        // ==========================================
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailInput = editTextEmail.getText().toString().trim();
                String passInput = editTextPassword.getText().toString().trim();

                if (emailInput.isEmpty() || passInput.isEmpty()) {
                    Toast.makeText(plane_LogIn_Activity.this, "Vui lòng nhập đầy đủ Email và Mật khẩu", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Gọi CSDL kiểm tra
                AppDatabase db = AppDatabase.getDatabase(plane_LogIn_Activity.this);
                AccountEntity user = db.accountDAO().checkLogin(emailInput, passInput);

                if (user != null) {
                    Toast.makeText(plane_LogIn_Activity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                    Intent m = new Intent(plane_LogIn_Activity.this, hotel_MainHome_Activity.class);
                    startActivity(m);
                    finish();
                } else {
                    Toast.makeText(plane_LogIn_Activity.this, "Sai email hoặc mật khẩu!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}