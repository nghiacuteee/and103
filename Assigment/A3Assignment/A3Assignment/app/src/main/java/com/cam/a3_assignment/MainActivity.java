package com.cam.a3_assignment;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.cam.a3_assignment.fragment.CartScreen;
import com.cam.a3_assignment.fragment.HomeScreen;
import com.cam.a3_assignment.fragment.SearchScreen;
import com.cam.a3_assignment.fragment.UserScreen;
import com.cam.a3_assignment.model.Account;
import com.cam.a3_assignment.model.Category;
import com.cam.a3_assignment.model.Product;
import com.cam.a3_assignment.model.Response;
import com.cam.a3_assignment.services.HttpReq;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FragmentManager fragmentManager;
    private HomeScreen homeScreen;
    private SearchScreen searchScreen;
    private CartScreen cartScreen;
    private UserScreen userScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Account account = (Account) getIntent().getSerializableExtra("account");
        Bundle bundle = new Bundle();
        bundle.putSerializable("account", account);

        homeScreen = new HomeScreen();
        searchScreen = new SearchScreen();
        cartScreen = new CartScreen();
        userScreen = new UserScreen();
        userScreen.setArguments(bundle);

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer, new HomeScreen()).commit();

        bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setItemRippleColor(null);
        bottomNavigationView.setSelectedItemId(R.id.homeScreen);

        bottomNavigationView.setOnItemSelectedListener(menuItem -> {
            if(menuItem.getItemId() == R.id.homeScreen){
                fragmentManager.beginTransaction().replace(R.id.fragmentContainer, homeScreen).commitNow();
            } else if (menuItem.getItemId() == R.id.searchScreen) {
                fragmentManager.beginTransaction().replace(R.id.fragmentContainer, searchScreen).commit();
            } else if (menuItem.getItemId() == R.id.cartScreen) {
                fragmentManager.beginTransaction().replace(R.id.fragmentContainer, cartScreen).commit();
            } else if (menuItem.getItemId() == R.id.userScreen) {
                fragmentManager.beginTransaction().replace(R.id.fragmentContainer, userScreen).commit();
            }
            return true;
        });
    }
}