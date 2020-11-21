package com.example.guwudangin.data.model;

public class ProductDetail {
    private String id;
    private String product_id;
    private String user_id;
    private String product_quantity;
    private String in_date;
    private String exp_date;
    private Product product;
    private String token;

    public ProductDetail(String id, String product_id, String user_id, String product_quantity, String in_date, String exp_date, Product product, String token) {
        this.id = id;
        this.product_id = product_id;
        this.user_id = user_id;
        this.product_quantity = product_quantity;
        this.in_date = in_date;
        this.exp_date = exp_date;
        this.product = product;
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(String product_quantity) {
        this.product_quantity = product_quantity;
    }

    public String getIn_date() {
        return in_date;
    }

    public void setIn_date(String in_date) {
        this.in_date = in_date;
    }

    public String getExp_date() {
        return exp_date;
    }

    public void setExp_date(String exp_date) {
        this.exp_date = exp_date;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
