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
public class Users {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String key;

   

    public Users() {
    }

    public Users(String FirstName, String LastName, String Email, String Password) {

        this.firstName = FirstName;
        this.lastName = LastName;
        this.email = Email;
        this.password = Password;
    }

    public Users(String FirstName, String LastName, String Email, String Password, String key) {

        this.firstName = FirstName;
        this.lastName = LastName;
        this.email = Email;
        this.password = Password;
        this.key = key;
    }

    public Users(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

     public String getKey() {
        return key;
    }
}
