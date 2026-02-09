package com.example.foodplannerapp.data.favorite.DataSource;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorite_meals")
public class FavoriteMealEntity {

    @PrimaryKey
    @NonNull
    private String mealId;

    private String mealName;
    private String mealThumb;

    public FavoriteMealEntity(@NonNull String mealId, String mealName, String mealThumb) {
        this.mealId = mealId;
        this.mealName = mealName;
        this.mealThumb = mealThumb;
    }

    public String getMealId() { return mealId; }
    public String getMealName() { return mealName; }
    public String getMealThumb() { return mealThumb; }
}
