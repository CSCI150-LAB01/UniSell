package com.example.unisellapplication.models;

public class ListingModel {
    String date;
    String time;
    String img_url;
    String title;
    String description;
    Float price;
    String category;
    String userName;
    String userPhone;
    public ListingModel() {
    }

    public ListingModel(String date, String time, String img_url, String title, String description, Float price, String category, String userName, String userPhone) {
        this.date = date;
        this.time = time;
        this.img_url = img_url;
        this.title = title;
        this.description = description;
        this.price = price;
        this.category = category;
        this.userName = userName;
        this.userPhone = userPhone;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
