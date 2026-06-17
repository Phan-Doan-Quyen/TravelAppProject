package com.example.travelappproject.view.hotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;

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
import com.google.android.material.bottomnavigation.BottomNavigationView;

// IMPORT DATABASE
import com.example.travelappproject.database.AppDatabase;
import com.example.travelappproject.database.RoomEntity;

public class hotel_MainRoom_Activity extends AppCompatActivity {

    private hotel_Category_Adapter categoryAdapter;
    private Spinner spinner;
    private RecyclerView recyclerView;
    private hotel_CgrChooseRoomAdapter_Room chooseRoomAdapter;
    private ImageView imageView;

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

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        bottomNavigationView.setSelectedItemId(R.id.action_hotel);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if (item.getItemId() == R.id.action_home) {
                    Intent intent = new Intent(hotel_MainRoom_Activity.this, hotel_MainHome_Activity.class);
                    startActivity(intent);
                    return true;
                }

                else if (item.getItemId() == R.id.action_hotel) {
                    // Nếu là mục Hotel (hoặc hiện tại là Hotel), không cần chuyển activity
                    return true;
                }

                else if (item.getItemId() == R.id.action_plane) {
                    Intent intent = new Intent(hotel_MainRoom_Activity.this, plane_VeMayBay_Activity.class);
                    startActivity(intent);
                    return true;
                }

                else if (item.getItemId() == R.id.action_tour) {
                    Intent intent = new Intent(hotel_MainRoom_Activity.this, Taikhoan.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });

        //Spinner
        spinner = findViewById(R.id.spn_selectedPerson);
        categoryAdapter = new hotel_Category_Adapter(this, R.layout.hotel_item_searchhotel, getListCategory());
        spinner.setAdapter(categoryAdapter);

        //Recycleview
        recyclerView = findViewById(R.id.rcv_chooseRoom);
        chooseRoomAdapter = new hotel_CgrChooseRoomAdapter_Room(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        // ==========================================
        // LẤY DỮ LIỆU PHÒNG TỪ CSDL (BẢNG ROOM)
        // ==========================================
        AppDatabase db = AppDatabase.getDatabase(this);

        // Tạm thời lấy danh sách phòng của khách sạn số 1 (sau này bạn có thể truyền ID khách sạn từ màn hình trước sang)
        int currentHotelId = getIntent().getIntExtra("HOTEL_ID", 1);
        List<RoomEntity> dbRooms = db.roomDAO().getRoomsByHotelId(currentHotelId);

        List<hotel_CgrChooseRoom_Room_Model> adapterRoomList = new ArrayList<>();

        for (RoomEntity r : dbRooms) {
            // Lấy ID của ảnh từ tên ảnh lưu trong CSDL
            int imageResId = getResources().getIdentifier(r.hinhAnh, "drawable", getPackageName());
            if (imageResId == 0) imageResId = R.drawable.room1; // Ảnh mặc định nếu lỗi tên file

            // Nạp dữ liệu thật vào Model để View hiển thị
            adapterRoomList.add(new hotel_CgrChooseRoom_Room_Model(
                    R.drawable.air_conditioner1,
                    R.drawable.bed3,
                    R.drawable.wifi1,
                    imageResId,
                    "Máy lạnh",
                    r.tenPhong,
                    r.soNguoi + " người",
                    r.giaTien + " VND",
                    "Wifi"
            ));
        }

        // Truyền danh sách thật vào Adapter
        chooseRoomAdapter.setData(adapterRoomList);
        recyclerView.setAdapter(chooseRoomAdapter);
        // ==========================================

        //Image
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