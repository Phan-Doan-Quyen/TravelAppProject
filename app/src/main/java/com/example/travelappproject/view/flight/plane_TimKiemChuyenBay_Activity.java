package com.example.travelappproject.view.flight;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelappproject.R;
// IMPORT DATABASE & ADAPTER
import com.example.travelappproject.database.AppDatabase;
import com.example.travelappproject.database.FlightEntity;
import com.example.travelappproject.adapter.flight.flight_Search_Adapter;

import com.example.travelappproject.view.hotel.Taikhoan;
import com.example.travelappproject.view.hotel.hotel_MainHome_Activity;
import com.example.travelappproject.view.hotel.hotel_MainHotel_Activity;
import com.example.travelappproject.view.hotel.hotel_MainInf_Activity;
import com.example.travelappproject.view.tour.tour_Tour_Activity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class plane_TimKiemChuyenBay_Activity extends AppCompatActivity {

    Button Exit;
    private RecyclerView rcvFlightSearch;
    private flight_Search_Adapter flightAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        // LƯU Ý: Tạm thời bạn cứ giữ nguyên file XML cũ, mình đã chèn RecyclerView qua Code Java ở dưới
        setContentView(R.layout.plane_timkiemvemaybay);

        Exit = findViewById(R.id.exit);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.action_plane);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.action_home) {
                startActivity(new Intent(this, hotel_MainHome_Activity.class));
                return true;
            } else if (item.getItemId() == R.id.action_hotel) {
                startActivity(new Intent(this, hotel_MainHotel_Activity.class));
                return true;
            } else if (item.getItemId() == R.id.action_plane) {
                startActivity(new Intent(this, plane_VeMayBay_Activity.class));
                return true;
            } else if (item.getItemId() == R.id.action_tour) {
                startActivity(new Intent(this, Taikhoan.class));
                return true;
            }
            else if (item.getItemId() == R.id.action_real_tour) {
                startActivity(new Intent(this, tour_Tour_Activity.class));
                return true;
            }
            return false;
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main1), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Exit.setOnClickListener(v -> {
            startActivity(new Intent(this, plane_VeMayBay_Activity.class));
        });

        // ==========================================
        // TÌM CÁI LINEAR LAYOUT MÀU XANH (#D0F0F4) ĐỂ GẮN RECYCLERVIEW VÀO BẰNG CODE
        // ==========================================
        // Lấy cái LinearLayout đang chứa các chuyến bay tĩnh (nằm dưới thanh ngày)
        android.widget.LinearLayout listContainer = findViewById(R.id.chonbay);
        if (listContainer != null && listContainer.getParent() instanceof android.widget.LinearLayout) {
            android.widget.LinearLayout parentLayout = (android.widget.LinearLayout) listContainer.getParent();

            // Xóa hết các view tĩnh cũ
            parentLayout.removeAllViews();

            // Tạo RecyclerView mới gắn vào
            rcvFlightSearch = new RecyclerView(this);
            rcvFlightSearch.setLayoutParams(new android.widget.LinearLayout.LayoutParams(
                    android.widget.LinearLayout.LayoutParams.MATCH_PARENT,
                    android.widget.LinearLayout.LayoutParams.WRAP_CONTENT));

            parentLayout.addView(rcvFlightSearch);

            // Nạp Adapter
            flightAdapter = new flight_Search_Adapter(this);
            rcvFlightSearch.setLayoutManager(new LinearLayoutManager(this));

            AppDatabase db = AppDatabase.getDatabase(this);
            List<FlightEntity> dbFlights = db.flightDAO().getAllFlights();

            flightAdapter.setData(dbFlights);
            rcvFlightSearch.setAdapter(flightAdapter);
        }
    }
}