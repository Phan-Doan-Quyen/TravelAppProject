package com.example.travelappproject.view.hotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView; // Make sure to import TextView

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.example.travelappproject.R;
import com.example.travelappproject.model.hotel.hotel_Category_Model;
import com.example.travelappproject.adapter.hotel.hotel_Category_Adapter;
import com.example.travelappproject.model.hotel.hotel_CgrChooseRoom_Room_Model;
import com.example.travelappproject.adapter.hotel.hotel_CgrChooseRoomAdapter_Room;
import com.example.travelappproject.view.flight.plane_VeMayBay_Activity;
import com.example.travelappproject.view.tour.tour_Tour_Activity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

// IMPORT DATABASE
import com.example.travelappproject.database.AppDatabase;
import com.example.travelappproject.database.RoomEntity;
import com.example.travelappproject.database.HotelEntity; // Need this to hold the hotel data

public class hotel_MainRoom_Activity extends AppCompatActivity {

    private hotel_Category_Adapter categoryAdapter;
    private Spinner spinner;
    private RecyclerView recyclerView;
    private hotel_CgrChooseRoomAdapter_Room chooseRoomAdapter;
    private ImageView imageView;

    // View variables for Hotel Info
    private TextView tvHotelName;
    private TextView tvHotelDesc;
    private TextView tvHotelAddress;
    private ImageView imgHotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.hotel_activity_main_room);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_chooseRoom), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. Initialize Hotel Info Views
        tvHotelName = findViewById(R.id.tv_room_hotelName);
        tvHotelDesc = findViewById(R.id.tv_room_hotelDesc);
        tvHotelAddress = findViewById(R.id.tv_room_hotelAddress);
        imgHotel = findViewById(R.id.img_room_hotel);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        bottomNavigationView.setSelectedItemId(R.id.action_hotel);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                // ... (Keep your existing BottomNav code) ...
                if (item.getItemId() == R.id.action_home) {
                    Intent intent = new Intent(hotel_MainRoom_Activity.this, hotel_MainHome_Activity.class);
                    startActivity(intent);
                    return true;
                } else if (item.getItemId() == R.id.action_hotel) {
                    return true;
                } else if (item.getItemId() == R.id.action_plane) {
                    Intent intent = new Intent(hotel_MainRoom_Activity.this, plane_VeMayBay_Activity.class);
                    startActivity(intent);
                    return true;
                } else if (item.getItemId() == R.id.action_tour) {
                    Intent intent = new Intent(hotel_MainRoom_Activity.this, Taikhoan.class);
                    startActivity(intent);
                    return true;
                }
                else if (item.getItemId() == R.id.action_real_tour) {
                    // Import class tour_Tour_Activity nếu máy báo đỏ nhé
                    Intent intent = new Intent(hotel_MainRoom_Activity.this, tour_Tour_Activity.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });

        // Spinner
        spinner = findViewById(R.id.spn_selectedPerson);
        categoryAdapter = new hotel_Category_Adapter(this, R.layout.hotel_item_searchhotel, getListCategory());
        spinner.setAdapter(categoryAdapter);

        // RecyclerView
        recyclerView = findViewById(R.id.rcv_chooseRoom);
        chooseRoomAdapter = new hotel_CgrChooseRoomAdapter_Room(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        // ==========================================
        // DATABASE OPERATIONS (HOTEL INFO & ROOMS)
        // ==========================================
        AppDatabase db = AppDatabase.getDatabase(this);

        // Lấy ID khách sạn từ màn hình trước truyền sang
        int currentHotelId = getIntent().getIntExtra("HOTEL_ID", -1);

        if (currentHotelId != -1) {
            // --- PHẦN 1: LOAD DỮ LIỆU LÚC VỪA MỞ MÀN HÌNH (Bạn đã lỡ xóa phần này) ---
            AppDatabase.databaseWriteExecutor.execute(() -> {
                // Lấy thông tin khách sạn
                HotelEntity currentHotel = db.hotelDAO().getHotelById(currentHotelId);
                // Lấy toàn bộ danh sách phòng của khách sạn đó
                List<RoomEntity> dbRooms = db.roomDAO().getRoomsByHotelId(currentHotelId);

                List<hotel_CgrChooseRoom_Room_Model> initialRoomList = new ArrayList<>();
                for (RoomEntity r : dbRooms) {
                    int imageResId = getResources().getIdentifier(r.hinhAnh, "drawable", getPackageName());
                    if (imageResId == 0) imageResId = R.drawable.room1;

                    initialRoomList.add(new hotel_CgrChooseRoom_Room_Model(
                            R.drawable.air_conditioner1, R.drawable.bed3, R.drawable.wifi1,
                            imageResId, "Máy lạnh", r.tenPhong, r.soNguoi + " người", r.giaTien + " VND", "Wifi"
                    ));
                }

                // Cập nhật giao diện lần đầu (Luồng chính)
                runOnUiThread(() -> {
                    // Hiện thông tin khách sạn
                    if (currentHotel != null) {
                        tvHotelName.setText(currentHotel.tenKhachSan);
                        if(currentHotel.moTa != null) tvHotelDesc.setText(currentHotel.moTa);
                        if(currentHotel.diaChi != null) tvHotelAddress.setText(currentHotel.diaChi);

                        int hotelImageResId = getResources().getIdentifier(currentHotel.hinhAnh, "drawable", getPackageName());
                        if(hotelImageResId != 0) {
                            imgHotel.setImageResource(hotelImageResId);
                        } else {
                            imgHotel.setImageResource(R.drawable.hotel_6);
                        }
                    }
                    // Hiện toàn bộ phòng
                    chooseRoomAdapter.setData(initialRoomList);
                    recyclerView.setAdapter(chooseRoomAdapter);
                });
            });

            // --- PHẦN 2: SỰ KIỆN NÚT "THAY ĐỔI" LỌC PHÒNG (Code mới của bạn) ---
            android.widget.Button btnChangePerson = findViewById(R.id.btn_changePerson);

            btnChangePerson.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hotel_Category_Model selectedItem = (hotel_Category_Model) spinner.getSelectedItem();
                    String selectedText = selectedItem.getName();
                    String capacityNumber = selectedText.replace(" người", "").trim();

                    AppDatabase.databaseWriteExecutor.execute(() -> {
                        // Gọi DAO để lọc phòng
                        List<RoomEntity> filteredRooms = db.roomDAO().getRoomsByCapacity(currentHotelId, capacityNumber);

                        List<hotel_CgrChooseRoom_Room_Model> adapterRoomList = new ArrayList<>();
                        for (RoomEntity r : filteredRooms) {
                            int imageResId = getResources().getIdentifier(r.hinhAnh, "drawable", getPackageName());
                            if (imageResId == 0) imageResId = R.drawable.room1;

                            adapterRoomList.add(new hotel_CgrChooseRoom_Room_Model(
                                    R.drawable.air_conditioner1, R.drawable.bed3, R.drawable.wifi1,
                                    imageResId, "Máy lạnh", r.tenPhong, r.soNguoi + " người", r.giaTien + " VND", "Wifi"
                            ));
                        }

                        // Cập nhật lại danh sách phòng (Luồng chính)
                        runOnUiThread(() -> {
                            chooseRoomAdapter.setData(adapterRoomList);

                            if (filteredRooms.isEmpty()) {
                                android.widget.Toast.makeText(hotel_MainRoom_Activity.this,
                                        "Khách sạn không có phòng cho " + selectedText,
                                        android.widget.Toast.LENGTH_SHORT).show();
                            }
                        });
                    });
                }
            });
        }
        // ==========================================
        // ==========================================

        // Image (Back button)
        imageView = findViewById(R.id.img_back_room);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(hotel_MainRoom_Activity.this, hotel_MainHotel_Activity.class);
                startActivity(intent);
            }
        });
    }

    private List<hotel_Category_Model> getListCategory() {
        List<hotel_Category_Model> list = new ArrayList<>();
        list.add(new hotel_Category_Model("1 người"));
        list.add(new hotel_Category_Model("2 người"));
        list.add(new hotel_Category_Model("3 người"));
        list.add(new hotel_Category_Model("4 người"));
        return list;
    }
}