package com.example.foodplannerapp.ui.main.home;

import com.example.foodplannerapp.model.Category;
import java.util.List;

public interface HomeContract {

    interface View {
        void showCategories(List<Category> categories);
        void showError(String message);
    }

    interface Presenter {
        void loadCategories();
    }
}

