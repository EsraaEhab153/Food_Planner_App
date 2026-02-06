package com.example.foodplannerapp.model;

public class Category {
    String name;
    int imageRes;
    public Category(String name, int imageRes){ this.name = name; this.imageRes = imageRes; }
    public String getName(){ return name; }
    public int getImageRes(){ return imageRes; }
}