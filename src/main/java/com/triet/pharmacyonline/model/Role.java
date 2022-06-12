package com.triet.pharmacyonline.model;

public class Role {
    private int id;
    private String code;
    private String role;

    public Role() {
    }

    public Role(int id, String code, String role) {
        this.id = id;
        this.code = code;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
