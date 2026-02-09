package com.example.foodplannerapp.ui.main.favorite;

import android.content.Context;
import com.example.foodplannerapp.data.db.AppDatabase;
import com.example.foodplannerapp.data.favorite.DataSource.FavoriteMealDao;
import com.example.foodplannerapp.data.favorite.DataSource.FavoriteMealEntity;
import java.util.List;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public class FavoriteRepository {

    private FavoriteMealDao dao;

    public FavoriteRepository(Context context) {
        dao = AppDatabase.getInstance(context).favoriteMealDao();
    }

    public Completable addToFavorite(FavoriteMealEntity meal) {
        return dao.insertFavorite(meal);
    }

    public Completable removeFromFavorite(FavoriteMealEntity meal) {
        return dao.deleteFavorite(meal);
    }

    public Flowable<List<FavoriteMealEntity>> getFavorites() {
        return dao.getAllFavorites();
    }

    public Flowable<Boolean> isFavorite(String mealId) {
        return dao.isMealFavorite(mealId);
    }
}

