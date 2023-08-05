package com.example.ph25533_asm.MODEL;

import java.util.Date;

public class Bill {
    private int id,priceProduct, tongTienSoSp,soluongmua,maAccount;
    private String img, name;

    public Bill() {
    }

    public Bill(int price, int tongTienSoSp, String img, String name,int soluongmua,int maAccount) {
        this.priceProduct = price;
        this.tongTienSoSp = tongTienSoSp;
        this.img = img;
        this.name = name;
        this.soluongmua = soluongmua;
        this.maAccount = maAccount;
    }

    public Bill(int id, int priceProduct, int tongTienSoSp,
                int maAccount, String img, String name, int soluongmua) {
        this.id = id;
        this.priceProduct = priceProduct;
        this.tongTienSoSp = tongTienSoSp;
        this.maAccount = maAccount;
        this.img = img;
        this.name = name;
        this.soluongmua = soluongmua;
    }

    public int getTongTienSoSp() {
        return tongTienSoSp;
    }

    public void setTongTienSoSp(int tongTienSoSp) {
        this.tongTienSoSp = tongTienSoSp;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(int priceProduct) {
        this.priceProduct = priceProduct;
    }

    public int getSoluongmua() {
        return soluongmua;
    }

    public void setSoluongmua(int soluongmua) {
        this.soluongmua = soluongmua;
    }

    public int getMaAccount() {
        return maAccount;
    }

    public void setMaAccount(int maAccount) {
        this.maAccount = maAccount;
    }
}
