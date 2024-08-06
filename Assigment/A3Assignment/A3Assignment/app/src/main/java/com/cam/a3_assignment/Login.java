package com.cam.a3_assignment;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

public class Login extends AppCompatActivity {

    private TextView btnForgot, btnSignup;
    private Button btnLogin;
    private EditText txtUsername, txtPassword;
    private CheckBox chkRemember;
    private HttpReq httpReq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtUsername = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnForgot = findViewById(R.id.btnForgot);
        btnForgot.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        btnSignup = findViewById(R.id.btnSignup);
        btnSignup.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

        httpReq = new HttpReq();

        btnSignup.setOnClickListener(v -> {
            startActivity(new Intent(Login.this, Signup.class));
        });
        btnLogin.setOnClickListener(v1 -> {
            if(!(txtUsername.getText().toString().isEmpty() || txtPassword.getText().toString().isEmpty())){
                httpReq.callApi().login(new Account(txtUsername.getText().toString(), txtPassword.getText().toString())).enqueue(responseAccount);
            }else{
                Toast.makeText(Login.this, "Thiếu Thông Tin", Toast.LENGTH_SHORT).show();
            }
        });
    }

    Callback<Response<Account>> responseAccount = new Callback<Response<Account>>() {
        @Override
        public void onResponse(Call<Response<Account>> call, retrofit2.Response<Response<Account>> response) {
            if(response.isSuccessful()){
                if(response.body().getStatus() == 200){
                    Account account = response.body().getData();
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    intent.putExtra("account", account);
                    startActivity(intent);
                }
            }
        }

        @Override
        public void onFailure(Call<Response<Account>> call, Throwable throwable) {
            System.out.println(throwable.getMessage());
        }
    };
}