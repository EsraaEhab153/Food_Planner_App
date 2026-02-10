package com.example.foodplannerapp.ui.main.filterdMeal;

import com.example.foodplannerapp.model.FilterType;
import com.example.foodplannerapp.model.MealsResponse;
import com.example.foodplannerapp.network.MealApiService;
import com.example.foodplannerapp.network.RetrofitClient;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FilteredMealPresenter implements FilteredMealsContract.Presenter{

    private FilteredMealsContract.View view;
    private MealApiService api;

    public FilteredMealPresenter(FilteredMealsContract.View view) {
        this.view = view;
        api = RetrofitClient.getInstance().create(MealApiService.class);
    }

    public void loadMeals(FilterType type, String value) {
        view.showLoading();

        Single<MealsResponse> single;

        switch (type) {
            case CATEGORY:
                single = api.getMealsByCategory(value);
                break;
            case AREA:
                single = api.getMealsByArea(value);
                break;
            case INGREDIENT:
                single = api.getMealsByIngredient(value);
                break;
            default:
                return;
        }

        single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    view.hideLoading();
                    view.showMeals(response.getMeals());
                }, throwable -> {
                    view.hideLoading();
                    view.showError(throwable.getMessage());
                });
    }

    @Override
    public void onDestroy() {

    }
}

