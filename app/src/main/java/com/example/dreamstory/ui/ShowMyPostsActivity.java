package com.example.dreamstory.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.window.OnBackInvokedDispatcher;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.dreamstory.R;
import com.example.dreamstory.adapter.StoryAdapter;
import com.example.dreamstory.data.Story;
import com.example.dreamstory.databinding.ActivityShowMyPostsBinding;
import com.example.dreamstory.dp.storyViewModel;

import java.util.ArrayList;
import java.util.List;

public class ShowMyPostsActivity extends AppCompatActivity {

    ActivityShowMyPostsBinding binding;
    private StoryAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityShowMyPostsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        storyViewModel mStoryViewModel = new ViewModelProvider(this).get(storyViewModel.class);


        adapter = new StoryAdapter(this,
                new ArrayList<>());
        binding.recMyPost.setAdapter(adapter);
        binding.recMyPost.setLayoutManager(new LinearLayoutManager(this));


        mStoryViewModel.selectMyStory().observe(this,
                new Observer<List<Story>>() {
                    @Override
                    public void onChanged(List<Story> stories) {
                        if (!stories.isEmpty()) {
                            binding.emptyImg.setVisibility(View.INVISIBLE);
                            binding.recMyPost.setVisibility(View.VISIBLE);
                            adapter.setStorys(stories);
                        }else{
                            binding.emptyImg.setVisibility(View.VISIBLE);
                            binding.recMyPost.setVisibility(View.INVISIBLE);
                        }
                    }
                });

        binding.backShowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

    }
}