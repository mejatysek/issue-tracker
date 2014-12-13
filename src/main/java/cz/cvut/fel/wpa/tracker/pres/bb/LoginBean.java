package cz.cvut.fel.wpa.tracker.pres.bb;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Created by mejty on 13.12.14.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

@Component("loginBean")
@Scope("request")
public class LoginBean {

    private String username;

    private String password;

    @Autowired
    private AuthenticationManager authenticationManager;

    public String login() {
        Authentication request = new UsernamePasswordAuthenticationToken(username, password);
        try {
            Authentication result = authenticationManager.authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(result);
        } catch (org.springframework.security.core.AuthenticationException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Invalid credentials!", "The username/password combination is not valid."));
            return "login";
        }

        return "issues?faces-redirect=true";
    }

    public String logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
        return "login";
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
//@Component("loginBean")
//@Scope("session")
//public class LoginBean {
//
//    private String username;
//
//    private String password;
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    public String login() {
//        Authentication request = new UsernamePasswordAuthenticationToken(username, password);
//        try {
//            Authentication result = authenticationManager.authenticate(request);
//            SecurityContextHolder.getContext().setAuthentication(result);
//        } catch (org.springframework.security.core.AuthenticationException e) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Invalid credentials!", "The username/password combination is not valid."));
//            return "login";
//        }
//
//        return "issues?faces-redirect=true";
//    }
//
//    public String logout() {
//        SecurityContextHolder.getContext().setAuthentication(null);
//        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
//        return "login?faces-redirect=true";
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//}

