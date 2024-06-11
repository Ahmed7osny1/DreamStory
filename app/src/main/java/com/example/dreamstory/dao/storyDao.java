package com.example.dreamstory.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.dreamstory.data.Story;
import java.util.List;

@Dao
public interface storyDao {
    @Insert
    void insertStory(Story... story);

    @Query("update story set favStatus = 1 where id =:id")
    void updateStory(int id);
    @Query("update story set favStatus = 0 where id =:id")
    void updateStoryNotFav(int id);

    @Query("select * from story")
    LiveData<List<Story>> selectAllStory();

    @Query("select * from story where favStatus = 1")
    LiveData<List<Story>> selectFavStory();

    @Query("select * from story where storyStatus = 1")
    LiveData<List<Story>> selectMyStory();

}
