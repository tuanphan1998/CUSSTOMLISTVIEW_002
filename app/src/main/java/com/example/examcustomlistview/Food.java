package com.example.examcustomlistview;

import java.io.Serializable;

public class Food implements Serializable {
    int image;
    String name;
    int price;
    boolean check;

    public Food(boolean check, int image, String name, int price) {
        this.check = check;
        this.image = image;
        this.name = name;
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}


