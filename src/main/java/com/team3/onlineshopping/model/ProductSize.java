/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.onlineshopping.model;

/**
 *
 * @author Admin
 */
public class ProductSize {

    private int proId;
    private int cateSizeId;
    private int proSizeQuantity;

    public ProductSize() {
    }

    public ProductSize(int proId, int cateSizeId, int proSizeQuantity) {
        this.proId = proId;
        this.cateSizeId = cateSizeId;
        this.proSizeQuantity = proSizeQuantity;
    }

    

    public int getProSizeQuantity() {
        return proSizeQuantity;
    }

    public void setProSizeQuantity(int proSizeQuantity) {
        this.proSizeQuantity = proSizeQuantity;
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public int getCateSizeId() {
        return cateSizeId;
    }

    public void setCateSizeId(int cateSizeId) {
        this.cateSizeId = cateSizeId;
    }

}
