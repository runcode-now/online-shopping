/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.onlineshopping.model;

/**
 *
 * @author Admin
 */
public class AccountStatistic {

    private String name;
    private int totalAccount;

    public AccountStatistic() {
    }

    public AccountStatistic(String name, int totalAccount) {
        this.name = name;
        this.totalAccount = totalAccount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalAccount() {
        return totalAccount;
    }

    public void setTotalAccount(int totalAccount) {
        this.totalAccount = totalAccount;
    }

}
