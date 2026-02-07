package com.example.foodplannerapp.ui.main.search;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplannerapp.R;
import com.example.foodplannerapp.model.DummyFilterData;
import com.example.foodplannerapp.model.FilterType;
import com.example.foodplannerapp.ui.main.home.adapter.SearchFilterAdapter;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;

public class SearchFragment extends Fragment {

    private TextInputEditText etSearch;
    private RecyclerView rvFilters;
    private SearchFilterAdapter adapter;
    ChipGroup chipGroup;



    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        setupRecyclerView();
        setupChips();
        setupSearch();
        chipGroup.check(R.id.chipCategory);
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

        adapter.setData( DummyFilterData.getByType(FilterType.CATEGORY));
        chipGroup.check(R.id.chipCategory);
    }

    private void setupSearch() {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void setupChips() {
        chipGroup.setOnCheckedChangeListener((group, checkedId) -> {

            if (checkedId == R.id.chipCategory) {
                adapter.setData(
                        DummyFilterData.getByType(FilterType.CATEGORY)
                );

            } else if (checkedId == R.id.chipArea) {
                adapter.setData(
                        DummyFilterData.getByType(FilterType.AREA)
                );

            } else if (checkedId == R.id.chipIngredient) {
                adapter.setData(
                        DummyFilterData.getByType(FilterType.INGREDIENT)
                );
            }

            // reset search text
            etSearch.setText("");
        });
    }

}