package com.movieapp.movienavigation.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.movieapp.movienavigation.MainActivity;
import com.movieapp.movienavigation.R;
import com.movieapp.movienavigation.response.User;

public class ProfileFragment extends Fragment {
    EditText etEmail, etPassword, etUsername, etPhone;
    Button btChangeProfile;
    User user;
    MainActivity mainActivity;
    private View view;
//    SharedPreferences sharedPreferences;
//    private static final String SHARED_PREF_NAME = "mypref";
//    private static final String KEY_USERNAME = "username";
//    private static final String KEY_PASSWORD = "password";

//    public static ProfileFragment getInstance(User user){
//        ProfileFragment profileFragment = new ProfileFragment();
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("user", user);
//        profileFragment.setArguments(bundle);
//        return profileFragment;
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile,container,false);
//        btChangeProfile = root.findViewById(R.id.btChangeProfile);
//        etUsername = view.findViewById(R.id.etUsername1);
//        mainActivity = (MainActivity) getActivity();
//
//        btChangeProfile = root.findViewById(R.id.btChangeProfile);
//        sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        String tvUsername = sharedPreferences.getString(KEY_USERNAME, null);
//        etUsername.setText(tvUsername);
        loadData();
//        clickChangeProfile();
        return root;
    }



//    private void clickChangeProfile() {
//        btChangeProfile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    user.setUsername(etUsername.getText().toString());
//                    user.setEmail(etEmail.getText().toString());
//                    user.setPhone(etPhone.getText().toString());
//                    user.setPassword(etPassword.getText().toString());
//                    Call<User> userCall = ApiService.apiService.updateUser(user);
//                    userCall.enqueue(new Callback<User>() {
//
//                        @Override
//                        public void onResponse(Call<User> call, Response<User> response) {
//                            if(response.isSuccessful()){
//                                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
//                                startActivity(intent);
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<User> call, Throwable t) {
//                            Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                }catch (Exception e){
//                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }

    private void loadData() {
        etUsername = view.findViewById(R.id.etUsername1);
//        User user = (User) getArguments().get("user");
       // etUsername.setText(user.getUsername());
        etUsername.setText(getArguments().getString("username"));
//        Bundle bundle = getIntent().getExtras();
//        Bundle bundle = getActivity().getIntent().getExtras();
//        if(bundle != null){
//            user = (User) bundle.get("user");
//            if(user != null){
//                etUsername.setText(user.getUsername());
//                etEmail.setText(user.getEmail());
//                etPhone.setText(user.getPhone());
//                etPassword.setText(user.getPassword());
//            }
//        }
//        etEmail = view.findViewById(R.id.tvUsernameP);
//        User user = (User) getArguments().get("user");
//        etEmail.setText(user.getUsername());
//        etUsername = view.findViewById(R.id.tvUsernameP);
//        Bundle i = getActivity().getIntent().getExtras();
//        usernameProfile.setText(i.getStringExtra("name"));
    }
}