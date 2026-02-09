package com.example.foodplannerapp.ui.main.weeklyPlan;

import android.content.Context;

import com.example.foodplannerapp.data.db.AppDatabase;
import com.example.foodplannerapp.data.weeklyplan.DataSource.local.WeeklyMealDao;
import com.example.foodplannerapp.data.weeklyplan.DataSource.local.WeeklyMealEntity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public class WeeklyPlanRepository {

    private WeeklyMealDao weeklyMealDao;

    public WeeklyPlanRepository(Context context) {
        weeklyMealDao = AppDatabase
                .getInstance(context)
                .weeklyMealDao();
    }

    public Completable insertMeal(WeeklyMealEntity meal) {
        return weeklyMealDao.insertMeal(meal);
    }

    public Flowable<List<WeeklyMealEntity>> getMealsForWeek(
            long start, long end
    ) {
        return weeklyMealDao.getMealsForWeek(start, end);
    }

//    public Map<Integer, List<WeeklyMealEntity>> convertListToMapByDay(List<WeeklyMealEntity> list) {
//        Map<Integer, List<WeeklyMealEntity>> map = new HashMap<>();
//        for (int i = 0; i < 7; i++) {
//            map.put(i, new ArrayList<>());
//        }
//
//        Calendar cal = Calendar.getInstance();
////        for (WeeklyMealEntity meal : list) {
////            cal.setTimeInMillis(meal.getSelectedDate());
////            int dayOfWeek = (cal.get(Calendar.DAY_OF_WEEK) + 1) % 7;
////            map.get(dayOfWeek).add(meal);
////        }
//        for (WeeklyMealEntity meal : list) {
//            cal.setTimeInMillis(meal.getSelectedDate());
//            int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 1=Sunday ... 7=Saturday
//
//            int key;
//            switch (dayOfWeek) {
//                case Calendar.SATURDAY: key = 0; break;
//                case Calendar.SUNDAY:   key = 1; break;
//                case Calendar.MONDAY:   key = 2; break;
//                case Calendar.TUESDAY:  key = 3; break;
//                case Calendar.WEDNESDAY:key = 4; break;
//                case Calendar.THURSDAY: key = 5; break;
//                case Calendar.FRIDAY:   key = 6; break;
//                default: key = 0;
//            }
//
//            map.get(key).add(meal);
//        }
//        return map;
//    }
public Map<Integer, List<WeeklyMealEntity>> convertListToMapByDay(List<WeeklyMealEntity> list) {
    Map<Integer, List<WeeklyMealEntity>> map = new HashMap<>();
    for (int i = 0; i < 7; i++) {
        map.put(i, new ArrayList<>());
    }

    Calendar cal = Calendar.getInstance();
    for (WeeklyMealEntity meal : list) {
        cal.setTimeInMillis(meal.getSelectedDate());
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

        int key = 0;
        switch (dayOfWeek) {
            case Calendar.SATURDAY: key = 0; break;
            case Calendar.SUNDAY: key = 1; break;
            case Calendar.MONDAY: key = 2; break;
            case Calendar.TUESDAY: key = 3; break;
            case Calendar.WEDNESDAY: key = 4; break;
            case Calendar.THURSDAY: key = 5; break;
            case Calendar.FRIDAY: key = 6; break;
        }

        map.get(key).add(meal);
    }

    return map;
}

}

