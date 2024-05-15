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
import com.example.nhatro247.Adapter.PhongAdapter;
import com.example.nhatro247.DAO.PhongTroDAO;
import com.example.nhatro247.Model.PhongTro;
import com.example.nhatro247.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements ItemClickListener {
    private RecyclerView rcv_phong;
    private FloatingActionButton btn_add;
    private View mView;
    private PhongAdapter phongAdapter;
    private PhongTroDAO phongTroDAO;
    private List<PhongTro> mListPhong;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home,container,false);


        rcv_phong = mView.findViewById(R.id.rcv_phong);
        btn_add = mView.findViewById(R.id.btn_add_phong);

        inData();
        rcv_phong.setHasFixedSize(true);// làm mươt kho co cung được

        phongAdapter = new PhongAdapter(mListPhong,HomeFragment.this,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        rcv_phong.setLayoutManager(linearLayoutManager);
        rcv_phong.setAdapter(phongAdapter);


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // test su ly chuyen các flagment
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame,new AddPhongFragment());
                //
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


        return mView;
    }

    private void inData() {
        mListPhong = new ArrayList<>();
        phongTroDAO = new PhongTroDAO(HomeFragment.this);
        mListPhong = phongTroDAO.getAll();
    }

    @Override
    public void OnItemClick(int positionItem) {

        mListPhong = new ArrayList<>();
        phongTroDAO = new PhongTroDAO(HomeFragment.this);
        mListPhong = phongTroDAO.getAll();

        int id_phong = mListPhong.get(positionItem).getIdPhong();
        Bundle myBundle = new Bundle();
        myBundle.putInt("idPhong",id_phong);

        //khoi tao fragmentnhan
        PhongDetailFragment phongDetailFragment = new PhongDetailFragment();
        phongDetailFragment.setArguments(myBundle);

        // xu ly chuyen các flagment
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame,phongDetailFragment);
        //
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
