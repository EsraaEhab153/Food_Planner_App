package com.example.foodplannerapp.ui.main.weeklyPlan.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplannerapp.R;
import com.example.foodplannerapp.data.weeklyplan.DataSource.local.WeeklyMealEntity;
import com.example.foodplannerapp.model.Meal;
import com.example.foodplannerapp.ui.main.weeklyPlan.OnMealDeleteListener;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class DayMealsAdapter extends RecyclerView.Adapter<DayMealsAdapter.MealViewHolder> {

    public interface OnPlanMealClickListener {
        void onMealClick(WeeklyMealEntity meal);
    }
    private List<WeeklyMealEntity> meals;

    private OnMealDeleteListener listener;
    private OnPlanMealClickListener mealClickListener;




    public DayMealsAdapter(List<WeeklyMealEntity> meals,
                           OnMealDeleteListener listener,OnPlanMealClickListener mealClickListener) {
        this.meals = meals;
        this.listener = listener;
        this.mealClickListener = mealClickListener;
    }

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meal, parent, false);
        return new MealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
        WeeklyMealEntity meal = meals.get(position);
        holder.tvMealName.setText(meal.getMealName());
        holder.btnDelete.setOnClickListener(v -> {
            listener.onDeleteClick(meal);
        });

        Glide.with(holder.imgMeal.getContext())
                .load(meal.getMealThumb())
                .into(holder.imgMeal);

        holder.imgMeal.setOnClickListener(
                v -> {
                    if (mealClickListener != null) {
                        mealClickListener.onMealClick(meal);
                    }
                });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public void updateData(List<WeeklyMealEntity> newMeals) {
        this.meals.clear();
        this.meals.addAll(newMeals);
        notifyDataSetChanged();
    }

    static class MealViewHolder extends RecyclerView.ViewHolder {
        ShapeableImageView imgMeal;
        TextView tvMealName;
        ImageView btnDelete;

        public MealViewHolder(@NonNull View itemView) {
            super(itemView);
            imgMeal = itemView.findViewById(R.id.imgMeal);
            tvMealName = itemView.findViewById(R.id.tvMealName);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
