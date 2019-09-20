package com.steven.entity;

/**
 * Steven Rumanto
 * 1772026
 */

public class Item extends Category {
    private String nama;
    private String price;
    private String tanggal;
    private Category category;


    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

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
