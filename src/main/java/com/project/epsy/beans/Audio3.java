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

public class Audio3 implements Serializable {
    
   
    
    
    private String time;
    private String session;
    private String track;
    private String laststayed;
    private Context initialContext;
    private DataSource datasource;
    private String key;
    private HttpSession s;
    private DateTime t1=null;
    private DateTime t2=null;

   
    
    
    
    
    public Audio3() {
       
    
    }

  
     public void navigate(){
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"In navigate..");
 
         String val="";
        
          update();
            if(session.equals("s1") || session.equals("s2") || session.equals("s3") || session.equals("s6") || session.equals("s7")){
              val="/survey/"+track+"/audio4.xhtml";
            }

            else if(session.equals("s4") || session.equals("s5")|| session.equals("s10") || session.equals("s11") || session.equals("s12")){
              val="/survey/"+track+"/envpage.xhtml";
            }
            else if(session.equals("s8") || session.equals("s9")){
              val="/survey/"+track+"/chat.xhtml";
            }
            s.removeAttribute("laststayed");
            s.setAttribute("laststayed",laststayed);
          redirectToNext(val);
    }



    public void initialize2(){

t1=new DateTime();

Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Initialize..Date is .." + t1);   
    }
    
     
     public void update(){
      String sql="";
         t2=new DateTime();
         Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"update..Date is .." + t2);
         Duration duration = new Duration(t1,t2);
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
           //Class.forName("com.mysql.jdbc.Driver");
         String updateLaststayed="update users set laststayed=? where userMail=?"; 
             String sql1="update times set t3=? where userMail=?";
             String sql2="update times set t7=? where userMail=?";
             String sql3="update times set t11=? where userMail=?";
             String sql4="update times set t15=? where userMail=?";
             String sql5="update times set t18=? where userMail=?";
             String sql6="update times set t21=? where userMail=?";
             String sql7="update times set t25=? where userMail=?";
             String sql8="update times set t29=? where userMail=?";
             String sql9="update times set t32=? where userMail=?";
             String sql10="update times set t35=? where userMail=?";
             String sql11="update times set t38=? where userMail=?";
             String sql12="update times set t41=? where userMail=?";
             
            
            
           if(session.equals("s1")){
                 sql=sql1;
                 track="track1";
                 laststayed="/survey/"+track+"/audio4.xhtml";
           }   

          else if(session.equals("s2")){
                 sql=sql2;
                 track="track1";
                 laststayed="/survey/"+track+"/audio4.xhtml";
           }

           else if(session.equals("s3")){
            sql=sql3;
            track="track1";
            laststayed="/survey/"+track+"/audio4.xhtml";
           }


           else if(session.equals("s4")){
            track="track2";
            sql=sql4;
            laststayed="/survey/"+track+"/postemotions1.xhtml";
           }

           else if(session.equals("s5")){
            track="track2";
            sql=sql5;
            laststayed="/survey/"+track+"/feedback1.xhtml";
           }

           else if(session.equals("s6")){
            track="track3";
            sql=sql6;
            laststayed="/survey/"+track+"/audio4.xhtml";
           }

           else if(session.equals("s7")){
            track="track3";
            sql=sql7;
            laststayed="/survey/"+track+"/audio4.xhtml";
           }

           else if(session.equals("s8")){
            track="track4";
            sql=sql8;
            laststayed="/survey/"+track+"/postemotions1.xhtml";
           }

           else if(session.equals("s9")){
            track="track4";
            sql=sql9;
            laststayed="/survey/"+track+"/feedback1.xhtml";
           }

           else if(session.equals("s10")){
            track="track5";
            sql=sql10;
            laststayed="/survey/"+track+"/postemotions1.xhtml";
           }

           else if(session.equals("s11")){
            track="track5";
            sql=sql11;
            laststayed="/survey/"+track+"/feedback1.xhtml";
           }
            //s12   
           else{
            track="track5";
            sql=sql12;
            laststayed="/survey/"+track+"/posttest/posttest.xhtml";
           }

           con=datasource.getConnection();
           con.setAutoCommit(false);
           ps=con.prepareStatement(sql);
             ps.setString(1,diff);
            ps.setString(2,key);
            
            ps.executeUpdate();

         

           ps=con.prepareStatement(updateLaststayed);
           ps.setString(1,laststayed);
            ps.setString(2,key);
            ps.executeUpdate();

            con.commit();

            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Inserted..");

            
           } catch (SQLException ex) {
            FacesContext.getCurrentInstance().addMessage("audio3:growl",new FacesMessage(FacesMessage.SEVERITY_WARN,"Ein Fehler ist aufgetreten","Bitte erneut versuchen.."));
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




public void redirect(String path){
        try {
            ExternalContext ec=FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath()+"/survey/"+path+"/audio3.xhtml");
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