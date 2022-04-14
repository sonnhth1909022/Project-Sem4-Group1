package com.movieapp.movienavigation.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.movieapp.movienavigation.R;
import com.movieapp.movienavigation.network.ApiManager;
import com.movieapp.movienavigation.response.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    private TextView login;
    private EditText etEmail, etPassword, etUsername, etPhone;
    private Button btRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        login = findViewById(R.id.login);
        clickLogin();

        etEmail = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);
        etUsername = findViewById(R.id.username);
        etPhone = findViewById(R.id.phone);

        btRegister = findViewById(R.id.register);
        click_registerUser();
    }

    private void clickLogin() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void click_registerUser(){
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(etEmail.getText().toString()) || TextUtils.isEmpty(etUsername.getText().toString()) || TextUtils.isEmpty(etPhone.getText().toString()) || TextUtils.isEmpty(etPassword.getText().toString())){
                    String message = "All inputs required";
                    Toast.makeText(RegisterActivity.this,message,Toast.LENGTH_SHORT).show();
                }else {
                    User user = new User();
                    user.setEmail(etEmail.getText().toString());
                    user.setUsername(etUsername.getText().toString());
                    user.setPhone(etPhone.getText().toString());
                    user.setPassword(etPassword.getText().toString());
                    registerUser(user);
                }
            }
        });
    }

    private void registerUser(User user) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiManager.SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiManager service = retrofit.create(ApiManager.class);
        service.registerUser(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    saveInfo();
                    String message = "Register Successful";
                    Toast.makeText(RegisterActivity.this,message,Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    finish();

                }else{
                    String message = "Register Failed";
                    Toast.makeText(RegisterActivity.this,message,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(RegisterActivity.this,message,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveInfo(){
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", etUsername.getText().toString());
        editor.putString("password", etPassword.getText().toString());
        editor.putString("email", etEmail.getText().toString());
        editor.putString("phone", etPhone.getText().toString());
        editor.commit();
    }
}