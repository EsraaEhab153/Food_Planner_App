package com.example.foodplannerapp.HelperClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplannerapp.R;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {

    Context context;

    int images[] ={
            R.drawable.first_onboard,
            R.drawable.second_onboard,
            R.drawable.third_onboard
    };

    int titles[] ={
            R.string.first_slide_title,
            R.string.second_slide_title,
            R.string.third_slide_title
    };

    int descriptions[] ={
            R.string.first_slide_desc,
            R.string.second_slide_desc,
            R.string.third_slide_desc
    };

    public SliderAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.slides_layout, parent, false);
        return new SliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        holder.imageView.setImageResource(images[position]);
        holder.title.setText(titles[position]);
        holder.description.setText(descriptions[position]);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    static class SliderViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title, description;

        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_slide_img);
            title = itemView.findViewById(R.id.tv_slide_title);
            description = itemView.findViewById(R.id.tv_slide_desc);
        }
    }
}