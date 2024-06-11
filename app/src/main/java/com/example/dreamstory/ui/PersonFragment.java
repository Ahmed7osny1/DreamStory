package com.example.dreamstory.ui;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dreamstory.data.Story;
import com.example.dreamstory.databinding.FragmentPersonBinding;

public class PersonFragment extends Fragment {

    FragmentPersonBinding binding;
    SharedPreferences prefs;
    SharedPreferences.Editor prefsEditor;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPersonBinding.inflate(inflater, container, false);

        // Create Shared Preferences
        prefs = this.requireActivity()
                .getSharedPreferences("Login", MODE_PRIVATE);
        prefsEditor = prefs.edit();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.yourName.setText(prefs.getString("UserName", ""));
        binding.yourEmail.setText(prefs.getString("Email", ""));
        binding.yourPassword.setText(prefs.getString("Password", ""));
        binding.yourPhone.setText(prefs.getString("Phone", ""));

        ActivityResultLauncher<Intent> arl = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) { }
                }
        );
        binding.showMyPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ShowMyPostsActivity.class);
                arl.launch(intent);
            }
        });

    }
}