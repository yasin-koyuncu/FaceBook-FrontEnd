/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.model;

/**
 *
 * @author yasin
 */

public class Messages {
    
    private Integer id;
    private String subject;
    private String content;
    private Users fkUserFrom;
    private Users fkUserTo;
    private String fkUserFromS;
    private String fkUserToS;

    public String getFkUserFromS() {
        return fkUserFromS;
    }

    public String getFkUserToS() {
        return fkUserToS;
    }

    public void setFkUserFromS(String fkUserFromS) {
        this.fkUserFromS = fkUserFromS;
    }

    public void setFkUserToS(String fkUserToS) {
        this.fkUserToS = fkUserToS;
    }

    public Messages(String subject, String content, Users fkUserFrom, Users fkUserTo) {
        this.subject = subject;
        this.content = content;
        this.fkUserFrom = fkUserFrom;
        this.fkUserTo = fkUserTo;
    }
    
    public Messages(String subject, String content, String fkUserFromS, String fkUserToS){
        this.subject = subject;
        this.content = content;
        this.fkUserFromS = fkUserFromS;
        this.fkUserToS = fkUserToS;
    }

    
    public Messages() {
    }

    public Messages(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Users getFkUserFrom() {
        return fkUserFrom;
    }

    public void setFkUserFrom(Users fkUserFrom) {
        this.fkUserFrom = fkUserFrom;
    }

    public Users getFkUserTo() {
        return fkUserTo;
    }

    public void setFkUserTo(Users fkUserTo) {
        this.fkUserTo = fkUserTo;
    }    

}
