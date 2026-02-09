package com.example.foodplannerapp.ui.main.favorite.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.foodplannerapp.R;
import com.example.foodplannerapp.data.favorite.DataSource.FavoriteMealEntity;
import java.util.ArrayList;
import java.util.List;

public class FavoriteMealAdapter extends RecyclerView.Adapter<FavoriteMealAdapter.FavViewHolder> {

    private List<FavoriteMealEntity> meals;
    private OnDeleteClickListener listener;

    public interface OnDeleteClickListener {
        void onDeleteClick(FavoriteMealEntity meal);
    }

    public FavoriteMealAdapter(OnDeleteClickListener listener) {
        this.listener = listener;
        meals = new ArrayList<>();
    }

    @NonNull
    @Override
    public FavViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite_meal, parent, false);
        return new FavViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavViewHolder holder, int position) {
        FavoriteMealEntity meal = meals.get(position);
        holder.tvMealName.setText(meal.getMealName());
        Glide.with(holder.imgMeal.getContext())
                .load(meal.getMealThumb())
                .into(holder.imgMeal);

        holder.btnDelete.setOnClickListener(v -> listener.onDeleteClick(meal));
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public void updateData(List<FavoriteMealEntity> newMeals) {
        meals.clear();
        meals.addAll(newMeals);
        notifyDataSetChanged();
    }

    static class FavViewHolder extends RecyclerView.ViewHolder {
        ImageView imgMeal;
        TextView tvMealName;
        ImageButton btnDelete;

        public FavViewHolder(@NonNull View itemView) {
            super(itemView);
            imgMeal = itemView.findViewById(R.id.imgMeal);
            tvMealName = itemView.findViewById(R.id.tvMealName);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
