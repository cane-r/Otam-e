/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.epsy.beans;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.faces.application.FacesMessage;

/**
 *
 * @author Cuneyt
 */

public class SessionCheck {
    

    /**
     * Creates a new instance of SessionCheck
     */
    public SessionCheck() {
    }
    
    public void checkCreds(){

     ExternalContext ec=FacesContext.getCurrentInstance().getExternalContext();   
    HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
    HttpSession s= (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    String path=req.getRequestURI();
    //String path=FacesContext.getCurrentInstance().getViewRoot().getViewId();
    String var=null;
    String var2=null;

    if(s!=null && null!=((String)s.getAttribute("laststayed")) && ! (((String)s.getAttribute("laststayed")).equals("")) )
    {
        var=((String)s.getAttribute("laststayed"));
    }


    if(s!=null && null!=((String)s.getAttribute("session")) && !(((String)s.getAttribute("session")).equals("")) )
    {
        var2=((String)s.getAttribute("session"));
    }

    
    if(s==null || s.getAttribute("user")==null || ((String)s.getAttribute("user")).equals("") ){

        try {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING,"User is null.Redirecting..");
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING,"Path is : " + path);
            
            if(ec!=null){
                //www.otame.de..
        FacesContext.getCurrentInstance().addMessage(
                    "r-form:growl",
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Es ist ein Fehler aufgetreten",
                    "Bitte melden Sie sich an.."));
              FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

        
        Logger.getLogger(SessionCheck.class.getName()).log(Level.SEVERE,"Cookies are disabled message..");
        Logger.getLogger(SessionCheck.class.getName()).log(Level.SEVERE,"Cookies are disabled..");
            ec.redirect(ec.getRequestContextPath()+"/index.xhtml");
            }
            else{
              ec.redirect("https://www.otame.de/index.xhtml");  
            }
            
        } catch (IOException ex) {
            Logger.getLogger(SessionCheck.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

//cookie control..
    /*
if( null==s && null==s.getAttribute("laststayed")) {
     Logger.getLogger(SessionCheck.class.getName()).log(Level.SEVERE,"Cookies are disabled..");
       
       try {
            
            if(ec!=null){

FacesContext.getCurrentInstance().addMessage("r-form:growl",new FacesMessage(FacesMessage.SEVERITY_WARN,"Error","Cookies disabled!Please enable cookies if you want to use this site.."));
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        Logger.getLogger(SessionCheck.class.getName()).log(Level.SEVERE,"Cookies are disabled message..");
                //www.otame.de/..
            ec.redirect(ec.getRequestContextPath()+"/index.xhtml");
            }
            else{
              ec.redirect("https://www.otame.de");  
            }
            
        } catch (IOException ex) {
            Logger.getLogger(SessionCheck.class.getName()).log(Level.SEVERE, null, ex);
        }
    

        Logger.getLogger(this.getClass().getName()).log(Level.WARNING,"Path is : " + path);
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING,"Laststayed is : " + var);
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING,"Session is : " + var2);
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING,"User is not null and value is : " + ((String)s.getAttribute("user")));


    
}
*/

//path.substring(0,var.length()).equals(var)
//path control..
//!(var.contains("welcome")) && 
    if(s!=null && var!=null && !(var.equals("")) && !(path.equals(var) ) && !(var.contains("welcome"))  && !( path.contains("welcome") ) && !(path.contains("pretrack")) && !(path.contains("envpage")) && !(path.contains("chat"))){

Logger.getLogger(SessionCheck.class.getName()).log(Level.SEVERE,"Redirecting to " + ec.getRequestContextPath() + var);

        Logger.getLogger(this.getClass().getName()).log(Level.WARNING,"Path is : " + path);
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING,"Laststayed is : " + var);
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING,"Session is : " + var2);
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING,"User is not null and value is : " + ((String)s.getAttribute("user")));

     
       
       try {
            
            if(ec!=null){
                //www.otame.de/..
            ec.redirect(ec.getRequestContextPath()+var);
            }
            else{
              ec.redirect("https://www.otame.de"+var);  
            }
            
        } catch (IOException ex) {
            Logger.getLogger(SessionCheck.class.getName()).log(Level.SEVERE, null, ex);
        }
    

        Logger.getLogger(this.getClass().getName()).log(Level.WARNING,"Path is : " + path);
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING,"Laststayed is : " + ((String)s.getAttribute("laststayed")));
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING,"User is not null and value is : " + ((String)s.getAttribute("user")));

}


    }
    public void checkAdminCreds(){
        
        
    //HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
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
    
    
    
    public void logout(){
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession s=(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        /*
        if(!s.getAttribute("mail").equals("") || !(s.getAttribute("mail")==null))
        {
        s.removeAttribute("mail");
        }
                */
        ec.invalidateSession();
        try {
            ec.redirect(ec.getRequestContextPath()+"/index.xhtml");
            
        } catch (IOException ex) {
            Logger.getLogger(PreTest.class.getName()).log(Level.SEVERE,"Redirection error.");
        }
    }
    
    
    
}
