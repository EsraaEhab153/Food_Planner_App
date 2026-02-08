package com.example.foodplannerapp.ui.main.mealDetails;

import com.example.foodplannerapp.model.MealsResponse;
import com.example.foodplannerapp.network.MealApiService;
import com.example.foodplannerapp.network.RetrofitClient;

import retrofit2.Callback;

public class MealDetailsRepository {

    private MealApiService apiService;

    public MealDetailsRepository() {
        apiService = RetrofitClient.getInstance().create(MealApiService.class);
    }

    public void getMealById(String id, Callback<MealsResponse> callback) {
        apiService.getMealDetailsById(id).enqueue(callback);
    }
}

