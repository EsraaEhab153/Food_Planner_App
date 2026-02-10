package com.example.foodplannerapp.ui.main.weeklyPlan;

import com.example.foodplannerapp.data.weeklyplan.DataSource.local.WeeklyMealEntity;

import java.util.List;
import java.util.Map;

public interface WeeklyPlanContract {
    interface View {
        void showMealsForWeek(Map<Integer, List<WeeklyMealEntity>> mealsByDay);
        void showSavedSuccessfully();
        void showError(String message);
    }

    interface Presenter {
        void loadWeeklyMeals();
        void onDateSelected(WeeklyMealEntity mealEntity);
        void deleteMeal(WeeklyMealEntity meal);

        void openMealDetails(WeeklyMealEntity meal);
    }
}

