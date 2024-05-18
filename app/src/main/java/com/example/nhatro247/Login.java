package com.example.nhatro247;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.nhatro247.DAO.UserDAO;
import com.example.nhatro247.Model.User;

public class Login extends AppCompatActivity {
    EditText edit_user,edit_pass;
    Button btn_Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edit_user = findViewById(R.id.edit_user);
        edit_pass = findViewById(R.id.edit_pass);
        btn_Login = findViewById(R.id.btnLogin);

        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit_user.getText().length() != 0 && edit_pass.getText().length() != 0){
                    String tk = edit_user.getText().toString().trim();
                    String pass = edit_pass.getText().toString().trim();

                    UserDAO userDAO = new UserDAO(Login.this);
                    User user = userDAO.CheckUser(tk,pass);
                    if (user == null){
                        Toast.makeText(Login.this,"Tài khoản hoặc mật khẩu không đúng",Toast.LENGTH_SHORT).show();
                    }else {
                        Intent intent = new Intent(Login.this,MainActivity.class);

                        Bundle myBundle = new Bundle();
                        myBundle.putString("username",tk);
                        intent.putExtra("bundle_user",myBundle);

                        // Đặt cờ để xóa stack hiện tại
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        // Kết thúc LoginActivity
                        finish();

                    }

                }else {
                    Toast.makeText(Login.this,"Mời bạn nhập thông tin",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}