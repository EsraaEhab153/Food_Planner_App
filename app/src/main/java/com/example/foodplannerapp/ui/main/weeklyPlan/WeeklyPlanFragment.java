package com.example.foodplannerapp.ui.main.weeklyPlan;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.foodplannerapp.R;
import com.example.foodplannerapp.data.weeklyplan.DataSource.local.WeeklyMealEntity;
import com.example.foodplannerapp.model.Meal;
import com.example.foodplannerapp.ui.main.weeklyPlan.adapter.WeeklyPlanAdapter;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class WeeklyPlanFragment extends Fragment implements WeeklyPlanContract.View {

    private WeeklyPlanContract.Presenter presenter;
    private Meal selectedMeal;

    private RecyclerView rvWeeklyPlan;
    private WeeklyPlanAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weekly_plan, container, false);

        rvWeeklyPlan = view.findViewById(R.id.rvWeeklyPlan);
        rvWeeklyPlan.setLayoutManager(new LinearLayoutManager(requireContext()));


        adapter = new WeeklyPlanAdapter();
        rvWeeklyPlan.setAdapter(adapter);

        presenter = new WeeklyPlanPresenter(this, new WeeklyPlanRepository(requireContext()));

        // Load meals for the week initially
        presenter.loadWeeklyMeals();

        return view;
    }

    @Override
    public void showMealsForWeek(Map<Integer, List<WeeklyMealEntity>> mealsByDay) {
        for (int i = 0; i < 7; i++) {
            Log.d("WeeklyPlan", "Day " + i + " meals: " + mealsByDay.get(i).size());
        }
        adapter.updateData(mealsByDay);
    }

    @Override
    public void showSavedSuccessfully() {
        presenter.loadWeeklyMeals();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (presenter instanceof WeeklyPlanPresenter) {
            ((WeeklyPlanPresenter) presenter).clear();
        }
    }

}