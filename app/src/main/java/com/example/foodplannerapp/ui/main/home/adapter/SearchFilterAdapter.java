package com.example.foodplannerapp.ui.main.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplannerapp.R;
import com.example.foodplannerapp.model.FilterItem;
import com.example.foodplannerapp.ui.main.search.OnFilterClickListener;

import java.util.ArrayList;
import java.util.List;

public class SearchFilterAdapter extends RecyclerView.Adapter<SearchFilterAdapter.ViewHolder> {

    private List<FilterItem> fullList = new ArrayList<>();
    private List<FilterItem> filteredList = new ArrayList<>();
    private OnFilterClickListener listener;

    public SearchFilterAdapter(OnFilterClickListener listener) {
        this.listener = listener;
    }



    public void setData(List<FilterItem> items) {
        fullList.clear();
        fullList.addAll(items);

        filteredList.clear();
        filteredList.addAll(items);

        notifyDataSetChanged();
    }

    public void filter(String query) {
        filteredList.clear();

        if (query == null || query.trim().isEmpty()) {
            filteredList.addAll(fullList);
        } else {
            String lowerQuery = query.toLowerCase();
            for (FilterItem item : fullList) {
                if (item.getName().toLowerCase().contains(lowerQuery)) {
                    filteredList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FilterItem item = filteredList.get(position);

        holder.tvName.setText(item.getName());

        if (item.getThumb() != null && !item.getThumb().isEmpty()) {
            Glide.with(holder.itemView.getContext())
                    .load(item.getThumb())
                    .centerCrop()
                    .into(holder.imgThumb);
        } else {
            holder.imgThumb.setImageResource(R.drawable.moc_meal);
        }
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onFilterClick(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView imgThumb;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvCategoryName);
            imgThumb = itemView.findViewById(R.id.imgCategory);
        }
    }
}