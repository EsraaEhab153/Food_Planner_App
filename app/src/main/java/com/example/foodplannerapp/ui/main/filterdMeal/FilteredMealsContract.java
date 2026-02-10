package com.example.foodplannerapp.ui.main.filterdMeal;

import com.example.foodplannerapp.model.FilterType;
import com.example.foodplannerapp.model.Meal;
import java.util.List;

public interface FilteredMealsContract {

    interface View {
        void showLoading();
        void hideLoading();
        void showMeals(List<Meal> meals);
        void showError(String message);
    }

    interface Presenter {
        void loadMeals(FilterType type, String value);
        void onDestroy();
    }
}

