/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.userinterface;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.webapp.model.Messages;
import com.webapp.model.Users;
import com.webapp.service.JerseyClients;

/**
 *
 * @author yasin
 */
@SessionScoped
@ManagedBean
public class MessageBean implements Serializable {
    
    private static Integer id;
    private Users fromUs;
    private String Subject;
    private String Content;
    private String from;
    private String to;
    private List<Messages> l;

    public Users getFromUs() {
        return fromUs;
    }

    public void setFromUs(Users fromUs) {
        this.fromUs = fromUs;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubject() {
        
        return Subject;
    }

    public void setSubject(String Subject) {
        this.Subject = Subject;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public String getFrom() {
       
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
    
    public void sendMessageto(String from){
        JerseyClients jClient =new JerseyClients("messages");
        jClient.sendMessageTo(Subject, Content, from, to);
    }
    public List<Messages> getMessages(String from){
        JerseyClients jClient =new JerseyClients("messages");
        return jClient.getMessages(from);
    }
    

}
