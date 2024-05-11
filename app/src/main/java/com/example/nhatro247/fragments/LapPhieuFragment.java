package com.example.nhatro247.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.nhatro247.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class LapPhieuFragment extends Fragment {
    private FloatingActionButton btn_add_phieu;

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

        return mView;
    }
}
