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

public class Audio1 implements Serializable {
    
   
    
    
    private String time;
    private String session;

   private Context initialContext;
    private DataSource datasource;
    private String key;
    private HttpSession s;
    private DateTime t1;
    private DateTime t2=null;
    private String track;
    
   
    
    
    
    
    public Audio1() {
       
    }



    public void initialize2(){

t1=new DateTime();

Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Initialize..Date is .." + t1);   
    }


@PostConstruct
public void initialize(){
  
Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"PostConstruct..");
//t1=new DateTime();
//Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Initialize..Date is .." + t1);
    key=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
    s=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    session=((String)s.getAttribute("session"));


     try {
            initialContext = new InitialContext();
            datasource = (DataSource)initialContext.lookup("java:jboss/datasources/MySQLDS");
        } catch (NamingException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Data source error..", ex);
        }


}




  
     public void navigate(){
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"In navigate..");
boolean exists=exists();
        if(session.equals("s1")){
          
         if(!exists){
          insert();
          }
          else{
            update();
          }
           
        }
        else{
          update();
          }

          s.removeAttribute("laststayed");
          s.setAttribute("laststayed","/survey/"+track+"/audio2.xhtml");

        redirectToNext(track);


    }

public boolean exists(){
        String sql="select userMail from times where userMail=?";
        PreparedStatement ps=null;
        ResultSet rs=null;
        Connection con=null;
        boolean result=false;
        
        try {
          con=datasource.getConnection();
             ps=con.prepareStatement(sql);
                ps.setString(1,key);
                rs=ps.executeQuery();
                result=rs.next();
                
                
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Db error while searching for a record..");
            redirect(track);
        } 

        finally{
            closeResultSet(rs);
            closeStatement(ps);
            closeConnection(con);
        }
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Is exists ?: "+result);
        return result;
    }


    public void redirectToNext(String path){
        try {
            ExternalContext ec=FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath()+"/survey/"+path+"/audio2.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Error while redirecting to current page..");
        }
   }




    
public void insert(){
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
          String diff=new String(String.valueOf(min)+":"+sec2);
         Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Diff is : "  + diff);
           
     Connection con=null;
    
    PreparedStatement ps=null;
     String sql = "INSERT INTO times(userMail,t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15,t16,t17,t18,t19,t20,t21,t22,t23,t24,t25,t26,t27,t28,t29,t30,t31,t32,t33,t34,t35,t36,t37,t38,t39,t40,t41) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     String sql2="update users set laststayed=? where userMail=?"; 
try{  
      track="track1";
      con=datasource.getConnection();
       con.setAutoCommit(false);
       ps=con.prepareStatement(sql);
            ps.setString(1,key);
            ps.setString(2,diff);
            ps.setString(3,"0");
            ps.setString(4,"0");
            ps.setString(5,"0");
            ps.setString(6,"0");
            ps.setString(7,"0");
            ps.setString(8,"0");
            ps.setString(9,"0");
            ps.setString(10,"0");
            ps.setString(11,"0");
            ps.setString(12,"0");
            ps.setString(13,"0");
            ps.setString(14,"0");
            ps.setString(15,"0");
            ps.setString(16,"0");
            ps.setString(17,"0");
            ps.setString(18,"0");
            ps.setString(19,"0");
            ps.setString(20,"0");
            ps.setString(21,"0");
            ps.setString(22,"0");
            ps.setString(23,"0");
            ps.setString(24,"0");
            ps.setString(25,"0");
            ps.setString(26,"0");
            ps.setString(27,"0");
            ps.setString(28,"0");
            ps.setString(29,"0");
            ps.setString(30,"0");
            ps.setString(31,"0");
            ps.setString(32,"0");
            ps.setString(33,"0");
            ps.setString(34,"0");
            ps.setString(35,"0");
            ps.setString(36,"0");
            ps.setString(37,"0");
            ps.setString(38,"0");
            ps.setString(39,"0");
            ps.setString(40,"0");
            ps.setString(41,"0");
            ps.setString(42,"0");
            
            ps.executeUpdate();

            ps=con.prepareStatement(sql2);
            ps.setString(1,"/survey/track1/audio2.xhtml");
            ps.setString(2,key);
            ps.executeUpdate();

            con.commit();

            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Inserted into times..");

}


catch(SQLException ex){
FacesContext.getCurrentInstance().addMessage("audio1:growl",new FacesMessage(FacesMessage.SEVERITY_WARN,"Ein Fehler ist aufgetreten","Bitte erneut versuchen.."));
FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Error while inserting: " +ex.getErrorCode()+" "+ex.getMessage()+ " " +ex.getCause());

                if(con!=null){

                   try {
                       con.rollback();
                   } catch (SQLException ex1) {
                       Logger.getLogger(PreTest.class.getName()).log(Level.SEVERE,"Error while rollback");
                   }
               }
            redirect(track);
}

finally {
               closeStatement(ps);
               closeConnection(con);
           }

}







     
     public void update(){
      String sql="";
         t2=new DateTime();
         Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Update..Date is .." + t2);
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
           //Class.forName("com.mysql.jdbc.Driver");
         String updateLaststayed="update users set laststayed=? where userMail=?"; 
             String sql1="update times set t1=? where userMail=?";
             String sql2="update times set t5=? where userMail=?";
             String sql3="update times set t9=? where userMail=?";
             String sql4="update times set t13=? where userMail=?";
             String sql5="update times set t16=? where userMail=?";
             String sql6="update times set t19=? where userMail=?";
             String sql7="update times set t23=? where userMail=?";
             String sql8="update times set t27=? where userMail=?";
             String sql9="update times set t30=? where userMail=?";
             String sql10="update times set t33=? where userMail=?";
             String sql11="update times set t36=? where userMail=?";
             String sql12="update times set t39=? where userMail=?";
             

          if(session.equals("s1")){
                 sql=sql1;
                 track="track1";
           }   

          else if(session.equals("s2")){
                 sql=sql2;
                 track="track1";
           }

           else if(session.equals("s3")){
            sql=sql3;
            track="track1";
           }


           else if(session.equals("s4")){
            track="track2";
            sql=sql4;
           }

           else if(session.equals("s5")){
            track="track2";
            sql=sql5;
           }

           else if(session.equals("s6")){
            track="track3";
            sql=sql6;
           }

           else if(session.equals("s7")){
            track="track3";
            sql=sql7;
           }

           else if(session.equals("s8")){
            track="track4";
            sql=sql8;
           }

           else if(session.equals("s9")){
            track="track4";
            sql=sql9;
           }

           else if(session.equals("s10")){
            track="track5";
            sql=sql10;
           }

           else if(session.equals("s11")){
            track="track5";
            sql=sql11;
           }
            //s12   
           else{
            track="track5";
            sql=sql12;
           }

           

           con=datasource.getConnection();
           con.setAutoCommit(false);
           ps=con.prepareStatement(sql);
             ps.setString(1,diff);
            ps.setString(2,key);
            
            ps.executeUpdate();

         String ls="/survey/"+track+"/audio2.xhtml";

           ps=con.prepareStatement(updateLaststayed);
           ps.setString(1,ls);
            ps.setString(2,key);
            ps.executeUpdate();

            con.commit();

            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Inserted..");

            
           } catch (SQLException ex) {
            FacesContext.getCurrentInstance().addMessage("audio1:growl",new FacesMessage(FacesMessage.SEVERITY_WARN,"Ein Fehler ist aufgetreten","Bitte erneut versuchen.."));
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
   

public void redirect(String path){
        try {
            ExternalContext ec=FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath()+"/survey/"+path+"/audio1.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Error while redirecting to current page..");
        }
   }


        
     
     
}