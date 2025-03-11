package com.pks.example.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="order_details")
public class OrderEntity {
    @Id
    @Column(name="order_id" ,nullable = false)
    private int orderId;

    @Column(name="item_name")
    private String itemName;

    @Column(name="customer_name")
    private String customerName;

    @Column(name="address")
    private String address;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "orderId=" + orderId +
                ", itemName='" + itemName + '\'' +
                ", customerName='" + customerName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
