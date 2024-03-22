/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.onlineshopping.model;

/**
 *
 * @author PC
 */
public class ExcelStatistic {

    private String orderDate;
    private double totalPrice;
    private int orderCount;
    private double averagePrice;
    private String orderStatus;

    public ExcelStatistic() {
    }

    public ExcelStatistic(String orderDate, double totalPrice, int orderCount, double averagePrice, String orderStatus) {
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.orderCount = orderCount;
        this.averagePrice = averagePrice;
        this.orderStatus = orderStatus;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "ExcelStatistic{" + "orderDate=" + orderDate + ", totalPrice=" + totalPrice + ", orderCount=" + orderCount + ", averagePrice=" + averagePrice + ", orderStatus=" + orderStatus + '}';
    }

}
