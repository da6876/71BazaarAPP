package com.bd.soc71bazaar.model;

public class TopRatedModel {

    private String products_id,product_types_id,product_types_name,category_id,categories_name,brands_id,
            brands_name,country_id,country_name,name,details,image_one,image_two,image_three,image_four,price,
            discount_price,discount,quantity,sort_order,products_status;


    public TopRatedModel(String products_id, String product_types_id, String product_types_name, String category_id,String country_name, String categories_name, String brands_id, String brands_name, String country_id, String name, String details, String image_one, String image_two, String image_three, String image_four, String price, String discount_price, String discount, String quantity, String sort_order, String products_status) {
        this.products_id = products_id;
        this.product_types_id = product_types_id;
        this.product_types_name = product_types_name;
        this.category_id = category_id;
        this.categories_name = categories_name;
        this.brands_id = brands_id;
        this.brands_name = brands_name;
        this.country_id = country_id;
        this.country_name = country_name;
        this.name = name;
        this.details = details;
        this.image_one = image_one;
        this.image_two = image_two;
        this.image_three = image_three;
        this.image_four = image_four;
        this.price = price;
        this.discount_price = discount_price;
        this.discount = discount;
        this.quantity = quantity;
        this.sort_order = sort_order;
        this.products_status = products_status;
    }

    public String getProducts_id() {
        return products_id;
    }

    public void setProducts_id(String products_id) {
        this.products_id = products_id;
    }

    public String getProduct_types_id() {
        return product_types_id;
    }

    public void setProduct_types_id(String product_types_id) {
        this.product_types_id = product_types_id;
    }

    public String getProduct_types_name() {
        return product_types_name;
    }

    public void setProduct_types_name(String product_types_name) {
        this.product_types_name = product_types_name;
    }

    public String getcountry_name() {
        return country_name;
    }

    public void setcountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCategories_name() {
        return categories_name;
    }

    public void setCategories_name(String categories_name) {
        this.categories_name = categories_name;
    }

    public String getBrands_id() {
        return brands_id;
    }

    public void setBrands_id(String brands_id) {
        this.brands_id = brands_id;
    }

    public String getBrands_name() {
        return brands_name;
    }

    public void setBrands_name(String brands_name) {
        this.brands_name = brands_name;
    }

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImage_one() {
        return image_one;
    }

    public void setImage_one(String image_one) {
        this.image_one = image_one;
    }

    public String getImage_two() {
        return image_two;
    }

    public void setImage_two(String image_two) {
        this.image_two = image_two;
    }

    public String getImage_three() {
        return image_three;
    }

    public void setImage_three(String image_three) {
        this.image_three = image_three;
    }

    public String getImage_four() {
        return image_four;
    }

    public void setImage_four(String image_four) {
        this.image_four = image_four;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(String discount_price) {
        this.discount_price = discount_price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getSort_order() {
        return sort_order;
    }

    public void setSort_order(String sort_order) {
        this.sort_order = sort_order;
    }

    public String getProducts_status() {
        return products_status;
    }

    public void setProducts_status(String products_status) {
        this.products_status = products_status;
    }
}