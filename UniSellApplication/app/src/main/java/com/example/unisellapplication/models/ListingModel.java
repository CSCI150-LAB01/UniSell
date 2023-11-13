package com.example.unisellapplication.models;

public class ListingModel {
    String name;
    String price;
    String category;
    String img_url;

    public ListingModel() {
    }

    public ListingModel(String name, String price, String category, String img_url) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
