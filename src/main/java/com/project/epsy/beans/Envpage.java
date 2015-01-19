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
import javax.annotation.PostConstruct;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpSession;

import javax.faces.application.FacesMessage;
import javax.servlet.http.HttpSession;


/**
 *
 * @author Cuneyt
 */

public class Envpage implements Serializable {
    
   
    
    private String outcome;
    private String session;
    private HttpSession s;
    
    

    public Envpage(){

    }


  
     public void navigate(){
       
 redirect(outcome);
    }

    
   
   
   public void redirect(String path){
        try {
            ExternalContext ec=FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath()+path);
        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Error while redirecting to current page..");
        }
   }


   @PostConstruct
public void initialize(){

s=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
session=((String)s.getAttribute("session")); 


if(session.equals("s10")){
        
        outcome="/survey/track5/postemotions1.xhtml";
       }
       else if(session.equals("s11")){
         
         outcome="/survey/track5/feedback1.xhtml";
       }
       else{
        
         outcome="/survey/track5/posttest/posttest.xhtml";
       }


}




     
     
}