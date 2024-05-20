package com.example.nhatro247.fragments;

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

public class AddPhongFragment extends Fragment {
    private EditText edit_sophong,edit_dientich,edit_giaphong,edit_giadien,edit_gianuoc;
    private Button btn_addP;

    private View mView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_add_phong,container,false);
        //code anh xa
        edit_sophong = mView.findViewById(R.id.edit_sophong);
        edit_dientich = mView.findViewById(R.id.edit_dientich);
        edit_giaphong = mView.findViewById(R.id.edit_giaphong);
        edit_giadien = mView.findViewById(R.id.edit_giadien);
        edit_gianuoc = mView.findViewById(R.id.edit_gianuoc);

        btn_addP = mView.findViewById(R.id.btn_add);
        btn_addP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // thêm dư lieu bang phong
                PhongTro phong = new PhongTro();
                int ip_phong = Integer.parseInt(edit_sophong.getText().toString().trim());
                int dientich = Integer.parseInt(edit_dientich.getText().toString().trim());
                int giaphong = Integer.parseInt(edit_giaphong.getText().toString().trim());
                int sodien = 0;
                int giadien = Integer.parseInt(edit_giadien.getText().toString().trim());
                int sonuoc = 0;
                int gianuoc = Integer.parseInt(edit_gianuoc.getText().toString().trim());
                String trangthai = "Phòng Trống";

                phong.setIdPhong(ip_phong);
                phong.setDienTich(dientich);
                phong.setGiaPhong(giaphong);
                phong.setSoDien(sodien);
                phong.setGiaDien(giadien);
                phong.setSoNuoc(sonuoc);
                phong.setGiaNuoc(gianuoc);
                phong.setTrangThai(trangthai);

                PhongTroDAO phongDAO = new PhongTroDAO(AddPhongFragment.this);
                phongDAO.insertPhong(phong);

                //them thong tin bang khach
                KhachTro khachTro = new KhachTro();
                String ten_khach = "Phòng Trống";
                String sdt = "Phòng Trống";
                String ngayvao = "Phòng Trống";

                khachTro.setIdKhach(ip_phong);
                khachTro.setIdPhong(ip_phong);
                khachTro.setTenKh(ten_khach);
                khachTro.setSdt(sdt);
                khachTro.setNgayVao(ngayvao);
                KhachTroDAO khachTroDAO = new KhachTroDAO(AddPhongFragment.this);
                khachTroDAO.insertKhach(khachTro);

                //  xu lý thoat ra fragmenthome
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame,new HomeFragment());

                transaction.addToBackStack(null);
                transaction.commit();
                Toast.makeText(getContext(),"Thêm phòng mới thành công",Toast.LENGTH_SHORT).show();
            }
        });
        return mView;
    }
}
