package com.example.foodplannerapp.ui.main.favorite;

import android.util.Log;
import com.example.foodplannerapp.data.favorite.DataSource.FavoriteMealEntity;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavoriteMealPresenter implements FavoriteContract.Presenter {

    private FavoriteContract.View view;
    private FavoriteRepository repository;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public FavoriteMealPresenter(FavoriteContract.View view,
                                 FavoriteRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void getAllFavorites() {
        compositeDisposable.add(
                repository.getFavorites()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                meals -> view.showFavorites(meals),
                                throwable -> view.showError(throwable.getMessage())
                        )
        );
    }

    @Override
    public void addToFavorite(FavoriteMealEntity meal) {
        compositeDisposable.add(
                repository.addToFavorite(meal)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                () -> view.onFavoriteAdded(),
                                throwable -> view.showError(throwable.getMessage())
                        )
        );
    }

    @Override
    public void removeFromFavorite(FavoriteMealEntity meal) {
        compositeDisposable.add(
                repository.removeFromFavorite(meal)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                () -> view.onFavoriteRemoved(),
                                throwable -> view.showError(throwable.getMessage())
                        )
        );
    }

    @Override
    public void checkIfFavorite(String mealId) {
        compositeDisposable.add(
                repository.isFavorite(mealId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                isFav -> Log.d("Favorite", "Is Favorite: " + isFav),
                                throwable -> view.showError(throwable.getMessage())
                        )
        );
    }

    @Override
    public void clear() {
        compositeDisposable.clear();
    }
}

