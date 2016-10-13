/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.userinterface;

import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.webapp.model.Posts;
import com.webapp.model.Users;

import com.webapp.service.JerseyClients;

/**
 *
 * @author yasin
 */
@ManagedBean
@SessionScoped

public class PostBean {
    
    private Integer id;
    private Date date;
    private String Content;
    private Users user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
    
    public void addPost(String from){
        JerseyClients jClient = new JerseyClients("posts");
        jClient.addPost(Content, from);
    }
    
    public List<Posts> getPosts(String from){
        JerseyClients jClient = new JerseyClients("posts");
        return jClient.getPosts(from);
    }
}
