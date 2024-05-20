package com.example.nhatro247.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.nhatro247.DAO.KhachTroDAO;
import com.example.nhatro247.DAO.PhieuThuDAO;
import com.example.nhatro247.DAO.PhongTroDAO;
import com.example.nhatro247.Model.KhachTro;
import com.example.nhatro247.Model.PhieuThu;
import com.example.nhatro247.Model.PhongTro;
import com.example.nhatro247.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class PhieuDetailFragment extends Fragment {
    TextView txt_maPhieuThu,txt_soPhong,txt_KhachPhong,txt_DienThoai,txt_TgLapPhieu,txt_SoDien,txt_SoDienTT,txt_GiaDien,txt_SoNuoc,txt_SoNuocTT,txt_GiaNuoc,txt_GiaPhong,txt_TongTien;
    Button btn_thutien;
    PhieuThuDAO phieuThuDAO;
    PhongTroDAO phongTroDAO;
    KhachTroDAO khachTroDAO;
    private View mView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_phieu_detail,container,false);

        txt_maPhieuThu = mView.findViewById(R.id.txt_so_phieu);
        txt_soPhong = mView.findViewById(R.id.txt_so_phong);
        txt_KhachPhong = mView.findViewById(R.id.txt_khach);
        txt_DienThoai = mView.findViewById(R.id.txt_stdkh);
        txt_TgLapPhieu = mView.findViewById(R.id.txt_tgLapPhieu);
        txt_SoDien = mView.findViewById(R.id.txt_so_dien);
        txt_SoDienTT = mView.findViewById(R.id.txt_so_dien_tieuthu);
        txt_GiaDien = mView.findViewById(R.id.txt_giaDien);
        txt_SoNuoc = mView.findViewById(R.id.txt_so_nuoc);
        txt_SoNuocTT = mView.findViewById(R.id.txt_so_nuoc_tieuthu);
        txt_GiaNuoc = mView.findViewById(R.id.txt_giaNuoc);
        txt_GiaPhong = mView.findViewById(R.id.txt_giaPhong);
        txt_TongTien = mView.findViewById(R.id.txt_TienThuThang);

        btn_thutien = mView.findViewById(R.id.btn_ThuTien);

        Bundle bundle = getArguments();
        if (bundle != null){
            int id_phieu = bundle.getInt("idPhieu");

            PhieuThu phieuThu = new PhieuThu();
            phieuThuDAO = new PhieuThuDAO(PhieuDetailFragment.this);
            phieuThu = phieuThuDAO.getPhieuById(id_phieu);

            int id = phieuThu.getIdPhong();

            PhongTro phong = new PhongTro();
            phongTroDAO = new PhongTroDAO(PhieuDetailFragment.this);
            phong = phongTroDAO.getPhongById(id);

            KhachTro khachTro = new KhachTro();
            khachTroDAO = new KhachTroDAO(PhieuDetailFragment.this);
            khachTro = khachTroDAO.getKhachById(id);

            txt_maPhieuThu.setText(String.valueOf(id_phieu));
            txt_soPhong.setText(String.valueOf(id));
            txt_KhachPhong.setText(khachTro.getTenKh());
            txt_DienThoai.setText(khachTro.getSdt());
            txt_TgLapPhieu.setText(phieuThu.getTg_Lapphieu());

            txt_SoDien.setText(String.valueOf(phong.getSoDien()));
            txt_SoDienTT.setText(String.valueOf(phieuThu.getDienTT()));
            txt_GiaDien.setText(String.valueOf(phong.getGiaDien()));

            txt_SoNuoc.setText(String.valueOf(phong.getSoNuoc()));
            txt_SoNuocTT.setText(String.valueOf(phieuThu.getNuocTT()));
            txt_GiaNuoc.setText(String.valueOf(phong.getGiaNuoc()));

            txt_GiaPhong.setText(String.valueOf(phong.getGiaPhong()));
            txt_TongTien.setText(String.valueOf(phieuThu.getTienThu()));

            btn_thutien.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PhieuThu phieuThu1 = new PhieuThu();
                    int idPhieuThu = Integer.parseInt(txt_maPhieuThu.getText().toString().trim());
                    int idKhach = Integer.parseInt(txt_soPhong.getText().toString().trim());
                    int idPhong = Integer.parseInt(txt_soPhong.getText().toString().trim());
                    int SoDienTT = Integer.parseInt(txt_SoDienTT.getText().toString().trim());
                    int SoNuocTT = Integer.parseInt(txt_SoNuocTT.getText().toString().trim());
                    int TienThu = Integer.parseInt(txt_TongTien.getText().toString().trim());
                    String tgLapPhieu = txt_TgLapPhieu.getText().toString().trim();

                    Calendar calendar = Calendar.getInstance();
                    int day = calendar.get(Calendar.DAY_OF_MONTH);
                    int month = calendar.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0
                    int year = calendar.get(Calendar.YEAR);
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    String tgThuTien = formatter.format(calendar.getTime());
                    String trangthaiPhieu = "Đã thu";

                    phieuThu1.setIdPThu(idPhieuThu);
                    phieuThu1.setIdKhach(idKhach);
                    phieuThu1.setIdPhong(idPhong);
                    phieuThu1.setDienTT(SoDienTT);
                    phieuThu1.setNuocTT(SoNuocTT);
                    phieuThu1.setTienThu(TienThu);
                    phieuThu1.setTg_Lapphieu(tgLapPhieu);
                    phieuThu1.setTg_ThuTien(tgThuTien);
                    phieuThu1.setTrangthaiphieu(trangthaiPhieu);

                    phieuThuDAO = new PhieuThuDAO(PhieuDetailFragment.this);
                    phieuThuDAO.updatePhieu(phieuThu1);

                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.content_frame,new LapPhieuFragment());
                    //transaction.addToBackStack(null);
                    transaction.commit();
                    Toast.makeText(getContext(),"Đã thu tiền tháng",Toast.LENGTH_SHORT).show();
                }
            });
        }
        return mView;
    }
}
