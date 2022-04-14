package com.movieapp.movienavigation.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.movieapp.movienavigation.MainActivity;
import com.movieapp.movienavigation.R;
import com.movieapp.movienavigation.event.SaveID;
import com.movieapp.movienavigation.network.ApiManager;
import com.movieapp.movienavigation.response.User;
import com.movieapp.movienavigation.response.UserDto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileActivity extends AppCompatActivity {

    private EditText etUsername1, etEmail, etPassword, etPhone;
    private TextView tvUsername;
    private User user;
    Button btChangeProfile1;
    SaveID saveID = new SaveID();
    int idCus1;
    public int idCus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvUsername = findViewById(R.id.tvUsername);
        etUsername1 = findViewById(R.id.etUsername1);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);

        showInfoRem();

        btChangeProfile1 = findViewById(R.id.btChangeProfile);
        btChangeProfile1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickChangeProfile();
            }
        });
    }

    private void clickChangeProfile() {
        if(TextUtils.isEmpty(etUsername1.getText().toString()) || TextUtils.isEmpty(etEmail.getText().toString()) || TextUtils.isEmpty(etPhone.getText().toString())){
            String message = "All inputs required";
            Toast.makeText(ProfileActivity.this,message,Toast.LENGTH_SHORT).show();
        }else {
            idCus1 = saveID.getId(ProfileActivity.this);
            UserDto userDto = new UserDto();
            userDto.setUsername(etUsername1.getText().toString());
            userDto.setEmail(etEmail.getText().toString());
            userDto.setPhone(etPhone.getText().toString());
            changeProfile(idCus1, userDto);
        }
    }

    private void changeProfile(int idCus1, UserDto userDto) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiManager.SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiManager service = retrofit.create(ApiManager.class);
        service.updateUser(idCus1, userDto).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    saveInfo();
                    String message = "Change Profile Successful";
                    Toast.makeText(ProfileActivity.this,message,Toast.LENGTH_SHORT).show();
                    User user = response.body();
                    startActivity(new Intent(ProfileActivity.this, ProfileActivity.class)
                            .putExtra("name",user.getUsername())
                            .putExtra("email", user.getEmail()));
//                            .putExtra("phone", user.getPhone())
//                            .putExtra("password", user.getPassword())
//                            .putExtra("id",user.getId()));
                    idCus = saveID.getId(ProfileActivity.this);
                    finish();
                }else{
                    String message = "Change Profile Failed";
                    Toast.makeText(ProfileActivity.this,message,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(ProfileActivity.this,message,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showInfoRem(){
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
        String tvUsername1 = sharedPreferences.getString("username", "");
        String un = sharedPreferences.getString("username","");
        String email = sharedPreferences.getString("email","");
        String phone = sharedPreferences.getString("phone","");
        tvUsername.setText(tvUsername1);
        etUsername1.setText(un);
        etEmail.setText(email);
        etPhone.setText(phone);
    }

    private void saveInfo(){
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", etUsername1.getText().toString());
        editor.putString("email", etEmail.getText().toString());
        editor.putString("phone", etPhone.getText().toString());
        editor.commit();
    }
}