package com.example.projektmb4pp.adapter;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Arrays;

public class Order {
    private long id;
    private long clientID;
    private ArrayList<OrderItem> items;
    private String date;
    private String homeAddress;
    private String city;
    private String postalCode;
    private String name;
    private Float total;

    public Order(long id, long clientID, ArrayList<OrderItem> items, String date, String homeAddress, String city, String postalCode, String name) {
        this.id = id;
        this.clientID = clientID;
        this.items = items;
        this.date = date;
        this.homeAddress = homeAddress;
        this.city = city;
        this.postalCode = postalCode;
        this.name = name;
        this.total = 0f;
        for (OrderItem item : items) {
            this.total += item.getTotal();
        }
    }

    public Order(long clientID, ArrayList<OrderItem> items, String date, String homeAddress, String city, String postalCode) {
        this.id = -1;
        this.clientID = clientID;
        this.items = items;
        this.date = date;
        this.homeAddress = homeAddress;
        this.city = city;
        this.postalCode = postalCode;
        this.name = "";
        this.total = 0f;
        for (OrderItem item : items) {
            total += item.getTotal();
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

    public ArrayList<OrderItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<OrderItem> items) {
        this.items = items;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
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
        if (!items.equals(order.items)) return false;
        if (!date.equals(order.date)) return false;
        if (!homeAddress.equals(order.homeAddress)) return false;
        if (!city.equals(order.city)) return false;
        if (!postalCode.equals(order.postalCode)) return false;
        if (!name.equals(order.name)) return false;
        return total.equals(order.total);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (clientID ^ (clientID >>> 32));
        result = 31 * result + items.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + homeAddress.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + postalCode.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + total.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", clientID=" + clientID +
                ", items=" + items +
                ", date='" + date + '\'' +
                ", homeAddress='" + homeAddress + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", name='" + name + '\'' +
                ", total=" + total +
                '}';
    }
}