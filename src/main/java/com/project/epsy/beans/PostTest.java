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


/**
 *
 * @author Cuneyt
 */

public class PostTest implements Serializable {
    
   
    
    
    private String q1,q2,q3,q4,q5,q6,q7,q8,q9,q10,q11,q12,q13,q14,q15,q16,q17,q18,q19,q20,q21,
            q22,q23,q24,q25,q26;

            private Context initialContext;
    private DataSource datasource;
    private String session;
    private Map<Byte,Byte> answers;
    private String answer;
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

    

    public String getQ19() {
        return q19;
    }

    public String getQ20() {
        return q20;
    }

    public String getQ21() {
        return q21;
    }

    public String getQ22() {
        return q22;
    }

    public String getQ23() {
        return q23;
    }

    public String getQ24() {
        return q24;
    }

    public String getQ25() {
        return q25;
    }

    public String getQ26() {
        return q26;
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

    public void setQ19(String q19) {
        this.q19 = q19;
    }

    public void setQ20(String q20) {
        this.q20 = q20;
    }

    public void setQ21(String q21) {
        this.q21 = q21;
    }

    public void setQ22(String q22) {
        this.q22 = q22;
    }

    public void setQ23(String q23) {
        this.q23 = q23;
    }

    public void setQ24(String q24) {
        this.q24 = q24;
    }

    public void setQ25(String q25) {
        this.q25 = q25;
    }

    public void setQ26(String q26) {
        this.q26 = q26;
    }

   
    
    
    
    
    public PostTest() {
       
    
    }


    public  Map<Byte, Byte> getAnswers() {
        return answers;
    }
    
    
    public void setAnswers(Map<Byte, Byte> answers) {
        this.answers = answers;
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
                append(q10).append(q11).append(q12).append(q13).append(q14).append(q15).append(q16).append(q17).append(q18).append(q19).
                append(q20).append(q21).append(q22).append(q23).append(q24).append(q25).append(q26);
        return sb.toString();
    }

    public boolean check(){
        if(     q1==null || q1.equals("") || q2==null || q2.equals("") ||
                q3==null || q3.equals("") || q4==null || q4.equals("") ||
                q5==null || q5.equals("") || q6==null || q6.equals("") ||
                q7==null || q7.equals("") || q8==null || q8.equals("") ||
                q9==null || q9.equals("") || q10==null || q10.equals("") ||
                q11==null || q11.equals("") || q12==null || q12.equals("") ||
                q13==null || q13.equals("") || q14==null || q14.equals("") ||
                q15==null || q15.equals("") || q16==null || q16.equals("") ||
                q17==null || q17.equals("") || q18==null || q18.equals("") || 
                q19==null || q19.equals("")|| q20==null || q20.equals("")|| 
                q21==null || q21.equals("")|| q22==null || q22.equals("")|| 
                q23==null || q23.equals("")|| q24==null || q24.equals("")|| 
                q25==null || q25.equals("")|| q26==null || q26.equals("") ){
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Validation error.");
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,build());
            return false;
        }
        else
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Validation ok.");
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,build());
            return true;
    }


public boolean exists(){
        String sql1="select userMail from posttest where userMail=?";
        String sql2="select userMail from posttest2 where userMail=?";
        String sql=null;
        ResultSet rs=null;
        Connection con=null;
        boolean result=false;
        PreparedStatement ps=null;
if(session.equals("s12")){//first post-test
            sql=sql1;

           }
           else{//last post-test...
sql=sql2;
           }


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


  
     public void navigate(){
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"In navigate..");
            
        boolean isValid=check();
      boolean exist=exists();
        
            if(isValid){

                if(exist){
                
            update();
            }
            else{
                insert();
            }
            redirectToNext();
            }
            else{
                
              FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Es ist ein Fehler aufgetreten",
                    "Bitte beantworten Sie alle Fragen.")); // Error Please answer all questions
              FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
              Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Not valid..not redirecting");
              redirect();
            }
        
    }
    
   
     
     public void update(){
         
         Connection con=null;

           PreparedStatement ps=null;
           try{
           //Class.forName("com.mysql.jdbc.Driver");
            String sql=null;
             String sql1="update posttest set q1=?,q2=?,q3=?,q4=?,q5=?,q6=?,q7=?,q8=?,q9=?,q10=?,q11=?,q12=?,q13=?,q14=?,q15=?,q16=?,q17=?,q18=?,q19=?,q20=?,q21=?,q22=?,q23=?,q24=?,q25=?,q26=? where userMail=?";
           String sql2="update posttest2 set q1=?,q2=?,q3=?,q4=?,q5=?,q6=?,q7=?,q8=?,q9=?,q10=?,q11=?,q12=?,q13=?,q14=?,q15=?,q16=?,q17=?,q18=?,q19=?,q20=?,q21=?,q22=?,q23=?,q24=?,q25=?,q26=? where userMail=?";
           if(session.equals("s12")){//first post-test
            sql=sql1;

           }
           else{//last post-test...
sql=sql2;
           }
           String sql_update="update users set laststayed=? where userMail=?"; 
           con=datasource.getConnection();
           ps=con.prepareStatement(sql);
            con.setAutoCommit(false);
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
            ps.setByte(16, Byte.parseByte(q16));
            ps.setByte(17, Byte.parseByte(q17));
            ps.setByte(18, Byte.parseByte(q18));
            ps.setByte(19, Byte.parseByte(q19));
            ps.setByte(20, Byte.parseByte(q20));
            ps.setByte(21, Byte.parseByte(q21));
            ps.setByte(22, Byte.parseByte(q22));
            ps.setByte(23, Byte.parseByte(q23));
            ps.setByte(24, Byte.parseByte(q24));
            ps.setByte(25, Byte.parseByte(q25));
            ps.setByte(26, Byte.parseByte(q26));
            ps.setString(27,key);
            ps.executeUpdate();

         

           ps=con.prepareStatement(sql_update);
           ps.setString(1,"/survey/track5/posttest/posttest1.xhtml");
            ps.setString(2,key);
            ps.executeUpdate();
            con.commit();

            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Inserted..");

            s.removeAttribute("laststayed");
            s.setAttribute("laststayed","/survey/track5/posttest/posttest1.xhtml");
            
           } catch (SQLException ex) {
            FacesContext.getCurrentInstance().addMessage("Warn", new FacesMessage("Ein Fehler ist aufgetreten","Bitte versuchen Sie es erneut.."));
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

public void insert(){
       
   Connection con=null;
   PreparedStatement ps=null;
   String sql=null;
   try
   {

 
    
            con=datasource.getConnection();
            con.setAutoCommit(false);
           String sql1 = "INSERT INTO posttest (userMail,q1,q2,q3,q4,q5,q6,q7,q8,q9,q10,q11,q12,q13,q14,q15,q16,q17,q18,q19,q20,q21,q22,q23,q24,q25,q26,q27,q28,q29,q30,q31,q32,q33,q34,q35,q36,q37,q38,q39,q40,q41,q42,q43,q44,q45,q46,q47,q48,q49,q50,q51,q52,q53,q54,q55,q56)"
           + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

           String sql2 = "INSERT INTO posttest2 (userMail,q1,q2,q3,q4,q5,q6,q7,q8,q9,q10,q11,q12,q13,q14,q15,q16,q17,q18,q19,q20,q21,q22,q23,q24,q25,q26,q27,q28,q29,q30,q31,q32,q33,q34,q35,q36,q37,q38,q39,q40,q41,q42,q43,q44,q45,q46,q47,q48,q49,q50,q51,q52,q53,q54,q55,q56)"
           + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

          String update="update users set laststayed=? where userMail=?";

if(session.equals("s12")){//s12-->1st posttest
            sql=sql1;

           }
           else{//s13..lastposttest..
sql=sql2;
           }


            ps=con.prepareStatement(sql);


             
            ps.setString(1,key);
            ps.setByte(2,Byte.parseByte(q1));
            ps.setByte(3,Byte.parseByte(q2));
            ps.setByte(4,Byte.parseByte(q3));
            ps.setByte(5,Byte.parseByte(q4));
            ps.setByte(6,Byte.parseByte(q5));
            ps.setByte(7,Byte.parseByte(q6));
            ps.setByte(8,Byte.parseByte(q7));
            ps.setByte(9,Byte.parseByte(q8));
            ps.setByte(10,Byte.parseByte(q9));
            ps.setByte(11,Byte.parseByte(q10));
            ps.setByte(12, Byte.parseByte(q11));
            ps.setByte(13, Byte.parseByte(q12));
            ps.setByte(14, Byte.parseByte(q13));
            ps.setByte(15, Byte.parseByte(q14));
            ps.setByte(16, Byte.parseByte(q15));
            ps.setByte(17, Byte.parseByte(q16));
            ps.setByte(18, Byte.parseByte(q17));
            ps.setByte(19, Byte.parseByte(q18));
            ps.setByte(20, Byte.parseByte(q19));
            ps.setByte(21, Byte.parseByte(q20));
            ps.setByte(22, Byte.parseByte(q21));
            ps.setByte(23, Byte.parseByte(q22));
            ps.setByte(24, Byte.parseByte(q23));
            ps.setByte(25, Byte.parseByte(q24));
            ps.setByte(26, Byte.parseByte(q25));
            ps.setByte(27, Byte.parseByte(q26));
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
            ps.setString(55, "");
            ps.setString(56, "");
            ps.setString(57, "");
            
            
            
            

           ps.executeUpdate();

          

           ps=con.prepareStatement(update);
           ps.setString(1,"/survey/track5/posttest/posttest1.xhtml");
            ps.setString(2,key);
            ps.executeUpdate();

            con.commit();

             Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Inserted..");
         

            s.removeAttribute("laststayed");
            s.setAttribute("laststayed","/survey/track5/posttest/posttest1.xhtml");
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
            Logger.getLogger(PreTest.class.getName()).log(Level.SEVERE,"Error while closing resultset");
        }
       }
   }
   
   public void redirect(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("posttest.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(PreTest.class.getName()).log(Level.SEVERE,"Error while redirecting to current page..");
        }
   }

   
   
   public void redirectToNext(){
        try {
            ExternalContext c=FacesContext.getCurrentInstance().getExternalContext();
          c.redirect(c.getRequestContextPath()+"/survey/track5/posttest/posttest1.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(PreTest.class.getName()).log(Level.SEVERE,"Error while redirecting to next page..");
        }
   }

   @PostConstruct
public void initialize(){
answers=new LinkedHashMap<>();
    
    for(byte i=1;i<=7;i++){
        answers.put(i,i);
    }

    key=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
s=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
session=((String)s.getAttribute("session"));

     try {

            
            initialContext = new InitialContext();
            datasource = (DataSource)initialContext.lookup("java:jboss/datasources/MySQLDS");
        } catch (NamingException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE,"Data source error..", ex);
        }
        catch (Exception ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE,"Error..", ex);
        }


}        
     
     
}