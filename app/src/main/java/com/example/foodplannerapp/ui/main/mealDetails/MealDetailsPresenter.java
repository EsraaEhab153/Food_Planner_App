package com.example.foodplannerapp.ui.main.mealDetails;

import android.util.Log;

import com.example.foodplannerapp.data.weeklyplan.DataSource.local.WeeklyMealEntity;
import com.example.foodplannerapp.model.Meal;
import com.example.foodplannerapp.model.MealsResponse;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealDetailsPresenter implements MealDetailsContract.Presenter {

    private MealDetailsContract.View view;
    private MealDetailsRepository repository;
    private Meal currentMeal;

    public MealDetailsPresenter(MealDetailsContract.View view, MealDetailsRepository repo){
        this.view = view;
        this.repository = repo;
    }

    @Override
    public void loadMealDetails(String mealId) {
        view.showLoading();

        repository.getMealById(mealId, new Callback<MealsResponse>() {
            @Override
            public void onResponse(Call<MealsResponse> call, Response<MealsResponse> response) {
                view.hideLoading();
                Log.d("MealDetails", "raw response = " + response.body());
                if(response.isSuccessful() && response.body() != null && response.body().getMeals() != null && !response.body().getMeals().isEmpty()){
                    view.showMealDetails(response.body().getMeals().get(0));
                    Log.d("MealDetails", "meal details = " + response.body().getMeals().get(0));
                    Log.d("MealDetails", "meal details = " + response.body().getMeals().get(0).getStrMeal());
                    Log.d("MealDetails", "meal details = " + response.body().getMeals().get(0).getStrCategory());
                    Log.d("MealDetails", "meal details = " + response.body().getMeals().get(0).getStrArea());
                    Log.d("MealDetails", "meal details = " + response.body().getMeals().get(0).getStrInstructions());
                    Log.d("MealDetails", "meal details = " + response.body().getMeals().get(0).getStrMealThumb());
                    Log.d("MealDetails", "meal details = " + response.body().getMeals().get(0).getStrYoutube());
                    Log.d("MealDetails", "meal details = " + response.body().getMeals().get(0).getIngredient(1));

                } else {
                    view.showError("Failed to load meal details");
                }
            }

            @Override
            public void onFailure(Call<MealsResponse> call, Throwable t) {
                view.hideLoading();
                view.showError(t.getMessage());
            }
        });
    }
    public void onDateSelected(long dayMillis) {
        WeeklyMealEntity entity = new WeeklyMealEntity(
                currentMeal.getIdMeal(),
                currentMeal.getStrMeal(),
                currentMeal.getStrMealThumb(),
                dayMillis
        );

        repository.insertMeal(entity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> view.showSavedSuccessfully(),
                        throwable -> view.showError(throwable.getMessage())
                );
    }

}