/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.onlineshopping.model;

/**
 *
 * @author Admin
 */
public class News {

    private int newsId;
    private String newsTitle;
    private String newsImgUrl;
    private String newsDescription;
    private String newsCreatedDate;
    private String newsUpdateDate;
    private String newsStatus;
    private int newsView;
    private int cateNewsId;
    private int emId;

    public News() {
    }

    public News(int newsId, String newsTitle, String newsImgUrl, String newsDescription, String newsCreatedDate, String newsUpdateDate, String newsStatus, int newsView, int cateNewsId, int emId) {
        this.newsId = newsId;
        this.newsTitle = newsTitle;
        this.newsImgUrl = newsImgUrl;
        this.newsDescription = newsDescription;
        this.newsCreatedDate = newsCreatedDate;
        this.newsUpdateDate = newsUpdateDate;
        this.newsStatus = newsStatus;
        this.newsView = newsView;
        this.cateNewsId = cateNewsId;
        this.emId = emId;
    }

    public News(String newsTitle, String newsImgUrl, String newsDescription, String newsCreatedDate, String newsUpdateDate, String newsStatus, int newsView, int cateNewsId, int emId) {
        this.newsTitle = newsTitle;
        this.newsImgUrl = newsImgUrl;
        this.newsDescription = newsDescription;
        this.newsCreatedDate = newsCreatedDate;
        this.newsUpdateDate = newsUpdateDate;
        this.newsStatus = newsStatus;
        this.newsView = newsView;
        this.cateNewsId = cateNewsId;
        this.emId = emId;
    }

    public News(int newsId, String newsTitle, String newsImgUrl, String newsDescription, String newsCreatedDate, String newsStatus, int newsView, int cateNewsId, int emId) {
        this.newsId = newsId;
        this.newsTitle = newsTitle;
        this.newsImgUrl = newsImgUrl;
        this.newsDescription = newsDescription;
        this.newsCreatedDate = newsCreatedDate;
        this.newsStatus = newsStatus;
        this.newsView = newsView;
        this.cateNewsId = cateNewsId;
        this.emId = emId;
    }

    public News(String newsTitle, String newsImgUrl, String newsDescription, String newsCreatedDate, String newsStatus, int newsView, int cateNewsId, int emId) {
        this.newsTitle = newsTitle;
        this.newsImgUrl = newsImgUrl;
        this.newsDescription = newsDescription;
        this.newsCreatedDate = newsCreatedDate;
        this.newsStatus = newsStatus;
        this.newsView = newsView;
        this.cateNewsId = cateNewsId;
        this.emId = emId;
    }
    
    

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsUpdateDate() {
        return newsUpdateDate;
    }

    public void setNewsUpdateDate(String newsUpdateDate) {
        this.newsUpdateDate = newsUpdateDate;
    }

    public String getNewsImgUrl() {
        return newsImgUrl;
    }

    public void setNewsImgUrl(String newsImgUrl) {
        this.newsImgUrl = newsImgUrl;
    }

    public String getNewsDescription() {
        return newsDescription;
    }

    public void setNewsDescription(String newsDescription) {
        this.newsDescription = newsDescription;
    }

    public String getNewsCreatedDate() {
        return newsCreatedDate;
    }

    public void setNewsCreatedDate(String newsCreatedDate) {
        this.newsCreatedDate = newsCreatedDate;
    }

    public String getNewsStatus() {
        return newsStatus;
    }

    public void setNewsStatus(String newsStatus) {
        this.newsStatus = newsStatus;
    }

    public int getNewsView() {
        return newsView;
    }

    public void setNewsView(int newsView) {
        this.newsView = newsView;
    }

    public int getCateNewsId() {
        return cateNewsId;
    }

    public void setCateNewsId(int cateNewsId) {
        this.cateNewsId = cateNewsId;
    }

    public int getEmId() {
        return emId;
    }

    public void setEmId(int emId) {
        this.emId = emId;
    }

    @Override
    public String toString() {
        return "News{" + "newsId=" + newsId + ", newsTitle=" + newsTitle + ", newsImgUrl=" + newsImgUrl + ", newsCreatedDate=" + newsCreatedDate + ", newsUpdateDate=" + newsUpdateDate + ", newsStatus=" + newsStatus + ", newsView=" + newsView + ", cateNewsId=" + cateNewsId + ", emId=" + emId + '}';
    }

}
