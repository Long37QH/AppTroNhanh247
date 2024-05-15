package com.example.nhatro247.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro247.Model.PhongTro;
import com.example.nhatro247.R;
import com.example.nhatro247.fragments.HomeFragment;

import java.util.List;

public class PhongAdapter extends RecyclerView.Adapter<PhongAdapter.PhongViewHolder>{
    private List<PhongTro> mListPhong;
    private HomeFragment context;

    private static ItemClickListener itemClickListener;
    public PhongAdapter(List<PhongTro> mListPhong, HomeFragment context, ItemClickListener itemClickListener) {
        this.mListPhong = mListPhong;
        this.context = context;
        this.itemClickListener = itemClickListener;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PhongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phong,parent,false);
        return new PhongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhongViewHolder holder, int position) {
        holder.txt_sophong.setText(String.valueOf(mListPhong.get(position).getIdPhong()));
        holder.txt_dientich.setText(String.valueOf(mListPhong.get(position).getDienTich()));
        holder.txt_trangthai.setText(mListPhong.get(position).getTrangThai());
    }

    @Override
    public int getItemCount() {
        return mListPhong.size();
    }

    public static class PhongViewHolder extends RecyclerView.ViewHolder{
        public TextView txt_sophong,txt_dientich,txt_trangthai;
        public PhongViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_sophong = itemView.findViewById(R.id.txt_sophong);
            txt_dientich = itemView.findViewById(R.id.txt_dientich);
            txt_trangthai = itemView.findViewById(R.id.txt_trangthai);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.OnItemClick(getAdapterPosition());
                }
            });
        }
    }
}
