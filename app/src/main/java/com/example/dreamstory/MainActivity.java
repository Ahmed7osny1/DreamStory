package com.example.dreamstory;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.dreamstory.adapter.ViewPagerAdapter;
import com.example.dreamstory.databinding.ActivityMainBinding;
import com.example.dreamstory.ui.FavouriteFragment;
import com.example.dreamstory.ui.HomeFragment;
import com.example.dreamstory.ui.ShowMyPostsFragment;
import com.example.dreamstory.ui.SplashActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    MenuItem prevMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.navigation.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        if (item.getItemId() == R.id.home)
                            binding.viewpager.setCurrentItem(0);
                        else if (item.getItemId() == R.id.Favourites)
                            binding.viewpager.setCurrentItem(1);
                        else if (item.getItemId() == R.id.MyPosts)
                            binding.viewpager.setCurrentItem(2);
                        else if (item.getItemId() == R.id.logout) {

                            Intent intent = new Intent(MainActivity.this,
                                    SplashActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        return false;
                    }
                });

        binding.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    binding.navigation.getMenu().getItem(0).setChecked(false);
                }
                binding.navigation.getMenu().getItem(position).setChecked(true);
                prevMenuItem = binding.navigation.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setupViewPager(binding.viewpager);
    }

    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new HomeFragment());
        adapter.addFragment(new FavouriteFragment());
        adapter.addFragment(new ShowMyPostsFragment());
        viewPager.setAdapter(adapter);
    }
}