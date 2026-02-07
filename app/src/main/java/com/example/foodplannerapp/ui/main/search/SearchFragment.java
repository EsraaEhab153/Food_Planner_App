package com.example.foodplannerapp.ui.main.search;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplannerapp.R;
import com.example.foodplannerapp.model.FilterItem;
import com.example.foodplannerapp.model.FilterType;
import com.example.foodplannerapp.model.Meal;
import com.example.foodplannerapp.ui.main.home.adapter.SearchFilterAdapter;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class SearchFragment extends Fragment implements SearchContract.View {

    private TextInputEditText etSearch;
    private RecyclerView rvFilters;
    private SearchFilterAdapter adapter;
    private ChipGroup chipGroup;
    private SearchContract.Presenter presenter;

    private FilterType currentFilterType = FilterType.CATEGORY;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        setupRecyclerView();
        setupChips();
        setupSearch();

        presenter = new SearchPresenter(this);

        presenter.loadFilters(currentFilterType);
    }

    private void initViews(View view) {
        etSearch = view.findViewById(R.id.etSearch);
        rvFilters = view.findViewById(R.id.rvFilters);
        chipGroup = view.findViewById(R.id.chipGroupFilters);
    }

    private void setupRecyclerView() {
        rvFilters.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapter = new SearchFilterAdapter();
        rvFilters.setAdapter(adapter);
    }

    private void setupSearch() {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                adapter.filter(s.toString());
            }
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
    }

    private void setupChips() {
        chipGroup.setOnCheckedChangeListener((group, checkedId) -> {
            etSearch.setText(""); // reset search text

            if (checkedId == R.id.chipCategory) {
                currentFilterType = FilterType.CATEGORY;
            } else if (checkedId == R.id.chipArea) {
                currentFilterType = FilterType.AREA;
            } else if (checkedId == R.id.chipIngredient) {
                currentFilterType = FilterType.INGREDIENT;
            }

            // جلب القيم ديناميكي من API
            presenter.loadFilters(currentFilterType);
        });
    }

    @Override
    public void showFilters(FilterType type, List<FilterItem> items) {
        adapter.setData(items);
    }

    @Override
    public void showMeals(List<Meal> meals) {

    }

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        // ممكن تضيفي ProgressBar
    }

    @Override
    public void hideLoading() {
        // اخفاء ProgressBar
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDestroy();
    }
}