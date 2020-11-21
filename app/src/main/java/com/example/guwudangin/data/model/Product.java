package com.example.guwudangin.data.model;

public class Product {
    private String id;
    private String product_type_id;
    private String user_id;
    private String product_name;
    private String price;
    private String units;
    private String description;
    private String product_picture;
    private String token;

    public Product(String id, String product_type_id, String user_id, String product_name, String price, String units, String description, String product_picture, String token) {
        this.id = id;
        this.product_type_id = product_type_id;
        this.user_id = user_id;
        this.product_name = product_name;
        this.price = price;
        this.units = units;
        this.description = description;
        this.product_picture = product_picture;
        this.token = token;
    }

    public Product(String id, String token) {
        this.id = id;
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct_type_id() {
        return product_type_id;
    }

    public void setProduct_type_id(String product_type_id) {
        this.product_type_id = product_type_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProduct_picture() {
        return product_picture;
    }

    public void setProduct_picture(String product_picture) {
        this.product_picture = product_picture;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
