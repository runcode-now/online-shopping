/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.onlineshopping.model;

/**
 *
 * @author Admin
 */
public class Order {

    private int orId;
    private String orTitle;
    private String orDate;
    private double orTotalPrice;
    private String orStatus;
    private int emId;
    private int cusId;
    private int addId;

    public Order() {
    }

    public Order(int orId, String orTitle, String orDate, double orTotalPrice, String orStatus, int emId, int cusId, int addId) {
        this.orId = orId;
        this.orTitle = orTitle;
        this.orDate = orDate;
        this.orTotalPrice = orTotalPrice;
        this.orStatus = orStatus;
        this.emId = emId;
        this.cusId = cusId;
        this.addId = addId;
    }
    
    public Order(String orTitle, String orDate, double orTotalPrice, String orStatus, int emId, int cusId, int addId) {
        this.orTitle = orTitle;
        this.orDate = orDate;
        this.orTotalPrice = orTotalPrice;
        this.orStatus = orStatus;
        this.emId = emId;
        this.cusId = cusId;
        this.addId = addId;
    }

    public int getAddId() {
        return addId;
    }

    public void setAddId(int addId) {
        this.addId = addId;
    }

    public int getOrId() {
        return orId;
    }

    public void setOrId(int orId) {
        this.orId = orId;
    }

    public String getOrTitle() {
        return orTitle;
    }

    public void setOrTitle(String orTitle) {
        this.orTitle = orTitle;
    }

    public String getOrDate() {
        return orDate;
    }

    public void setOrDate(String orDate) {
        this.orDate = orDate;
    }

    public double getOrTotalPrice() {
        return orTotalPrice;
    }

    public void setOrTotalPrice(double orTotalPrice) {
        this.orTotalPrice = orTotalPrice;
    }

    public String getOrStatus() {
        return orStatus;
    }

    public void setOrStatus(String orStatus) {
        this.orStatus = orStatus;
    }

    public int getEmId() {
        return emId;
    }

    public void setEmId(int emId) {
        this.emId = emId;
    }

    public int getCusId() {
        return cusId;
    }

    public void setCusId(int cusId) {
        this.cusId = cusId;
    }

    @Override
    public String toString() {
        return "Order{" + "orId=" + orId + ", orTitle=" + orTitle + ", orDate=" + orDate + ", orTotalPrice=" + orTotalPrice + ", orStatus=" + orStatus + ", emId=" + emId + ", cusId=" + cusId + ", addId=" + addId + '}';
    }

}
