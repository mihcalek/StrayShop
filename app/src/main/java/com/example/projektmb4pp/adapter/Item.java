package com.example.projektmb4pp.adapter;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Arrays;
import java.util.Objects;

public class Item {
    private int id;
    private String name;
    private String desc;
    private String photo;
    private float price;
    private String type;

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", photo='" + photo + '\'' +
                ", price=" + price +
                ", type='" + type + '\'' +
                '}';
    }

    public Item(int id, String name, String desc, String photo, float price, String type) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.photo = photo;
        this.price = price;
        this.type = type;
    }

    public Item getItem(SQLiteDatabase db, int id) {
        Cursor cursor = db.rawQuery("SELECT * FROM Product WHERE _id = " + id, null);
        Item item = null;
        if (cursor.moveToNext()) {
            item = new Item(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getFloat(4),
                    cursor.getString(5)
            );
        }
        cursor.close();
        return item;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
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
        return getId() == item.getId() && Float.compare(item.getPrice(), getPrice()) == 0 && getName().equals(item.getName()) && getDesc().equals(item.getDesc()) && getPhoto().equals(item.getPhoto()) && getType().equals(item.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDesc(), getPhoto(), getPrice(), getType());
    }
}
