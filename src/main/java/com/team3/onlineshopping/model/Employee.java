/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.onlineshopping.model;

/**
 *
 * @author Admin
 */
public class Employee {

    private int emId;
    private long emSalary;
    private int accId;
    private int roleId;

    public Employee() {
    }

    public Employee(int emId, long emSalary, int accId, int roleId) {
        this.emId = emId;
        this.emSalary = emSalary;
        this.accId = accId;
        this.roleId = roleId;
    }

    public int getEmId() {
        return emId;
    }

    public void setEmId(int emId) {
        this.emId = emId;
    }

    public long getEmSalary() {
        return emSalary;
    }

    public void setEmSalary(long emSalary) {
        this.emSalary = emSalary;
    }

    public int getAccId() {
        return accId;
    }

    public void setAccId(int accId) {
        this.accId = accId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

}
