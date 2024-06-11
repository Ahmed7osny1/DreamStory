package com.example.dreamstory.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dreamstory.R;
import com.example.dreamstory.data.Story;
import com.example.dreamstory.dp.storyViewModel;
import java.util.List;
import java.util.Objects;

public class StoryAdapter extends RecyclerView.
        Adapter<StoryAdapter.viewHolder> {

    private List<Story> storys;
    private final Context context;
    storyViewModel mStoryViewModel;

    public StoryAdapter(Context context,
                        List<Story> storys) {
        this.context = context;
        this.storys = storys;
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

        if(Objects.equals(mStory.getFavStatus(), "1"))
            holder.favBtn.setImageResource(R.drawable.ic_fav_red);
        else holder.favBtn.setImageResource(R.drawable.ic_fav);

        holder.favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mStoryViewModel = new ViewModelProvider(
                        (ViewModelStoreOwner) context).get(storyViewModel.class);
                if (Objects.equals(mStory.getFavStatus(), "0")) {
                    mStoryViewModel.updateStory(mStory.getId());
                    holder.favBtn.setImageResource(R.drawable.ic_fav_red);
                }
                else {
                    mStoryViewModel.updateStoryNotFav(mStory.getId());
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
