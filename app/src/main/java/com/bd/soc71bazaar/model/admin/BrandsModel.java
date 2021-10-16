package com.bd.soc71bazaar.model.admin;

public class BrandsModel {
    String  brands_id ,  brands_name ,  brands_status ,  create_info ,  update_info;

    public BrandsModel(String brands_id, String brands_name, String brands_status, String create_info, String update_info) {
        this.brands_id = brands_id;
        this.brands_name = brands_name;
        this.brands_status = brands_status;
        this.create_info = create_info;
        this.update_info = update_info;
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

    public String getBrands_status() {
        return brands_status;
    }

    public void setBrands_status(String brands_status) {
        this.brands_status = brands_status;
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
