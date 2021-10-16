package com.bd.soc71bazaar.model.admin;

public class SubCategoriesModel {
    private String  sub_categories_id ,  categories_id ,  sub_categories_name ,   sub_categories_status ,  create_info ,  update_info ;

    public SubCategoriesModel(String sub_categories_id, String categories_id, String sub_categories_name, String sub_categories_status, String create_info, String update_info) {
        this.sub_categories_id = sub_categories_id;
        this.categories_id = categories_id;
        this.sub_categories_name = sub_categories_name;
        this.sub_categories_status = sub_categories_status;
        this.create_info = create_info;
        this.update_info = update_info;
    }

    public String getSub_categories_id() {
        return sub_categories_id;
    }

    public void setSub_categories_id(String sub_categories_id) {
        this.sub_categories_id = sub_categories_id;
    }

    public String getCategories_id() {
        return categories_id;
    }

    public void setCategories_id(String categories_id) {
        this.categories_id = categories_id;
    }

    public String getSub_categories_name() {
        return sub_categories_name;
    }

    public void setSub_categories_name(String sub_categories_name) {
        this.sub_categories_name = sub_categories_name;
    }

    public String getSub_categories_status() {
        return sub_categories_status;
    }

    public void setSub_categories_status(String sub_categories_status) {
        this.sub_categories_status = sub_categories_status;
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