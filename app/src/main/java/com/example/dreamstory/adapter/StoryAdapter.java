package com.example.dreamstory.adapter;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dreamstory.R;
import com.example.dreamstory.data.Story;
import com.example.dreamstory.databinding.ActivityHomeBinding;
import com.example.dreamstory.dp.storyViewModel;

import java.util.List;
import java.util.Objects;

public class StoryAdapter extends RecyclerView.
        Adapter<StoryAdapter.viewHolder> {

    private List<Story> storys;
    private storyViewModel mViewModel;

    public StoryAdapter(List<Story> storys, storyViewModel mViewModel) {
        this.storys = storys;
        this.mViewModel = mViewModel;
    }

    public List<Story> getStorys() {
        return storys;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setStorys(List<Story> storys) {
        this.storys = storys;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        Story mStory = storys.get(position);

        holder.storyDate.setText(mStory.getDate());
        holder.storyTitle.setText(mStory.getTitle());
        holder.storyReflection.setText(mStory.getTextStory());
        holder.storyLocation.setText(mStory.getLocation());
        holder.storyLanguage.setText(mStory.getLanguage());

        holder.favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Objects.equals(mStory.getFavStatus(), "0")) {

                    holder.favBtn.setImageResource(R.drawable.ic_fav_red);
                }
                else {
                    holder.favBtn.setImageResource(R.drawable.ic_fav);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return storys.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        TextView storyDate, storyTitle, storyReflection, storyLocation, storyLanguage;
        ImageView favBtn;

        public viewHolder(@NonNull View v) {
            super(v);
            storyDate = v.findViewById(R.id.storyDate);
            storyTitle = v.findViewById(R.id.storyTitle);
            storyReflection = v.findViewById(R.id.storyText);
            storyLocation = v.findViewById(R.id.storyLocation);
            storyLanguage = v.findViewById(R.id.storyLanguage);
            favBtn = v.findViewById(R.id.favBtn);
        }

    }
}
