package com.bd.soc71bazaar.model.admin;

public class ProductModel {
    String  products_id ,  product_types_id ,  category_id ,  brands_id ,  country_id ,  name ,
            details ,  image_one ,  image_two ,  image_three ,  image_four ,  price ,  discount_price ,
            discount ,  quantity ,  sort_order ,  products_status ,  create_info ,  update_info;

    public ProductModel(String products_id, String product_types_id, String category_id, String brands_id, String country_id, String name, String details, String image_one, String image_two, String image_three, String image_four, String price, String discount_price, String discount, String quantity, String sort_order, String products_status, String create_info, String update_info) {
        this.products_id = products_id;
        this.product_types_id = product_types_id;
        this.category_id = category_id;
        this.brands_id = brands_id;
        this.country_id = country_id;
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
        this.create_info = create_info;
        this.update_info = update_info;
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

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getBrands_id() {
        return brands_id;
    }

    public void setBrands_id(String brands_id) {
        this.brands_id = brands_id;
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

    public String getCreate_info() {
        return create_info;
    }

    public void setCreate_info(String create_info) {
        this.create_info = create_info;
    }

    public String getUpdate_info() {
        return update_info;
    }

    public void setUpdate_info(String update_info) {
        this.update_info = update_info;
    }
}
