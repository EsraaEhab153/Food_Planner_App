package com.example.foodplannerapp.network;

import com.example.foodplannerapp.model.ListResponse;
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
    // Filter by Area
    @GET("filter.php")
    Call<MealsResponse> getMealsByArea(
            @Query("a") String area
    );

    // Filter by Main Ingredient
    @GET("filter.php")
    Call<MealsResponse> getMealsByIngredient(
            @Query("i") String ingredient
    );

    // Get List of Categories
    @GET("list.php")
    Call<ListResponse> getCategories(
            @Query("c") String cList
    );

    // Get List of Areas
    @GET("list.php")
    Call<ListResponse> getAreas(
            @Query("a") String aList
    );

    // Get List of Ingredients
    @GET("list.php")
    Call<ListResponse> getIngredients(
            @Query("i") String iList
    );
   //Get meal details by id
    @GET("lookup.php")
    Call<MealsResponse> getMealDetailsById(
            @Query("i") String mealId
    );
}
