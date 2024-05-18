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
import com.example.dreamstory.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;

    String email, password, userName;

    SharedPreferences sp;
    SharedPreferences.Editor edt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sp = getSharedPreferences("Login", MODE_PRIVATE);
        edt = sp.edit();

        binding.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { RegisterAuth(); }
        });

        binding.returnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,
                        LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void RegisterAuth(){

        userName = binding.userNameEdt.getText().toString();
        email = binding.emailEdt.getText().toString();
        password = binding.passwordEdt.getText().toString();

        if(email.isEmpty() || password.isEmpty() || userName.isEmpty()){
            if(email.isEmpty())
                Toast.makeText(RegisterActivity.this,"Enter Your Email",
                        Toast.LENGTH_LONG).show();
            if(password.isEmpty())
                Toast.makeText(RegisterActivity.this,"Enter Your Password",
                        Toast.LENGTH_LONG).show();
            if(userName.isEmpty())
                Toast.makeText(RegisterActivity.this,"Enter Your User Name",
                        Toast.LENGTH_LONG).show();
        } else {

            edt.putString("UserName", userName);
            edt.putString("Email", email);
            edt.putString("Password", password);

            edt.apply();

            Toast.makeText(RegisterActivity.this,
                    "Your Account created Successfully",
                    Toast.LENGTH_LONG).show();

            Intent intent = new Intent(RegisterActivity.this,
                    LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

}