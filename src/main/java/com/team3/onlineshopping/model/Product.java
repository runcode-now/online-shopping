/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.onlineshopping.model;

public class Product {
    private int proId;
    private String proName;
    private String proImgDefault;
    private double proPrice;
    private double proCost;
    private String proDescription;
    private double proRating;
    private int proSold;
    private String proCreatedDate;
    private String proStatus;
    private int emId;
    private int cateProDeId;

    public Product() {
    }

    public Product(int proId, String proName, String proImgDefault, double proPrice, double proCost, String proDescription, double proRating, int proSold, String proCreatedDate, String proStatus, int emId, int cateProDeId) {
        this.proId = proId;
        this.proName = proName;
        this.proImgDefault = proImgDefault;
        this.proPrice = proPrice;
        this.proCost = proCost;
        this.proDescription = proDescription;
        this.proRating = proRating;
        this.proSold = proSold;
        this.proCreatedDate = proCreatedDate;
        this.proStatus = proStatus;
        this.emId = emId;
        this.cateProDeId = cateProDeId;
    }

    public Product(String proName, String proImgDefault, double proPrice, double proCost, String proDescription, double proRating, int proSold, String proCreatedDate, String proStatus, int emId, int cateProDeId) {
        this.proName = proName;
        this.proImgDefault = proImgDefault;
        this.proPrice = proPrice;
        this.proCost = proCost;
        this.proDescription = proDescription;
        this.proRating = proRating;
        this.proSold = proSold;
        this.proCreatedDate = proCreatedDate;
        this.proStatus = proStatus;
        this.emId = emId;
        this.cateProDeId = cateProDeId;
    }

    

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public double getProPrice() {
        return proPrice;
    }

    public void setProPrice(double proPrice) {
        this.proPrice = proPrice;
    }

    public double getProCost() {
        return proCost;
    }

    public void setProCost(double proCost) {
        this.proCost = proCost;
    }

    public String getProDescription() {
        return proDescription;
    }

    public void setProDescription(String proDescription) {
        this.proDescription = proDescription;
    }

    public double getProRating() {
        return proRating;
    }

    public void setProRating(double proRating) {
        this.proRating = proRating;
    }

    public int getProSold() {
        return proSold;
    }

    public void setProSold(int proSold) {
        this.proSold = proSold;
    }

    public String getProStatus() {
        return proStatus;
    }

    public void setProStatus(String proStatus) {
        this.proStatus = proStatus;
    }

    public int getEmId() {
        return emId;
    }

    public void setEmId(int emId) {
        this.emId = emId;
    }

    public int getCateProDeId() {
        return cateProDeId;
    }

    public void setCateProDeId(int cateProDeId) {
        this.cateProDeId = cateProDeId;
    }

    public String getProCreatedDate() {
        return proCreatedDate;
    }

    public void setProCreatedDate(String proCreatedDate) {
        this.proCreatedDate = proCreatedDate;
    }

    public String getProImgDefault() {
        return proImgDefault;
    }

    public void setProImgDefault(String proImgDefault) {
        this.proImgDefault = proImgDefault;
    }

    @Override
    public String toString() {
        return "Product{" + "proId=" + proId + ", proName=" + proName + ", proImgDefault=" + proImgDefault + '}';
    }

    
    
    
    
}
