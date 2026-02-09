package com.example.foodplannerapp.ui.main.favorite;


import com.example.foodplannerapp.data.favorite.DataSource.FavoriteMealEntity;
import java.util.List;

public interface FavoriteContract {

    interface View {
        void showFavorites(List<FavoriteMealEntity> meals);
        void showError(String message);
        void onFavoriteAdded();
        void onFavoriteRemoved();
    }

    interface Presenter {
        void getAllFavorites();
        void addToFavorite(FavoriteMealEntity meal);
        void removeFromFavorite(FavoriteMealEntity meal);
        void checkIfFavorite(String mealId);
        void clear();
    }
}

