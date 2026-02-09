package com.example.foodplannerapp.ui.main.mealDetails.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplannerapp.R;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientViewHolder> {

    public static class IngredientItem {
        public String name;
        public String measure;
        public String imageUrl;

        public IngredientItem(String name, String measure, String imageUrl) {
            this.name = name;
            this.measure = measure;
            this.imageUrl = imageUrl;
        }
    }

    private List<IngredientItem> ingredients;

    public IngredientsAdapter(List<IngredientItem> ingredients) {
        this.ingredients = ingredients;
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ingredient, parent, false);
        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        IngredientItem item = ingredients.get(position);
        holder.tvName.setText(item.name);
        holder.tvMeasure.setText(item.measure);


        Glide.with(holder.itemView.getContext())
                .load(item.imageUrl != null ? item.imageUrl : R.drawable.moc_meal)
                .into(holder.imgIngredient);
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    static class IngredientViewHolder extends RecyclerView.ViewHolder {
        ImageView imgIngredient;
        TextView tvName, tvMeasure;

        public IngredientViewHolder(@NonNull View itemView) {
            super(itemView);
            imgIngredient = itemView.findViewById(R.id.imgIngredient);
            tvName = itemView.findViewById(R.id.tvIngredientName);
            tvMeasure = itemView.findViewById(R.id.tvIngredientMeasure);
        }
    }
}