package com.example.foodplannerapp.ui.main.home;

import com.example.foodplannerapp.R;
import com.example.foodplannerapp.model.Category;

import java.util.ArrayList;
import java.util.List;

public class HomeRepository {

    public List<Category> getCategories() {
        List<Category> list = new ArrayList<>();
        list.add(new Category("Breakfast", R.drawable.meat));
        list.add(new Category("Lunch", R.drawable.meat));
        list.add(new Category("Dinner", R.drawable.meat));
        list.add(new Category("Dessert", R.drawable.vegetables));
        return list;
    }
}

