package com.example.dreamstory.dp;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.example.dreamstory.dao.storyDao;
import com.example.dreamstory.data.Story;
import java.util.List;

public class RepositoryStory {
    private storyDao mStoryDao;
    // private LiveData<List<Story>> mAllStorys;

    RepositoryStory(Application application) {
        StoryRoomDatabase db = StoryRoomDatabase.getDatabase(application);
        mStoryDao = db.storyDao();
    }

    public void insertStory(Story... story){
        StoryRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mStoryDao.insertStory(story);
            }
        });
    }

    public void updateStory(int id){
        StoryRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mStoryDao.updateStory(id);
            }
        });
    }

    public void updateStoryNotFav(int id){
        StoryRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mStoryDao.updateStoryNotFav(id);
            }
        });
    }

    public void deleteStory(int id){
        StoryRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mStoryDao.deleteStory(id);
            }
        });
    }

    public LiveData<List<Story>> selectAllStory(){
        return mStoryDao.selectAllStory();
    }

    public LiveData<List<Story>> selectFavStory(){
        return mStoryDao.selectFavStory();
    }

    public LiveData<List<Story>> selectMyStory(){
        return mStoryDao.selectMyStory();
    }

    public LiveData<List<Story>> selectOriginLabelStory(String originLabel){
        return mStoryDao.selectOriginLabelStory(originLabel);
    }

}
