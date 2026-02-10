package com.example.foodplannerapp.ui.main.search;

import com.example.foodplannerapp.model.Meal;
import com.example.foodplannerapp.model.MealsResponse;
import com.example.foodplannerapp.network.MealApiService;
import com.example.foodplannerapp.network.RetrofitClient;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class SearchRepository {

    private final MealApiService apiService;

    public SearchRepository() {
        apiService = RetrofitClient.getInstance().create(MealApiService.class);
    }

    public Single<List<Meal>> getMealsByArea(String area) {
        return apiService.getMealsByArea(area)
                .map(MealsResponse::getMeals);
    }

    public Single<List<Meal>> getMealsByCategory(String category) {
        return apiService.getMealsByCategory(category)
                .map(MealsResponse::getMeals);
    }

    public Single<List<Meal>> getMealsByIngredient(String area) {
        return apiService.getMealsByIngredient(area)
                .map(MealsResponse::getMeals);
    }

}

