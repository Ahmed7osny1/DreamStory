package com.example.dreamstory.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dreamstory.R;
import com.example.dreamstory.adapter.StoryAdapter;
import com.example.dreamstory.data.Story;
import com.example.dreamstory.databinding.FragmentShowMyPostsBinding;
import com.example.dreamstory.dp.storyViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShowMyPostsFragment extends Fragment {

    FragmentShowMyPostsBinding binding;
    private StoryAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentShowMyPostsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        storyViewModel mStoryViewModel = new ViewModelProvider(this)
                .get(storyViewModel.class);

        adapter = new StoryAdapter(getContext(),
                new ArrayList<>(),this::onItemClicked);
        binding.recMyPost.setAdapter(adapter);
        binding.recMyPost.setLayoutManager(new LinearLayoutManager(getContext()));


        mStoryViewModel.selectMyStory().observe((LifecycleOwner) requireContext(),
                new Observer<List<Story>>() {
                    @Override
                    public void onChanged(List<Story> stories) {
                        if (!stories.isEmpty()) {
                            binding.emptyImg.setVisibility(View.INVISIBLE);
                            binding.recMyPost.setVisibility(View.VISIBLE);
                            adapter.setStorys(stories);
                        } else {
                            binding.emptyImg.setVisibility(View.VISIBLE);
                            binding.recMyPost.setVisibility(View.INVISIBLE);
                        }
                    }
                });
    }
    private void onItemClicked(Story story) {

        Intent intent = new Intent(getActivity(),DetailsActivity.class);
        intent.putExtra("story", story);
        startActivity(intent);
    }
}