package com.example.travelappproject.adapter.tour;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelappproject.R;
import com.example.travelappproject.database.TourEntity;
import com.example.travelappproject.view.tour.tour_ChiTiet_Activity;

import java.util.List;

public class tour_List_Adapter extends RecyclerView.Adapter<tour_List_Adapter.TourViewHolder> {

    private Context context;
    private List<TourEntity> tourList;

    public tour_List_Adapter(Context context) {
        this.context = context;
    }

    public void setData(List<TourEntity> tourList) {
        this.tourList = tourList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LƯU Ý: Bạn cần tạo một file layout XML tên là item_tour_list.xml (Thiết kế 1 ô chứa thông tin Tour)
        // Tạm thời mình gọi R.layout.item_tour_list, nếu tên file giao diện 1 item của bạn khác thì nhớ đổi lại nhé!
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tour_list, parent, false);
        return new TourViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TourViewHolder holder, int position) {
        TourEntity currentTour = tourList.get(position);
        if (currentTour == null) return;

        // Load text
        holder.tvTenTour.setText(currentTour.tenTour);
        holder.tvGiaSale.setText(currentTour.giaSale + " VND");
        holder.tvSoSao.setText(currentTour.soSao);
        holder.tvDiaChi.setText(currentTour.diaChi);

        // Load ảnh
        int imageResId = context.getResources().getIdentifier(currentTour.hinhAnh, "drawable", context.getPackageName());
        if (imageResId != 0) {
            holder.imgTour.setImageResource(imageResId);
        } else {
            holder.imgTour.setImageResource(R.drawable.anh1); // Ảnh mặc định nếu lỗi
        }

        // Bắt sự kiện click vào tour để chuyển sang màn hình Chi Tiết
        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, tour_ChiTiet_Activity.class);
                // Truyền ID sang màn hình chi tiết (để sau này query thông tin)
                intent.putExtra("TOUR_ID", currentTour.tourId);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (tourList != null) return tourList.size();
        return 0;
    }

    public class TourViewHolder extends RecyclerView.ViewHolder {
        ImageView imgTour;
        TextView tvTenTour, tvGiaSale, tvSoSao, tvDiaChi;
        LinearLayout layoutItem;

        public TourViewHolder(@NonNull View itemView) {
            super(itemView);
            // Sửa lại ID cho khớp với thiết kế item layout của bạn
            layoutItem = itemView.findViewById(R.id.layout_item_tour);
            imgTour = itemView.findViewById(R.id.img_tour);
            tvTenTour = itemView.findViewById(R.id.tv_ten_tour);
            tvGiaSale = itemView.findViewById(R.id.tv_gia_sale);
            tvSoSao = itemView.findViewById(R.id.tv_so_sao);
            tvDiaChi = itemView.findViewById(R.id.tv_dia_chi);
        }
    }
}