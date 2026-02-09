package com.example.foodplannerapp.data.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodplannerapp.data.favorite.DataSource.FavoriteMealDao;
import com.example.foodplannerapp.data.favorite.DataSource.FavoriteMealEntity;
import com.example.foodplannerapp.data.weeklyplan.DataSource.local.WeeklyMealDao;
import com.example.foodplannerapp.data.weeklyplan.DataSource.local.WeeklyMealEntity;
@Database(
        entities = {
                WeeklyMealEntity.class,
                FavoriteMealEntity.class
        },
        version = 2,
        exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    AppDatabase.class,
                                    "food_planner_db"
                            )
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract WeeklyMealDao weeklyMealDao();
    public abstract FavoriteMealDao favoriteMealDao();
}
