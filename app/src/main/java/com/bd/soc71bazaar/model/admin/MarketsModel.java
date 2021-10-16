package com.bd.soc71bazaar.model.admin;

public class MarketsModel {
    
    String  markets_id ,  products_id ,  admins_id ,  markets_status ,  create_info ,  update_info;

    public MarketsModel(String markets_id, String products_id, String admins_id, String markets_status, String create_info, String update_info) {
        this.markets_id = markets_id;
        this.products_id = products_id;
        this.admins_id = admins_id;
        this.markets_status = markets_status;
        this.create_info = create_info;
        this.update_info = update_info;
    }

    public String getMarkets_id() {
        return markets_id;
    }

    public void setMarkets_id(String markets_id) {
        this.markets_id = markets_id;
    }

    public String getProducts_id() {
        return products_id;
    }

    public void setProducts_id(String products_id) {
        this.products_id = products_id;
    }

    public String getAdmins_id() {
        return admins_id;
    }

    public void setAdmins_id(String admins_id) {
        this.admins_id = admins_id;
    }

    public String getMarkets_status() {
        return markets_status;
    }

    public void setMarkets_status(String markets_status) {
        this.markets_status = markets_status;
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
