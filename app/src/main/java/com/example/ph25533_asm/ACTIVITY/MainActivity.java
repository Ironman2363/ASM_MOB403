package com.example.ph25533_asm.ACTIVITY;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.ph25533_asm.ADAPTER.ViewPagerAdapter;
import com.example.ph25533_asm.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private ViewPager2 viewPager2;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_nav);
        viewPager2 = findViewById(R.id.viewpager2);
        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager2.setAdapter(viewPagerAdapter);

        viewPager2.setUserInputEnabled(false);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.item_home) {
                    viewPager2.setCurrentItem(0);
                    return true;
                } else if (item.getItemId() == R.id.item_cart) {
                    viewPager2.setCurrentItem(1);
                    return true;
                } else if (item.getItemId() == R.id.item_favourite) {
                    viewPager2.setCurrentItem(2);
                    return true;
                } else if (item.getItemId() == R.id.item_search) {
                    viewPager2.setCurrentItem(3);
                    return true;
                } else if (item.getItemId() == R.id.item_user) {
                    viewPager2.setCurrentItem(4);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        // Handle the back button action here if needed.
        // You can add logic to handle the back press according to your app requirements.
        super.onBackPressed();
    }
}
