package com.example.dreamstory.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.dreamstory.R;
import com.example.dreamstory.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {


    Animation topAnim, bottomAnim;

    private ActivitySplashBinding binding;
    SharedPreferences sp;
    SharedPreferences.Editor edt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sp = getSharedPreferences("Login",MODE_PRIVATE);
        edt = sp.edit();

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);


        binding.splashImage.setAnimation(topAnim);
        binding.splashText.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if(sp.getBoolean("logined",false)){
                    intent = new Intent(SplashActivity.this,
                            HomeActivity.class);
                }else {
                    intent = new Intent(SplashActivity.this,
                            LoginActivity.class);
                }
                startActivity(intent);
                finish();
            }
        }, 5000);

    }
}