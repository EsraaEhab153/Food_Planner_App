package com.example.foodplannerapp.ui.main.home;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodplannerapp.R;
import com.example.foodplannerapp.model.Category;
import com.example.foodplannerapp.ui.main.home.adapter.CategoriesAdapter;

import java.util.List;

public class HomeFragment extends Fragment implements HomeContract.View {

    private RecyclerView rvCategories, rvTrending, rvNew;
    private CategoriesAdapter categoriesAdapter;
    private HomeContract.Presenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        rvCategories = view.findViewById(R.id.rvCategories);
        rvTrending = view.findViewById(R.id.rvTrending);
        rvNew = view.findViewById(R.id.rvNewRecipes);

        // Initialize presenter
        presenter = new HomePresenter(this, new HomeRepository());

        // Load data
        presenter.loadCategories();

        return view;
    }

    @Override
    public void showCategories(List<Category> categories) {
        categoriesAdapter = new CategoriesAdapter(categories);
        rvCategories.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)
        );
        rvCategories.setAdapter(categoriesAdapter);
    }
    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
