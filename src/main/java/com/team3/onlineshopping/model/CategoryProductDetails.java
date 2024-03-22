/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.onlineshopping.model;

/**
 *
 * cateProDeId
 */
public class CategoryProductDetails {
    private int cateProDeId;
    private String cateProDeName;
    private int cateProId;

    public CategoryProductDetails() {
    }

    public CategoryProductDetails(int cateProDeId, String cateProDeName, int cateProId) {
        this.cateProDeId = cateProDeId;
        this.cateProDeName = cateProDeName;
        this.cateProId = cateProId;
    }

    public int getCateProDeId() {
        return cateProDeId;
    }

    public void setCateProDeId(int cateProDeId) {
        this.cateProDeId = cateProDeId;
    }

    public String getCateProDeName() {
        return cateProDeName;
    }

    public void setCateProDeName(String cateProDeName) {
        this.cateProDeName = cateProDeName;
    }

    public int getCateProId() {
        return cateProId;
    }

    public void setCateProId(int cateProId) {
        this.cateProId = cateProId;
    }
    
    
}
