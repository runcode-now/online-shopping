/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.onlineshopping.model;

/**
 *
 * @author Admin
 */
public class Account {

    private int accId;
    private String accEmail;
    private String accPass;
    private String accPhone;
    private String accName;
    private String accAvarUrl;
    private String accDoB;
    private String accCreatedDate;
    private String accStatus;
    private int roleId;

    public Account() {
    }

    public Account(int accId, String accEmail, String accPass, String accPhone, String accName, String accAvarUrl, String accDoB, String accCreatedDate, String accStatus, int roleId) {
        this.accId = accId;
        this.accEmail = accEmail;
        this.accPass = accPass;
        this.accPhone = accPhone;
        this.accName = accName;
        this.accAvarUrl = accAvarUrl;
        this.accDoB = accDoB;
        this.accCreatedDate = accCreatedDate;
        this.accStatus = accStatus;
        this.roleId = roleId;
    }

    public int getAccId() {
        return accId;
    }

    public void setAccId(int accId) {
        this.accId = accId;
    }

    public String getAccEmail() {
        return accEmail;
    }

    public void setAccEmail(String accEmail) {
        this.accEmail = accEmail;
    }

    public String getAccPass() {
        return accPass;
    }

    public void setAccPass(String accPass) {
        this.accPass = accPass;
    }

    public String getAccPhone() {
        return accPhone;
    }

    public void setAccPhone(String accPhone) {
        this.accPhone = accPhone;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public String getAccAvarUrl() {
        return accAvarUrl;
    }

    public void setAccAvarUrl(String accAvarUrl) {
        this.accAvarUrl = accAvarUrl;
    }

    public String getAccStatus() {
        return accStatus;
    }

    public void setAccStatus(String accStatus) {
        this.accStatus = accStatus;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getAccDoB() {
        return accDoB;
    }

    public void setAccDoB(String accDoB) {
        this.accDoB = accDoB;
    }

    public String getAccCreatedDate() {
        return accCreatedDate;
    }

    public void setAccCreatedDate(String accCreatedDate) {
        this.accCreatedDate = accCreatedDate;
    }

    @Override
    public String toString() {
        return "Account{" + "accId=" + accId + ", accEmail=" + accEmail + ", accPass=" + accPass + ", accPhone=" + accPhone + ", accName=" + accName + ", accAvarUrl=" + accAvarUrl + ", accDoB=" + accDoB + ", accStatus=" + accStatus + ", roleId=" + roleId + '}';
    }

}
