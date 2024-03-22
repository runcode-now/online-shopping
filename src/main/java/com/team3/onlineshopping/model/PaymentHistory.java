/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.onlineshopping.model;

/**
 *
 * @author Admin
 */
public class PaymentHistory {

    private int payId;
    private String payDate;
    private String payMethod;
    private int orId;
    private int cusId;

    public PaymentHistory() {
    }

    public PaymentHistory(int payId, String payDate, String payMethod, int orId, int cusId) {
        this.payId = payId;
        this.payDate = payDate;
        this.payMethod = payMethod;
        this.orId = orId;
        this.cusId = cusId;
    }

    public int getPayId() {
        return payId;
    }

    public void setPayId(int payId) {
        this.payId = payId;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public int getOrId() {
        return orId;
    }

    public void setOrId(int orId) {
        this.orId = orId;
    }

    public int getCusId() {
        return cusId;
    }

    public void setCusId(int cusId) {
        this.cusId = cusId;
    }

    @Override
    public String toString() {
        return "PaymentHistory{" + "payId=" + payId + ", payDate=" + payDate + ", payMethod=" + payMethod + ", orId=" + orId + ", cusId=" + cusId + '}';
    }

}
