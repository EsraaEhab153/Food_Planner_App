package com.example.foodplannerapp.ui.main.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplannerapp.R;
import com.example.foodplannerapp.model.Meal;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class TrendingMealAdapter extends RecyclerView.Adapter<TrendingMealAdapter.MealViewHolder> {

    private final List<Meal> meals;
    private OnMealClickListener listener;

    public TrendingMealAdapter(List<Meal> meals, OnMealClickListener listener) {
        this.meals = meals;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_trending_meal, parent, false);
        return new MealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
        Meal meal = meals.get(position);

        holder.tvName.setText(meal.getStrMeal());
        holder.tvCategory.setText(meal.getStrCategory());

        Glide.with(holder.itemView.getContext())
                .load(meal.getStrMealThumb())
                .centerCrop()
                .into(holder.imgMeal);

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onMealClick(meal);
            }
        });

    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    static class MealViewHolder extends RecyclerView.ViewHolder {
        ShapeableImageView imgMeal;
        TextView tvName, tvCategory;

        public MealViewHolder(@NonNull View itemView) {
            super(itemView);
            imgMeal = itemView.findViewById(R.id.imgMeal);
            tvName = itemView.findViewById(R.id.tvMealName);
            tvCategory = itemView.findViewById(R.id.tvMealCategory);
        }
    }
}