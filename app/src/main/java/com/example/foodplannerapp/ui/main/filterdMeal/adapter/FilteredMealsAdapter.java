package com.example.foodplannerapp.ui.main.filterdMeal.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplannerapp.R;
import com.example.foodplannerapp.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class FilteredMealsAdapter
        extends RecyclerView.Adapter<FilteredMealsAdapter.ViewHolder> {

    public interface OnMealClickListener {
        void onMealClick(Meal meal);
    }

    private List<Meal> meals = new ArrayList<>();
    private OnMealClickListener listener;

    public FilteredMealsAdapter(OnMealClickListener listener) {
        this.listener = listener;
    }

    public void setMeals(List<Meal> newMeals) {
        meals.clear();
        meals.addAll(newMeals);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_filtered_meal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meal meal = meals.get(position);
        holder.tvName.setText(meal.getStrMeal());

        Glide.with(holder.itemView.getContext())
                .load(meal.getStrMealThumb())
                .into(holder.imgMeal);

        holder.itemView.setOnClickListener(
                v -> {
                    if (listener != null) {
                        listener.onMealClick(meal);
                    }
                });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgMeal;
        TextView tvName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgMeal = itemView.findViewById(R.id.imgMeal);
            tvName = itemView.findViewById(R.id.tvMealName);
        }
    }
}
