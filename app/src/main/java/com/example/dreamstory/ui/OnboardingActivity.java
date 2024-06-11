package com.example.dreamstory.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dreamstory.R;
import com.example.dreamstory.adapter.OnBoardingAdapter;
import com.example.dreamstory.data.OnboardingItem;
import com.example.dreamstory.databinding.ActivityOnboardingBinding;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class OnboardingActivity extends AppCompatActivity {
    OnBoardingAdapter onBoardingAdapter;
    int position = 0;
    Animation btnAnim;
    ActivityOnboardingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityOnboardingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        final List<OnboardingItem> mList = new ArrayList<>();
        mList.add(new OnboardingItem(
                "Capture Your Adventures",
                "Easily document your travels and create captivating stories to share with friends and family. Our app provides a seamless platform to chronicle your journeys, from stunning photographs to memorable anecdotes.",
                R.drawable.capture));
        mList.add(new OnboardingItem(
                "Discover the World Together",
                "Connect with a global community of adventurers and explorers. Explore travel stories, recommendations, and insights from fellow wanderers to inspire your next great adventure.",
                R.drawable.together));
        mList.add(new OnboardingItem(
                "Preserve Your Memories",
                "Effortlessly preserve your travel memories in a beautifully curated digital journal. Our app helps you organize your photos, notes, and experiences, ensuring your most cherished moments are safeguarded for years to come.",
                R.drawable.memories));

        onBoardingAdapter = new OnBoardingAdapter(this, mList);
        binding.screenViewpager.setAdapter(onBoardingAdapter);

        // setup tablayout with viewpager

        binding.tabIndicator.setupWithViewPager(binding.screenViewpager);

        // next button click Listner

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                position = binding.screenViewpager.getCurrentItem();
                if (position < mList.size()) {
                    position++;
                    binding.screenViewpager.setCurrentItem(position);
                }
                if (position == mList.size() - 1) {

                    loadedLastScreen();
                }
            }
        });


        binding.tabIndicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getPosition() < mList.size() - 1) {

                    PreviousScreen();

                } else if (tab.getPosition() == mList.size() - 1) {

                    loadedLastScreen();

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });


        // Get Started button click listener

        binding.btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //open main activity

                Intent mainActivity = new Intent(getApplicationContext(),
                        LoginActivity.class);
                startActivity(mainActivity);
                savePrefsData();
                finish();
            }
        });
        binding.tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.screenViewpager.setCurrentItem(mList.size());
            }
        });


    }

    private void savePrefsData() {

        SharedPreferences pref = getApplicationContext()
                .getSharedPreferences("Login", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isOnboardingOpened", true);
        editor.apply();
    }

    private void PreviousScreen() {

        binding.btnNext.setVisibility(View.VISIBLE);
        binding.btnGetStarted.setVisibility(View.INVISIBLE);
        binding.tvSkip.setVisibility(View.VISIBLE);
        binding.tabIndicator.setVisibility(View.VISIBLE);

    }

    private void loadedLastScreen() {

        binding.btnNext.setVisibility(View.INVISIBLE);
        binding.btnGetStarted.setVisibility(View.VISIBLE);
        binding.tvSkip.setVisibility(View.INVISIBLE);
        binding.tabIndicator.setVisibility(View.INVISIBLE);
        binding.btnGetStarted.setAnimation(btnAnim);


    }
}