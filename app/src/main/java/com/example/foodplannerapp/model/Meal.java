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
    private String strIngredient3;
    private String strIngredient4;
    private String strIngredient5;
    private String strIngredient6;
    private String strIngredient7;
    private String strIngredient8;
    private String strIngredient9;
    private String strIngredient10;
    private String strIngredient11;
    private String strIngredient12;
    private String strIngredient13;
    private String strIngredient14;
    private String strIngredient15;
    private String strIngredient16;
    private String strIngredient17;
    private String strIngredient18;
    private String strIngredient19;
    private String strIngredient20;

    private String strMeasure1;
    private String strMeasure2;
    private String strMeasure3;
    private String strMeasure4;
    private String strMeasure5;
    private String strMeasure6;
    private String strMeasure7;
    private String strMeasure8;
    private String strMeasure9;
    private String strMeasure10;
    private String strMeasure11;
    private String strMeasure12;
    private String strMeasure13;
    private String strMeasure14;
    private String strMeasure15;
    private String strMeasure16;
    private String strMeasure17;
    private String strMeasure18;
    private String strMeasure19;
    private String strMeasure20;

    // ===== Helpers =====
    public String getIngredient(int i){
        switch(i){
            case 1: return strIngredient1;
            case 2: return strIngredient2;
            case 3: return strIngredient3;
            case 4: return strIngredient4;
            case 5: return strIngredient5;
            case 6: return strIngredient6;
            case 7: return strIngredient7;
            case 8: return strIngredient8;
            case 9: return strIngredient9;
            case 10: return strIngredient10;
            case 11: return strIngredient11;
            case 12: return strIngredient12;
            case 13: return strIngredient13;
            case 14: return strIngredient14;
            case 15: return strIngredient15;
            case 16: return strIngredient16;
            case 17: return strIngredient17;
            case 18: return strIngredient18;
            case 19: return strIngredient19;
            case 20: return strIngredient20;
            default: return null;
        }
    }

    public String getMeasure(int i){
        switch(i){
            case 1: return strMeasure1;
            case 2: return strMeasure2;
            case 3: return strMeasure3;
            case 4: return strMeasure4;
            case 5: return strMeasure5;
            case 6: return strMeasure6;
            case 7: return strMeasure7;
            case 8: return strMeasure8;
            case 9: return strMeasure9;
            case 10: return strMeasure10;
            case 11: return strMeasure11;
            case 12: return strMeasure12;
            case 13: return strMeasure13;
            case 14: return strMeasure14;
            case 15: return strMeasure15;
            case 16: return strMeasure16;
            case 17: return strMeasure17;
            case 18: return strMeasure18;
            case 19: return strMeasure19;
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