package com.example.foodplannerapp.data.favorite.DataSource;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface FavoriteMealDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertFavorite(FavoriteMealEntity meal);

    @Delete
    Completable deleteFavorite(FavoriteMealEntity meal);

    @Query("SELECT * FROM favorite_meals")
    Flowable<List<FavoriteMealEntity>> getAllFavorites();

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_meals WHERE mealId = :mealId)")
    Flowable<Boolean> isMealFavorite(String mealId);
}
