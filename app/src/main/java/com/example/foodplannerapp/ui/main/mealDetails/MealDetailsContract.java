package com.example.foodplannerapp.ui.main.mealDetails;

import com.example.foodplannerapp.model.Meal;

public interface MealDetailsContract {
    interface View {
        void showMealDetails(Meal meal);
        void showError(String message);
        void showSavedSuccessfully();
        void showLoading();
        void hideLoading();
    }

    interface Presenter {
        void loadMealDetails(String mealId);
    }
}
