/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.onlineshopping.model;

/**
 *
 * @author PC
 */
public class Address {

    private int addId;
    private String addRecName;
    private String addRecPhone;
    private String addName;
    private String addDefault;
    private String addStatus;
    private int cusId;

    public Address() {
    }

    public Address(int addId, String addRecName, String addRecPhone, String addName, String addDefault, String addStatus, int cusId) {
        this.addId = addId;
        this.addRecName = addRecName;
        this.addRecPhone = addRecPhone;
        this.addName = addName;
        this.addDefault = addDefault;
        this.addStatus = addStatus;
        this.cusId = cusId;
    }

    public String getAddRecName() {
        return addRecName;
    }

    public void setAddRecName(String addRecName) {
        this.addRecName = addRecName;
    }

    public String getAddRecPhone() {
        return addRecPhone;
    }

    public void setAddRecPhone(String addRecPhone) {
        this.addRecPhone = addRecPhone;
    }

    public int getAddId() {
        return addId;
    }

    public void setAddId(int addId) {
        this.addId = addId;
    }

    public String getAddName() {
        return addName;
    }

    public void setAddName(String addName) {
        this.addName = addName;
    }

    public String getAddDefault() {
        return addDefault;
    }

    public void setAddDefault(String addDefault) {
        this.addDefault = addDefault;
    }

    public String getAddStatus() {
        return addStatus;
    }

    public void setAddStatus(String addStatus) {
        this.addStatus = addStatus;
    }

    public int getCusId() {
        return cusId;
    }

    public void setCusId(int cusId) {
        this.cusId = cusId;
    }

    @Override
    public String toString() {
        return "Address{" + "addId=" + addId + ", addRecName=" + addRecName + ", addRecPhone=" + addRecPhone + ", addName=" + addName + ", addDefault=" + addDefault + ", addStatus=" + addStatus + ", cusId=" + cusId + '}';
    }
    
    

}
