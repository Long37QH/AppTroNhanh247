package com.example.nhatro247.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro247.Adapter.ItemClickListener;
import com.example.nhatro247.Adapter.ThongKeAdapter;
import com.example.nhatro247.DAO.PhieuThuDAO;
import com.example.nhatro247.Model.PhieuThu;
import com.example.nhatro247.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ThongKeFragment extends Fragment implements ItemClickListener {
    final Calendar myCalendar = Calendar.getInstance();
    private EditText edit_TG_LapPhieu;
    private Button btn_ThongKe;
    private RecyclerView rcv_thongKe;
    private TextView txt_tong_tien_tk;
    private ThongKeAdapter thongKeAdapter;
    private List<PhieuThu> mListPhieu;
    private PhieuThuDAO phieuThuDAO;
    private View mView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_thongke,container,false);

        edit_TG_LapPhieu = mView.findViewById(R.id.edit_TG_LapPhieu);
        selectDate();

        btn_ThongKe = mView.findViewById(R.id.btn_ThongKe);
        rcv_thongKe = mView.findViewById(R.id.rcv_thongke);
        txt_tong_tien_tk = mView.findViewById(R.id.txt_tong_tien_tk);

        btn_ThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit_TG_LapPhieu.length() != 0){
                    thongke();
                }else {
                    Toast.makeText(getContext(),"Bạn phải chọn ngày lập phiếu",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return mView;
    }

    private void thongke() {
        inputData();
        rcv_thongKe.setHasFixedSize(true);
        thongKeAdapter = new ThongKeAdapter(mListPhieu,ThongKeFragment.this,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        rcv_thongKe.setLayoutManager(linearLayoutManager);
        rcv_thongKe.setAdapter(thongKeAdapter);

        String tg_LapPhieu = edit_TG_LapPhieu.getText().toString().trim();
        phieuThuDAO = new PhieuThuDAO(ThongKeFragment.this);
        int Tongtien = phieuThuDAO.getTongThu(tg_LapPhieu);
        txt_tong_tien_tk.setText(String.valueOf(Tongtien));
    }

    private void inputData() {
        mListPhieu = new ArrayList<>();
        String tg_LapPhieu = edit_TG_LapPhieu.getText().toString().trim();
        phieuThuDAO = new PhieuThuDAO(ThongKeFragment.this);
        mListPhieu = phieuThuDAO.getPhieuByDate(tg_LapPhieu);
    }

    private void selectDate() {
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR,year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                edit_TG_LapPhieu.setText(UpdateDate());
            }
        };
        edit_TG_LapPhieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(),date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }
    private String UpdateDate(){
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.getDefault());
        return dateFormat.format(myCalendar.getTime());
    }

    @Override
    public void OnItemClick(int positionItem) {

    }
}
