package com.example.foodplannerapp.data.weeklyplan.DataSource.local;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "weekly_meals")
public class WeeklyMealEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String mealId;
    private String mealName;
    private String mealThumb;


    private long selectedDate;


    public WeeklyMealEntity(String mealId, String mealName,
                            String mealThumb, long selectedDate) {
        this.mealId = mealId;
        this.mealName = mealName;
        this.mealThumb = mealThumb;
        this.selectedDate = selectedDate;
    }

    public int getId() { return id; }
    public String getMealId() { return mealId; }
    public String getMealName() { return mealName; }
    public String getMealThumb() { return mealThumb; }
    public long getSelectedDate() { return selectedDate; }


    public void setId(int id) {
        this.id = id;
    }
}

