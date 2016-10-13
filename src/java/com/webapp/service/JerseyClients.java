/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import com.webapp.model.Messages;
import com.webapp.model.Posts;
import com.webapp.model.Users;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Jersey REST client generated for REST resource:UsersFacadeREST [users]<br>
 * USAGE:
 * <pre>
 *        JerseyClients client = new JerseyClients();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author yasin
 */

/**
 * This class commu
 * @author yasin93
 */
public class JerseyClients {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8081/FaceBookBackEnd/webresources";

    public JerseyClients(String path) {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path(path);
    }

    public void registerUser(String firstname, String lastname, String email, String password, String secretKey) throws ClientErrorException {
        Form form = new Form();
        form.param("FirstName", firstname);
        form.param("LastName", lastname);
        form.param("Email", email);
        form.param("Password", password);
        form.param("secretKey", secretKey);
        webTarget.path("register").request().post(Entity.form(form));
    }

    public boolean login(String email, String password, String otpKey) {
        String str = performGet(BASE_URI + "/users/login/" + email + "/" + password + "/" + otpKey);

        return str.equals("true");

    }

    public void addPost(String content, String from) {
        Form form = new Form();
        form.param("Content", content);
        form.param("From", from);
        webTarget.path("addpost").request().post(Entity.form(form));
    }

    public List<Posts> getPosts(String from) {
        List<Posts> pList = new ArrayList<Posts>();

        String sOutput = performGet(BASE_URI + "/posts/getposts/" + from);

        JSONArray jArray = null;

        try {
            jArray = new JSONArray(sOutput);
        } catch (JSONException ex) {
            Logger.getLogger(JerseyClients.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < jArray.length(); i++) {

            JSONObject jObject;

            try {
                jObject = jArray.getJSONObject(i);

                pList.add(new Posts(jObject.getString("content"),
                        jObject.getString("date")));
            } catch (JSONException ex) {
                Logger.getLogger(JerseyClients.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return pList;
    }

    public void sendMessageTo(String Subject, String Content, String from, String to) {
        Form form = new Form();
        form.param("Subject", Subject);
        form.param("Content", Content);
        form.param("UserFrom", from);
        form.param("UserTo", to);

        webTarget.path("addmessage").request().post(Entity.form(form));

    }

    public List<Messages> getMessages(String from) {
        List<Messages> mList = new ArrayList<Messages>();

        String sOutput = performGet(BASE_URI + "/messages/getmessages/" + from);

        JSONArray jArray = null;

        try {
            jArray = new JSONArray(sOutput);
        } catch (JSONException ex) {
            Logger.getLogger(JerseyClients.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < jArray.length(); i++) {

            JSONObject jObject;
            try {
                jObject = jArray.getJSONObject(i);

                mList.add(new Messages(jObject.getString("subject"),
                        jObject.getString("content"),
                        jObject.getJSONObject("fkUserFrom").getString("email"),
                        jObject.getJSONObject("fkUserTo").getString("email")));
            } catch (JSONException ex) {
                Logger.getLogger(JerseyClients.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return mList;
    }

    public List<Users> allUsers() {

        List<Users> uList = new ArrayList<Users>();

        String sOutput = performGet(BASE_URI + "/users/id/all");

        JSONArray jArray = null;

        try {
            jArray = new JSONArray(sOutput);
        } catch (JSONException ex) {
            Logger.getLogger(JerseyClients.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < jArray.length(); i++) {

            try {
                JSONObject jObject = jArray.getJSONObject(i);

                uList.add(new Users(jObject.getString("firstName"),
                        jObject.getString("lastName"),
                        jObject.getString("email"),
                        jObject.getString("password")));

            } catch (JSONException ex) {
                Logger.getLogger(JerseyClients.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return uList;

    }

    // Return email of the user with the given ID
    public Users getUserById(int id) {

        String sOutput = performGet(BASE_URI + "/users/id/" + id);

        sOutput = "[" + sOutput + "]";

        JSONArray jArray = null;

        try {
            jArray = new JSONArray(sOutput);
        } catch (JSONException ex) {
            Logger.getLogger(JerseyClients.class.getName()).log(Level.SEVERE, null, ex);
        }

        Users user = null;

        for (int i = 0; i < jArray.length(); i++) {

            try {
                JSONObject jObject = jArray.getJSONObject(i);

                user = new Users(jObject.getString("email"),
                        jObject.getString("firstName"),
                        jObject.getString("lastName"),
                        jObject.getString("password"));

            } catch (JSONException ex) {
                Logger.getLogger(JerseyClients.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return user;
    }

    public Users getUsersByName(String Email) {

        String sOutput = performGet(BASE_URI + "/users/name/" + Email);

        sOutput = "[" + sOutput + "]";

        JSONArray jArray = null;

        try {
            jArray = new JSONArray(sOutput);
        } catch (JSONException ex) {
            Logger.getLogger(JerseyClients.class.getName()).log(Level.SEVERE, null, ex);
        }

        Users user = null;

        for (int i = 0; i < jArray.length(); i++) {

            try {
                JSONObject jObject = jArray.getJSONObject(i);

                user = new Users(jObject.getString("email"),
                        jObject.getString("firstName"),
                        jObject.getString("lastName"),
                        jObject.getString("password"));

            } catch (JSONException ex) {
                Logger.getLogger(JerseyClients.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return user;
    }

    public Collection getNamess(String FirstName) {

        List<Users> uList = new ArrayList<Users>();
        String sOutput = performGet(BASE_URI + "/users/search/" + FirstName);
        if (sOutput == null || sOutput.isEmpty() || sOutput.equals(" ") || sOutput.equals("")) {
            return null;
        }
        JSONArray jArray = null;
        try {
            jArray = new JSONArray(sOutput);
        } catch (JSONException ex) {
            Logger.getLogger(JerseyClients.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < jArray.length(); i++) {
            try {
                JSONObject jObject = jArray.getJSONObject(i);

                uList.add(new Users(jObject.getString("firstName"),
                        jObject.getString("lastName"),
                        jObject.getString("email"),
                        jObject.getString("password")));

            } catch (JSONException ex) {
                Logger.getLogger(JerseyClients.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return uList;
    }

    public Users getUserByEmail(String email) {
        String sOutput = performGet(BASE_URI + "/users/name/" + email);

        sOutput = "[" + sOutput + "]";

        JSONArray jArray = null;

        try {
            jArray = new JSONArray(sOutput);
        } catch (JSONException ex) {
            Logger.getLogger(JerseyClients.class.getName()).log(Level.SEVERE, null, ex);
        }

        Users user = null;
        for (int i = 0; i < jArray.length(); i++) {
            try {
                JSONObject jObject = jArray.getJSONObject(i);

                user = new Users(jObject.getString("firstName"),
                        jObject.getString("lastName"),
                        jObject.getString("email"),
                        jObject.getString("password"));

            } catch (JSONException ex) {
                Logger.getLogger(JerseyClients.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return user;
    }

    public void registerUser() throws ClientErrorException {
        webTarget.path("register").request().post(null);
    }

    public void remove(String id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{id})).request().delete();
    }

    public void close() {
        client.close();
    }

    private static String performGet(String customUrl) {
        String jString = "";
        try {
            URL url = new URL(customUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;

            while ((output = br.readLine()) != null) {
                jString += output;
            }

            conn.disconnect();

        } catch (MalformedURLException ex) {
            Logger.getLogger(JerseyClients.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JerseyClients.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jString;
    }

   
}
