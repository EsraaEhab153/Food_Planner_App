package com.example.foodplannerapp.ui.main.home;

import android.os.Bundle;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.example.foodplannerapp.R;
import com.example.foodplannerapp.model.Meal;
import com.example.foodplannerapp.ui.main.home.adapter.CategoriesAdapter;
import com.example.foodplannerapp.ui.main.home.adapter.TrendingMealAdapter;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class HomeFragment extends Fragment implements HomeContract.View {

    private CategoriesAdapter categoriesAdapter;
    private HomeContract.Presenter presenter;

    // Meal of the Day UI
    private TextView tvFeaturedTitle, mealCategory,tvUsername;
    private ShapeableImageView imgFeatured;

    //trending meal
    private RecyclerView rvTrending;
    private TrendingMealAdapter trendingAdapter;
    private Button btnLetsCook;
    private LottieAnimationView loadingAnimation;
    private NestedScrollView nestedScrollView;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String email = user.getEmail().toString();
    String username = email.substring(0, (user.getEmail().toString()).indexOf("@"));


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        loadingAnimation = view.findViewById(R.id.loadingAnimation);
        nestedScrollView = view.findViewById(R.id.nestedScrollView);

        // ===== Bind UI =====
        rvTrending = view.findViewById(R.id.rvTrending);

        // Meal of the Day views
        tvFeaturedTitle = view.findViewById(R.id.tvFeaturedTitle);
        mealCategory = view.findViewById(R.id.meal_category);
        imgFeatured = view.findViewById(R.id.imgFeatured);
        btnLetsCook = view.findViewById(R.id.lets_cock_btn);
        tvUsername = view.findViewById(R.id.tv_username);
        tvUsername.setText(username);


        // ===== Initialize presenter =====
        presenter = new HomePresenter(this, new HomeRepository());

        // Load Categories & Meal of the Day
        presenter.loadMealOfTheDay();
        presenter.loadTrendingMeals();

        return view;
    }

    // ======= Meal of the Day callback =======
    @Override
    public void showMealOfTheDay(Meal meal) {
        tvFeaturedTitle.setText(meal.getStrMeal());
        mealCategory.setText(meal.getStrCategory());

        Glide.with(requireContext())
                .load(meal.getStrMealThumb())
                .centerCrop()
                .into(imgFeatured);
        btnLetsCook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("meal_id", meal.getIdMeal());
                Log.d("MealDetails", "Meal ID = " +  meal.getIdMeal());
                NavHostFragment.findNavController(HomeFragment.this)
                        .navigate(R.id.action_homeFragment_to_mealDetailsFragment, bundle);
            }
        });
    }

    @Override
    public void showTrendingMeals(List<Meal> meals) {
        trendingAdapter = new TrendingMealAdapter(meals, meal -> {
            presenter.onTrendingMealClicked(meal);
        });

        rvTrending.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)
        );
        rvTrending.setAdapter(trendingAdapter);
    }



    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToMealDetails(String mealId) {
        Bundle bundle = new Bundle();
        bundle.putString("meal_id", mealId);

        NavHostFragment.findNavController(this)
                .navigate(R.id.action_homeFragment_to_mealDetailsFragment, bundle);
    }

    @Override
    public void showLoading() {
        loadingAnimation.setVisibility(View.VISIBLE);
        nestedScrollView.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        loadingAnimation.setVisibility(View.GONE);
        nestedScrollView.setVisibility(View.VISIBLE);
    }
}
