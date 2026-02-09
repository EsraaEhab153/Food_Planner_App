package com.example.foodplannerapp.ui.main.home;

import com.example.foodplannerapp.model.Category;
import com.example.foodplannerapp.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class HomePresenter implements HomeContract.Presenter {

    private final HomeContract.View view;
    private final HomeRepository repo;

    public HomePresenter(HomeContract.View view, HomeRepository repo) {
        this.view = view;
        this.repo = repo;
    }

    public void loadMealOfTheDay() {
        repo.getRandomMeal(new HomeRepository.OnMealResult() {
            @Override
            public void onSuccess(Meal meal) {
                view.showMealOfTheDay(meal);
            }

            @Override
            public void onError(String error) {
                view.showError(error);
            }
        });
    }

    @Override
    public void onTrendingMealClicked(Meal meal) {
        if (meal != null && meal.getIdMeal() != null) {
            view.navigateToMealDetails(meal.getIdMeal());
        }
    }

    public void loadTrendingMeals() {
        List<Meal> trendingMeals = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            repo.getRandomMeal(new HomeRepository.OnMealResult() {
                @Override
                public void onSuccess(Meal meal) {
                    trendingMeals.add(meal);

                    // لما نوصل 7 وجبات، نعرضهم
                    if (trendingMeals.size() == 7) {
                        view.showTrendingMeals(trendingMeals);
                    }
                }

                @Override
                public void onError(String error) {
                    view.showError(error);
                }
            });
        }
    }

}