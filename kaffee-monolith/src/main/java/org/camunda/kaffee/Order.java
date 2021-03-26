package org.camunda.kaffee;

public class Order {
    public String orderInfo;
    public String orderName;
    public Integer orderId;

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
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
        this.orderInfo = orderMessage;
        this.orderName = orderName;
        this.orderId = orderId;
    }
}
