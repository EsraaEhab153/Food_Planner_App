package com.example.foodplannerapp.ui.main.home;

import com.example.foodplannerapp.model.Meal;

import java.util.List;

public interface HomeContract {

    interface View {
        void showMealOfTheDay(Meal meal);
        void showTrendingMeals(List<Meal> meals);
        void showError(String message);
    }

    interface Presenter {
        void loadMealOfTheDay();
        void loadTrendingMeals();
    }
}

