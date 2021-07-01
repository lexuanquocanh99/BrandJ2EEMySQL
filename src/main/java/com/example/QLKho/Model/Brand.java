package com.example.QLKho.Model;

public class Brand {
    private int id;
    private String name;
    private int is_enabled;
    private String description;

    public Brand () {
    }

    public Brand(int id) {
        this.id = id;
    }

    public Brand (String name, int is_enabled, String description) {
        super();
        this.name = name;
        this.is_enabled = is_enabled;
        this.description = description;
    }

    public Brand(int id, String name, int is_enabled, String description) {
        super();
        this.id = id;
        this.name = name;
        this.is_enabled = is_enabled;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIs_enabled() {
        return is_enabled;
    }

    public void setIs_enabled(int is_enabled) {
        this.is_enabled = is_enabled;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
