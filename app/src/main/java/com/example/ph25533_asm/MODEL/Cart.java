package com.example.ph25533_asm.MODEL;

public class Cart {
    private int idFood,soluongsp,soluongmua,tongtien;
    private String nameFood, imageFood;
    private long price;
    public int check_chon;

    public Cart() {
    }

    public Cart(int idFood, int soluongsp, String nameFood, String imageFood, long price) {
        this.idFood = idFood;
        this.soluongsp = soluongsp;
        this.nameFood = nameFood;
        this.imageFood = imageFood;
        this.price = price;
    }

    public int getSoluongmua() {
        return soluongmua;
    }

    public void setSoluongmua(int soluongmua) {
        this.soluongmua = soluongmua;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    public int getIdFood() {
        return idFood;
    }

    public void setIdFood(int idFood) {
        this.idFood = idFood;
    }

    public int getSoluongsp() {
        return soluongsp;
    }

    public void setSoluongsp(int soluongsp) {
        this.soluongsp = soluongsp;
    }

    public int getCheck_chon() {
        return check_chon;
    }

    public void setCheck_chon(int check_chon) {
        this.check_chon = check_chon;
    }

    public String getNameFood() {
        return nameFood;
    }

    public void setNameFood(String nameFood) {
        this.nameFood = nameFood;
    }

    public String getImageFood() {
        return imageFood;
    }

    public void setImageFood(String imageFood) {
        this.imageFood = imageFood;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}

