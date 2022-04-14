package com.movieapp.movienavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.movieapp.movienavigation.activity.ChangePasswordActivity;
import com.movieapp.movienavigation.activity.LoginActivity;
import com.movieapp.movienavigation.activity.ProfileActivity;
import com.movieapp.movienavigation.fragment.FavoriteFragment;
import com.movieapp.movienavigation.fragment.HomeFragment;
import com.movieapp.movienavigation.fragment.LibraryFragment;
import com.movieapp.movienavigation.fragment.SearchFragment;
import com.movieapp.movienavigation.fragment.SubscriptionFragment;
import com.movieapp.movienavigation.network.ApiManager;
import com.movieapp.movienavigation.response.TransactionResDto;
import com.movieapp.movienavigation.response.User;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_FAVORITE = 1;
    private static final int FRAGMENT_LIBRARY = 2;
    private static final int FRAGMENT_SEARCH = 3;
    private static final int FRAGMENT_SUB = 4;
    private int FRAGMENT_CURRENT = FRAGMENT_HOME;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private TextView username, subscribe, usernameP, emailP;
    private EditText usernameProfile, etEmail;
    private User user;
    public SearchView searchView;
    public int id;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar();


    }


    private void toolbar() {

        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_drawer_open, R.string.nav_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //bắt sự kiên item
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        replaceFragment(new HomeFragment());
        navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);

        //
        navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
        View headerView = navigationView.getHeaderView(0);
        // get user name and email textViews
//        TextView userName = headerView.findViewById(R.id.tvUsername);
        //TextView userEmail = headerView.findViewById(R.id.text_email_address);
        username = headerView.findViewById(R.id.tvUsername);

        subscribe = headerView.findViewById(R.id.tvSubscribe);
        //email = headerView.findViewById(R.id.tvEmail);
        Intent i=getIntent();
        username.setText(i.getStringExtra("name"));
        String accountType = i.getStringExtra("accountType");
        if(accountType.equalsIgnoreCase("ACCOUNT_VIP")){
            subscribe.setText("Subscribed");
        }else {
            subscribe.setText("Not Subscribed");
        }
       // email.setText(i.getStringExtra("email"));
        id=i.getIntExtra("id",0);
       // username.setText(String.valueOf(id));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            if (FRAGMENT_CURRENT != FRAGMENT_HOME) {
                replaceFragment(new HomeFragment());
                FRAGMENT_CURRENT = FRAGMENT_HOME;

            }
        } else if (id == R.id.nav_favorite) {
            if (FRAGMENT_CURRENT != FRAGMENT_FAVORITE) {
                replaceFragment(new FavoriteFragment());
                FRAGMENT_CURRENT = FRAGMENT_FAVORITE;
            }

        } else if(id == R.id.nav_my_profile){
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(intent);
        }else if(id == R.id.nav_change_password) {
            Intent intent = new Intent(MainActivity.this, ChangePasswordActivity.class);
            startActivity(intent);
        }else if(id == R.id.nav_logout){
            SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }else if(id==R.id.nav_sub){
            if (FRAGMENT_CURRENT != FRAGMENT_SUB) {
                replaceFragment(new SubscriptionFragment());
                FRAGMENT_CURRENT = FRAGMENT_SUB;
            }
        }else if(id==R.id.nav_library){
            if (FRAGMENT_CURRENT!=FRAGMENT_LIBRARY){
                replaceFragment(new LibraryFragment());
                FRAGMENT_CURRENT=FRAGMENT_LIBRARY;
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search Movie Here");
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            //startActivity(new Intent(MainActivity.this, SearchActivity.class));
            // finish();
            if (FRAGMENT_CURRENT != FRAGMENT_SEARCH) {
                replaceFragment(new SearchFragment());
                FRAGMENT_CURRENT = FRAGMENT_SEARCH;
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}