package com.example.dreamstory.ui;

import static android.content.Context.MODE_PRIVATE;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        binding.addPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddPostActivity.class);
                startActivity(intent);
            }
        });

    }
}