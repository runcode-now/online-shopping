/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.onlineshopping.model;

/**
 *
 * @author Admin
 */
public class OrderDetails {

    private int proId;
    private int orId;
    private int orDeQuantity;
    private int cateSizeId;

    public OrderDetails() {
    }

    public OrderDetails(int proId, int orId, int orDeQuantity, int cateSizeId) {
        this.proId = proId;
        this.orId = orId;
        this.orDeQuantity = orDeQuantity;
        this.cateSizeId = cateSizeId;
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public int getOrId() {
        return orId;
    }

    public void setOrId(int orId) {
        this.orId = orId;
    }

    public int getOrDeQuantity() {
        return orDeQuantity;
    }

    public void setOrDeQuantity(int orDeQuantity) {
        this.orDeQuantity = orDeQuantity;
    }

    public int getCateSizeId() {
        return cateSizeId;
    }

    public void setCateSizeId(int cateSizeId) {
        this.cateSizeId = cateSizeId;
    }

    @Override
    public String toString() {
        return "OrderDetails{" + "proId=" + proId + ", orId=" + orId + ", orDeQuantity=" + orDeQuantity + ", cateSizeId=" + cateSizeId + '}';
    }

}
