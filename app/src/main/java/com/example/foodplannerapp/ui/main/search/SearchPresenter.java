package com.example.foodplannerapp.ui.main.search;

import android.net.Uri;
import android.util.Log;

import com.example.foodplannerapp.model.AreaFlagMapper;
import com.example.foodplannerapp.model.FilterItem;
import com.example.foodplannerapp.model.FilterType;
import com.example.foodplannerapp.model.ListResponse;
import com.example.foodplannerapp.model.Meal;
import com.example.foodplannerapp.model.MealsResponse;
import com.example.foodplannerapp.model.NameResponse;
import com.example.foodplannerapp.network.MealApiService;
import com.example.foodplannerapp.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPresenter implements SearchContract.Presenter {

    private SearchContract.View view;
    private MealApiService apiService;

    public SearchPresenter(SearchContract.View view) {
        this.view = view;
        this.apiService = RetrofitClient.getInstance().create(MealApiService.class);
    }

    @Override
    public void onSearchQueryChanged(String query) {
        if (query == null || query.isEmpty()) return;

        view.showLoading();
        apiService.searchMealByName(query).enqueue(new Callback<MealsResponse>() {
            @Override
            public void onResponse(Call<MealsResponse> call, Response<MealsResponse> response) {
                view.hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    view.showMeals(response.body().getMeals());
                } else {
                    view.showError("No meals found");
                }
            }

            @Override
            public void onFailure(Call<MealsResponse> call, Throwable t) {
                view.hideLoading();
                view.showError(t.getMessage());
            }
        });
    }

    @Override
    public void onFilterSelected(FilterType type, String filterValue) {
        view.showLoading();
        Call<MealsResponse> call;

        switch (type) {
            case CATEGORY:
                call = apiService.getMealsByCategory(filterValue);
                break;
            case AREA:
                call = apiService.getMealsByArea(filterValue);
                break;
            case INGREDIENT:
                call = apiService.getMealsByIngredient(filterValue);
                break;
            default:
                return;
        }

        call.enqueue(new Callback<MealsResponse>() {
            @Override
            public void onResponse(Call<MealsResponse> call, Response<MealsResponse> response) {
                view.hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    view.showMeals(response.body().getMeals());
                } else {
                    view.showError("No meals found");
                }
            }

            @Override
            public void onFailure(Call<MealsResponse> call, Throwable t) {
                view.hideLoading();
                view.showError(t.getMessage());
            }
        });
    }

    @Override
    public void loadFilters(FilterType type) {
        Call<ListResponse> call;

        switch (type) {
            case CATEGORY:
                call = apiService.getCategories("list");
                break;
            case AREA:
                call = apiService.getAreas("list");
                break;
            case INGREDIENT:
                call = apiService.getIngredients("list");
                break;
            default:
                return;
        }

        call.enqueue(new Callback<ListResponse>() {
            @Override
            public void onResponse(Call<ListResponse> call, Response<ListResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<NameResponse> names = response.body().getMeals();
                    if (names == null || names.isEmpty()) {
                        if (view != null) view.showError("No filters found");
                        return;
                    }

                    List<FilterItem> items = new ArrayList<>();
                    final int total = names.size();
                    final int[] counter = {0};

                    for (NameResponse n : names) {
                        String name = n.getName(type);

                        if (type == FilterType.CATEGORY) {
                            String thumb = "https://www.themealdb.com/images/category/" + Uri.encode(name) + ".png";
                            items.add(new FilterItem(name, type, thumb));
                            counter[0]++;
                            if (counter[0] == total && view != null) view.showFilters(type, items);

                        } else if (type == FilterType.AREA) {
                            String thumb = AreaFlagMapper.getFlagUrl(name);

                            items.add(new FilterItem(name, type, thumb));
                            counter[0]++;

                            if (counter[0] == total && view != null) {
                                view.showFilters(type, items);
                            }

                        } else if (type == FilterType.INGREDIENT) {

                            String thumbLocal = n.getThumb(type);
                            items.add(new FilterItem(name, type, thumbLocal));
                            counter[0]++;
                            if (counter[0] == total && view != null) view.showFilters(type, items);
                        }
                    }

                } else {
                    if (view != null) view.showError("Failed to load filters");
                }
            }

            @Override
            public void onFailure(Call<ListResponse> call, Throwable t) {
                if (view != null) view.showError(t.getMessage());
            }
        });
    }









    @Override
    public void onDestroy() {
        view = null;
    }
}
