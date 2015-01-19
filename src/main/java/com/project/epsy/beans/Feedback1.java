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
import org.joda.time.DateTime;

/**
 *
 * @author Cuneyt
 */

public class Feedback1 implements Serializable {
    
   
    
    
    private String q1,q2,q3,q4,q5,q6,q7,q8,q9,q10,q11,q12;

    private Context initialContext;
    private DataSource datasource;
    private String track;
    private String session;
    private Map<Byte,Byte> answers;
    private String key;
    private HttpSession s;
    private DateTime dt;



    public Feedback1() {
       
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

    public String getQ11() {
        return q11;
    }


    public String getQ12() {
        return q12;
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




    public  Map<Byte, Byte> getAnswers() {
        return answers;
    }
    
    
    public void setAnswers(Map<Byte, Byte> answers) {
        this.answers = answers;
    }

      
   public synchronized String build(){
        StringBuilder sb=new StringBuilder();
        sb.append(q1).append(q2).append(q3).append(q4).append(q5).append(q6).append(q7).append(q8).append(q9).append(q10).append(q11).append(q12);
        return sb.toString();
    }

    public boolean check(){
        
build();
      if(q1==null || q2==null || q3==null || q4==null || q5==null || q6==null || q7==null || q8==null || q9==null || q10==null || q11==null || q12==null){
        FacesContext.getCurrentInstance().addMessage(
                    "pretest-r-form:growl",
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Es ist ein Fehler aufgetreten",
                    "Bitte beantworten Sie alle Fragen."));  
              FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        return false;
      }


  else if(q2.length()>500 || q3.length()>500 || q5.length()>500 ||q6.length()>500 || q8.length()>500 || q9.length()>500 ||q11.length()>500 ||q12.length()>500 ){

    FacesContext.getCurrentInstance().addMessage(
                    "pretest-r-form:growl",
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Es ist ein Fehler aufgetreten.",
                    "Benutzen Sie bitte weniger als 250 Zeichen.")); //Write something less then 250 characters.
              FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Validation error.");
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,build());
            return false;

}

else if(q2.length()<0 || q3.length()<0 || q5.length()<0 || q6.length()<0 || q8.length()<0  || q9.length()<0 || q11.length()<0 || q12.length()<0 ){
 
    FacesContext.getCurrentInstance().addMessage(
                    "pretest-r-form:growl",
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Es ist ein Fehler aufgetreten.",
                    "Min. 1 Zeichen.")); //Write something more than a characters.
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


 public boolean check2(){
        
build();
      if(q1==null || q2==null || q3==null || q4==null || q5==null || q6==null || q7==null || q8==null || q9==null){
        FacesContext.getCurrentInstance().addMessage(
                    "pretest-r-form:growl",
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Es ist ein Fehler aufgetreten",
                    "Bitte beantworten Sie alle Fragen."));  
              FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        return false;
      }


  else if(q2.length()>500 || q3.length()>500 || q5.length()>500 ||q6.length()>500 || q8.length()>500 || q9.length()>500 ){

    FacesContext.getCurrentInstance().addMessage(
                    "pretest-r-form:growl",
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Es ist ein Fehler aufgetreten.",
                    "Benutzen Sie bitte weniger als 500 Zeichen.")); //Write something less then 250 characters.
              FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Validation error.");
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,build());
            return false;

}

else if(q2.length()<0 || q3.length()<0 || q5.length()<0 || q6.length()<0 || q8.length()<0  || q9.length()<0 ){
 
    FacesContext.getCurrentInstance().addMessage(
                    "pretest-r-form:growl",
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Es ist ein Fehler aufgetreten.",
                    "Min. 1 Zeichen.")); //Write something more than a characters.
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
      boolean valid;
      if(session.equals("s3")){
        valid=check();
      }
      else{
        valid=check2();
      }

      boolean exist=exists();
      if(valid){
      if(exist){
      update();
    }
    else{
      insert();
    }
      logout();
}
else{
/*
  FacesContext.getCurrentInstance().addMessage(
                    "pretest-r-form:growl",
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Es ist ein Fehler aufgetreten.",
                    "Write something more then a character."));
  FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
  */
  redirect(track);

}
        
        }


public void update(){
         String laststayed;
         String ses;
         Connection con=null;
          String sql="";
           PreparedStatement ps=null;
           try{
           
             String sql1="update feedback1 set q1=?,q2=?,q3=?,q4=?,q5=?,q6=?,q7=?,q8=?,q9=?,q10=?,q11=?,q12=? where userMail=?";
             String sql2="update feedback2 set q1=?,q2=?,q3=?,q4=?,q5=?,q6=?,q7=?,q8=?,q9=? where userMail=?";
             String sql3="update feedback3 set q1=?,q2=?,q3=?,q4=?,q5=?,q6=?,q7=?,q8=?,q9=? where userMail=?";
             String sql4="update feedback4 set q1=?,q2=?,q3=?,q4=?,q5=?,q6=?,q7=?,q8=?,q9=? where userMail=?";
             String sql5="update feedback5 set q1=?,q2=?,q3=?,q4=?,q5=?,q6=?,q7=?,q8=?,q9=? where userMail=?";

             String update2="update users set laststayed=?,session=?,track_time=? where userMail=?";
            //String update2="update users set laststayed=?,session=?,time_control=? where userMail=?";
           //String update="update users set laststayed=?,session=? where userMail=?";
           String update="update users set laststayed=?,session=?,time_control=? where userMail=?";
          if(session.equals("s3")){
            sql=sql1;
            laststayed="login3ok";
            ses="s4";
            track="track1";
          }
          else if(session.equals("s5")){
            sql=sql2;
            laststayed="login5ok";
            ses="s6";
            track="track2";
          }
          else if(session.equals("s7")){
            sql=sql3;
            laststayed="login7ok";
            ses="s8";
            track="track3";
          }
          else if(session.equals("s9")){
            sql=sql4;
            laststayed="login9ok";
            ses="s10";
            track="track4";
          }
          else{
            sql=sql5;
            laststayed="login11ok";
            ses="s12";
            track="track5";
          }

           con=datasource.getConnection();
           con.setAutoCommit(false);

           if(session.equals("s3")){
           ps=con.prepareStatement(sql);
            ps.setByte(1, Byte.parseByte(q1));
            ps.setString(2, q2);
            ps.setString(3, q3);
            ps.setByte(4, Byte.parseByte(q4));
            ps.setString(5, q5);
            ps.setString(6, q6);
            ps.setByte(7, Byte.parseByte(q7));
            ps.setString(8, q8);
            ps.setString(9, q9);
            ps.setByte(10, Byte.parseByte(q10));
            ps.setString(11,q11);
            ps.setString(12,q12);
            ps.setString(13,key);
          }
          else{

            ps=con.prepareStatement(sql);
            ps.setByte(1, Byte.parseByte(q1));
            ps.setString(2, q2);
            ps.setString(3, q3);
            ps.setByte(4, Byte.parseByte(q4));
            ps.setString(5, q5);
            ps.setString(6, q6);
            ps.setByte(7, Byte.parseByte(q7));
            ps.setString(8, q8);
            ps.setString(9, q9);
            ps.setString(10,key);


          }
            ps.executeUpdate();
/*
            if(session.equals("s11")){
              ps=con.prepareStatement(update2);
              ps.setString(1,laststayed);
            ps.setString(2,ses);
            dt=new DateTime();
            ps.setString(3,dt.toString());
            ps.setString(4,key);
            }

        else{
           ps=con.prepareStatement(update);
           ps.setString(1,laststayed);
            ps.setString(2,ses);
            dt=new DateTime();
            ps.setString(3,dt.toString());
            ps.setString(4,key);
          }
          */
              if(session.equals("s3")){

                ps=con.prepareStatement(update2);
              ps.setString(1,laststayed);
            ps.setString(2,ses);
            dt=new DateTime();
            ps.setString(3,dt.toString());
            ps.setString(4,key);

              }
              else {
          ps=con.prepareStatement(update);
           ps.setString(1,laststayed);
            ps.setString(2,ses);
            ps.setString(3,new DateTime().toString());
            ps.setString(4,key);
}

            ps.executeUpdate();

            con.commit();

            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Inserted..");
            
            //s.removeAttribute("laststayed");
            //s.setAttribute("laststayed","/survey/pretest1.xhtml");
            
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
               redirect(track);
        } 
           finally{
               closeStatement(ps);
               closeConnection(con);
           }
           }




        public boolean exists(){
          String sql="";
        String sql1="select userMail from feedback1 where userMail=?";
        String sql2="select userMail from feedback2 where userMail=?";
        String sql3="select userMail from feedback3 where userMail=?";
        String sql4="select userMail from feedback4 where userMail=?";
        String sql5="select userMail from feedback5 where userMail=?";
        ResultSet rs=null;
        Connection con=null;
        boolean result=false;
        PreparedStatement ps=null;

       if(session.equals("s3")){
        sql=sql1;
        track="track1";
        
       }
       else if(session.equals("s5")){
         sql=sql2;
         track="track2";
        
       }
       else if(session.equals("s7")){
        sql=sql3;
        track="track3";
        
       }
       else if(session.equals("s9")){
        sql=sql4;
        track="track4";
        
       }
       else{
        sql=sql5;
        track="track5";
        
       }



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
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Result is: "+result);
        return result;
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
            ec.redirect(ec.getRequestContextPath()+"/survey/"+path+"/feedback1.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Error while redirecting to current page..");
        }
   }
   
   public void insert(){
      
   Connection con=null;
   PreparedStatement ps=null;
   String sql;
   String laststayed;
   String ses;
   try
   {
    
            con=datasource.getConnection();
            con.setAutoCommit(false);
   String sql1 = "INSERT INTO feedback1 (userMail,q1,q2,q3,q4,q5,q6,q7,q8,q9,q10,q11,q12) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
   String sql2 = "INSERT INTO feedback2 (userMail,q1,q2,q3,q4,q5,q6,q7,q8,q9) values(?,?,?,?,?,?,?,?,?,?)";
   String sql3 = "INSERT INTO feedback3 (userMail,q1,q2,q3,q4,q5,q6,q7,q8,q9) values(?,?,?,?,?,?,?,?,?,?)";
   String sql4 = "INSERT INTO feedback4 (userMail,q1,q2,q3,q4,q5,q6,q7,q8,q9) values(?,?,?,?,?,?,?,?,?,?)";
   String sql5 = "INSERT INTO feedback5 (userMail,q1,q2,q3,q4,q5,q6,q7,q8,q9) values(?,?,?,?,?,?,?,?,?,?)";

          String update2="update users set laststayed=?,session=?,track_time=? where userMail=?"; 
          //String update2="update users set laststayed=?,session=?,time_control=? where userMail=?";
          String update="update users set laststayed=?,session=?,time_control=? where userMail=?";
          if(session.equals("s3")){
            sql=sql1;
            laststayed="login3ok";
            ses="s4";
            track="track1";
          }
          else if(session.equals("s5")){
            sql=sql2;
            laststayed="login5ok";
            ses="s6";
            track="track2";
          }
          else if(session.equals("s7")){
            sql=sql3;
            laststayed="login7ok";
            ses="s8";
            track="track3";
          }
          else if(session.equals("s9")){
            sql=sql4;
            laststayed="login9ok";
            ses="s10";
            track="track4";
          }
          else{
            sql=sql5;
            laststayed="login11ok";
            ses="s12";
            track="track5";
          }

         if(session.equals("s3")){

            ps=con.prepareStatement(sql);
            ps.setString(1,key);

            ps.setByte(2,Byte.parseByte(q1));
            ps.setString(3,q2);
            ps.setString(4,q3);

            ps.setByte(5,Byte.parseByte(q4));
            ps.setString(6,q5);
            ps.setString(7,q6);

            ps.setByte(8,Byte.parseByte(q7));
            ps.setString(9,q8);
            ps.setString(10,q9);
            
             ps.setByte(11,Byte.parseByte(q10));
            ps.setString(12,q11);
            ps.setString(13,q12);
          }
          else{

ps=con.prepareStatement(sql);
            ps.setString(1,key);

            ps.setByte(2,Byte.parseByte(q1));
            ps.setString(3,q2);
            ps.setString(4,q3);

            ps.setByte(5,Byte.parseByte(q4));
            ps.setString(6,q5);
            ps.setString(7,q6);

            ps.setByte(8,Byte.parseByte(q7));
            ps.setString(9,q8);
            ps.setString(10,q9);

          }

           ps.executeUpdate();
           /*

         if(session.equals("s11")){
              ps=con.prepareStatement(update2);
              ps.setString(1,laststayed);
            ps.setString(2,ses);
            dt=new DateTime();
            ps.setString(3,dt.toString());
            ps.setString(4,key);
            }
        else{
           ps=con.prepareStatement(update);
           ps.setString(1,laststayed);
            ps.setString(2,ses);
            dt=new DateTime();
            ps.setString(3,dt.toString());
            ps.setString(4,key);
          }*/

 /*
           ps=con.prepareStatement(update);
           ps.setString(1,laststayed);
            ps.setString(2,ses);
            ps.setString(3,key);
*/

if(session.equals("s3")){

                ps=con.prepareStatement(update2);
              ps.setString(1,laststayed);
            ps.setString(2,ses);
            dt=new DateTime();
            ps.setString(3,dt.toString());
            ps.setString(4,key);

              }
              else {
          ps=con.prepareStatement(update);
           ps.setString(1,laststayed);
            ps.setString(2,ses);
            ps.setString(3,new DateTime().toString());
            ps.setString(4,key);
}



            ps.executeUpdate();

            con.commit();

             Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Inserted..");
         
   }           
            
        catch (SQLException ex) {
        FacesContext.getCurrentInstance().addMessage(
                    "pretest-r-form:growl",
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Es ist ein Fehler aufgetreten",
                    "Bitte beantworten Sie alle Fragen."));  
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Error while inserting: " + ex.getErrorCode() + ex.getMessage() + ex.getCause());

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


  public void logout(){

FacesContext.getCurrentInstance().addMessage(
                    "r-form:growl",
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Info",
                    "Sie haben den Übungsdurchgang vollständig beendet." ));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);


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

   
   
   @PostConstruct
public void initialize(){

answers=new LinkedHashMap<>();

    
    for(byte i=1;i<=7;i++){
        answers.put(i,i);
    }
    
key=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
    s=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
       session=((String)s.getAttribute("session"));


 if(session.equals("s3")){
       
        track="track1";
        
       }
       else if(session.equals("s5")){
         
         track="track2";
        
       }
       else if(session.equals("s7")){
        
        track="track3";
        
       }
       else if(session.equals("s9")){
        
        track="track4";
        
       }
       else{
        
        track="track5";
        
}


     try {
            initialContext = new InitialContext();
            datasource = (DataSource)initialContext.lookup("java:jboss/datasources/MySQLDS");
        } catch (NamingException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Data source error..", ex);
        }




}        
     
     
}