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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Cuneyt
 */

public class PreTest1 implements Serializable {
    
   
    
    
    private String q1,q2,q3,q4,q5,q6,q7,q8,q9,q10,q11,q12,q13,q14,q15,q16,q17,q18,q19,q20,q21,
            q22,q23,q24,q25,q26;

            private Context initialContext;
    private DataSource datasource;
    
    private Map<Byte,Byte> answers;
    private Map<String,String> answers2;
    private Map<String,String> answers3;
    private String answer;
    private String key;
    private HttpSession s;



    public PreTest1() {
       
    }

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


    
    
    


    public  Map<Byte, Byte> getAnswers() {
        return answers;
    }
    
    
    public void setAnswers(Map<Byte, Byte> answers) {
        this.answers = answers;
    }



    public  Map<String,String> getAnswers2() {
        return answers2;
    }
    
    
    public void setAnswers2(Map<String,String> answers2) {
        this.answers2= answers2;
    }


    public  Map<String,String> getAnswers3() {
        return answers3;
    }
    
    
    public void setAnswers3(Map<String,String> answers3) {
        this.answers3= answers3;
    }

    
    public String getAnswer() {
        return answer;
    }
    
    public void setAnswer(String answer) {
        this.answer = answer;
    }

   public synchronized String build(){
        StringBuilder sb=new StringBuilder();
        sb.append(q1).append(q2).append(q3).append(q4).append(q5).append(q6).append(q7).append(q8).append(q9).
                append(q10);
        return sb.toString();
    }

    public boolean check(){
        
build();
      if(q1==null || q3==null ||  q5==null || q10==null){
        FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Es ist ein Fehler aufgetreten",
                    "Bitte beantworten Sie alle Fragen."));  
              FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        return false;
      }

      //else if(q5.equals("ja"))


       else if(q1.equals("ja") && q2.length()<1 || q3.equals("ja") && q4.length()<1 ||  q1.equals("nein") && q2.length()>0 || q3.equals("nein") && q4.length()>0  /*|| q5.equals("nein")  && (q6.equals("ja") || q7.length()>0 || q8.length()>0  )*/ ){
        //js disable edilmiş.1 ja sa 2 de değer boş gelemez js çalışıyorsa kontrol var..
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Error",
                    "Sie haben ja gewählt aber nichts in das Eingabefeld geschrieben.")); //5 seçili ama 6,7,8,9 dan birinde değer var.. js disable edilmiş demektir.çünkü 5 nein se js 6,7,8,9 u kapatıyor.
              FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Validation error.");
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,build());
            return false;
        }

else if(q2.length()>250 || q4.length()>250 || q7.length()>250 || q8.length()>250 ){

    FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Es ist ein Fehler aufgetreten.",
                    "Benutzen Sie bitte weniger als 250 Zeichen.")); //Write something less then 250 characters.
              FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Validation error.");
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,build());
            return false;

}

        else{
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Validation ok.");
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,build());
            return true;
    }
}
  
    public void navigate(){
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"In navigate..");
            
        boolean isValid=check();
        
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Is valid? : " + isValid);
        
        boolean isExist=exists();
        
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Is exists?: " + isExist);
        
        if(isExist){
            if(isValid){
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Exists and valid");
            update();
            redirectToNext();
            }
            else{
               /* 
              FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Es ist ein Fehler aufgetreten",
                    "Bitte beantworten Sie alle Fragen."));    // Error Please answer all questions 
              FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
              Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Exists and not valid");
              */
              redirect();
            }
        }
        
        else if(!isExist){
           if(isValid){
               Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Not exists and valid");
            insert();
            redirectToNext();
            }
            else{
                /*
                FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Es ist ein Fehler aufgetreten",
                    "Bitte beantworten Sie alle Fragen.")); 
                FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
                */
               Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Not exists and not valid");
                
              
              redirect();
            }
        }
        
        else{
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Unknown error..");
            redirect();
                
       }
    }
               
    
    
    
     public boolean exists(){
        String sql="select userMail from pretest where userMail=?";
        ResultSet rs=null;
        Connection con=null;
        boolean result=false;
        PreparedStatement ps=null;
        try {  
             //Class.forName("com.mysql.jdbc.Driver");
             //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/epsydb?zeroDateTimeBehavior=convertToNull","root","19888891");
            con=datasource.getConnection();
             ps=con.prepareStatement(sql);
                ps.setString(1,key);
                rs=ps.executeQuery();
                result=rs.next();
                
                
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Db error while searching for a record..");
            redirect();
        } 

        finally{
            closeResultSet(rs);
            closeStatement(ps);
            closeConnection(con);
        }
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Result is: "+result);
        return result;
    }
     
     public void update(){
         
         Connection con=null;

           PreparedStatement ps=null;
           try{
           //Class.forName("com.mysql.jdbc.Driver");
             String sql="update pretest set q1=?,q2=?,q3=?,q4=?,q5=?,q6=?,q7=?,q8=?,q9=?,q10=? where userMail=?";
             String sql2="update users set laststayed=? where userMail=?";
           //con=DriverManager.getConnection("jdbc:mysql://localhost:3306/epsydb?zeroDateTimeBehavior=convertToNull","root","19888891");
           con=datasource.getConnection();
           con.setAutoCommit(false);
           ps=con.prepareStatement(sql);
           if(q6==null){
            q6="";
           }
           if(q9==null){
            q9="";
           }
            ps.setString(1, q1);
            ps.setString(2, q2);
            ps.setString(3, q3);
            ps.setString(4, q4);
            ps.setString(5, q5);
            ps.setString(6, q6);
            ps.setString(7, q7);
            ps.setString(8, q8);
            ps.setString(9, q9);
            ps.setByte(10, Byte.parseByte(q10));
            ps.setString(11,key);
            ps.executeUpdate();

            ps=con.prepareStatement(sql2);
           ps.setString(1,"/survey/pretest1.xhtml");
            ps.setString(2,key);
            ps.executeUpdate();

            con.commit();
             Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Inserted..");
            s.removeAttribute("laststayed");
            s.setAttribute("laststayed","/survey/pretest1.xhtml");
            
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
            FacesContext.getCurrentInstance().getExternalContext().redirect("pretest.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Error while redirecting to current page..");
        }
   }
   
   public void insert(){
       
   Connection con=null;
   PreparedStatement ps=null;
   try
   {

 
    
            con=datasource.getConnection();
            con.setAutoCommit(false);
   String sql = "INSERT INTO pretest (userMail,q1,q2,q3,q4,q5,q6,q7,q8,q9,q10,q11,q12,q13,q14,q15,q16,q17,q18,q19,q20,q21,q22,q23,q24,q25,q26,q27,q28,q29,q30,q31,q32,q33,q34,q35,q36,q37,q38,q39,q40,q41,q42,q43,q44,q45,q46,q47,q48,q49,q50,q51,q52,q53,q54,q55,q56,q57,q58)"
           + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
          String sql2="update users set laststayed=? where userMail=?"; 
            ps=con.prepareStatement(sql);
             if(q6==null){
            q6="";
           }
           if(q9==null){
            q9="";
           }
            ps.setString(1,key);
            ps.setString(2,q1);
            ps.setString(3,q2);
            ps.setString(4,q3);
            ps.setString(5,q4);
            ps.setString(6,q5);
            ps.setString(7,q6);
            ps.setString(8,q7);
            ps.setString(9,q8);
            ps.setString(10,q9);
            ps.setByte(11,Byte.parseByte(q10));
            ps.setByte(12, (byte)0);
            ps.setByte(13, (byte)0);
            ps.setByte(14, (byte)0);
            ps.setByte(15, (byte)0);
            ps.setByte(16, (byte)0);
            ps.setByte(17, (byte)0);
            ps.setByte(18, (byte)0);
            ps.setByte(19, (byte)0);
            ps.setByte(20, (byte)0);
            ps.setByte(21, (byte)0);
            ps.setByte(22, (byte)0);
            ps.setByte(23, (byte)0);
            ps.setByte(24, (byte)0);
            ps.setByte(25, (byte)0);
            ps.setByte(26, (byte)0);
            ps.setByte(27, (byte)0);
            ps.setByte(28, (byte)0);
            ps.setByte(29, (byte)0);
            ps.setByte(30, (byte)0);
            ps.setByte(31, (byte)0);
            ps.setByte(32, (byte)0);
            ps.setByte(33, (byte)0);
            ps.setByte(34, (byte)0);
            ps.setByte(35, (byte)0);
            ps.setByte(36, (byte)0);
            ps.setByte(37, (byte)0);
            ps.setByte(38, (byte)0);
            ps.setByte(39, (byte)0);
            ps.setByte(40, (byte)0);
            ps.setByte(41, (byte)0);
            ps.setByte(42, (byte)0);
            ps.setByte(43, (byte)0);
            ps.setByte(44, (byte)0);
            ps.setByte(45, (byte)0);
            ps.setByte(46, (byte)0);
            ps.setByte(47, (byte)0);
            ps.setByte(48, (byte)0);
            ps.setByte(49, (byte)0);
            ps.setByte(50, (byte)0);
            ps.setByte(51, (byte)0);
            ps.setByte(52, (byte)0);
            ps.setByte(53, (byte)0);
            ps.setByte(54, (byte)0);
            ps.setByte(55, (byte)0);
            ps.setByte(56, (byte)0);
            ps.setByte(57, (byte)0);
            ps.setByte(58, (byte)0);
            ps.setByte(59, (byte)0);
            
            
            

           ps.executeUpdate();

          

           ps=con.prepareStatement(sql2);
           ps.setString(1,"/survey/pretest1.xhtml");
            ps.setString(2,key);
            ps.executeUpdate();

            con.commit();

             Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Inserted..");
         

            s.removeAttribute("laststayed");
            s.setAttribute("laststayed","/survey/pretest1.xhtml");
   }           
            
        catch (SQLException ex) {
         
        FacesContext.getCurrentInstance().addMessage("Warn", new FacesMessage("Hinweis","Bitte erneut versuchen."));
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Error while inserting: " + ex.getErrorCode() + ex.getMessage() + ex.getCause());

         if(con!=null){

                   try {
                       con.rollback();
                   } catch (SQLException ex1) {
                       Logger.getLogger(PreTest.class.getName()).log(Level.SEVERE,"Error while rollback");
                   }
               }

         redirect();
        }
   
   finally {
               closeStatement(ps);
               closeConnection(con);
           }
   }
   
   
   public void redirectToNext(){
        try {
            ExternalContext c=FacesContext.getCurrentInstance().getExternalContext();
          c.redirect(c.getRequestContextPath()+"/survey/pretest1.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Error while redirecting to next page..");
        }
   }

   @PostConstruct
public void initialize(){

answers=new LinkedHashMap<>();
answers2=new LinkedHashMap<>();
answers3=new LinkedHashMap<>();
    
    for(byte i=1;i<=7;i++){
        answers.put(i,i);
    }
    answers2.put("ja","ja");
    answers2.put("nein","nein");
    answers3.put("Weniger als 30 Min","Weniger als 30 Min");
    answers3.put("Mehr als 30 Min","Mehr als 30 Min");
    /*
    q1="nein";
    q2="";
    q3="nein";
    q4="";
    q5="nein";
    q6="";
    q7="nein";
    q8="";
    q9="Weniger als 30 Min";
    q10="1";
     */
    q2="";
    q4="";
    q7="";
    q8="";
key=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
    s=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

     try {
            initialContext = new InitialContext();
            datasource = (DataSource)initialContext.lookup("java:jboss/datasources/MySQLDS");
        } catch (NamingException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Data source error..", ex);
        }




}        
     
     
}