package com.example.foodplannerapp.model;

import java.io.Serializable;

public class Meal implements Serializable {

    private String idMeal;
    private String strMeal;
    private String strCategory;
    private String strArea;
    private String strInstructions;
    private String strMealThumb;
    private String strYoutube;

    private String strIngredient1;
    private String strIngredient2;
    // ...
    private String strIngredient20;

    private String strMeasure1;
    private String strMeasure2;
    // ...
    private String strMeasure20;

    // ===== Helpers =====
    public String getIngredient(int i){
        switch(i){
            case 1: return strIngredient1;
            case 2: return strIngredient2;
            // ...
            case 20: return strIngredient20;
            default: return null;
        }
    }

    public String getMeasure(int i){
        switch(i){
            case 1: return strMeasure1;
            case 2: return strMeasure2;
            // ...
            case 20: return strMeasure20;
            default: return null;
        }
    }

    // ===== Getters =====

    public String getIdMeal() { return idMeal; }
    public String getStrMeal() { return strMeal; }
    public String getStrCategory() { return strCategory; }
    public String getStrArea() { return strArea; }
    public String getStrInstructions() { return strInstructions; }
    public String getStrMealThumb() { return strMealThumb; }
    public String getStrYoutube() { return strYoutube; }
}