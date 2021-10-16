package com.bd.soc71bazaar.model;

public class FeaturedProductsModel {
    private String title, genre, year;

    public FeaturedProductsModel(String title, String genre, String year) {
        this.title = title;
        this.genre = genre;
        this.year = year;
    }

    public FeaturedProductsModel(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}