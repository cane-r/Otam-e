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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Cuneyt
 */

public class PreTest3 implements Serializable {
    
   
    
    
    private String q1,q2,q3,q4,q5,q6,q7,q8,q9,q10;
    
    private Map<Byte,Byte> answers;

    private Context initialContext;
    private DataSource datasource;
    
     private String key;
    private HttpSession s;

    public String getQ1() {
        return q1;
    }

    public String getQ2() {
        return q2;
    }

    public String getQ3() {
        return q3;
    }

    public String getQ4() {
        return q4;
    }

    public String getQ5() {
        return q5;
    }

    public String getQ6() {
        return q6;
    }

    public String getQ7() {
        return q7;
    }

    public String getQ8() {
        return q8;
    }


    public String getQ9() {
        return q7;
    }

    public String getQ10() {
        return q8;
    }




    

    public void setQ1(String q1) {
        this.q1 = q1;
    }

    public void setQ2(String q2) {
        this.q2 = q2;
    }

    public void setQ3(String q3) {
        this.q3 = q3;
    }

    public void setQ4(String q4) {
        this.q4 = q4;
    }

    public void setQ5(String q5) {
        this.q5 = q5;
    }

    public void setQ6(String q6) {
        this.q6 = q6;
    }

    public void setQ7(String q7) {
        this.q7 = q7;
    }

    public void setQ8(String q8) {
        this.q8 = q8;
    }
    public void setQ9(String q9) {
        this.q9 = q9;
    }

    public void setQ10(String q10) {
        this.q10 = q10;
    }


    
    
    public PreTest3() {
    
    }
   
   @PostConstruct
public void initialize(){
answers=new LinkedHashMap<>();
    
    for(byte i=1;i<=7;i++){
        answers.put(i,i);
    }

key=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
s=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    
     try {
            initialContext = new InitialContext();
            datasource = (DataSource)initialContext.lookup("java:jboss/datasources/MySQLDS");
        } catch (NamingException ex) {
            Logger.getLogger(PreTest3.class.getName()).log(Level.SEVERE,"Data source error..", ex);
        }




} 


    public  Map<Byte, Byte> getAnswers() {
        return answers;
    }
    
    
    public void setAnswers(Map<Byte, Byte> answers) {
        this.answers = answers;
    }

    
    public synchronized String build(){
        StringBuilder sb=new StringBuilder();
        sb.append(q1).append(q2).append(q3).append(q4).append(q5).append(q6).append(q7).append(q8).append(q9).
                append(q10);
        return sb.toString();
    }

    public boolean check(){
        if(     q1==null || q1.equals("") || q2==null || q2.equals("") ||
                q3==null || q3.equals("") || q4==null || q4.equals("") ||
                q5==null || q5.equals("") || q6==null || q6.equals("") ||
                q7==null || q7.equals("") || q8==null || q8.equals("") ||
                q9==null || q9.equals("") || q10==null || q10.equals("") ){
            Logger.getLogger(PreTest3.class.getName()).log(Level.SEVERE,"Validation error.");
            Logger.getLogger(PreTest3.class.getName()).log(Level.SEVERE,build());
            return false;
        }
        else
            Logger.getLogger(PreTest3.class.getName()).log(Level.SEVERE,"Validation ok.");
        Logger.getLogger(PreTest3.class.getName()).log(Level.SEVERE,build());
            return true;
    }
  
    public void navigate(){
        Logger.getLogger(PreTest3.class.getName()).log(Level.SEVERE,"In navigate..");
            
        boolean isValid=check();
      
        
            if(isValid){
                Logger.getLogger(PreTest3.class.getName()).log(Level.SEVERE,"Valid..redirecting to pretest3");
            update();
            redirectToNext();
            }
            else{
                Logger.getLogger(PreTest3.class.getName()).log(Level.SEVERE,"Not valid..not redirecting");
              FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Es ist ein Fehler aufgetreten",
                    "Bitte beantworten Sie alle Fragen."));    // Error Please answer all questions
              FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
              redirect();
            }
        
    }
        
       
    
     
     public void update(){
         
         Connection con=null;

           PreparedStatement ps=null;
           try{
           //Class.forName("com.mysql.jdbc.Driver");
           //con=DriverManager.getConnection("jdbc:mysql://localhost:3306/epsydb?zeroDateTimeBehavior=convertToNull","root","19888891");
            String sql = "update pretest set q49=?,q50=?,q51=?,q52=?,q53=?,q54=?,q55=?,q56=?,q57=?,q58=? where userMail=?";
            String sql2="update users set laststayed=? where userMail=?"; 
           con=datasource.getConnection();
           con.setAutoCommit(false);
            ps=con.prepareStatement(sql);
            ps.setByte(1, Byte.parseByte(q1));
            ps.setByte(2, Byte.parseByte(q2));
            ps.setByte(3, Byte.parseByte(q3));
            ps.setByte(4, Byte.parseByte(q4));
            ps.setByte(5, Byte.parseByte(q5));
            ps.setByte(6, Byte.parseByte(q6));
            ps.setByte(7, Byte.parseByte(q7));
            ps.setByte(8, Byte.parseByte(q8));
             ps.setByte(9, Byte.parseByte(q9));
              ps.setByte(10, Byte.parseByte(q10));
            ps.setString(11,key);

            ps.executeUpdate();


         

           ps=con.prepareStatement(sql2);
           ps.setString(1,"/survey/track1/audio1.xhtml");
            ps.setString(2,key);
            ps.executeUpdate();

             con.commit();
           

           

            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Updated..");
             s.removeAttribute("laststayed");
            s.setAttribute("laststayed","/survey/track1/audio1.xhtml");

            
           } catch (SQLException ex) {
            FacesContext.getCurrentInstance().addMessage("Warn", new FacesMessage("Hinweis","Bitte erneut versuchen."));
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

            Logger.getLogger(PreTest3.class.getName()).log(Level.SEVERE, "Sql exception occured while updating...." + ex.getMessage());


                    if(con!=null){

                   try {
                       con.rollback();
                   } catch (SQLException ex1) {
                       Logger.getLogger(PreTest.class.getName()).log(Level.SEVERE, null, ex1);
                   }
               }

               redirect();
            
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
            Logger.getLogger(PreTest3.class.getName()).log(Level.SEVERE, "Error while closing connection");
        }
       
   }
   
   public void closeStatement(PreparedStatement ps){
        try {
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(PreTest3.class.getName()).log(Level.SEVERE,"Error while closing statement");
        }
       
   }
   
   public void closeResultSet(ResultSet rs){
       if(rs!=null){
        try {
           rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(PreTest3.class.getName()).log(Level.SEVERE,"Error while closing resultset");
        }
       }
   }
   
   public void redirect(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("pretest3.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(PreTest3.class.getName()).log(Level.SEVERE,"Error while redirecting to current page..");
        }
   }
   
  
   
   public void redirectToNext(){
        try {
            ExternalContext c=FacesContext.getCurrentInstance().getExternalContext();
          c.redirect(c.getRequestContextPath()+"/survey/track1/pretrack.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(PreTest3.class.getName()).log(Level.SEVERE,"Error while redirecting to next page..");
        }
   }        
     
     
}