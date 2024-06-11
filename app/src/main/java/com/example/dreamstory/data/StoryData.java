package com.example.dreamstory.data;

import android.content.Context;
import com.example.dreamstory.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;


public class StoryData {

    ArrayList<Story> story;

    public String readJSON(Context context) {
        String json = null;

        try {
            InputStream is = context.getResources().openRawResource(R.raw.cis);
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

    /* public ArrayList<Story> parseJSON (String jsonStr, Context context) {

        FavDB favDB = new FavDB(context);

        story = new ArrayList<>();

        if (jsonStr != null) {
            try {
                JSONArray cis = new JSONArray(jsonStr);
                for (int i = 0; i < cis.length(); i++) {
                    JSONObject c = cis.getJSONObject(i);

                    int id = c.getInt("id");
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
                            id,
                            title,
                            originLabel,
                            date,
                            textStory,
                            language,
                            authorID,
                            location,
                            "0"
                    );

                    favDB.insertIntoTheDatabase(
                            id,
                            title,
                            originLabel,
                            date,
                            textStory,
                            language,
                            authorID,
                            location,
                            "0"
                    );

                    story.add(storyData);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return story;
    }*/

    public ArrayList<Story> getLabel(String label, ArrayList<Story> story){

        ArrayList<Story> storyLabel;

        storyLabel = new ArrayList<>();

        for(int i = 0; i < story.size();i++){
            if(Objects.equals(story.get(i).getOriginLabel(), label)){
                storyLabel.add(story.get(i));
            }
        }
        return storyLabel;
    }

}

