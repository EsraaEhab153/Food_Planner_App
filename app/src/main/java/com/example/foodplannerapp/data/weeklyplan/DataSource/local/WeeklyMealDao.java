package com.example.foodplannerapp.data.weeklyplan.DataSource.local;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface WeeklyMealDao {

    @Insert
    Completable insertMeal(WeeklyMealEntity meal);

    @Query("SELECT * FROM weekly_meals WHERE selectedDate BETWEEN :startDate AND :endDate ORDER BY selectedDate ASC")
    Flowable<List<WeeklyMealEntity>> getMealsForWeek(
            long startDate,
            long endDate
    );
    @Query("DELETE FROM weekly_meals WHERE id = :mealId")
    Completable deleteMealById(int mealId);
}

