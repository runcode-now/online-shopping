/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.onlineshopping.model;

/**
 *
 * @author Admin
 */
public class CategoryNews {
    private int cateNewsId;
    private String cateNewsName;

    public CategoryNews() {
    }

    public CategoryNews(int cateNewsId, String cateNewsName) {
        this.cateNewsId = cateNewsId;
        this.cateNewsName = cateNewsName;
    }

    public int getCateNewsId() {
        return cateNewsId;
    }

    public void setCateNewsId(int cateNewsId) {
        this.cateNewsId = cateNewsId;
    }

    public String getCateNewsName() {
        return cateNewsName;
    }

    public void setCateNewsName(String cateNewsName) {
        this.cateNewsName = cateNewsName;
    }
    
    
}
