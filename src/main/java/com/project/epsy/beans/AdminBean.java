/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.epsy.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Cuneyt
 */
public class AdminBean implements Serializable {

    private String username,pass;
    
    public String getUser() {
        return username;
    }

    public String getUsername() {
        return username;
    }
//..
    public String getPass() {
        return pass;
    }
    
    public void setPass(String pass){
        this.pass=pass;
    }
    
    public void setUser(String username){
        this.username=username;
    }
    
    
 
    public void login(){
        if(username.equals("root") && pass.equals("tYRWEaZVmgLs")){
            try{
            HttpSession s= (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            s.setAttribute("userAttr","root");
            ExternalContext ec=FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath()+"/protected/admin/index.xhtml");
        }
            catch(IOException e){
               FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Falscher Login!",
                    "Versuchen Sie es erneut.")); // Invalid Login! Try Again.
            }
        }
        else{
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Falscher Login!",
                    "Falsche Benutzerdaten!"));  //Invalid Login!  Wrong user credentials!
            
        }
    }
    
    public void checkCreds(){
        
    HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
    HttpSession s= (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    
    if(s==null || s.getAttribute("userAttr")==null || !s.getAttribute("userAttr").equals("root")){
        try {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING,"User is null.Redirecting..");
            ExternalContext ec=FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath()+"/protected/admin/auth.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(SessionCheck.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    else{
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING,"User is not null");
    }
    
}
}
