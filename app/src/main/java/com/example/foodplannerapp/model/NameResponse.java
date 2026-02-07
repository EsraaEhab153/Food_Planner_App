package com.example.foodplannerapp.model;

import com.google.gson.annotations.SerializedName;

public class NameResponse {

    @SerializedName("strCategory")
    private String strCategory;

    @SerializedName("strArea")
    private String strArea;

    @SerializedName("strIngredient")
    private String strIngredient;

    @SerializedName("strThumb")
    private String strThumb;


    public String getName(FilterType type) {
        switch (type) {
            case CATEGORY: return strCategory != null ? strCategory : "";
            case AREA: return strArea != null ? strArea : "";
            case INGREDIENT: return strIngredient != null ? strIngredient : "";
            default: return "";
        }
    }

    public String getThumb(FilterType type) {
        if (type == FilterType.INGREDIENT && strThumb != null) {
            return strThumb;
        }
        return "";
    }
}