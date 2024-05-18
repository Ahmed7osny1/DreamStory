package com.example.dreamstory.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.dreamstory.R;
import com.example.dreamstory.databinding.ActivityForgetPasswordBinding;

public class ForgetPasswordActivity extends AppCompatActivity {


    private ActivityForgetPasswordBinding binding;
    String emailForgot;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityForgetPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sp = getSharedPreferences("Login", MODE_PRIVATE);

        binding.loginForgotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { getPassword(); }
        });

        binding.bachBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgetPasswordActivity.this,
                        LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    private void getPassword() {

        emailForgot = binding.emailForgetEdt.getText().toString();

        if (emailForgot.isEmpty()) {
            Toast.makeText(ForgetPasswordActivity.this,
                    "Enter Your Email", Toast.LENGTH_LONG).show();
        } else if (!sp.getString("Email", "").equals(emailForgot)) {
            Toast.makeText(ForgetPasswordActivity.this,
                    "Your Email not Found", Toast.LENGTH_LONG).show();
        } else {
            binding.yourPassword.setVisibility(View.VISIBLE);
            binding.yourPassword.setText(sp.getString("Password", ""));
        }
    }

}