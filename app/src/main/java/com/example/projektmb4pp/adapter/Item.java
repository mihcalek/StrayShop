package com.example.projektmb4pp.adapter;

import java.util.Arrays;
import java.util.Objects;

public class Item {
    private int id;
    private String name;
    private String desc;
    private byte[] photo;
    private float price;
    private String type;

    public Item(int id, String name, String desc, byte[] photo, float price, String type) {
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

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
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
        return getId() == item.getId() && Float.compare(item.getPrice(), getPrice()) == 0 && getName().equals(item.getName()) && getDesc().equals(item.getDesc()) && Arrays.equals(getPhoto(), item.getPhoto()) && getType().equals(item.getType());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getId(), getName(), getDesc(), getPrice(), getType());
        result = 31 * result + Arrays.hashCode(getPhoto());
        return result;
    }
}
