package com.example.dreamstory.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.dreamstory.R;

public class LoginActivity extends AppCompatActivity {

    Button logBtn;
    EditText emailInput, passwordInput;
    String email, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);


        logBtn = findViewById(R.id.loginBtn);
        emailInput = findViewById(R.id.emailEdt);
        passwordInput = findViewById(R.id.passwordEdt);


        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = emailInput.getText().toString();
                pass = passwordInput.getText().toString();

                if(email.isEmpty() || pass.isEmpty()){
                    if(email.isEmpty())
                        Toast.makeText(LoginActivity.this,"Enter Your Email",
                                Toast.LENGTH_LONG).show();
                    if(pass.isEmpty())
                        Toast.makeText(LoginActivity.this,"Enter Your Password",
                                Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(LoginActivity.this,
                            HomeActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });

    }
}