package com.example.foodplannerapp.ui.main.search;

import com.example.foodplannerapp.model.FilterItem;
import com.example.foodplannerapp.model.FilterType;
import com.example.foodplannerapp.model.Meal;
import java.util.List;

public interface SearchContract {

    interface View {
        void showMeals(List<Meal> meals);
        void showError(String message);
        void showLoading();
        void hideLoading();
        void showFilters(FilterType type, List<FilterItem> items);
    }

    interface Presenter {
        void onSearchQueryChanged(String query);
        void onFilterSelected(FilterType type, String filterValue);
        void loadFilters(FilterType type);
        void onDestroy();
    }
}

