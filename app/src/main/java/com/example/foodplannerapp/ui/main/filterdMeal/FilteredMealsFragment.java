package com.example.foodplannerapp.ui.main.filterdMeal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplannerapp.R;
import com.example.foodplannerapp.model.FilterType;
import com.example.foodplannerapp.model.Meal;
import com.example.foodplannerapp.ui.main.filterdMeal.adapter.FilteredMealsAdapter;

import java.util.List;

public class FilteredMealsFragment extends Fragment
        implements FilteredMealsContract.View {

    private FilteredMealsContract.Presenter presenter;
    private FilteredMealsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_filtered_meal, container, false);

        RecyclerView rvMeals = view.findViewById(R.id.rvFilteredMeals);
        rvMeals.setLayoutManager(new GridLayoutManager(requireContext(), 2));

        adapter = new FilteredMealsAdapter(meal -> {
            Bundle bundle = new Bundle();
            bundle.putString("meal_id", meal.getIdMeal());

             NavHostFragment.findNavController(this)
                    .navigate(R.id.action_filteredMeals_to_mealDetails, bundle);
        });

        rvMeals.setAdapter(adapter);

        presenter = new FilteredMealPresenter(this);

        // arguments
        FilterType type =
                FilterType.valueOf(getArguments().getString("filter_type"));
        String value = getArguments().getString("filter_value");

        presenter.loadMeals(type, value);

        return view;
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void showMeals(List<Meal> meals) {
        adapter.setMeals(meals);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDestroy();
    }
}