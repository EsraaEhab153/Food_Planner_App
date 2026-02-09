//package com.example.foodplannerapp.ui.main.mealDetails;
//
//import com.example.foodplannerapp.model.MealsResponse;
//import com.example.foodplannerapp.network.MealApiService;
//import com.example.foodplannerapp.network.RetrofitClient;
//
//import retrofit2.Callback;
//
//public class MealDetailsRepository {
//
//    private MealApiService apiService;
//
//    public MealDetailsRepository() {
//        apiService = RetrofitClient.getInstance().create(MealApiService.class);
//    }
//
//    public void getMealById(String id, Callback<MealsResponse> callback) {
//        apiService.getMealDetailsById(id).enqueue(callback);
//    }
//}
//
package com.example.foodplannerapp.ui.main.mealDetails;

import android.content.Context;

import com.example.foodplannerapp.data.db.AppDatabase;
import com.example.foodplannerapp.data.weeklyplan.DataSource.local.WeeklyMealDao;
import com.example.foodplannerapp.data.weeklyplan.DataSource.local.WeeklyMealEntity;
import com.example.foodplannerapp.model.MealsResponse;
import com.example.foodplannerapp.network.MealApiService;
import com.example.foodplannerapp.network.RetrofitClient;

import io.reactivex.rxjava3.core.Completable;
import retrofit2.Callback;

public class MealDetailsRepository {

    private MealApiService apiService;
    private WeeklyMealDao weeklyMealDao;

    // Constructor for API calls only
    public MealDetailsRepository() {
        apiService = RetrofitClient.getInstance().create(MealApiService.class);
    }

    // Constructor for API + Database
    public MealDetailsRepository(Context context) {
        apiService = RetrofitClient.getInstance().create(MealApiService.class);
        AppDatabase db = AppDatabase.getInstance(context);
        weeklyMealDao = db.weeklyMealDao();
    }

    // === API call ===
    public void getMealById(String id, Callback<MealsResponse> callback) {
        apiService.getMealDetailsById(id).enqueue(callback);
    }

    // === Room Database ===
    public Completable insertMeal(WeeklyMealEntity entity) {
        if (weeklyMealDao == null) {
            throw new IllegalStateException("WeeklyMealDao is null. Use constructor with Context");
        }
        return weeklyMealDao.insertMeal(entity);
    }
}

