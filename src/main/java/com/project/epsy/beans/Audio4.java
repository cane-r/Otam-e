/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.epsy.beans;


import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.faces.application.FacesMessage;
import org.joda.time.DateTime;
import org.joda.time.Duration;

/**
 *
 * @author Cuneyt
 */

public class Audio4 implements Serializable {
    
   
    
    
    private String time;
    private String session;
    private String track;
   private Context initialContext;
    private DataSource datasource;
    
    private String key;
    private HttpSession s;
    private DateTime t1=null;
    private DateTime t2=null;

   
    
    
    
    
    public Audio4() {
       
    
    }



    public void initialize2(){

t1=new DateTime();

Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Initialize..Date is .." + t1);   
    }

  
     public void navigate(){
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"In navigate..");
String ls="";
          update();


          if(session.equals("s1")){
            FacesContext.getCurrentInstance().addMessage("r-form:growl",new FacesMessage(FacesMessage.SEVERITY_INFO,"Auf Wiedersehen","Sie haben den ersten Übungsdurchgang vollständig beendet."));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Logging out...");
            //Thread.sleep("300");
            logout();
          }
          else if(session.equals("s2")){
            ls="/survey/track1/postemotions1.xhtml";
            s.removeAttribute("laststayed");
            s.setAttribute("laststayed",ls);
            redirectToNext(ls);
          }
          else if(session.equals("s3")){
            ls="/survey/track1/feedback1.xhtml";
            s.removeAttribute("laststayed");
            s.setAttribute("laststayed",ls);
            redirectToNext(ls);
          }

          else if(session.equals("s6")){
            ls="/survey/track3/postemotions1.xhtml";
            s.removeAttribute("laststayed");
            s.setAttribute("laststayed",ls);
            redirectToNext(ls);
          }
          else {
            ls="/survey/track3/feedback1.xhtml";
            s.removeAttribute("laststayed");
            s.setAttribute("laststayed",ls);
            redirectToNext(ls);
          }

          ///redirectToNext(ls);
           
          
    }
    
     
     public void update(){
      String sql="";
      String ls1;
      String update;
         t2=new DateTime();
         
         Duration duration = new Duration(t1, t2);
         String min2,sec2;
         Long l=duration.getStandardSeconds();
         long min=l/60;
         long sec=l%60;
         if(sec<10){
          sec2="0"+String.valueOf(sec);
         }
         else{
          sec2=String.valueOf(sec);
         }
         min2=String.valueOf(min);
         String diff=new String(min2+":"+sec2);
         Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Diff is : "  + diff);
         Connection con=null;

           PreparedStatement ps=null;
           try{
          String updateLaststayed="update users set laststayed=? where userMail=?"; 
          String updateLaststayed2="update users set laststayed=?,session=?,time_control=? where userMail=?";
             
             String sql1="update times set t4=? where userMail=?";
             String sql2="update times set t8=? where userMail=?";
             String sql3="update times set t12=? where userMail=?";

             String sql4="update times set t22=? where userMail=?";
             String sql5="update times set t26=? where userMail=?";
             
             
          if(session.equals("s1")){
            sql=sql1;
            ls1="login1ok";
            update=updateLaststayed2;
            track="track1";
          }

          else if(session.equals("s2")){
                 sql=sql2;
                 ls1="/survey/track1/postemotions1.xhtml";
                 update=updateLaststayed;
                 track="track1";
           }

           //here add else if s3,s4,s5..for now.1.2..
           else if(session.equals("s3")){
                sql=sql3;
                ls1="/survey/track1/feedback1.xhtml";
                update=updateLaststayed;
                track="track1";
           }

           else if(session.equals("s6")){
                sql=sql4;
                ls1="/survey/track3/postemotions1.xhtml";
                update=updateLaststayed;
                track="track3";
           }

           else {
                sql=sql5;
                ls1="/survey/track3/feedback1.xhtml";
                update=updateLaststayed;
                track="track3";
           }

           con=datasource.getConnection();
           con.setAutoCommit(false);
           ps=con.prepareStatement(sql);
             ps.setString(1,diff);
            ps.setString(2,key);
            
            ps.executeUpdate();

         

           ps=con.prepareStatement(update);
           if(update.contains("sess")){
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"sess contains.Means t1s1");
            ps.setString(1,ls1);
            ps.setString(2,"s2");
            ps.setString(3,new DateTime().toString());
            ps.setString(4,key);

           }
           else{
           ps.setString(1,ls1);
            ps.setString(2,key);
          }
            ps.executeUpdate();

            con.commit();
            
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Inserted..");

            
           } catch (SQLException ex) {
           FacesContext.getCurrentInstance().addMessage("audio4:growl",new FacesMessage(FacesMessage.SEVERITY_WARN,"Ein Fehler ist aufgetreten","Bitte erneut versuchen.."));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Sql exception occured while updating...." + ex.getMessage());
            
                if(con!=null){

                   try {
                       con.rollback();
                   } catch (SQLException ex1) {
                       Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex1);
                   }
              }
              redirect(track);
            
        } 
           finally{
               closeStatement(ps);
               closeConnection(con);
           }

           }
     
     
     public void closeConnection(Connection c){
        try {
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Error while closing connection");
        }
       
   }
   
   public void closeStatement(PreparedStatement ps){
        try {
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Error while closing statement");
        }
       
   }
   
   public void closeResultSet(ResultSet rs){
       if(rs!=null){
        try {
           rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Error while closing resultset");
        }
       }
   }  
   
  public void redirectToNext(String path){
        try {
            ExternalContext ec=FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath()+path);
        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Error while redirecting to current page..");
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



public void redirect(String path){
        try {
            ExternalContext ec=FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath()+"/survey/"+path+"/audio4.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Error while redirecting to current page..");
        }
   }




   @PostConstruct
public void initialize(){

t1=new DateTime();
    key=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
    s=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    session=((String)s.getAttribute("session"));


     try {
            initialContext = new InitialContext();
            datasource = (DataSource)initialContext.lookup("java:jboss/datasources/MySQLDS");
        } catch (NamingException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Data source error..", ex);
        }
        catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Unknown error..");
        }


}        
     
     
}