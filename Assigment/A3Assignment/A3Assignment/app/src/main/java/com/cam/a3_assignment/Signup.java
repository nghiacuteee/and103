package com.cam.a3_assignment;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.cam.a3_assignment.model.Account;
import com.cam.a3_assignment.model.Response;
import com.cam.a3_assignment.services.HttpReq;

import retrofit2.Call;
import retrofit2.Callback;

public class Signup extends AppCompatActivity {

    private EditText edtNewEmail, edtNewPassword, edtNewName, edtReTypePassword;
    private Button btnSignUp;
    private HttpReq httpReq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        TextView btnBackToLogin = findViewById(R.id.btnBackToLogin);
        btnBackToLogin.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        btnBackToLogin.setOnClickListener(view -> finish());

        btnSignUp = findViewById(R.id.btnSignup);
        edtNewEmail = findViewById(R.id.txtNewEmail);
        edtNewPassword = findViewById(R.id.txtNewPassword);
        edtNewName = findViewById(R.id.txtNewUsername);
        edtReTypePassword = findViewById(R.id.txtNewRetypePassword);

        httpReq = new HttpReq();

        btnSignUp.setOnClickListener(v -> {
            String newEmail = edtNewEmail.getText().toString();
            String newName = edtNewName.getText().toString();
            String newPassword = edtNewPassword.getText().toString();
            if(!(newName.isEmpty() || newPassword.isEmpty() || newEmail.isEmpty() || edtReTypePassword.getText().toString().isEmpty())){
                if(edtNewPassword.getText().toString().equals(edtReTypePassword.getText().toString())){
                    httpReq.callApi().signup(new Account(newEmail, newName, newPassword)).enqueue(responseSignUp);
                }else{
                    Toast.makeText(Signup.this, "Mật Khẩu Không Trùng Khớp", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(Signup.this, "Thiếu Thông Tin", Toast.LENGTH_SHORT).show();
            }
        });
    }

    Callback<Response<Account>> responseSignUp = new Callback<Response<Account>>() {
        @Override
        public void onResponse(Call<Response<Account>> call, retrofit2.Response<Response<Account>> response) {
            if(response.isSuccessful()){
                if(response.body().getStatus() == 200){
                    Toast.makeText(Signup.this, "Đăng Ký Thành Công", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(Signup.this, "Đăng Ký Thành Bại", Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public void onFailure(Call<Response<Account>> call, Throwable throwable) {

        }
    };
}