/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.onlineshopping.model;

/**
 *
 * @author Admin
 */
public class Promotion {
    private int promoId;
    private String promoName;
    private int promoDiscount;
    private String promoStartDate;
    private String promoEndDate;
    private int promoQuantity;
    private int proId;

    public Promotion() {
    }

    public Promotion(int promoId, String promoName, int promoDiscount, String promoStartDate, String promoEndDate, int promoQuantity, int proId) {
        this.promoId = promoId;
        this.promoName = promoName;
        this.promoDiscount = promoDiscount;
        this.promoStartDate = promoStartDate;
        this.promoEndDate = promoEndDate;
        this.promoQuantity = promoQuantity;
        this.proId = proId;
    }

    public int getPromoId() {
        return promoId;
    }

    public void setPromoId(int promoId) {
        this.promoId = promoId;
    }

    public String getPromoName() {
        return promoName;
    }

    public void setPromoName(String promoName) {
        this.promoName = promoName;
    }

    public int getPromoDiscount() {
        return promoDiscount;
    }

    public void setPromoDiscount(int promoDiscount) {
        this.promoDiscount = promoDiscount;
    }

    public String getPromoStartDate() {
        return promoStartDate;
    }

    public void setPromoStartDate(String promoStartDate) {
        this.promoStartDate = promoStartDate;
    }

    public String getPromoEndDate() {
        return promoEndDate;
    }

    public void setPromoEndDate(String promoEndDate) {
        this.promoEndDate = promoEndDate;
    }

    public int getPromoQuantity() {
        return promoQuantity;
    }

    public void setPromoQuantity(int promoQuantity) {
        this.promoQuantity = promoQuantity;
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }
    
    
}
