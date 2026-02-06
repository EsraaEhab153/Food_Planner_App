package com.example.foodplannerapp.ui.main.home;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.foodplannerapp.R;
import com.example.foodplannerapp.model.Category;
import com.example.foodplannerapp.model.Meal;
import com.example.foodplannerapp.ui.main.home.adapter.CategoriesAdapter;
import com.example.foodplannerapp.ui.main.home.adapter.TrendingMealAdapter;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class HomeFragment extends Fragment implements HomeContract.View {

    private CategoriesAdapter categoriesAdapter;
    private HomeContract.Presenter presenter;

    // Meal of the Day UI
    private TextView tvFeaturedTitle, mealCategory;
    private ShapeableImageView imgFeatured;

    //trending meal
    private RecyclerView rvTrending;
    private TrendingMealAdapter trendingAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // ===== Bind UI =====
        rvTrending = view.findViewById(R.id.rvTrending);

        // Meal of the Day views
        tvFeaturedTitle = view.findViewById(R.id.tvFeaturedTitle);
        mealCategory = view.findViewById(R.id.meal_category);
        imgFeatured = view.findViewById(R.id.imgFeatured);

        // ===== Initialize presenter =====
        presenter = new HomePresenter(this, new HomeRepository());

        // Load Categories & Meal of the Day
       // presenter.loadCategories();
        presenter.loadMealOfTheDay();
        presenter.loadTrendingMeals();

        return view;
    }

    // ======= Categories callback =======
//    @Override
//    public void showCategories(List<Category> categories) {
//        categoriesAdapter = new CategoriesAdapter(categories);
//        rvCategories.setLayoutManager(
//                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)
//        );
//        rvCategories.setAdapter(categoriesAdapter);
//    }

    // ======= Meal of the Day callback =======
    @Override
    public void showMealOfTheDay(Meal meal) {
        tvFeaturedTitle.setText(meal.getStrMeal());
        mealCategory.setText(meal.getStrCategory());

        Glide.with(requireContext())
                .load(meal.getStrMealThumb())
                .centerCrop()
                .into(imgFeatured);
    }

    @Override
    public void showTrendingMeals(List<Meal> meals) {
        trendingAdapter = new TrendingMealAdapter(meals);
        rvTrending.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)
        );
        rvTrending.setAdapter(trendingAdapter);
    }


    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
