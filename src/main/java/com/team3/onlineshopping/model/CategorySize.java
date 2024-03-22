/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.onlineshopping.model;

/**
 *
 * @author Admin
 */
public class CategorySize {
    private int cateSizeId;
    private String cateSizeName;

    public CategorySize() {
    }

    public CategorySize(int cateSizeId, String cateSizeName) {
        this.cateSizeId = cateSizeId;
        this.cateSizeName = cateSizeName;
    }

    public int getCateSizeId() {
        return cateSizeId;
    }

    public void setCateSizeId(int cateSizeId) {
        this.cateSizeId = cateSizeId;
    }

    public String getCateSizeName() {
        return cateSizeName;
    }

    public void setCateSizeName(String cateSizeName) {
        this.cateSizeName = cateSizeName;
    }
    
    
}
