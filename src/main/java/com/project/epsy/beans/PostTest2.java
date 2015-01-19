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

public class PostTest2 implements Serializable {
    
   
    
    
    private String q1,q2,q3,q4,q5,q6,q7,q8,q9,q10,q11,q12,q13,q14,q15,q16,q17,q18;
    
    private Map<Byte,Byte> answers;
    private Map<String,String> answers2;

    private Context initialContext;
    private DataSource datasource;
    private String key;
    private HttpSession s;
    private String session;
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
        return q9;
    }

    public String getQ10() {
        return q10;
    }

    public String getQ11() {
        return q11;
    }

    public String getQ12() {
        return q12;
    }

     public String getQ13() {
        return q13;
    }

    public String getQ14() {
        return q14;
    }

    public String getQ15() {
        return q15;
    }

     public String getQ16() {
        return q16;
    }

    public String getQ17() {
        return q17;
    }

    public String getQ18() {
        return q18;
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

    public void setQ11(String q11) {
        this.q11 = q11;
    }

    public void setQ12(String q12) {
        this.q12 = q12;
    }

    public void setQ13(String q13) {
        this.q13 = q13;
    }

    public void setQ14(String q14) {
        this.q14 = q14;
    }

    public void setQ15(String q15) {
        this.q15 = q15;
    }

    public void setQ16(String q16) {
        this.q16 = q16;
    }

    public void setQ17(String q17) {
        this.q17 = q17;
    }

    public void setQ18(String q18) {
        this.q18 = q18;
    }


    
   
    
    
    
    
    public PostTest2() {
    
    }
   
   @PostConstruct
public void initialize(){
answers=new LinkedHashMap<>();
answers2=new LinkedHashMap<>();
    
    for(byte i=1;i<=7;i++){
        answers.put(i,i);
    }

    answers2.put("ja","ja");
    answers2.put("nein","nein");

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


    public  Map<Byte, Byte> getAnswers() {
        return answers;
    }
    
    
    public void setAnswers(Map<Byte, Byte> answers) {
        this.answers = answers;
    }


    public  Map<String, String> getAnswers2() {
        return answers2;
    }
    
    
    public void setAnswers2(Map<String, String> answers2) {
        this.answers2 = answers2;
    }

    
    public synchronized String build(){
        StringBuilder sb=new StringBuilder();
        sb.append(q1).append(q2).append(q3).append(q4).append(q5).append(q6).append(q7).append(q8).append(q9).
                append(q10).append(q11).append(q12);
        return sb.toString();
    }

    public boolean check(){
        if(     q1==null || q1.equals("") || q2==null || q2.equals("") ||
                q3==null || q3.equals("") || q4==null || q4.equals("") ||
                q5==null || q5.equals("") || q6==null || q6.equals("") ||
                q7==null || q7.equals("") || q8==null || q8.equals("") ||
                q9==null || q9.equals("") || q10==null || q10.equals("") ||
                q11==null || q11.equals("") || q12==null || q12.equals("") ||
                q13==null || q14.equals("") || q15==null || q16.equals("") ||
                q17==null || q17.length()<1 ) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Validation error.");
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,build());

            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Es ist ein Fehler aufgetreten",
                    "Bitte beantworten Sie alle Fragen."));    // Error Please answer all questions
              FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            return false;
        }

        else if(q16.length()<1 || q17.length()<1){
            FacesContext.getCurrentInstance().addMessage(
                    "pretest-r-form:growl",
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Es ist ein Fehler aufgetreten.",
                    "Min 1 wörten."));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            return false;

        }
        else if(q17.length()>250 || q17.length()>250){
FacesContext.getCurrentInstance().addMessage(
                    "pretest-r-form:growl",
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Es ist ein Fehler aufgetreten.",
                    "Benutzen Sie bitte weniger als 250 Zeichen."));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            return false;

        }
        else
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Validation ok.");
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,build());
            return true;
    }
  
    public void navigate(){
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"In navigate..");
        String message=null;
        String message1="Sie haben den Übungsdurchgang vollständig beendet";
         String message2= "Sie haben das OTAME-Programm vollständig beendet. Vielen Dank für Ihre Teilnahme."; 
        boolean isValid=check();
    
        
            if(isValid){
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Valid..");
            update();

              if(session.equals("s12")){//first post-test
            message=message1;

           }
           else{//last post-test...
message=message2;
}

FacesContext.getCurrentInstance().addMessage(
                    "r-form:growl",
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Info",message));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

            logout();
            }
            else{
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Not valid..not redirecting");
              
              redirect();
            }
        
    }
        
     
     public void update(){
         
         Connection con=null;
        String sql=null;
        String ses=null;
        String laststayed=null;
           PreparedStatement ps=null;
           try{
           //Class.forName("com.mysql.jdbc.Driver");
           //con=DriverManager.getConnection("jdbc:mysql://localhost:3306/epsydb?zeroDateTimeBehavior=convertToNull","root","19888891");
            //String sql = "update posttest set q37=?,q38=?,q39=?,q40=?,q41=?,q42=?,q43=?,q44=?,q45=?,q46=?,q47=?,q48=? where userMail=?";
            String sql1="update posttest set q39=?,q40=?,q41=?,q42=?,q43=?,q44=?,q45=?,q46=?,q47=?,q48=?,q49=?,q50=?,q51=?,q52=?,q53=?,q54=?,q55=?,q56=? where userMail=?";
            String sql2="update posttest2 set q39=?,q40=?,q41=?,q42=?,q43=?,q44=?,q45=?,q46=?,q47=?,q48=?,q49=?,q50=?,q51=?,q52=?,q53=?,q54=?,q55=?,q56=? where userMail=?";
if(session.equals("s12")){//first post-test
            sql=sql1;
            ses="s13";
            laststayed="login12ok";

           }
           else{//last post-test...
sql=sql2;
ses="s14";
laststayed="login13ok";
}
            String update="update users set laststayed=?,session=? where userMail=?"; 
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
            ps.setByte(11, Byte.parseByte(q11));
            ps.setByte(12, Byte.parseByte(q12));
            ps.setByte(13, Byte.parseByte(q13));
            ps.setByte(14, Byte.parseByte(q14));
            ps.setByte(15, Byte.parseByte(q15));
            ps.setString(16, q16);
            ps.setString(17, q17);
            ps.setString(18, q18);
            ps.setString(19,key);

            ps.executeUpdate();


           
         

           ps=con.prepareStatement(update);
           ps.setString(1,laststayed);
            ps.setString(2,ses);
            ps.setString(3,key);
            ps.executeUpdate();

            con.commit();
             Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Inserted..");
            
            
           } catch (SQLException ex) {
            FacesContext.getCurrentInstance().addMessage("Warn", new FacesMessage("Hinweis","Bitte erneut versuchen."));
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Sql exception occured while updating...." + ex.getMessage());

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



   
   public void closeResultSet(ResultSet rs){
       if(rs!=null){
        try {
           rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Error while closing resultset");
        }
       }
   }
   
   public void redirect(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("posttest2.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Error while redirecting to current page..");
        }
   }
   
   
     
}