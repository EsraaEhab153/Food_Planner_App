package com.example.foodplannerapp.model;

public class FilterItem {
    private String name;
    private FilterType type; // CATEGORY, INGREDIENT, AREA
    private String thumb; // الصورة لكل item

    // constructor جديد يدعم الصورة
    public FilterItem(String name, FilterType type, String thumb) {
        this.name = name;
        this.type = type;
        this.thumb = thumb;
    }

    public String getName() { return name; }
    public FilterType getType() { return type; }
    public String getThumb() { return thumb; } // getter للصورة
}