/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.model;

import java.util.Date;

/**
 *
 * @author yasin
 */

public class Posts {
    
    private Integer id;
    private String content;
    private String date;
    private Date Date2;

    public Date getDate2() {
        return Date2;
    }

    public void setDate2(Date Date) {
        this.Date2 = Date;
    }
    
    private Users fkUserOwner;

    public Posts(String content, String date, Users fkUserOwner) {
        this.content = content;
        this.date = date;
        this.fkUserOwner = fkUserOwner;
    }
    
    public Posts(String content, String date){
        this.content = content;
        this.date = date;
    }
    public Posts(String content, Date date){
        this.content = content;
        this.Date2 = date;
    }

    
    public Posts() {
    }

    public Posts(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Users getFkUserOwner() {
        return fkUserOwner;
    }

    public void setFkUserOwner(Users fkUserOwner) {
        this.fkUserOwner = fkUserOwner;
    }
    
}
