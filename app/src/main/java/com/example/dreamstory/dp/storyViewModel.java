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

    public void updateStoryNotFav(int id){
        mRepository.updateStoryNotFav(id);
    }

    public void deleteStory(int id){
        mRepository.deleteStory(id);
    }

    public LiveData<List<Story>> selectAllStory(){
        return mRepository.selectAllStory();
    }

    public LiveData<List<Story>> selectFavStory(){
        return mRepository.selectFavStory();
    }

    public LiveData<List<Story>> selectMyStory(){
        return mRepository.selectMyStory();
    }

    public LiveData<List<Story>> selectOriginLabelStory(String originLabel){
        return mRepository.selectOriginLabelStory(originLabel);
    }

}
