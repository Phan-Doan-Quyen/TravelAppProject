package com.example.travelappproject.adapter.flight;

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
import com.example.travelappproject.database.FlightEntity;
import com.example.travelappproject.view.flight.plane_ChonChuyenBay_Activity;

import java.util.List;

public class flight_Search_Adapter extends RecyclerView.Adapter<flight_Search_Adapter.FlightViewHolder> {
    private Context context;
    private List<FlightEntity> flightList;

    public flight_Search_Adapter(Context context) {
        this.context = context;
    }

    public void setData(List<FlightEntity> list) {
        this.flightList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FlightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flight_search, parent, false);
        return new FlightViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlightViewHolder holder, int position) {
        FlightEntity flight = flightList.get(position);
        if (flight == null) return;

        holder.tvHangBay.setText(flight.hangBay);
        holder.tvThoiGianBay.setText(flight.thoiGianBay);
        holder.tvGioBay.setText(flight.gioDi + " - " + flight.gioDen);
        holder.tvGiaVe.setText(flight.giaGiam + " VND/Khách");

        // Gán tạm điểm đi/đến (VD: HAN, SGN)
        holder.tvSanBayDi.setText("SGN");
        holder.tvSanBayDen.setText("HAN");

        // Load ảnh logo
        int imageResId = context.getResources().getIdentifier(flight.logoHangBay, "drawable", context.getPackageName());
        if (imageResId != 0) holder.imgLogo.setImageResource(imageResId);

        // Click để sang màn hình chọn
        holder.layoutItem.setOnClickListener(v -> {
            Intent intent = new Intent(context, plane_ChonChuyenBay_Activity.class);
            intent.putExtra("FLIGHT_ID", flight.id);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        if (flightList != null) return flightList.size();
        return 0;
    }

    public class FlightViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layoutItem;
        ImageView imgLogo;
        TextView tvHangBay, tvThoiGianBay, tvGioBay, tvGiaVe, tvSanBayDi, tvSanBayDen;

        public FlightViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutItem = itemView.findViewById(R.id.layout_item_flight);
            imgLogo = itemView.findViewById(R.id.img_logo);
            tvHangBay = itemView.findViewById(R.id.tv_hang_bay);
            tvThoiGianBay = itemView.findViewById(R.id.tv_thoi_gian_bay);
            tvGioBay = itemView.findViewById(R.id.tv_gio_bay);
            tvGiaVe = itemView.findViewById(R.id.tv_gia_ve);
            tvSanBayDi = itemView.findViewById(R.id.tv_san_bay_di);
            tvSanBayDen = itemView.findViewById(R.id.tv_san_bay_den);
        }
    }
}