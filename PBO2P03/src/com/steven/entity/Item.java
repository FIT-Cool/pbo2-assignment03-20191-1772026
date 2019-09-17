package com.steven.entity;

public class Item extends Category {
    private String nama;
    private String price;
    private Category category;


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String kode) {
        this.price = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
