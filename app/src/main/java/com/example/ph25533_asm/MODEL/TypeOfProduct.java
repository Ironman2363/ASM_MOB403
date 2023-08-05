package com.example.ph25533_asm.MODEL;

public class TypeOfProduct {
    private int id;
    private String name;

    public TypeOfProduct() {
    }

    public TypeOfProduct(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
