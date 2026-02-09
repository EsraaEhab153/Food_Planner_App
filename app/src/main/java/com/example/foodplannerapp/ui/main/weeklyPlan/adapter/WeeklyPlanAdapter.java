package com.example.foodplannerapp.ui.main.weeklyPlan.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplannerapp.R;
import com.example.foodplannerapp.data.weeklyplan.DataSource.local.WeeklyMealEntity;
import com.example.foodplannerapp.ui.main.weeklyPlan.OnMealDeleteListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeeklyPlanAdapter extends RecyclerView.Adapter<WeeklyPlanAdapter.DayViewHolder> {

    private Map<Integer, List<WeeklyMealEntity>> mealsByDay;
    private final String[] dayNames = {"Saturday","Sunday","Monday","Tuesday","Wednesday","Thursday","Friday"};
    private DayMealsAdapter[] dayAdapters = new DayMealsAdapter[7];
    private OnMealDeleteListener deleteListener;

    public WeeklyPlanAdapter(OnMealDeleteListener deleteListener) {
        this.deleteListener = deleteListener;
    }


    public void updateData(Map<Integer, List<WeeklyMealEntity>> newMealsByDay) {
        this.mealsByDay = newMealsByDay;

        for (int i = 0; i < 7; i++) {
            if (dayAdapters[i] != null) {
                List<WeeklyMealEntity> meals = mealsByDay.get(i);
                if (meals == null) meals = new ArrayList<>();
                dayAdapters[i].updateData(meals);
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_day_meals, parent, false);
        return new DayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DayViewHolder holder, int position) {
        holder.tvDayName.setText(dayNames[position]);
        List<WeeklyMealEntity> meals = mealsByDay != null ? mealsByDay.get(position) : new ArrayList<>();
        if (meals == null) meals = new ArrayList<>();

        if (dayAdapters[position] == null) {
            dayAdapters[position] = new DayMealsAdapter(meals, deleteListener);
        } else {
            dayAdapters[position].updateData(meals);
        }

        holder.rvMealsForDay.setLayoutManager(new LinearLayoutManager(holder.rvMealsForDay.getContext()));
        holder.rvMealsForDay.setAdapter(dayAdapters[position]);
    }

    @Override
    public int getItemCount() {
        return 7;
    }

    static class DayViewHolder extends RecyclerView.ViewHolder {
        TextView tvDayName;
        RecyclerView rvMealsForDay;

        public DayViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDayName = itemView.findViewById(R.id.tvDayName);
            rvMealsForDay = itemView.findViewById(R.id.rvMealsForDay);
        }
    }
}
