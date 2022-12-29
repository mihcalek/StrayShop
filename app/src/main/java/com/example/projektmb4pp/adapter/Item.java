package com.example.projektmb4pp.adapter;

import java.util.Objects;

public class Item {
    private int id;
    private String name;
    private String desc;
    private int photo;
    private float price;
    private String type;

    public Item(int id, String name, String desc, int photo, float price, String type) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.photo = photo;
        this.price = price;
        this.type = type;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return getId() == item.getId() && getPhoto() == item.getPhoto() && Float.compare(item.getPrice(), getPrice()) == 0 && getName().equals(item.getName()) && getDesc().equals(item.getDesc()) && getType().equals(item.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDesc(), getPhoto(), getPrice(), getType());
    }
}
