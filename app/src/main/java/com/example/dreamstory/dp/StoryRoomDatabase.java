package com.example.dreamstory.dp;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.dreamstory.dao.storyDao;
import com.example.dreamstory.data.Story;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Story.class}, version = 1, exportSchema = false)
public abstract class StoryRoomDatabase extends RoomDatabase {

    public abstract storyDao storyDao();

    private static volatile StoryRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static StoryRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (StoryRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    StoryRoomDatabase.class, "story_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
