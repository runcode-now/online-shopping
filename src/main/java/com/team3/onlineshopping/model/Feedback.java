/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.onlineshopping.model;

/**
 *
 * @author Admin
 */
public class Feedback {

    private int feedId;
    private String feedContent;
    private String feedCreatedDate;
    private int feedRating;
    private int proId;
    private int cusId;
    private int orId;

    public Feedback() {
    }

    public Feedback(int feedId, String feedContent, String feedCreatedDate, int feedRating, int proId, int cusId) {
        this.feedId = feedId;
        this.feedContent = feedContent;
        this.feedCreatedDate = feedCreatedDate;
        this.feedRating = feedRating;
        this.proId = proId;
        this.cusId = cusId;
    }

    public Feedback(int feedId, String feedContent, String feedCreatedDate, int feedRating, int proId, int cusId, int orId) {
        this.feedId = feedId;
        this.feedContent = feedContent;
        this.feedCreatedDate = feedCreatedDate;
        this.feedRating = feedRating;
        this.proId = proId;
        this.cusId = cusId;
        this.orId = orId;
    }

    public int getOrId() {
        return orId;
    }

    public void setOrId(int orId) {
        this.orId = orId;
    }

    public int getFeedId() {
        return feedId;
    }

    public void setFeedId(int feedId) {
        this.feedId = feedId;
    }

    public String getFeedContent() {
        return feedContent;
    }

    public void setFeedContent(String feedContent) {
        this.feedContent = feedContent;
    }

    public String getFeedCreatedDate() {
        return feedCreatedDate;
    }

    public void setFeedCreatedDate(String feedCreatedDate) {
        this.feedCreatedDate = feedCreatedDate;
    }

    public int getFeedRating() {
        return feedRating;
    }

    public void setFeedRating(int feedRating) {
        this.feedRating = feedRating;
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
}
