package com.example.travelappproject.adapter.hotel;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelappproject.R;
import com.example.travelappproject.view.hotel.hotel_MainRoom_Activity;

// IMPORT DB ENTITY THAY CHO MODEL GIẢ CŨ
import com.example.travelappproject.database.HotelEntity;

import java.util.List;

public class hotel_ChooseHotel_Hotel_Adapter extends RecyclerView.Adapter<hotel_ChooseHotel_Hotel_Adapter.CategoryViewHolder> {

    private Context context;

    // ĐỔI SANG DÙNG HotelEntity TỪ CSDL
    private List<HotelEntity> mCategory;

    public hotel_ChooseHotel_Hotel_Adapter(Context context) {
        this.context = context;
    }

    public void setData(List<HotelEntity> list){
        this.mCategory = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_item_chooseroom,parent,false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        HotelEntity category = mCategory.get(position);
        if(category == null){
            return;
        }

        // Đẩy dữ liệu thật từ DB lên giao diện
        holder.tvName.setText(category.tenKhachSan);
        holder.tvLocation.setText(category.thanhPho); // Bạn có thể đổi thành category.diaChi nếu muốn
        holder.tvPrice.setText(category.giaSale + " VND");

        // Load ảnh động từ tên ảnh lưu trong CSDL
        int imageResId = context.getResources().getIdentifier(category.hinhAnh, "drawable", context.getPackageName());
        if (imageResId != 0) {
            holder.imgHotel.setImageResource(imageResId);
        } else {
            holder.imgHotel.setImageResource(R.drawable.hotel_6); // Ảnh dự phòng nếu không tìm thấy
        }

        // Gắn cứng ảnh 5 sao tạm thời
        holder.imgFiveStar.setImageResource(R.drawable.rating);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, hotel_MainRoom_Activity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // ==========================================
            // ĐÂY LÀ DÒNG QUAN TRỌNG NHẤT: TRUYỀN ID KHÁCH SẠN ĐI
            // ==========================================
            intent.putExtra("HOTEL_ID", category.id);

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        if (mCategory != null){
            return mCategory.size();
        }
        return 0;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgHotel;
        private ImageView imgFiveStar;
        private TextView tvName;
        private TextView tvLocation;
        private TextView tvPrice;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHotel = itemView.findViewById(R.id.img_chooseHotel);
            imgFiveStar = itemView.findViewById(R.id.img_fiveStarChoose);
            tvName = itemView.findViewById(R.id.tv_nameChooseHotel);
            tvLocation = itemView.findViewById(R.id.tv_locationChoose);
            tvPrice = itemView.findViewById(R.id.tv_priceChoose);
        }
    }
}