package com.example.dreamstory.dp;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.dreamstory.data.Story;

import java.util.List;

public class storyViewModel extends AndroidViewModel {

    private RepositoryStory mRepository;

    public storyViewModel (Application application) {
        super(application);
        mRepository = new RepositoryStory(application);
    }

    public void insertStory(Story... story){
        mRepository.insertStory(story);
    }

    public void updateStory(int id){
        mRepository.updateStory(id);
    }

    public void deleteStory(int id){
        mRepository.deleteStory(id);
    }

    public LiveData<List<Story>> selectAllStory(){
        return mRepository.selectAllStory();
    }

    public LiveData<List<Story>> selectFavStory(String favStatus){
        return mRepository.selectFavStory(favStatus);
    }

    public LiveData<List<Story>> selectMyStory(String storyStatus){
        return mRepository.selectMyStory(storyStatus);
    }

    public LiveData<List<Story>> selectOriginLabelStory(String originLabel){
        return mRepository.selectOriginLabelStory(originLabel);
    }

}
