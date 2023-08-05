package com.example.ph25533_asm.MODEL;

import java.io.Serializable;

public class Product implements Serializable {
    private int idFood, idTypeOfFood, price, amount , checkfav;
    private String imageFood, name , moTa;

    public Product() {
    }

    public Product(int idFood, int idTypeOfFood, int price, int amount, String imageFood, String name, String mota, int checkfav) {
        this.idFood = idFood;
        this.idTypeOfFood = idTypeOfFood;
        this.price = price;
        this.amount = amount;
        this.imageFood = imageFood;
        this.name = name;
        this.moTa = mota;
        this.checkfav = checkfav;
    }

    public int getIdFood() {
        return idFood;
    }

    public void setIdFood(int idFood) {
        this.idFood = idFood;
    }

    public int getIdTypeOfFood() {
        return idTypeOfFood;
    }

    public void setIdTypeOfFood(int idTypeOfFood) {
        this.idTypeOfFood = idTypeOfFood;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getImageFood() {
        return imageFood;
    }

    public void setImageFood(String imageFood) {
        this.imageFood = imageFood;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCheckfav() {
        return checkfav;
    }

    public void setCheckfav(int checkfav) {
        this.checkfav = checkfav;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
