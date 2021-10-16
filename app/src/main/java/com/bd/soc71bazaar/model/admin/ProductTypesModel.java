package com.bd.soc71bazaar.model.admin;

public class ProductTypesModel {
    
    String  product_types_id ,  product_types_name ,  product_types_status ,  create_info ,  update_info ;

    public ProductTypesModel(String product_types_id, String product_types_name, String product_types_status, String create_info, String update_info) {
        this.product_types_id = product_types_id;
        this.product_types_name = product_types_name;
        this.product_types_status = product_types_status;
        this.create_info = create_info;
        this.update_info = update_info;
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

    public String getProduct_types_status() {
        return product_types_status;
    }

    public void setProduct_types_status(String product_types_status) {
        this.product_types_status = product_types_status;
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
