/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.onlineshopping.model;

/**
 *
 * @author PC
 */
public class Cart {

    private int cartId;
    private int proId;
    private int cusId;
    private int cateSizeId;
    private int cartQuantity;
    private String cartCreatedDate;
    private String cartStatus;

    public Cart() {
    }

    public Cart(int cartId, int proId, int cusId, int cateSizeId, int cartQuantity, String cartCreatedDate, String cartStatus) {
        this.cartId = cartId;
        this.proId = proId;
        this.cusId = cusId;
        this.cateSizeId = cateSizeId;
        this.cartQuantity = cartQuantity;
        this.cartCreatedDate = cartCreatedDate;
        this.cartStatus = cartStatus;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public int getCusId() {
        return cusId;
    }

    public void setCusId(int cusId) {
        this.cusId = cusId;
    }

    public int getCateSizeId() {
        return cateSizeId;
    }

    public void setCateSizeId(int cateSizeId) {
        this.cateSizeId = cateSizeId;
    }

    public int getCartQuantity() {
        return cartQuantity;
    }

    public void setCartQuantity(int cartQuantity) {
        this.cartQuantity = cartQuantity;
    }

    public String getCartCreatedDate() {
        return cartCreatedDate;
    }

    public void setCartCreatedDate(String cartCreatedDate) {
        this.cartCreatedDate = cartCreatedDate;
    }

    public String getCartStatus() {
        return cartStatus;
    }

    public void setCartStatus(String cartStatus) {
        this.cartStatus = cartStatus;
    }

}
