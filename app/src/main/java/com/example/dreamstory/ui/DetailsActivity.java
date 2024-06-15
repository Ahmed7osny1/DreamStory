package com.example.dreamstory.ui;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dreamstory.data.Story;
import com.example.dreamstory.databinding.ActivityDetailsBinding;

public class DetailsActivity extends AppCompatActivity {

    ActivityDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Story story = (Story) getIntent().getSerializableExtra("story");

        binding.Title.setText(story.getTitle());
        binding.date.setText(story.getDate());
        binding.originLabel.setText(story.getOriginLabel());
        binding.storyLanguage.setText(story.getLanguage());
        binding.storyLocation.setText(story.getLocation());
        binding.textStory.setText(story.getTextStory());

    }
}