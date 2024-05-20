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
import com.example.nhatro247.fragments.LapPhieuFragment;

import java.util.List;

public class PhieuAdapter extends RecyclerView.Adapter<PhieuAdapter.PhieuViewHolder>{
    private List<PhieuThu> mListPhieu;
    private LapPhieuFragment context;

    private static ItemClickListener itemClickListener;

    public PhieuAdapter(List<PhieuThu> mListPhieu, LapPhieuFragment context, ItemClickListener itemClickListener) {
        this.mListPhieu = mListPhieu;
        this.context = context;
        this.itemClickListener = itemClickListener;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PhieuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phieu,parent,false);
        return new PhieuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhieuViewHolder holder, int position) {
        holder.txt_sophong.setText(String.valueOf(mListPhieu.get(position).getIdPhong()));
        holder.txt_tienthang.setText(String.valueOf(mListPhieu.get(position).getTienThu()));
        holder.txt_trangthaiphieu.setText(mListPhieu.get(position).getTrangthaiphieu());

    }

    @Override
    public int getItemCount() {
        return mListPhieu.size();
    }

    public static class PhieuViewHolder extends RecyclerView.ViewHolder{
        TextView txt_sophong,txt_tienthang,txt_trangthaiphieu;
        public PhieuViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_sophong = itemView.findViewById(R.id.txt_sophongph);
            txt_tienthang = itemView.findViewById(R.id.txt_tienthang);
            txt_trangthaiphieu = itemView.findViewById(R.id.txt_trang_thai_phieu);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.OnItemClick(getAdapterPosition());
                }
            });
        }
    }
}
