package com.example.nhatro247.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro247.Model.PhieuThu;
import com.example.nhatro247.R;
import com.example.nhatro247.fragments.ThongKeFragment;

import java.util.List;

public class ThongKeAdapter extends RecyclerView.Adapter<ThongKeAdapter.ThongKeViewHolder>{
    private List<PhieuThu> mListPhieu;
    private ThongKeFragment context;
    private static ItemClickListener itemClickListener;

    public ThongKeAdapter(List<PhieuThu> mListPhieu, ThongKeFragment context, ItemClickListener itemClickListener) {
        this.mListPhieu = mListPhieu;
        this.context = context;
        this.itemClickListener = itemClickListener;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ThongKeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thongke,parent,false);
       return new ThongKeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThongKeViewHolder holder, int position) {
        holder.txt_sophongTK.setText(String.valueOf(mListPhieu.get(position).getIdPhong()));
        holder.txt_sotienTK.setText(String.valueOf(mListPhieu.get(position).getTienThu()));
        holder.txt_tgThuTien.setText(mListPhieu.get(position).getTg_ThuTien());
        holder.txt_trangthaiPhieu.setText(mListPhieu.get(position).getTrangthaiphieu());
    }

    @Override
    public int getItemCount() {
        return mListPhieu.size();
    }

    public static class ThongKeViewHolder extends RecyclerView.ViewHolder{
        TextView txt_sophongTK,txt_sotienTK, txt_tgThuTien, txt_trangthaiPhieu;

        public ThongKeViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_sophongTK = itemView.findViewById(R.id.txt_sophongtk);
            txt_sotienTK = itemView.findViewById(R.id.txt_tienthang_tk);
            txt_tgThuTien = itemView.findViewById(R.id.txt_tg_thutien_tk);
            txt_trangthaiPhieu = itemView.findViewById(R.id.txt_trang_thai_phieuTk);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.OnItemClick(getAdapterPosition());
                }
            });
        }
    }
}
