package com.movieapp.movienavigation.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.movieapp.movienavigation.MainActivity;
import com.movieapp.movienavigation.R;
import com.movieapp.movienavigation.event.SaveAccountType;
import com.movieapp.movienavigation.event.SaveID;
import com.movieapp.movienavigation.network.ApiManager;
import com.movieapp.movienavigation.response.LoginRequest;
import com.movieapp.movienavigation.response.TransactionResDto;
import com.movieapp.movienavigation.response.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private TextView tvRegister;
    private EditText etUsername, etPassword;
    private Button btLogin;
    private CheckBox cbCheckBox;
    SaveID saveID = new SaveID();
    public int idCus;
    SaveAccountType saveAccountType = new SaveAccountType();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.username);
        etPassword = findViewById(R.id.password);
        cbCheckBox = findViewById(R.id.cbCheckBox);

        btLogin = findViewById(R.id.login);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickLogin();

            }
        });

        showInfoRem();

        tvRegister = findViewById(R.id.register);
        clickRegister();

    }

    private void clickLogin() {
        if(TextUtils.isEmpty(etUsername.getText().toString()) || TextUtils.isEmpty(etPassword.getText().toString())){
            String message = "All inputs required";
            Toast.makeText(LoginActivity.this,message,Toast.LENGTH_SHORT).show();
        }else {
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setUsername(etUsername.getText().toString());
            loginRequest.setPassword(etPassword.getText().toString());
            loginUser(loginRequest);
        }
    }

    public void loginUser(LoginRequest loginRequest) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiManager.SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiManager service = retrofit.create(ApiManager.class);
        service.loginUser(loginRequest).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()) {
                    saveInfo();
                    String message = "Login Successful";
                    Toast.makeText(LoginActivity.this,message,Toast.LENGTH_SHORT).show();
                    User user = response.body();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class)
                            .putExtra("name",user.getUsername())
                            .putExtra("email", user.getEmail())
                            .putExtra("id",user.getId())
                    .putExtra("accountType",user.getAccountType()));
                   saveID.setId(LoginActivity.this,user.getId());
                   saveAccountType.setAccountType(LoginActivity.this,user.getAccountType());
                   idCus = user.getId();
                }else {
                    String message = "Login Failed";
                    Toast.makeText(LoginActivity.this,message,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(LoginActivity.this,message,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void clickRegister() {
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showInfoRem(){
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
        String un = sharedPreferences.getString("username","");
        String pw = sharedPreferences.getString("password","");
        boolean isRem = sharedPreferences.getBoolean("remember",false);
        etUsername.setText(un);
        etPassword.setText(pw);
        cbCheckBox.setChecked(isRem);
    }

    private void saveInfo(){
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        boolean isRem = cbCheckBox.isChecked();
        if(isRem){
            editor.putString("username", etUsername.getText().toString());
            editor.putString("password", etPassword.getText().toString());
            editor.putBoolean("remember", isRem);
        }else {
            editor.clear();
        }
        editor.commit();
    }
    public void save_ID(){
        SharedPreferences sharedPreferences = getSharedPreferences("idcus", MODE_PRIVATE);
    }


}