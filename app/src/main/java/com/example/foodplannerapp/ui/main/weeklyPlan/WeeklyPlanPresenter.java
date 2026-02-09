package com.example.foodplannerapp.ui.main.weeklyPlan;

import com.example.foodplannerapp.HelperClasses.DateUtils;
import com.example.foodplannerapp.data.weeklyplan.DataSource.local.WeeklyMealEntity;
import com.example.foodplannerapp.ui.main.weeklyPlan.WeeklyPlanContract.View;
import com.example.foodplannerapp.ui.main.weeklyPlan.WeeklyPlanContract.Presenter;

import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

//public class WeeklyPlanPresenter implements WeeklyPlanContract.Presenter {
//
//    private View view;
//    private WeeklyPlanRepository repository;
//
//    public WeeklyPlanPresenter(View view, WeeklyPlanRepository repository) {
//        this.view = view;
//        this.repository = repository;
//    }
//
//    @Override
//    public void loadWeeklyMeals() {
//        long today = System.currentTimeMillis();
//        long startOfWeek = DateUtils.getStartOfWeek(today);
//        long endOfWeek = DateUtils.getEndOfWeek(today);
//
//        repository.getMealsForWeek(startOfWeek, endOfWeek)
//                .subscribeOn(Schedulers.io())
//                .map(repository::convertListToMapByDay)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        list -> {
//                            map -> view.showMealsForWeek(map),
//                           throwable -> view.showError(throwable.getMessage())
//                        },
//                        throwable -> view.showError(throwable.getMessage())
//                );
//    }
//
//    @Override
//    public void onDateSelected(WeeklyMealEntity mealEntity) {
//        repository.insertMeal(mealEntity)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        () -> {
//                            view.showSavedSuccessfully();
//                            loadWeeklyMeals();
//                        },
//                        throwable -> view.showError(throwable.getMessage())
//                );
//    }
//
//}
//

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class WeeklyPlanPresenter implements WeeklyPlanContract.Presenter {

    private View view;
    private WeeklyPlanRepository repository;
    private CompositeDisposable disposable = new CompositeDisposable();

    public WeeklyPlanPresenter(View view, WeeklyPlanRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void loadWeeklyMeals() {
        long today = System.currentTimeMillis();
        long startOfWeek = DateUtils.getStartOfWeek(today);
        long endOfWeek = DateUtils.getEndOfWeek(today);

        disposable.add(
                repository.getMealsForWeek(startOfWeek, endOfWeek)
                        .subscribeOn(Schedulers.io())
                        .map(repository::convertListToMapByDay)  // تحويل الليست لماب
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                map -> view.showMealsForWeek(map),
                                throwable -> view.showError(throwable.getMessage())
                        )
        );
    }

    @Override
    public void onDateSelected(WeeklyMealEntity mealEntity) {
        disposable.add(
                repository.insertMeal(mealEntity)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                () -> view.showSavedSuccessfully(),  // UI هيتحدث تلقائيًا بفضل Flowable
                                throwable -> view.showError(throwable.getMessage())
                        )
        );
    }

    public void clear() {
        disposable.clear();
    }
}

