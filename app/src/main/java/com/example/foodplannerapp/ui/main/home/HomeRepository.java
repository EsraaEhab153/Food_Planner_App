package com.example.foodplannerapp.ui.main.home;

import com.example.foodplannerapp.model.Meal;
import com.example.foodplannerapp.model.MealsResponse;
import com.example.foodplannerapp.network.MealApiService;
import com.example.foodplannerapp.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeRepository {

    private MealApiService apiService;

    public HomeRepository() {
        apiService = RetrofitClient.getInstance()
                .create(MealApiService.class);
    }

    public void getRandomMeal(OnMealResult callback) {
        apiService.getRandomMeal().enqueue(new Callback<MealsResponse>() {
            @Override
            public void onResponse(Call<MealsResponse> call,
                                   Response<MealsResponse> response) {
                if (response.isSuccessful()
                        && response.body() != null
                        && response.body().getMeals() != null) {

                    callback.onSuccess(
                            response.body().getMeals().get(0)
                    );
                } else {
                    callback.onError("No meal found");
                }
            }

            @Override
            public void onFailure(Call<MealsResponse> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    // Callback interface
    public interface OnMealResult {
        void onSuccess(Meal meal);
        void onError(String error);
    }
}