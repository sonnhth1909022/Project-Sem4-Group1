package com.movieapp.movienavigation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.movieapp.movienavigation.R;
import com.movieapp.movienavigation.event.SaveID;
import com.movieapp.movienavigation.network.ApiManager;
import com.movieapp.movienavigation.response.PasswordDto;
import com.movieapp.movienavigation.response.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChangePasswordActivity extends AppCompatActivity {

    private EditText etNewPassword, etRePassword;
    private Button btChangePassword;
    SaveID saveID = new SaveID();
    int idCus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        etNewPassword = findViewById(R.id.etNewPassword);
        etRePassword = findViewById(R.id.etRePassword);

        btChangePassword = findViewById(R.id.btChangePassword);
        btChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickChangePassword();
            }
        });

    }

    private void clickChangePassword() {
        if(TextUtils.isEmpty(etNewPassword.getText().toString()) || TextUtils.isEmpty(etRePassword.getText().toString()) && etNewPassword.getText().toString() != etRePassword.getText().toString()) {
            String message = "Change Password Failed";
            Toast.makeText(ChangePasswordActivity.this, message, Toast.LENGTH_SHORT).show();
        }else {
            idCus = saveID.getId(ChangePasswordActivity.this);
            PasswordDto passwordDto = new PasswordDto();
            passwordDto.setNewPassword(etNewPassword.getText().toString());
            passwordDto.setRePassword(etRePassword.getText().toString());
            changePassword(idCus, passwordDto);
        }
    }

    private void changePassword(int idCus, PasswordDto passwordDto) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiManager.SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiManager service = retrofit.create(ApiManager.class);
        service.updatePassword(idCus, passwordDto).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    String message = "Change Password Successful";
                    Toast.makeText(ChangePasswordActivity.this,message,Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ChangePasswordActivity.this, ChangePasswordActivity.class));
                    finish();
                }else{
                    String message = "Change Password Failed";
                    Toast.makeText(ChangePasswordActivity.this,message,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(ChangePasswordActivity.this,message,Toast.LENGTH_SHORT).show();
            }
        });
    }
}