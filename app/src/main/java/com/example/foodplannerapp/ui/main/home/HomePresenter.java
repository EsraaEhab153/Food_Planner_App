package com.example.foodplannerapp.ui.main.home;

import com.example.foodplannerapp.model.Category;

import java.util.List;

public class HomePresenter implements HomeContract.Presenter {

    private final HomeContract.View view;
    private final HomeRepository repo;

    public HomePresenter(HomeContract.View view, HomeRepository repo) {
        this.view = view;
        this.repo = repo;
    }

    @Override
    public void loadCategories() {
        List<Category> categories = repo.getCategories();
        if (categories != null && !categories.isEmpty()) {
            view.showCategories(categories);
        } else {
            view.showError("No categories found");
        }
    }
}

