package com.example.foodplannerapp.model;

public class FilterItem {
    private String name;
    private FilterType type; // CATEGORY, INGREDIENT, AREA

    public FilterItem(String name, FilterType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() { return name; }
    public FilterType getType() { return type; }
}
 enum FilterType {
    CATEGORY,
    INGREDIENT,
    AREA
}

