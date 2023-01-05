package com.example.projektmb4pp;

import com.example.projektmb4pp.adapter.Item;

public class OrderItem {
    private int id;

    private Item item;
    private int count;
    private String size;

    public OrderItem(int id, Item item, int count, String size) {
        this.id = id;
        this.item = item;
        this.count = count;
        this.size = size;
    }

    public OrderItem(Item item, int count, String size) {
        this.id = -1;
        this.item = item;
        this.count = count;
        this.size = size;
    }

    public float getTotal() {
        return item.getPrice() * count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderItem orderItem = (OrderItem) o;

        if (id != orderItem.id) return false;
        if (!item.equals(orderItem.item)) return false;
        return size.equals(orderItem.size);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + item.hashCode();
        result = 31 * result + size.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return count + " x " + item.getName() + " (" + size + ")";
    }
}