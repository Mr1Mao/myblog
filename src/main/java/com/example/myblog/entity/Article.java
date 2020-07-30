package com.example.myblog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Article {
    private Integer articleId;
    private String articleTitle;
    private String articleContent;
    private String articleAuthor;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time;
    private String articleBrief;
    private Integer viewNumber;
    private String sort;

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public String getArticleAuthor() {
        return articleAuthor;
    }

    public void setArticleAuthor(String articleAuthor) {
        this.articleAuthor = articleAuthor;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getArticleBrief() {
        return articleBrief;
    }

    public void setArticleBrief(String articleBrief) {
        this.articleBrief = articleBrief;
    }

    public Integer getViewNumber() {
        return viewNumber;
    }

    public void setViewNumber(Integer viewNumber) {
        this.viewNumber = viewNumber;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
