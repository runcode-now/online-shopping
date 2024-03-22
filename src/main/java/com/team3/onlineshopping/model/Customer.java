/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.onlineshopping.model;

/**
 *
 * @author Admin
 */
public class Customer {
    private int cusId;
    private String cusCreatedDate;
    private double cusTotalPurchase;
    private int accId;
    private int roleId;

    public Customer() {
    }

    public Customer(int cusId, String cusCreatedDate, double cusTotalPurchase, int accId, int roleId) {
        this.cusId = cusId;
        this.cusCreatedDate = cusCreatedDate;
        this.cusTotalPurchase = cusTotalPurchase;
        this.accId = accId;
        this.roleId = roleId;
    }

    public int getCusId() {
        return cusId;
    }

    public void setCusId(int cusId) {
        this.cusId = cusId;
    }

    public String getCusCreatedDate() {
        return cusCreatedDate;
    }

    public void setCusCreatedDate(String cusCreatedDate) {
        this.cusCreatedDate = cusCreatedDate;
    }

    public double getCusTotalPurchase() {
        return cusTotalPurchase;
    }

    public void setCusTotalPurchase(double cusTotalPurchase) {
        this.cusTotalPurchase = cusTotalPurchase;
    }

    public int getAccId() {
        return accId;
    }

    public void setAccId(int accId) {
        this.accId = accId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    
}
