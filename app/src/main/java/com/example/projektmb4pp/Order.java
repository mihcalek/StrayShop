package com.example.projektmb4pp;

import java.util.Arrays;

public class Order {
    private long id;
    private long clientID;
    private OrderItem[] items;
    private String date;
    private String address;
    private String name;
    private Float total;

    public Order(long id, long clientID, OrderItem[] items, String date, String address, String name) {
        this.id = id;
        this.clientID = clientID;
        this.items = items;
        this.date = date;
        this.address = address;
        this.name = name;
        this.total = 0f;
        for (OrderItem item : items) {
            this.total += item.getTotal();
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getClientID() {
        return clientID;
    }

    public void setClientID(long clientID) {
        this.clientID = clientID;
    }

    public OrderItem[] getItems() {
        return items;
    }

    public void setItems(OrderItem[] items) {
        this.items = items;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (clientID != order.clientID) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(items, order.items)) return false;
        if (!date.equals(order.date)) return false;
        if (!address.equals(order.address)) return false;
        if (!name.equals(order.name)) return false;
        return total.equals(order.total);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (clientID ^ (clientID >>> 32));
        result = 31 * result + Arrays.hashCode(items);
        result = 31 * result + date.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + total.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", clientID=" + clientID +
                ", items=" + Arrays.toString(items) +
                ", date='" + date + '\'' +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", total=" + total +
                '}';
    }
}
