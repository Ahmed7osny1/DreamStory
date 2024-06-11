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

    @Query("delete from story where id =:id")
    void deleteStory(int id);

    @Query("select * from story")
    LiveData<List<Story>> selectAllStory();

    @Query("select * from story where favStatus =:favStatus")
    LiveData<List<Story>> selectFavStory(String favStatus);

    @Query("select * from story where storyStatus =:storyStatus")
    LiveData<List<Story>> selectMyStory(String storyStatus);

    @Query("select * from story where originLabel =:originLabel")
    LiveData<List<Story>> selectOriginLabelStory(String originLabel);

}
