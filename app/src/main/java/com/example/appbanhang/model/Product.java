package com.example.appbanhang.model;

public class Product {
    public  String name, price, imageLink, masp, soluong;

    public Product(){}

    public Product(String masp,String name, String price, String imageLink) {
        this.masp = masp;
        this.name = name;
        this.price = price;
        this.imageLink = imageLink;
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

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
