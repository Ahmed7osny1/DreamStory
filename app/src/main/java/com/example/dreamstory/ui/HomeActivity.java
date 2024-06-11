package com.example.dreamstory.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.dreamstory.R;
import com.example.dreamstory.adapter.StoryAdapter;
import com.example.dreamstory.data.Story;
import com.example.dreamstory.databinding.ActivityHomeBinding;
import com.example.dreamstory.dp.storyViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    storyViewModel mStoryViewModel;
    StoryAdapter adapter;
    SharedPreferences sp;
    SharedPreferences.Editor edt;
    String jsonStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mStoryViewModel = new ViewModelProvider(this).get(storyViewModel.class);


        adapter = new StoryAdapter(HomeActivity.this,
                new ArrayList<>(),mStoryViewModel);
        binding.recPost.setAdapter(adapter);
        binding.recPost.setLayoutManager(new LinearLayoutManager(this));

        sp = getSharedPreferences("Login", MODE_PRIVATE);
        binding.nameTxt.setText(sp.getString("UserName",""));

        edt = sp.edit();

        if(!sp.getBoolean("dataFetched", false)) {
            parseJSON();
        }


        mStoryViewModel.selectAllStory().observe(this,
                new Observer<List<Story>>() {
                    @Override
                    public void onChanged(List<Story> stories) {
                        adapter.setStorys(stories);
                    }
                });

        ActivityResultLauncher<Intent> arl = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                            if(o.getResultCode() == RESULT_OK && o.getData() != null){
                                Story story = (Story) o.getData().
                                        getSerializableExtra("postValue");
                                mStoryViewModel.insertStory(story);
                            }
                    }
                }
        );
        binding.addPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,
                        AddPostActivity.class);

                arl.launch(intent);
            }
        });
    }
    public String readJSON() {
        String json = null;

        try {
            InputStream is = this.getResources().openRawResource(R.raw.cis);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void parseJSON () {

        edt.putBoolean("dataFetched",true);
        edt.apply();

        jsonStr = readJSON();

        if (jsonStr != null) {
            try {
                JSONArray cis = new JSONArray(jsonStr);
                for (int i = 0; i < cis.length(); i++) {
                    JSONObject c = cis.getJSONObject(i);

                    String title = c.getString("title");
                    String originLabel = c.getJSONObject("origin").getString("label");
                    String date = c.getString("created");
                    String textStory = c.getJSONObject("textStory").getString("story");
                    String location = c.getJSONObject("location").getJSONObject("country").getString("label");
                    String language = c.getJSONObject("textStory").getJSONArray("languages").getJSONObject(0).getString("label");
                    JSONObject authorGender = c.getJSONObject("textStory").getJSONObject("author");
                    String authorID;
                    if (authorGender.isNull("gender")) {
                        authorID = "NOT FOUND";
                    } else {
                        authorID = authorGender.getJSONObject("gender").getString("id");
                    }


                    Story storyData = new Story(
                            title,
                            originLabel,
                            date,
                            textStory,
                            language,
                            authorID,
                            location,
                            "0",
                            "0"
                    );

                    mStoryViewModel.insertStory(storyData);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


}