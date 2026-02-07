package com.example.foodplannerapp.model;



import java.util.ArrayList;
import java.util.List;

public class DummyFilterData {

    public static List<FilterItem> getAll() {
        List<FilterItem> list = new ArrayList<>();

        // Categories
        list.add(new FilterItem("Breakfast", FilterType.CATEGORY));
        list.add(new FilterItem("Dessert", FilterType.CATEGORY));
        list.add(new FilterItem("Seafood", FilterType.CATEGORY));
        list.add(new FilterItem("Pasta", FilterType.CATEGORY));
        list.add(new FilterItem("Vegetarian", FilterType.CATEGORY));

        // Areas
        list.add(new FilterItem("Italian", FilterType.AREA));
        list.add(new FilterItem("Canadian", FilterType.AREA));
        list.add(new FilterItem("Mexican", FilterType.AREA));
        list.add(new FilterItem("Egyptian", FilterType.AREA));
        list.add(new FilterItem("Indian", FilterType.AREA));

        // Ingredients
        list.add(new FilterItem("Chicken", FilterType.INGREDIENT));
        list.add(new FilterItem("Beef", FilterType.INGREDIENT));
        list.add(new FilterItem("Rice", FilterType.INGREDIENT));
        list.add(new FilterItem("Cheese", FilterType.INGREDIENT));
        list.add(new FilterItem("Tomato", FilterType.INGREDIENT));

        return list;
    }

    public static List<FilterItem> getByType(FilterType type) {
        List<FilterItem> result = new ArrayList<>();

        for (FilterItem item : getAll()) {
            if (item.getType() == type) {
                result.add(item);
            }
        }
        return result;
    }
}
