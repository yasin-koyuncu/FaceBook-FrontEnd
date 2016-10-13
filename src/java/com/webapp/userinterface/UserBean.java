/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.userinterface;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.webapp.model.Users;
import org.jboss.aerogear.security.otp.api.Base32;
import org.mindrot.jbcrypt.*;
import com.webapp.security.SecureLogin;

import com.webapp.service.JerseyClients;

/**
 *
 * @author yasin
 */
@ManagedBean
@SessionScoped
public class UserBean implements Serializable {

    private String FirstName;
    private String LastName;
    private String Email;
    private String Password;
    private String confirmPassword;
    private Integer id;
    private String goToUser;
    private boolean signedIn;
    private String secretKey;
    private String otpKey;

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getOtpKey() {
        return otpKey;
    }

    public void setOtpKey(String otpKey) {
        this.otpKey = otpKey;
    }

    public boolean isSignedIn() {
        return signedIn;
    }

    public void setSignedIn(boolean signedIn) {
        this.signedIn = signedIn;
    }

    public String getGoToUser() {
        return goToUser;
    }

    public void setGoToUser(String goToUser) {
        this.goToUser = goToUser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
   

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String doLogin() {
        JerseyClients jClient = new JerseyClients("users");
        if (jClient.login(Email, Password, otpKey)) {
            signedIn = true;
            return "Welcome " + Email;

        }
        clear();
        signedIn = false;

        return "Password or username is wrong";

    }

    public String registration() {
        secretKey = Base32.random();
        JerseyClients jClient = new JerseyClients("users");

        List<Users> list = jClient.allUsers();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getKey() != null)///detta läägs till eftersom viss data finns ej i db, alltså null
            {
                if (list.get(i).getKey().equals(secretKey)) {//If key exist then change key
                    secretKey = Base32.random();
                    i = 0;
                }
            }
        }

        if (!Password.equals(confirmPassword)) {  //Check also if the password is correct with the confirmed password
            return "Password is not the same as confirm password";
        }
         //Check if user exists
        jClient.registerUser(FirstName, LastName, Email, SecureLogin.hashPass(Password), secretKey);
        clear();

        return "secretCode.xhtml";

    }

    public String getKey() {
        return secretKey;
    }

    private void clear() {
        FirstName = null;
        LastName = null;
        Email = null;
        Password = null;
        secretKey = null;
    }

    public static List<Users> allUsers() {
        JerseyClients jClient=new JerseyClients("users");
        List<Users> l = jClient.allUsers();
        return l;
    }

    public String getUserById(int id) {
        JerseyClients jClient=new JerseyClients("users");
        Users us = jClient.getUserById(id);
        return us.getEmail();
    }

    public List<Users> getNames() {
        JerseyClients jClient=new JerseyClients("users");
        return (List<Users>)jClient.getNamess(FirstName);
    }

    public Users getUsersByName() {
        JerseyClients jClient=new JerseyClients("users");
        return jClient.getUsersByName(Email);
    }

    public List<Users> getNamess() {
         JerseyClients jClient=new JerseyClients("users");
        return (List<Users>)jClient.getNamess(FirstName);
    }

    public Users getUserByEmail(String email) {
        JerseyClients jClient=new JerseyClients("users");
        return jClient.getUsersByName(email);
    }

   

    public void logout() {
        signedIn = false;
        Email = null;
        FirstName = null;
        Password = null;
        LastName = null;
    }


}



