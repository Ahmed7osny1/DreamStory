package com.example.dreamstory.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dreamstory.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    String email, pass;

    SharedPreferences sp;
    SharedPreferences.Editor edt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sp = getSharedPreferences("Login", MODE_PRIVATE);
        edt = sp.edit();
        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginAuth();
            }
        });

        binding.noAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,
                        RegisterActivity.class);
                startActivity(intent);
            }
        });

        binding.forgetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,
                        ForgetPasswordActivity.class);
                startActivity(intent);
            }
        });

    }

    // Check login success or not
    private void loginAuth() {
        email = binding.emailEdt.getText().toString();
        pass = binding.passwordEdt.getText().toString();

        if (email.isEmpty() || pass.isEmpty()) {

            if (email.isEmpty() && pass.isEmpty()) {
                Toast.makeText(LoginActivity.this,
                        "Enter Your Email and Password",
                        Toast.LENGTH_LONG).show();
            } else {

                if (email.isEmpty())
                    Toast.makeText(LoginActivity.this, "Enter Your Email",
                            Toast.LENGTH_LONG).show();
                if (pass.isEmpty())
                    Toast.makeText(LoginActivity.this, "Enter Your Password",
                            Toast.LENGTH_LONG).show();

            }

        } else if (!sp.getString("Email", "").equals(email) ||
                !sp.getString("Password", "").equals(pass)) {

            if (!sp.getString("Email", "").equals(email) &&
                    !sp.getString("Password", "").equals(pass)) {

                Toast.makeText(LoginActivity.this, "Your Email and Password Wrong",
                        Toast.LENGTH_LONG).show();

            } else {

                if (!sp.getString("Email", "").equals(email))
                    Toast.makeText(LoginActivity.this, "Your Email is wrong",
                            Toast.LENGTH_LONG).show();
                if (!sp.getString("Password", "").equals(pass))
                    Toast.makeText(LoginActivity.this, "Your Password is wrong",
                            Toast.LENGTH_LONG).show();
            }

        } else {


            edt.putBoolean("logined", true);

            edt.apply();

            Intent intent = new Intent(LoginActivity.this,
                    HomeActivity.class);
            startActivity(intent);
            finish();

        }
    }

}