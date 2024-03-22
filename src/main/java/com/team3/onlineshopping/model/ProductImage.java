/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.onlineshopping.model;

/**
 *
 * @author Admin
 */
public class ProductImage {
    private int proImgId;
    private String proImgUrl;
    private int proId;

    public ProductImage() {
    }

    public ProductImage(int proImgId, String proImgUrl, int proId) {
        this.proImgId = proImgId;
        this.proImgUrl = proImgUrl;
        this.proId = proId;
    }

    public ProductImage(String proImgUrl, int proId) {
        this.proImgUrl = proImgUrl;
        this.proId = proId;
    }

    
    public int getProImgId() {
        return proImgId;
    }

    public void setProImgId(int proImgId) {
        this.proImgId = proImgId;
    }

    public String getProImgUrl() {
        return proImgUrl;
    }

    public void setProImgUrl(String proImgUrl) {
        this.proImgUrl = proImgUrl;
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }
    
    
}
