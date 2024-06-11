package com.example.dreamstory.ui;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dreamstory.R;
import com.example.dreamstory.data.Story;
import com.example.dreamstory.databinding.ActivityAddPostBinding;

import java.util.Calendar;
import java.util.Objects;

public class AddPostActivity extends AppCompatActivity {

    ActivityAddPostBinding binding;
    SharedPreferences sp;
    String Title, originLabel, date, textStory, language, location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityAddPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sp = getSharedPreferences("Login", MODE_PRIVATE);

        binding.date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { calenderShow(); }
        });

        binding.LabelRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);
                originLabel = radioButton.getText().toString();
            }

        });

        binding.addPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getData();
                Story story = new Story(
                        Title,
                        originLabel,
                        date,
                        textStory,
                        language,
                        String.valueOf(sp.getInt("UserId", 0)),
                        location,
                        "0",
                        "1"
                );
                Intent intent = new Intent();
                intent.putExtra("postValue", story);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        binding.backPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddPostActivity.this,
                        HomeFragment.class);
                startActivity(intent);
                finish();
            }
        });


    }

    private void calenderShow() {

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        binding.date.setText(day + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, day);
        //currentTimeMillis
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();

    }

    private void getData() {

        Title = Objects.requireNonNull(binding.title.getText()).toString();
        location = Objects.requireNonNull(binding.storyLocation.getText()).toString();
        language = Objects.requireNonNull(binding.storyLanguage.getText()).toString();
        textStory = Objects.requireNonNull(binding.storyText.getText()).toString();

        date = binding.date.getText().toString();

    }

}