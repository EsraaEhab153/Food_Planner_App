package com.example.foodplannerapp.ui.main.weeklyPlan;

import com.example.foodplannerapp.data.weeklyplan.DataSource.local.WeeklyMealEntity;

public interface OnMealDeleteListener {
    void onDeleteClick(WeeklyMealEntity meal);
}

