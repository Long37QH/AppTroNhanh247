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

import com.example.nhatro247.DAO.UserDAO;
import com.example.nhatro247.Model.User;
import com.example.nhatro247.R;

public class DoiPassFragment extends Fragment {
    EditText inputUser, inputPassOld, inputPassNew;
    Button btn_update_user;

    private View mView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_doipass,container,false);

        inputUser = mView.findViewById(R.id.inputUser);
        inputPassOld = mView.findViewById(R.id.inputPass_cu);
        inputPassNew = mView.findViewById(R.id.inputPass_new);
        btn_update_user = mView.findViewById(R.id.btn_update_user);

        //lay thong tin user old
        int idUser = 1;
        UserDAO userDAO = new UserDAO(DoiPassFragment.this);
        User user = userDAO.getUserById(idUser);
        inputUser.setText(user.getUsername());
        inputPassOld.setText(user.getPass());

        btn_update_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user1 = new User();
                user1.setIdUser(idUser);
                user1.setUsername(inputUser.getText().toString().trim());
                user1.setPass(inputPassNew.getText().toString().trim());
                userDAO.updateUser(user1);

                //  xu lý thoat ra fragmenthome
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame,new HomeFragment());

                transaction.addToBackStack(null);
                transaction.commit();

                Toast.makeText(getContext(),"Câp nhật tài khoản thành công",Toast.LENGTH_SHORT).show();
            }
        });
        return mView;
    }
}
