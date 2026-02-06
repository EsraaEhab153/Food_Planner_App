package com.example.foodplannerapp.network;

import com.example.foodplannerapp.model.MealsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealApiService {

    // Meal of the Day (Random Meal)
    @GET("random.php")
    Call<MealsResponse> getRandomMeal();


    // Get meals by category (Trending / Category meals)
    @GET("filter.php")
    Call<MealsResponse> getMealsByCategory(
            @Query("c") String category
    );

    // Search meal by name
    @GET("search.php")
    Call<MealsResponse> searchMealByName(
            @Query("s") String mealName
    );
}
