package com.example.travelappproject.view.tour;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView; // Thêm import TextView

import androidx.appcompat.app.AppCompatActivity;

import com.example.travelappproject.R;

// IMPORT DATABASE
import com.example.travelappproject.database.AppDatabase;
import com.example.travelappproject.database.TourEntity;

public class tour_ChiTiet_Activity extends AppCompatActivity {

    // Khai báo các biến giao diện
    private ImageView imgTour;
    private TextView tvTenTour, tvSoSao, tvDiaChi, tvMoTa, tvGiaSale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_chitiet);

        // 1. Ánh xạ các View (Hãy đảm bảo ID khớp với file XML của bạn)
        imgTour = findViewById(R.id.img_chitiet_tour);
        tvTenTour = findViewById(R.id.tv_chitiet_tentour);
        tvSoSao = findViewById(R.id.tv_chitiet_sosao);
        tvDiaChi = findViewById(R.id.tv_chitiet_diachi);
        tvMoTa = findViewById(R.id.tv_chitiet_mota);
        tvGiaSale = findViewById(R.id.tv_chitiet_giasale);

        // 2. Load dữ liệu từ CSDL
        loadTourDataFromDatabase();

        // 3. Bắt sự kiện click vào từng layout
        setupClickListeners();
    }

    private void loadTourDataFromDatabase() {
        // Nhận ID từ màn hình danh sách truyền sang
        int currentTourId = getIntent().getIntExtra("TOUR_ID", -1);

        if (currentTourId != -1) {
            AppDatabase db = AppDatabase.getDatabase(this);
            AppDatabase.databaseWriteExecutor.execute(() -> {

                // Lấy Tour từ CSDL
                TourEntity currentTour = db.tourDAO().getTourById(currentTourId);

                if (currentTour != null) {
                    runOnUiThread(() -> {
                        // Đổ dữ liệu chữ lên giao diện
                        if(tvTenTour != null) tvTenTour.setText(currentTour.tenTour);
                        if(tvSoSao != null) tvSoSao.setText(String.valueOf(currentTour.soSao));

                        // Nối Địa chỉ và Thành phố lại với nhau
                        if(tvDiaChi != null) tvDiaChi.setText(currentTour.diaChi + ", Thành phố " + currentTour.thanhPho + ", Việt Nam");

                        if(tvMoTa != null) tvMoTa.setText(currentTour.mota);
                        if(tvGiaSale != null) tvGiaSale.setText(currentTour.giaSale + "đ");

                        // Load ảnh động
                        if(imgTour != null) {
                            int imageResId = getResources().getIdentifier(currentTour.hinhAnh, "drawable", getPackageName());
                            if (imageResId != 0) {
                                imgTour.setImageResource(imageResId);
                            } else {
                                imgTour.setImageResource(R.drawable.anh1); // Ảnh mặc định nếu lỗi
                            }
                        }
                    });
                }
            });
        }
    }

    // Phương thức để set sự kiện click cho các layout (GIỮ NGUYÊN)
    private void setupClickListeners() {
        LinearLayout firstProduct = findViewById(R.id.first_product_chitiet);
        LinearLayout secondProduct = findViewById(R.id.second_product_chitiet);
        LinearLayout thirdProduct = findViewById(R.id.third_product_chitiet);

        if(firstProduct != null) {
            firstProduct.setOnClickListener(v -> openDetailActivity());
        }

        if(secondProduct != null) {
            secondProduct.setOnClickListener(v -> openDetailActivity());
        }

        final ScrollView scrollView = findViewById(R.id.scrollView);
        if(thirdProduct != null && scrollView != null && firstProduct != null) {
            thirdProduct.setOnClickListener(v -> scrollView.smoothScrollTo(0, firstProduct.getTop()));
        }

        ImageView backButton = findViewById(R.id.back_button);
        if(backButton != null) {
            backButton.setOnClickListener(v -> openDatChoActivity());
        }
    }

    private void openDatChoActivity() {
        Intent intent = new Intent(tour_ChiTiet_Activity.this, tour_CoTheBanSeThich_Activity.class);
        startActivity(intent);
    }

    private void openDetailActivity() {
        Intent intent = new Intent(tour_ChiTiet_Activity.this, tour_DatVe_Activity.class);
        startActivity(intent);
    }
}