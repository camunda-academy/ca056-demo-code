package com.camunda.controller;

public class Order {
    public String orderMessage;
    public String orderName;
    public Integer orderId;

    public String getOrderMessage() {
        return orderMessage;
    }

    public void setOrderMessage(String orderMessage) {
        this.orderMessage = orderMessage;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    public Order(String orderMessage, String orderName, Integer orderId) {
        this.orderMessage = orderMessage;
        this.orderName = orderName;
        this.orderId = orderId;
    }
}
