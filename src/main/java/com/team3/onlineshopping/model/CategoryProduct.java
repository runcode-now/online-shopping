/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.onlineshopping.model;

/**
 *
 * @author Admin
 */
public class CategoryProduct {
    private int cateProId;
    private String cateProName;

    public CategoryProduct() {
    }

    public CategoryProduct(int cateProId, String cateProName) {
        this.cateProId = cateProId;
        this.cateProName = cateProName;
    }

    public int getCateProId() {
        return cateProId;
    }

    public void setCateProId(int cateProId) {
        this.cateProId = cateProId;
    }

    public String getCateProName() {
        return cateProName;
    }

    public void setCateProName(String cateProName) {
        this.cateProName = cateProName;
    }
    
    
}
