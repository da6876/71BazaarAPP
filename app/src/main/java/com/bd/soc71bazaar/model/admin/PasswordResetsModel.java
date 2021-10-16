package com.bd.soc71bazaar.model.admin;

public class PasswordResetsModel {
    String  email ,  token ,  password_resets_status ,  create_info ,  update_info ;

    public PasswordResetsModel(String email, String token, String password_resets_status, String create_info, String update_info) {
        this.email = email;
        this.token = token;
        this.password_resets_status = password_resets_status;
        this.create_info = create_info;
        this.update_info = update_info;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword_resets_status() {
        return password_resets_status;
    }

    public void setPassword_resets_status(String password_resets_status) {
        this.password_resets_status = password_resets_status;
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
