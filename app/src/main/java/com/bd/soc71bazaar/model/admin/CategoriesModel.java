package com.bd.soc71bazaar.model.admin;

public class CategoriesModel {
    private String  categories_id ,  categories_name ,  categories_status ,  create_info ,  update_info ;

    public CategoriesModel(String categories_id, String categories_name, String categories_status, String create_info, String update_info) {
        this.categories_id = categories_id;
        this.categories_name = categories_name;
        this.categories_status = categories_status;
        this.create_info = create_info;
        this.update_info = update_info;
    }

    public String getCategories_id() {
        return categories_id;
    }

    public void setCategories_id(String categories_id) {
        this.categories_id = categories_id;
    }

    public String getCategories_name() {
        return categories_name;
    }

    public void setCategories_name(String categories_name) {
        this.categories_name = categories_name;
    }

    public String getCategories_status() {
        return categories_status;
    }

    public void setCategories_status(String categories_status) {
        this.categories_status = categories_status;
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