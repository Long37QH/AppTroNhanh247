package com.example.nhatro247.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.nhatro247.DAO.KhachTroDAO;
import com.example.nhatro247.DAO.PhongTroDAO;
import com.example.nhatro247.Model.KhachTro;
import com.example.nhatro247.Model.PhongTro;
import com.example.nhatro247.R;

public class PhongDetailFragment extends Fragment {
    private EditText edit_Usophong,edit_Utenkhach,edit_Usdt,edit_Udientich,edit_Ugiaphong,edit_Ugiadien,edit_Ugianuoc,edit_Utrangthai,edit_Ungayvao;
    private Button btn_update,btn_delete;
    private PhongTroDAO phongTroDAO;
    private PhongTro phong;

    private View mView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_phong_detail,container,false);
        //code anh xa
        edit_Usophong = mView.findViewById(R.id.edit_Usophong);
        edit_Udientich = mView.findViewById(R.id.edit_Udientich);
        edit_Ugiaphong = mView.findViewById(R.id.edit_Ugiaphong);
        edit_Ugiadien = mView.findViewById(R.id.edit_Ugiadien);
        edit_Ugianuoc = mView.findViewById(R.id.edit_Ugianuoc);
        edit_Utrangthai = mView.findViewById(R.id.edit_Utrangthai);

        edit_Utenkhach = mView.findViewById(R.id.edit_Utenkhach);
        edit_Usdt = mView.findViewById(R.id.edit_Usdt);
        edit_Ungayvao = mView.findViewById(R.id.edit_Ungayvao);

        btn_update = mView.findViewById(R.id.btn_update);
        btn_delete = mView.findViewById(R.id.btn_delete);

        // lay id tư home fragment
        Bundle bundle = getArguments();
        if (bundle != null){
            int id_phong = bundle.getInt("idPhong");
            //edit_Usophong.setText(String.valueOf(id_phong));
            phong = new PhongTro();
            phongTroDAO = new PhongTroDAO(PhongDetailFragment.this);
            phong = phongTroDAO.getPhongById(id_phong);

            KhachTro khachTro = new KhachTro();
            KhachTroDAO khachTroDAO = new KhachTroDAO(PhongDetailFragment.this);
            khachTro = khachTroDAO.getKhachById(id_phong);

            // Hiển thị dữ liêu lên fragment
            edit_Usophong.setText(String.valueOf(id_phong));
            edit_Utenkhach.setText(khachTro.getTenKh());
            edit_Usdt.setText(khachTro.getSdt());
            edit_Udientich.setText(String.valueOf(phong.getDienTich()));
            edit_Ugiaphong.setText(String.valueOf(phong.getGiaPhong()));
            edit_Ugiadien.setText(String.valueOf(phong.getGiaDien()));
            edit_Ugianuoc.setText(String.valueOf(phong.getGiaNuoc()));
            edit_Utrangthai.setText(phong.getTrangThai());
            edit_Ungayvao.setText(khachTro.getNgayVao());
        }

        // xư ly btn_udate
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhongTro phong = new PhongTro();
                int id_phong = Integer.parseInt(edit_Usophong.getText().toString().trim());
                int dientich = Integer.parseInt(edit_Udientich.getText().toString().trim());
                int giaphong = Integer.parseInt(edit_Ugiaphong.getText().toString().trim());
                int sodien = 0;
                int giadien = Integer.parseInt(edit_Ugiadien.getText().toString().trim());
                int sonuoc = 0;
                int gianuoc = Integer.parseInt(edit_Ugianuoc.getText().toString().trim());
                String trangthai = edit_Utrangthai.getText().toString().trim();

                phong.setIdPhong(id_phong);
                phong.setDienTich(dientich);
                phong.setGiaPhong(giaphong);
                phong.setSoDien(sodien);
                phong.setGiaDien(giadien);
                phong.setSoNuoc(sonuoc);
                phong.setGiaNuoc(gianuoc);
                phong.setTrangThai(trangthai);

                PhongTroDAO phongDAO = new PhongTroDAO(PhongDetailFragment.this);
                phongDAO.updatePhong(phong);

                //cap nhat thon tin khách
                KhachTro khachTro1 = new KhachTro();
                String ten_khach = edit_Utenkhach.getText().toString().trim();
                String sdt = edit_Usdt.getText().toString().trim();
                String ngayvao = edit_Ungayvao.getText().toString().trim();

                khachTro1.setIdKhach(id_phong);
                khachTro1.setIdPhong(id_phong);
                khachTro1.setTenKh(ten_khach);
                khachTro1.setSdt(sdt);
                khachTro1.setNgayVao(ngayvao);

                KhachTroDAO khachTroDAO1 = new KhachTroDAO(PhongDetailFragment.this);
                khachTroDAO1.updateKhach(khachTro1);

                //  xu lý thoat ra fragmenthome
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame,new HomeFragment());

                transaction.addToBackStack(null);
                transaction.commit();

                Toast.makeText(getContext(),"Câp nhật thành công",Toast.LENGTH_SHORT).show();
            }
        });

        // bắt sự kiên btn_xóa

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id_phong = Integer.parseInt(edit_Usophong.getText().toString().trim());
                PhongTroDAO phongDAO = new PhongTroDAO(PhongDetailFragment.this);
                phongDAO.deletePhong(id_phong);

                KhachTroDAO khachTroDAO1 = new KhachTroDAO(PhongDetailFragment.this);
                khachTroDAO1.deleteKhach(id_phong);

                //  xu lý thoat ra fragmenthome
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame,new HomeFragment());

                //transaction.addToBackStack(null);
                transaction.commit();
                //finish();
                Toast.makeText(getContext(),"Đã xóa thành công",Toast.LENGTH_SHORT).show();
            }
        });
        return mView;
    }

}
