package com.example.nhatro247.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.nhatro247.DAO.PhieuThuDAO;
import com.example.nhatro247.DAO.PhongTroDAO;
import com.example.nhatro247.Model.PhieuThu;
import com.example.nhatro247.Model.PhongTro;
import com.example.nhatro247.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddPhieuFragment extends Fragment {
    EditText edit_so_phong,edit_so_dien_moi,edit_so_nuoc_moi;
    Button btn_ghi,btn_tong,btn_lapphieu;
    TextView txt_so_nuoc_cu,txt_so_dien_cu,txt_dien_tt,txt_nuoc_tt,txt_gia_nuoc,txt_gia_dien,txt_gia_phong,txt_tongtien;
    private View mView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_add_phieu,container,false);

        edit_so_phong = mView.findViewById(R.id.edit_sophong_phieu);
        edit_so_dien_moi = mView.findViewById(R.id.edit_sodien_moi);
        edit_so_nuoc_moi = mView.findViewById(R.id.edit_sonuoc_moi);

        txt_so_dien_cu = mView.findViewById(R.id.txt_sodien_cu);
        txt_dien_tt = mView.findViewById(R.id.txt_sodien_tt);
        txt_gia_dien = mView.findViewById(R.id.txt_gia_dien);

        txt_so_nuoc_cu = mView.findViewById(R.id.txt_sonuoc_cu);
        txt_nuoc_tt = mView.findViewById(R.id.txt_sonuoc_tt);
        txt_gia_nuoc = mView.findViewById(R.id.txt_gia_nuoc);

        txt_gia_phong = mView.findViewById(R.id.txt_gia_phong);
        txt_tongtien = mView.findViewById(R.id.txt_tong_tien);

        btn_ghi = mView.findViewById(R.id.btn_ghi);
        btn_tong = mView.findViewById(R.id.btn_Tongtien);
        btn_lapphieu = mView.findViewById(R.id.btn_lapphieu);

        btn_ghi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idPhong = Integer.parseInt(edit_so_phong.getText().toString().trim());
                int sodienmoi = Integer.parseInt(edit_so_dien_moi.getText().toString().trim());
                int sonuocmoi = Integer.parseInt(edit_so_nuoc_moi.getText().toString().trim());

                PhongTro phong = new PhongTro();
                PhongTroDAO phongTroDAO = new PhongTroDAO(AddPhieuFragment.this);
                phong = phongTroDAO.getPhongById(idPhong);

                int dientich = phong.getDienTich();
                int giaphong = phong.getGiaPhong();
                int sodiencu = phong.getSoDien();
                int giadien = phong.getGiaDien();
                int sonuoccu = phong.getSoNuoc();
                int gianuoc = phong.getGiaNuoc();
                String trangthaip = phong.getTrangThai();

                PhongTro phong1 = new PhongTro();
                phong1.setIdPhong(idPhong);
                phong1.setDienTich(dientich);
                phong1.setGiaPhong(giaphong);
                phong1.setSoDien(sodienmoi);
                phong1.setGiaDien(giadien);
                phong1.setSoNuoc(sonuocmoi);
                phong1.setGiaNuoc(gianuoc);
                phong1.setTrangThai(trangthaip);

                phongTroDAO.updatePhong(phong1);

                int dienTT = sodienmoi - sodiencu;
                int nuocTT = sonuocmoi - sonuoccu;

                txt_so_dien_cu.setText(String.valueOf(sodiencu));
                txt_dien_tt.setText(String.valueOf(dienTT));
                txt_gia_dien.setText(String.valueOf(giadien));

                txt_so_nuoc_cu.setText(String.valueOf(sonuoccu));
                txt_nuoc_tt.setText(String.valueOf(nuocTT));
                txt_gia_nuoc.setText(String.valueOf(gianuoc));

                txt_gia_phong.setText(String.valueOf(giaphong));
            }
        });

        btn_tong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int dtt = Integer.parseInt(txt_dien_tt.getText().toString().trim());
                int giad = Integer.parseInt(txt_gia_dien.getText().toString().trim());
                int ntt = Integer.parseInt(txt_nuoc_tt.getText().toString().trim());
                int gian = Integer.parseInt(txt_gia_nuoc.getText().toString().trim());
                int giap = Integer.parseInt(txt_gia_phong.getText().toString().trim());
                int tongtien = dtt*giad + ntt*gian +giap;

                txt_tongtien.setText(String.valueOf(tongtien));
            }
        });

        btn_lapphieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhieuThu phieuThu = new PhieuThu();
                int id = Integer.parseInt(edit_so_phong.getText().toString().trim());
                int dtt = Integer.parseInt(txt_dien_tt.getText().toString().trim());
                int ntt = Integer.parseInt(txt_nuoc_tt.getText().toString().trim());

                int tienthu = Integer.parseInt(txt_tongtien.getText().toString().trim());

                // Lấy thời gian hiện tai
                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0
                int year = calendar.get(Calendar.YEAR);
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                String tgLapPhieu = formatter.format(calendar.getTime());

                String tgThuTien ="";
                String trangthaiphieu = "Chưa đóng tiền";

                phieuThu.setIdKhach(id);
                phieuThu.setIdPhong(id);
                phieuThu.setDienTT(dtt);
                phieuThu.setNuocTT(ntt);
                phieuThu.setTienThu(tienthu);
                phieuThu.setTg_Lapphieu(tgLapPhieu);
                phieuThu.setTg_ThuTien(tgThuTien);
                phieuThu.setTrangthaiphieu(trangthaiphieu);

                PhieuThuDAO phieuThuDAO = new PhieuThuDAO(AddPhieuFragment.this);
                phieuThuDAO.insertPhieu(phieuThu);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame,new LapPhieuFragment());
                //transaction.addToBackStack(null);
                transaction.commit();
                Toast.makeText(getContext(),"Lập phiếu mới thành công",Toast.LENGTH_SHORT).show();
            }
        });
        return mView;
    }
}
