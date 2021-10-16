package com.bd.soc71bazaar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.bd.soc71bazaar.fragmentpage.AccountFragment;
import com.bd.soc71bazaar.fragmentpage.CardFragment;
import com.bd.soc71bazaar.fragmentpage.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().addToBackStack("home").replace(R.id.container,
                new HomeFragment()).commit();
    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            getSupportFragmentManager().beginTransaction().addToBackStack("home").replace(R.id.container,
                                new HomeFragment()).commit();
                            return true;
                        case R.id.navigation_card:
                            getSupportFragmentManager().beginTransaction().addToBackStack("home").replace(R.id.container,
                                    new CardFragment()).commit();
                            return true;
                        case R.id.navigation_profile:
                            getSupportFragmentManager().beginTransaction().addToBackStack("home").replace(R.id.container,
                                    new AccountFragment()).commit();
                            return true;
                    }
                    return false;
                }
            };
}