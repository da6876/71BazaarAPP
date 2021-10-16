package com.bd.soc71bazaar.model.admin;

public class UserTypesModel {
    String  user_type_id ,  user_type_name ,  user_type_status ,  create_info ,  update_info ;

    public UserTypesModel(String user_type_id, String user_type_name, String user_type_status, String create_info, String update_info) {
        this.user_type_id = user_type_id;
        this.user_type_name = user_type_name;
        this.user_type_status = user_type_status;
        this.create_info = create_info;
        this.update_info = update_info;
    }

    public String getUser_type_id() {
        return user_type_id;
    }

    public void setUser_type_id(String user_type_id) {
        this.user_type_id = user_type_id;
    }

    public String getUser_type_name() {
        return user_type_name;
    }

    public void setUser_type_name(String user_type_name) {
        this.user_type_name = user_type_name;
    }

    public String getUser_type_status() {
        return user_type_status;
    }

    public void setUser_type_status(String user_type_status) {
        this.user_type_status = user_type_status;
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
