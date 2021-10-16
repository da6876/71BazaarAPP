package com.bd.soc71bazaar.model.admin;

public class AdminModel {
    String admins_id, shop_id ,  user_type_id ,  name ,  username ,
            phone ,  email ,  email_verified_at ,  password ,  remember_token ,
            admins_status ,  create_info ,  update_info ;

    public AdminModel(String admins_id, String shop_id, String user_type_id, String name, String username, String phone, String email, String email_verified_at, String password, String remember_token, String admins_status, String create_info, String update_info) {
        this.admins_id = admins_id;
        this.shop_id = shop_id;
        this.user_type_id = user_type_id;
        this.name = name;
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.email_verified_at = email_verified_at;
        this.password = password;
        this.remember_token = remember_token;
        this.admins_status = admins_status;
        this.create_info = create_info;
        this.update_info = update_info;
    }

    public String getAdmins_id() {
        return admins_id;
    }

    public void setAdmins_id(String admins_id) {
        this.admins_id = admins_id;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getUser_type_id() {
        return user_type_id;
    }

    public void setUser_type_id(String user_type_id) {
        this.user_type_id = user_type_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail_verified_at() {
        return email_verified_at;
    }

    public void setEmail_verified_at(String email_verified_at) {
        this.email_verified_at = email_verified_at;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRemember_token() {
        return remember_token;
    }

    public void setRemember_token(String remember_token) {
        this.remember_token = remember_token;
    }

    public String getAdmins_status() {
        return admins_status;
    }

    public void setAdmins_status(String admins_status) {
        this.admins_status = admins_status;
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
