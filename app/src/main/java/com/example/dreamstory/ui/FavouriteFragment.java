package com.example.dreamstory.ui;

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
import com.example.dreamstory.adapter.StoryAdapter;
import com.example.dreamstory.data.Story;
import com.example.dreamstory.databinding.FragmentFavouriteBinding;
import com.example.dreamstory.dp.storyViewModel;
import java.util.ArrayList;
import java.util.List;

public class FavouriteFragment extends Fragment {

    FragmentFavouriteBinding binding;
    private StoryAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFavouriteBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        storyViewModel mStoryViewModel = new ViewModelProvider(this).get(storyViewModel.class);


        adapter = new StoryAdapter(getContext(),
                new ArrayList<>());
        binding.recPostFav.setAdapter(adapter);
        binding.recPostFav.setLayoutManager(new LinearLayoutManager(getContext()));


        mStoryViewModel.selectFavStory().observe((LifecycleOwner) requireActivity(),
                new Observer<List<Story>>() {
                    @Override
                    public void onChanged(List<Story> stories) {
                        if (!stories.isEmpty()) {
                            binding.emptyImg.setVisibility(View.INVISIBLE);
                            binding.recPostFav.setVisibility(View.VISIBLE);
                            adapter.setStorys(stories);
                        }else{
                            binding.emptyImg.setVisibility(View.VISIBLE);
                            binding.recPostFav.setVisibility(View.INVISIBLE);
                        }
                    }
                });
    }
}