package com.example.nhatro247.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro247.Adapter.ItemClickListener;
import com.example.nhatro247.Adapter.PhieuAdapter;
import com.example.nhatro247.DAO.PhieuThuDAO;
import com.example.nhatro247.Model.PhieuThu;
import com.example.nhatro247.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class LapPhieuFragment extends Fragment implements ItemClickListener {
    RecyclerView rcv_phieu;
    private FloatingActionButton btn_add_phieu;
    private PhieuAdapter phieuAdapter;

    private List<PhieuThu> mListPhieu;
    private PhieuThuDAO phieuThuDAO;

    private View mView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_lapphieu,container,false);

        btn_add_phieu = mView.findViewById(R.id.btn_add_phieu);

        btn_add_phieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame,new AddPhieuFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        rcv_phieu = mView.findViewById(R.id.rcv_phieu);
        btn_add_phieu = mView.findViewById(R.id.btn_add_phieu);
        inputData();
        rcv_phieu.setHasFixedSize(true);

        phieuAdapter = new PhieuAdapter(mListPhieu,LapPhieuFragment.this,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        rcv_phieu.setLayoutManager(linearLayoutManager);
        rcv_phieu.setAdapter(phieuAdapter);

        btn_add_phieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame,new AddPhieuFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return mView;
    }
    private void inputData() {
        mListPhieu = new ArrayList<>();
        phieuThuDAO = new PhieuThuDAO(LapPhieuFragment.this);
        mListPhieu = phieuThuDAO.getAllPhieu();
    }

    @Override
    public void OnItemClick(int positionItem) {
        mListPhieu = new ArrayList<>();
        phieuThuDAO = new PhieuThuDAO(LapPhieuFragment.this);
        mListPhieu = phieuThuDAO.getAllPhieu();

        int idPhieu = mListPhieu.get(positionItem).getIdPThu();
        Bundle myBundle = new Bundle();
        myBundle.putInt("idPhieu",idPhieu);

        //khoi tao fragmentnhan
        PhieuDetailFragment PhieuDetailFragment = new PhieuDetailFragment() ;
        PhieuDetailFragment.setArguments(myBundle);

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame,PhieuDetailFragment);
        //
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
