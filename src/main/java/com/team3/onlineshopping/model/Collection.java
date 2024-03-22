/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.onlineshopping.model;

/**
 *
 * @author PC
 */
public class Collection {

    private int collectionId;
    private String collectionTitle;
    private String collectionProId;
    private String collectionCreatedDate;
    private String collectionStatus;
    private int newsId;
    private int employeeId;

    public Collection() {
    }

    public Collection(int collectionId, String collectionTitle, String collectionProId, String collectionCreatedDate, String collectionStatus, int newsId, int employeeId) {
        this.collectionId = collectionId;
        this.collectionTitle = collectionTitle;
        this.collectionProId = collectionProId;
        this.collectionCreatedDate = collectionCreatedDate;
        this.collectionStatus = collectionStatus;
        this.newsId = newsId;
        this.employeeId = employeeId;
    }

    public Collection(String collectionTitle, String collectionProId, String collectionCreatedDate, String collectionStatus, int newsId, int employeeId) {
        this.collectionTitle = collectionTitle;
        this.collectionProId = collectionProId;
        this.collectionCreatedDate = collectionCreatedDate;
        this.collectionStatus = collectionStatus;
        this.newsId = newsId;
        this.employeeId = employeeId;
    }

    



    public int getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }

    public String getCollectionTitle() {
        return collectionTitle;
    }

    public void setCollectionTitle(String collectionTitle) {
        this.collectionTitle = collectionTitle;
    }

    public String getCollectionProId() {
        return collectionProId;
    }

    public void setCollectionProId(String collectionProId) {
        this.collectionProId = collectionProId;
    }

    public String getCollectionCreatedDate() {
        return collectionCreatedDate;
    }

    public void setCollectionCreatedDate(String collectionCreatedDate) {
        this.collectionCreatedDate = collectionCreatedDate;
    }

    public String getCollectionStatus() {
        return collectionStatus;
    }

    public void setCollectionStatus(String collectionStatus) {
        this.collectionStatus = collectionStatus;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    

}