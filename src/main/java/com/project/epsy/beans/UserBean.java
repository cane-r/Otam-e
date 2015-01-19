/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  com.project.epsy.beans;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Map;
import java.sql.PreparedStatement;
import java.sql.Connection;
//import com.project.epsy.dao.DatabaseArgumentGrabber;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.LinkedHashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


import javax.servlet.http.HttpSession;

/*
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
*/
import javax.activation.DataHandler;
import javax.faces.context.ExternalContext;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.commons.codec.binary.Base64;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.joda.time.Duration;
import org.joda.time.DateTime;

/**
 *
 * @author Cuneyt
 */

public class UserBean implements Serializable {
 

    private String userMail;
    private String password;
    private String password2;
    private String department;
    private char sex;
    private String year;
    private Map<Integer,Integer> years;
    private Map<String,String> departments;
    private Map<String,Object> sexes;
    private boolean cert;
    private Context initialContext;
    private DataSource datasource;
    private javax.activation.DataSource mailDataSource;
    private Base64 base;
    private String token;

    
    /**
     * Creates a new instance of UserBean
     */
    public UserBean() {
       
    }
    
     public Map<Integer,Integer> getYears(){
        return years;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }
    
    public void setYears(Map arg){
        years=arg;
    }
    public String getYear(){
        return year;
    }
    public void setYear(String yearArg){
        year=yearArg;
    }
    
    public Map getSexes(){
        return sexes;
    }
    
    public String getUserMail() {
        return userMail;
    }

    public char getSex() {
        return sex;
    }

   public String getPassword() {
        return password;
    }
    
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }
    
    
    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    
    
    public void setCert(boolean cert) {
        this.cert = cert;
    }

    public boolean getCert() {
        return cert;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setDepartments(Map<String, String> departments) {
        this.departments = departments;
    }
    
    
    public String getDepartment() {
        return department;
    }

    public Map<String, String> getDepartments() {
        return departments;
    
    }



public void login(){
        String path=null;
        Connection con = null;
           PreparedStatement ps = null;
           ResultSet rs=null;
           String pass,salt;
           String laststayed=null;
           String session=null;
           boolean auth=false;
           boolean active=false;
           boolean exists=false;
           String time=null;
           String track=null;
           ExternalContext ec=FacesContext.getCurrentInstance().getExternalContext();
           String progress=null;
           try{
              
            con=datasource.getConnection();
            String sql = "select psssssrd,salt,active,laststayed,session,time_control,track_time from users where userMail=?";
            ps = con.prepareStatement(sql);
            ps.setString(1,userMail);
            rs=ps.executeQuery();
            if(rs.next()){
                exists=true;
                Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE,"Found with mail..");
                pass= rs.getString("psssssrd");
                salt = rs.getString("salt");
                active=rs.getBoolean("active");
                laststayed=rs.getString("laststayed");
                session=rs.getString("session");
                progress=getProgressFromSession(session);
                time=rs.getString("time_control");
                track=rs.getString("track_time");
                byte[] bDigest = base64ToByte(pass);//user password with pass+hash
                byte[] bSalt = base64ToByte(salt);//salt
                byte[] proposedDigest = getHash(1000, password, bSalt);//get hash from the password entered..

          
           auth=Arrays.equals(proposedDigest, bDigest);
           }
            
            if(!active && exists){

 Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE,"User account is not activated..");
 FacesContext.getCurrentInstance().addMessage("Warn", new FacesMessage("Hinweis","Ihr Konto ist noch nicht aktiviert. Bitte prüfen Sie Ihren Posteingang, ob Sie eine Aktivierungsmail erhalten haben. Wenn Sie keine E-Mail erhalten haben gehen Sie auf www.otame.de/resend.xhtml")); //Your account is not activated yet.Check the mail adress you provided to activate your account..
 ec.getFlash().setKeepMessages(true);
           ec.redirect(ec.getRequestContextPath());
           return;


            }
            
            if(auth && active && exists){
             Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE,"User authorized..");
           HttpSession s= (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
           
           Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE,"laststayed is " + laststayed);
            
            ///////////////////////////////////////////////////////////////////////////////////////////

           if(laststayed.equals("login1ok")) {

DateTime t1=new DateTime();
            DateTime t2=new DateTime(time);
            Duration d=new Duration(t2,t1);
            //d.getStandardHours()<7
            
            if(d.getStandardHours()<7){
               //Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE,"in login 1ok ..val is ." + d.getStandardHours());
              Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE,"in login 1ok ..val is ." + d.getStandardMinutes());
              String indicator="";
              String val="";
               Long left=7-d.getStandardHours();
               Long left2=60-d.getStandardMinutes();

               if(left>1){
                indicator="Stunden";
                val=String.valueOf(left);
               }

               else if(left==1){
                indicator="Stunde";
              val=String.valueOf(left);
               }
               
               else if(left<1){
                   
                   if(left2<2){
                indicator="Minute";
                val=String.valueOf(left2);   
               }

               else{
                indicator="Minuten";
                val=String.valueOf(left2);
               }
                
               }
               
                
              //FacesContext.getCurrentInstance().addMessage("r-form:growl",new FacesMessage(FacesMessage.SEVERITY_WARN,"Achtung..","Sie haben den Übungsdurchgang 1 vollständig abgeschlossen. Sie können sich erst wieder in "+ (2-d.getStandardMinutes()) +" "+indicator+" anmelden."));
             FacesContext.getCurrentInstance().addMessage("r-form:growl",new FacesMessage(FacesMessage.SEVERITY_WARN,"Achtung..","Sie haben den Übungsdurchgang 1 vollständig abgeschlossen. Sie können sich erst wieder in "+ val + " " + indicator + " anmelden."));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE,"messages added..redirecting.. .");
            //ec.invalidateSession();
          ec.redirect(ec.getRequestContextPath()+"/index.xhtml");
           //return;
            }

        else{

FacesContext.getCurrentInstance().addMessage("pretest-r-form:growl",new FacesMessage(FacesMessage.SEVERITY_INFO,"Info","Willkommen zum Übungsdurchgang 2."));
FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

           s.setAttribute("user",userMail);
           s.setAttribute("session",session);
           s.setAttribute("progress",progress);
           s.setAttribute("laststayed","/survey/track1/preemotions1.xhtml");

           ec.redirect(ec.getRequestContextPath()+"/survey/track1/preemotions1.xhtml");
           }

           }

           /////////////////////////////////////////////////////////////////////////////////////////////

          else if(laststayed.equals("login2ok")){
Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE,"in login2 ok");
DateTime t1=new DateTime();
            DateTime t2=new DateTime(time);
            Duration d=new Duration(t2,t1);
             //d.getStandardHours()<7
            
            if(d.getStandardHours()<7){
               String indicator="";
              String val="";
               Long left=7-d.getStandardHours();
               Long left2=60-d.getStandardMinutes();

               if(left>1){
                indicator="Stunden";
                val=String.valueOf(left);
               }

               else if(left==1){
                indicator="Stunde";
              val=String.valueOf(left);
               }
               
               else if(left<1){
                   
                   if(left2<2){
                indicator="Minute";
                val=String.valueOf(left2);   
               }

               else{
                indicator="Minuten";
                val=String.valueOf(left2);
               }
                
               }
               
               //FacesContext.getCurrentInstance().addMessage("r-form:growl",new FacesMessage(FacesMessage.SEVERITY_WARN,"Achtung..","Sie haben den Übungsdurchgang 2 vollständig abgeschlossen. Sie können sich erst wieder in "+ (2-d.getStandardMinutes()) +" "+indicator+" anmelden."));
            
              FacesContext.getCurrentInstance().addMessage("r-form:growl",new FacesMessage(FacesMessage.SEVERITY_WARN,"Achtung..","Sie haben den Übungsdurchgang 2 vollständig abgeschlossen. Sie können sich erst wieder in "+ val + " " + indicator + " anmelden."));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
           Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE,"messages added..redirecting.. .");
            //ec.invalidateSession();
             ec.redirect(ec.getRequestContextPath()+"/index.xhtml");
           //return;
            }

          else{
  FacesContext.getCurrentInstance().addMessage("pretrack:growl",new FacesMessage(FacesMessage.SEVERITY_INFO,"Info","Willkommen zum Übungsdurchgang 3."));
  FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
               s.setAttribute("user",userMail);
           s.setAttribute("session",session);
           s.setAttribute("progress",progress);
           s.setAttribute("laststayed","/survey/track1/audio1.xhtml");
               ec.redirect(ec.getRequestContextPath()+"/survey/track1/pretrack.xhtml");
}

           }


          /////////////////////////////////////////////////////////////////////////////////////////////////// 

            
else if(laststayed.equals("login3ok")){
Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE,"in login3 ok");
DateTime t1=new DateTime();
            DateTime t2=new DateTime(track);
            Duration d=new Duration(t2,t1);
             //d.getStandardDays()<2
            
           if(d.getStandardHours()<7){
               String indicator="";
              String val="";
               Long left=7-d.getStandardHours();
               Long left2=60-d.getStandardMinutes();

               if(left>1){
                indicator="Stunden";
                val=String.valueOf(left);
               }

               else if(left==1){
                indicator="Stunde";
              val=String.valueOf(left);
               }
               
               else if(left<1){
                   
                   if(left2<2){
                indicator="Minute";
                val=String.valueOf(left2);   
               }

               else{
                indicator="Minuten";
                val=String.valueOf(left2);
               }
                
               }
               

               FacesContext.getCurrentInstance().addMessage("r-form:growl",new FacesMessage(FacesMessage.SEVERITY_WARN,"Achtung..","Sie haben die Sitzung 1 vollständig abgeschlossen. Sie können sich erst wieder in "+ val + " " + indicator + " anmelden."));
            
               
              //FacesContext.getCurrentInstance().addMessage("r-form:growl",new FacesMessage(FacesMessage.SEVERITY_WARN,"Achtung..","Sie haben Session 3 vollständig abgeschlossen. Sie können sich erst wieder in "+ val + " " + indicator + " anmelden."));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
           Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE,"messages added..redirecting.. .");
            //ec.invalidateSession();
             ec.redirect(ec.getRequestContextPath()+"/index.xhtml");
           //return;
            }

          else{
  FacesContext.getCurrentInstance().addMessage("pretest-r-form:growl",new FacesMessage(FacesMessage.SEVERITY_INFO,"Info","Willkommen zum den Übungsdurchgang 1."));
  FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
               s.setAttribute("user",userMail);
           s.setAttribute("session",session);
           s.setAttribute("progress",progress);
           s.setAttribute("laststayed","/survey/track2/preemotions1.xhtml");
               ec.redirect(ec.getRequestContextPath()+"/survey/track2/preemotions1.xhtml");
}

           }



///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////







else if(laststayed.equals("login4ok")){
Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE,"in login4 ok");
DateTime t1=new DateTime();
            DateTime t2=new DateTime(time);
            Duration d=new Duration(t2,t1);
             //d.getStandardHours()<7
            
            if(d.getStandardHours()<7){
               String indicator="";
              String val="";
               Long left=7-d.getStandardHours();
               Long left2=60-d.getStandardMinutes();

               if(left>1){
                indicator="Stunden";
                val=String.valueOf(left);
               }

               else if(left==1){
                indicator="Stunde";
              val=String.valueOf(left);
               }
               
               else if(left<1){
                   
                   if(left2<2){
                indicator="Minute";
                val=String.valueOf(left2);   
               }

               else{
                indicator="Minuten";
                val=String.valueOf(left2);
               }
                
               }
               
               //FacesContext.getCurrentInstance().addMessage("r-form:growl",new FacesMessage(FacesMessage.SEVERITY_WARN,"Achtung..","Sie haben Session 4 vollständig abgeschlossen. Sie können sich erst wieder in "+ (2-d.getStandardMinutes()) +" minutes."));
            
              FacesContext.getCurrentInstance().addMessage("r-form:growl",new FacesMessage(FacesMessage.SEVERITY_WARN,"Achtung..","Sie haben den Übungsdurchgang 1 vollständig abgeschlossen. Sie können sich erst wieder in "+ val + " " + indicator + " anmelden."));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
           Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE,"messages added..redirecting.. .");
            //ec.invalidateSession();
             ec.redirect(ec.getRequestContextPath()+"/index.xhtml");
           //return;
            }

          else{
  FacesContext.getCurrentInstance().addMessage("pretrack:growl",new FacesMessage(FacesMessage.SEVERITY_INFO,"Info","Willkommen zum den Übungsdurchgang 2."));
  FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
               s.setAttribute("user",userMail);
           s.setAttribute("session",session);
           s.setAttribute("progress",progress);
           s.setAttribute("laststayed","/survey/track2/audio1.xhtml");
               ec.redirect(ec.getRequestContextPath()+"/survey/track2/pretrack.xhtml");
}

           }




//////////////////////////////////////////////////////////////////////////////////////////////////////////

else if(laststayed.equals("login5ok")){
Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE,"in login5 ok");
DateTime t1=new DateTime();
            DateTime t2=new DateTime(time);
            Duration d=new Duration(t2,t1);
             //d.getStandardDays()<2
            
            if(d.getStandardHours()<7){
               String indicator="";
              String val="";
               Long left=7-d.getStandardHours();
               Long left2=60-d.getStandardMinutes();

               if(left>1){
                indicator="Stunden";
                val=String.valueOf(left);
               }

               else if(left==1){
                indicator="Stunde";
              val=String.valueOf(left);
               }
               
               else if(left<1){
                   
                   if(left2<2){
                indicator="Minute";
                val=String.valueOf(left2);   
               }

               else{
                indicator="Minuten";
                val=String.valueOf(left2);
               }
                
               }
               

               FacesContext.getCurrentInstance().addMessage("r-form:growl",new FacesMessage(FacesMessage.SEVERITY_WARN,"Achtung..","Sie haben Sitzung 2 vollständig abgeschlossen. Sie können sich erst wieder in "+ val + " " + indicator + " anmelden."));
            
               
              //FacesContext.getCurrentInstance().addMessage("r-form:growl",new FacesMessage(FacesMessage.SEVERITY_WARN,"Achtung..","Sie haben Sitzung 3 vollständig abgeschlossen. Sie können sich erst wieder in "+ val + " " + indicator + " anmelden."));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
           Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE,"messages added..redirecting.. .");
            //ec.invalidateSession();
             ec.redirect(ec.getRequestContextPath()+"/index.xhtml");
           ///return;
            }

          else{
  FacesContext.getCurrentInstance().addMessage("pretest-r-form:growl",new FacesMessage(FacesMessage.SEVERITY_INFO,"Info","Willkommen zum Übungsdurchgang 1."));
  FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
               s.setAttribute("user",userMail);
           s.setAttribute("session",session);
           s.setAttribute("progress",progress);
           s.setAttribute("laststayed","/survey/track3/preemotions1.xhtml");
               ec.redirect(ec.getRequestContextPath()+"/survey/track3/preemotions1.xhtml");
}

           }



///////////////////////////////////////////////////////////////////



else if(laststayed.equals("login6ok")){
Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE,"in login6 ok");
DateTime t1=new DateTime();
            DateTime t2=new DateTime(time);
            Duration d=new Duration(t2,t1);
             //d.getStandardHours()<7
            
            if(d.getStandardHours()<7){
               String indicator="";
              String val="";
               Long left=7-d.getStandardHours();
               Long left2=60-d.getStandardMinutes();

               if(left>1){
                indicator="Stunden";
                val=String.valueOf(left);
               }

               else if(left==1){
                indicator="Stunde";
              val=String.valueOf(left);
               }
               
               else if(left<1){
                   
                   if(left2<2){
                indicator="Minute";
                val=String.valueOf(left2);   
               }

               else{
                indicator="Minuten";
                val=String.valueOf(left2);
               }
                
               }
               
               //FacesContext.getCurrentInstance().addMessage("r-form:growl",new FacesMessage(FacesMessage.SEVERITY_WARN,"Achtung..","Sie haben Session 6 vollständig abgeschlossen. Sie können sich erst wieder in "+ (2-d.getStandardMinutes()) +" minutes."));
            
              FacesContext.getCurrentInstance().addMessage("r-form:growl",new FacesMessage(FacesMessage.SEVERITY_WARN,"Achtung..","Sie haben den Übungsdurchgang 1 vollständig abgeschlossen. Sie können sich erst wieder in "+ val + " " + indicator + " anmelden."));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
           Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE,"messages added..redirecting.. .");
            //ec.invalidateSession();
             ec.redirect(ec.getRequestContextPath()+"/index.xhtml");
           //return;
            }

          else{
  FacesContext.getCurrentInstance().addMessage("pretrack:growl",new FacesMessage(FacesMessage.SEVERITY_INFO,"Info","Willkommen zum Übungsdurchgang 2."));
  FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
               s.setAttribute("user",userMail);
           s.setAttribute("session",session);
           s.setAttribute("progress",progress);
           s.setAttribute("laststayed","/survey/track3/audio1.xhtml");
               ec.redirect(ec.getRequestContextPath()+"/survey/track3/pretrack.xhtml");
}

           }




//////////////////////////here add a block (chat incomplete).////////////////////////////////////////////////////////////////////////////

           else if(laststayed.equals("login7ok")){
Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE,"in login7 ok");
DateTime t1=new DateTime();
            //DateTime t2=new DateTime(track);
            DateTime t2=new DateTime(time);
            Duration d=new Duration(t2,t1);
             //d.getStandardDays()<2
            if(d.getStandardHours()<7){
               String indicator="";
              String val="";
               Long left=7-d.getStandardHours();
               Long left2=60-d.getStandardMinutes();

               if(left>1){
                indicator="Stunden";
                val=String.valueOf(left);
               }

               else if(left==1){
                indicator="Stunde";
              val=String.valueOf(left);
               }
               
               else if(left<1){
                   
                   if(left2<2){
                indicator="Minute";
                val=String.valueOf(left2);   
               }

               else{
                indicator="Minuten";
                val=String.valueOf(left2);
               }
                
               }
               

               FacesContext.getCurrentInstance().addMessage("r-form:growl",new FacesMessage(FacesMessage.SEVERITY_WARN,"Achtung..","Sie haben Sitzung 3 vollständig abgeschlossen. Sie können sich erst wieder in "+ val + " " + indicator + " anmelden.."));
            
               
              //FacesContext.getCurrentInstance().addMessage("r-form:growl",new FacesMessage(FacesMessage.SEVERITY_WARN,"Achtung..","Sie haben Sitzung 3 vollständig abgeschlossen. Sie können sich erst wieder in "+ val + " " + indicator + " anmelden."));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
           Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE,"messages added..redirecting.. .");
            //ec.invalidateSession();
             ec.redirect(ec.getRequestContextPath()+"/index.xhtml");
           ///return;
            }

          else{
            
  FacesContext.getCurrentInstance().addMessage("pretest-r-form:growl",new FacesMessage(FacesMessage.SEVERITY_INFO,"Info","Willkommen zum Übungsdurchgang 1"));
  FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
               s.setAttribute("user",userMail);
           s.setAttribute("session",session);
           s.setAttribute("progress",progress);
           s.setAttribute("laststayed","/survey/track4/preemotions1.xhtml");
               ec.redirect(ec.getRequestContextPath()+"/survey/track4/preemotions1.xhtml");
               
/*
               FacesContext.getCurrentInstance().addMessage("pretest-r-form:growl",new FacesMessage(FacesMessage.SEVERITY_INFO,"Info","You cant login for now.."));
  FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            ec.redirect(ec.getRequestContextPath()+"/index.xhtml");
            */
}

           }


////////////////////////////////////////////////////////////////////////////

else if(laststayed.equals("login8ok")){
Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE,"in login8 ok");
DateTime t1=new DateTime();
            DateTime t2=new DateTime(time);
            Duration d=new Duration(t2,t1);
             //d.getStandardHours()<7
            
            if(d.getStandardHours()<7){
               String indicator="";
              String val="";
               Long left=7-d.getStandardHours();
               Long left2=60-d.getStandardMinutes();

               if(left>1){
                indicator="Stunden";
                val=String.valueOf(left);
               }

               else if(left==1){
                indicator="Stunde";
              val=String.valueOf(left);
               }
               
               else if(left<1){
                   
                   if(left2<2){
                indicator="Minute";
                val=String.valueOf(left2);   
               }

               else{
                indicator="Minuten";
                val=String.valueOf(left2);
               }
                
               }
               
               //FacesContext.getCurrentInstance().addMessage("r-form:growl",new FacesMessage(FacesMessage.SEVERITY_WARN,"Achtung..","Sie haben Session 8 vollständig abgeschlossen. Sie können sich erst wieder in "+ (2-d.getStandardMinutes()) +" minutes."));
            
              FacesContext.getCurrentInstance().addMessage("r-form:growl",new FacesMessage(FacesMessage.SEVERITY_WARN,"Achtung..","Sie haben den Übungsdurchgang 1 vollständig abgeschlossen. Sie können sich erst wieder in "+ val + " " + indicator + " anmelden."));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
           Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE,"messages added..redirecting.. .");
            //ec.invalidateSession();
             ec.redirect(ec.getRequestContextPath()+"/index.xhtml");
           //return;
            }

          else{
  FacesContext.getCurrentInstance().addMessage("pretrack:growl",new FacesMessage(FacesMessage.SEVERITY_INFO,"Info","Willkommen zum Übungsdurchgang 2."));
  FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
               s.setAttribute("user",userMail);
           s.setAttribute("session",session);
           s.setAttribute("progress",progress);
           s.setAttribute("laststayed","/survey/track4/audio1.xhtml");
               ec.redirect(ec.getRequestContextPath()+"/survey/track4/pretrack.xhtml");
}

           }




/////////////////////////////////////////////////////////////////////////////




           else if(laststayed.equals("login9ok")){
Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE,"in login9 ok");
DateTime t1=new DateTime();
            //DateTime t2=new DateTime(track);
            DateTime t2=new DateTime(time);
            Duration d=new Duration(t2,t1);
             //d.getStandardDays()<2
            
            if(d.getStandardHours()<7){
               String indicator="";
              String val="";
               Long left=7-d.getStandardHours();
               Long left2=60-d.getStandardMinutes();

               if(left>1){
                indicator="Stunden";
                val=String.valueOf(left);
               }

               else if(left==1){
                indicator="Stunde";
              val=String.valueOf(left);
               }
               
               else if(left<1){
                   
                   if(left2<2){
                indicator="Minute";
                val=String.valueOf(left2);   
               }

               else{
                indicator="Minuten";
                val=String.valueOf(left2);
               }
                
               }
               

               FacesContext.getCurrentInstance().addMessage("r-form:growl",new FacesMessage(FacesMessage.SEVERITY_WARN,"Achtung..","Sie haben Sitzung 4 vollständig abgeschlossen. Sie können sich erst wieder in "+ val + " " + indicator + " anmelden."));
            
               
              //FacesContext.getCurrentInstance().addMessage("r-form:growl",new FacesMessage(FacesMessage.SEVERITY_WARN,"Achtung..","Sie haben Session 3 vollständig abgeschlossen. Sie können sich erst wieder in "+ val + " " + indicator + " anmelden."));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
           Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE,"messages added..redirecting.. .");
            //ec.invalidateSession();
             ec.redirect(ec.getRequestContextPath()+"/index.xhtml");
           ///return;
            }

          else{
          
   FacesContext.getCurrentInstance().addMessage("pretest-r-form:growl",new FacesMessage(FacesMessage.SEVERITY_INFO,"Info","Willkommen zum Übungsdurchgang 1"));
  FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
               s.setAttribute("user",userMail);
           s.setAttribute("session",session);
           s.setAttribute("progress",progress);
           s.setAttribute("laststayed","/survey/track5/preemotions1.xhtml");
               ec.redirect(ec.getRequestContextPath()+"/survey/track5/preemotions1.xhtml");
              
              /*
              FacesContext.getCurrentInstance().addMessage("pretest-r-form:growl",new FacesMessage(FacesMessage.SEVERITY_INFO,"Info","You cant login for now.."));
  FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
  ec.redirect(ec.getRequestContextPath()+"/index.xhtml");
           */

}

           }

           else if(laststayed.equals("login10ok")){
Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE,"in login9 ok");
DateTime t1=new DateTime();
            //DateTime t2=new DateTime(track);
            DateTime t2=new DateTime(time);
            Duration d=new Duration(t2,t1);
             //d.getStandardDays()<2
            
            if(d.getStandardHours()<7){
               String indicator="";
              String val="";
               Long left=7-d.getStandardHours();
               Long left2=60-d.getStandardMinutes();

               if(left>1){
                indicator="Stunden";
                val=String.valueOf(left);
               }

               else if(left==1){
                indicator="Stunde";
              val=String.valueOf(left);
               }
               
               else if(left<1){
                   
                   if(left2<2){
                indicator="Minute";
                val=String.valueOf(left2);   
               }

               else{
                indicator="Minuten";
                val=String.valueOf(left2);
               }
                
               }
               

               FacesContext.getCurrentInstance().addMessage("r-form:growl",new FacesMessage(FacesMessage.SEVERITY_WARN,"Achtung..","Sie haben den Übungsdurchgang 1 vollständig abgeschlossen. Sie können sich erst wieder in "+ val + " " + indicator + " anmelden."));
            
               
              //FacesContext.getCurrentInstance().addMessage("r-form:growl",new FacesMessage(FacesMessage.SEVERITY_WARN,"Achtung..","Sie haben Session 3 vollständig abgeschlossen. Sie können sich erst wieder in "+ val + " " + indicator + " anmelden."));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
           Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE,"messages added..redirecting.. .");
            //ec.invalidateSession();
             ec.redirect(ec.getRequestContextPath()+"/index.xhtml");
           ///return;
            }

          else{
            
   FacesContext.getCurrentInstance().addMessage("pretrack:growl",new FacesMessage(FacesMessage.SEVERITY_INFO,"Info","Willkommen zum Übungsdurchgang 2"));
  FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
               s.setAttribute("user",userMail);
           s.setAttribute("session",session);
           s.setAttribute("progress",progress);
           s.setAttribute("laststayed","/survey/track5/audio1.xhtml");
           ec.redirect(ec.getRequestContextPath()+"/survey/track5/pretrack.xhtml");
             /*  
               FacesContext.getCurrentInstance().addMessage("pretest-r-form:growl",new FacesMessage(FacesMessage.SEVERITY_INFO,"Info","You cant login for now.."));
  FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
  ec.redirect(ec.getRequestContextPath()+"/index.xhtml");
           */

}

           }

           else if(laststayed.equals("login11ok")){

DateTime t1=new DateTime();
            //DateTime t2=new DateTime(track);
            DateTime t2=new DateTime(time);
            Duration d=new Duration(t2,t1);
             //d.getStandardDays()<2
            
            if(d.getStandardHours()<7){
               String indicator="";
              String val="";
               Long left=7-d.getStandardHours();
               Long left2=60-d.getStandardMinutes();

               if(left>1){
                indicator="Stunden";
                val=String.valueOf(left);
               }

               else if(left==1){
                indicator="Stunde";
              val=String.valueOf(left);
               }
               
               else if(left<1){
                   
                   if(left2<2){
                indicator="Minute";
                val=String.valueOf(left2);   
               }

               else{
                indicator="Minuten";
                val=String.valueOf(left2);
               }
                
               }
               

               FacesContext.getCurrentInstance().addMessage("r-form:growl",new FacesMessage(FacesMessage.SEVERITY_WARN,"Achtung..","Sie haben den Übungsdurchgang 2 vollständig abgeschlossen. Sie können sich erst wieder in "+ val + " " + indicator + " anmelden."));
            
               
              //FacesContext.getCurrentInstance().addMessage("r-form:growl",new FacesMessage(FacesMessage.SEVERITY_WARN,"Achtung..","Sie haben Session 3 vollständig abgeschlossen. Sie können sich erst wieder in "+ val + " " + indicator + " anmelden."));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
           Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE,"messages added..redirecting.. .");
            //ec.invalidateSession();
             ec.redirect(ec.getRequestContextPath()+"/index.xhtml");
           ///return;
            }

          else{
            
            FacesContext.getCurrentInstance().addMessage("pretrack:growl",new FacesMessage(FacesMessage.SEVERITY_INFO,"Info","Willkommen zum Übungsdurchgang 3"));
  
  FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
               s.setAttribute("user",userMail);
           s.setAttribute("session",session);
           s.setAttribute("progress",progress);
           s.setAttribute("laststayed","/survey/track5/audio1.xhtml");
               ec.redirect(ec.getRequestContextPath()+"/survey/track5/pretrack.xhtml");
               
               /*  
               FacesContext.getCurrentInstance().addMessage("pretest-r-form:growl",new FacesMessage(FacesMessage.SEVERITY_INFO,"Info","You can not login for now.."));
  FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
  ec.redirect(ec.getRequestContextPath()+"/index.xhtml");
           */
           

}

           }
               
           else if(laststayed.equals("login12ok")){


FacesContext.getCurrentInstance().addMessage("pretest-r-form:growl",new FacesMessage(FacesMessage.SEVERITY_INFO,"Info","Willkommen zum Posttest 2"));
  
  FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
               s.setAttribute("user",userMail);
           s.setAttribute("session","s13");//s13
           s.setAttribute("progress",progress);
           s.setAttribute("laststayed","/survey/track5/posttest/posttest.xhtml");
           
               ec.redirect(ec.getRequestContextPath()+"/survey/track5/posttest/posttest.xhtml");



           }
/////////////////finished../////////////////////////
           else if(laststayed.equals("login13ok")){

            FacesContext.getCurrentInstance().addMessage("r-form:growl",new FacesMessage(FacesMessage.SEVERITY_INFO,"Info","Sie haben bereits das OTAME-Programm vollständig beendet. Vielen Dank für Ihre Teilnahme."));

  FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
  ec.redirect(ec.getRequestContextPath()+"/index.xhtml");

           }
















             else{
              s.setAttribute("user",userMail);
           //s.setAttribute("laststayed",laststayed);
           s.setAttribute("session",session);
           s.setAttribute("progress",progress);
           s.setAttribute("laststayed",laststayed);
           ec.redirect(ec.getRequestContextPath()+laststayed);
           }
             
      }
            
            else
            {
           Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE,"Mail or pass is not valid...");
           FacesContext.getCurrentInstance().addMessage("Warn", new FacesMessage("Hinweis","Email Adresse oder Passwort sind nicht gültig...")); //Mail or password is not valid...
           ec.getFlash().setKeepMessages(true);
           ec.redirect(ec.getRequestContextPath());
           //return;
          
            }//end
            
           
           }//end try

       catch(IOException e){
        Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE,"IO error..");
            FacesContext.getCurrentInstance().addMessage("Warn", new FacesMessage("Ein Fehler ist aufgetreten","Bitte versuchen Sie es erneut..")); //Error.Please try again..
             ec.getFlash().setKeepMessages(true);
             try{
                 if(ec!=null){
             ec.redirect(ec.getRequestContextPath());
           }
           }
             catch (IOException ex2) {
            Logger.getLogger(SessionCheck.class.getName()).log(Level.SEVERE, null, ex2);
        }
           
       } 

       catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE,"Algo error..");
            FacesContext.getCurrentInstance().addMessage("Warn", new FacesMessage("Ein Fehler ist aufgetreten","Bitte versuchen Sie es erneut..")); //Error.Please try again..
             ec.getFlash().setKeepMessages(true);
             try{
                 if(ec!=null){
             ec.redirect(ec.getRequestContextPath());
           }
           }
             catch (IOException ex2) {
            Logger.getLogger(SessionCheck.class.getName()).log(Level.SEVERE, null, ex2);
        }
        } 

        catch (SQLException ex) {
            FacesContext.getCurrentInstance().addMessage("Warn", new FacesMessage("Ein Fehler ist aufgetreten","Bitte versuchen Sie es erneut..")); //Error.Please try again..
            ec.getFlash().setKeepMessages(true);
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, "Sql exception" + ex.getCause() + " : " + ex.getMessage());
             try{

             if(ec!=null){
             ec.redirect(ec.getRequestContextPath());
           }
}
             catch (IOException ex2) {
            Logger.getLogger(SessionCheck.class.getName()).log(Level.SEVERE, null, ex2);
        }
        }
           finally{
               closeConnection(con);
               closeStatement(ps);
               closeResultSet(rs);
           }
         
       
    }




public String register(){
        boolean isExists=exists();
        if(isExists){
          //here ask if you forget password go to like .../recovery.xhtml
            FacesContext.getCurrentInstance().addMessage("Warn", new FacesMessage("Hinweis","Es existiert bereits ein Benutzer mit dieser Email. Wenn Sie Ihr Passwort vergessen haben, gehen sie auf folgende Seite www.otame.de/recovery.xhtml")); //A user with this mail is already registered.If you forget your password,proceed to www.otame.de/recovery.xhtml
             return "";
        }

        else if(password.length()<5 || password.length()>12){
          FacesContext.getCurrentInstance().addMessage("Warn", new FacesMessage("Hinweis","Passwörter müssen mindestens 5 und maximal 12 Zeichen haben und dürfen keine Leerzeichen enthalten")); 
             return  "";
        }

        else if(!password.equals(password2)){
          FacesContext.getCurrentInstance().addMessage("Warn", new FacesMessage("Hinweis","Passwörter stimmen nicht überein")); 
             return "";
        }

        else if(sex==' ') {
         FacesContext.getCurrentInstance().addMessage("Warn", new FacesMessage("Hinweis","Bitte geben Sie Ihr Geschlecht an")); //A user with this mail is already registered.If you forget your password,proceed to www.otame.de/recovery.xhtml
             return "";

        }

        else if(department==null || department.equals(" ") || department.equals("")){
FacesContext.getCurrentInstance().addMessage("Warn", new FacesMessage("Hinweis","Bitte geben Sie Ihr Studienfach an")); //A user with this mail is already registered.If you forget your password,proceed to www.otame.de/recovery.xhtml
             return "";
        }

        else if(year.equals("") || year.equals(" ") || year==null){
FacesContext.getCurrentInstance().addMessage("Warn", new FacesMessage("Hinweis","Bitte geben Sie Ihr Geburtsjahr an")); //A user with this mail is already registered.If you forget your password,proceed to www.otame.de/recovery.xhtml
             return "";
        }
        else{

        addUser();
        Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE,"User added..");

        sendMail(); 

        Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE,"Mail sent...");
        /*
           HttpSession s= (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
           s.setAttribute("user",userMail);
           //s.setMaxInactiveInterval(1000*60*65);
           //sendMail();
            return "pretest";
            */

            FacesContext.getCurrentInstance().addMessage("Warn", new FacesMessage("Registrierung","Prüfen Sie Ihr Email Postfach, ob Sie eine Aktivierungslink erhalten haben.")); //Check your mail adress to activate your account.
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE,"Message...");
            return null;
          }
    }



public void addUser(){
        Connection con = null;
           PreparedStatement ps = null;
           
        
         try {
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
               // Salt generation 64 bits long
               byte[] bSalt = new byte[8];
               random.nextBytes(bSalt);
               // Digest computation
               byte[] bDigest = getHash(1000,password,bSalt);
               String pass = byteToBase64(bDigest);
               String salt = byteToBase64(bSalt);
               
             //Class.forName("com.mysql.jdbc.Driver");
            //con=DriverManager.getConnection("jdbc:mysql://mysql21380-epsyapp.appengine.flow.ch/epsydb?useUnicode=yes&characterEncoding=UTF-8","root","GXOvii22145");
            //con=DriverManager.getConnection("jdbc:mysql://localhost:3306/epsydb?zeroDateTimeBehavior=convertToNull","root","19888891");
               con=datasource.getConnection();
               con.setAutoCommit(false);
            //String sql = "INSERT INTO users(userMail,department,sex,age,cert) VALUES(?,?,?,?,?)";
            String sql = "INSERT INTO users(userMail,psssssrd,salt,department,sex,age,cert,token,active,laststayed,session,time_control,track_time) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1,userMail);  
            ps.setString(2,pass);
            ps.setString(3,salt);
            ps.setString(4,department);
            ps.setString(5,Character.toString(sex)); 
                int age=(Calendar.getInstance().get(Calendar.YEAR)-Integer.parseInt(year));
                byte b=(byte)age;
            ps.setByte(6,b);
            ps.setBoolean(7,cert);
            ps.setString(8,token);
            ps.setBoolean(9,false);
            ps.setString(10,"/survey/welcome.xhtml");
            ps.setString(11,"s1");
            DateTime d=new DateTime();
            ps.setString(12,d.toString());
            ps.setString(13,d.toString());
                Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE,"Executing sql command");
            ps.executeUpdate();
            con.commit();
                    
            
        } catch (SQLException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, "Sql Error : {0}", ex.getCause());
            FacesContext.getCurrentInstance().addMessage("Warn", new FacesMessage("Hinweis","Bitte erneut versuchen.")); //Try again.

              if(con!=null){

                   try {
                       con.rollback();
                   } catch (SQLException ex1) {
                       Logger.getLogger(PreTest.class.getName()).log(Level.SEVERE, null, ex1);
                   }
               }
               redirect();
            
        

           
        }
       catch (NullPointerException ex) {
            
            FacesContext.getCurrentInstance().addMessage("Warn", new FacesMessage("Hinweis","Bitte erneut versuchen.")); //Try again.
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE,"npe.");  
            redirect();
        } catch (NoSuchAlgorithmException ex) {
            
            FacesContext.getCurrentInstance().addMessage("Warn", new FacesMessage("Hinweis","Bitte erneut versuchen.")); //Try again.
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE,"Algorithm error..");
            redirect();
        }
          finally{
            closeStatement(ps);
            closeConnection(con);
        }
        
    }

    
    public void updateUser(){
        
         FacesContext.getCurrentInstance().addMessage("Warn", new FacesMessage("Willkommen.","bei OTAME")); //Welcome again
            HttpSession s= (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
           String sql="update users set department=?,sex=?,age=?,cert=? where userMail=?";
           Connection con = null;

           PreparedStatement ps = null;
           try {  
               //Class.forName("com.mysql.jdbc.Driver");
           //con=DriverManager.getConnection("jdbc:mysql://mysql21380-epsyapp.appengine.flow.ch/epsydb?useUnicode=yes&characterEncoding=UTF-8","root","GXOvii22145");
             //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/epsydb?zeroDateTimeBehavior=convertToNull","root","19888891");
             con=datasource.getConnection();

          
             ps=con.prepareStatement(sql);
                ps.setString(5, userMail);
                ps.setString(1, department);
                ps.setString(2,String.valueOf(sex));
                int age=(Calendar.getInstance().get(Calendar.YEAR)-Integer.parseInt(year));
                byte a=(byte)age;
                ps.setByte(3,a);  
                ps.setBoolean(4, cert);
                ps.executeUpdate();
            } 

            catch (SQLException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE,"Sql exception : "+ ex.getMessage());
            FacesContext.getCurrentInstance().addMessage("warn", new FacesMessage("Es ist ein Fehler aufgetreten","Bitte versuchen Sie es erneut.")); //Error while processing.Please try Again
            
            //redirect();
        }
           
           finally{
            closeStatement(ps);
            closeConnection(con);
        }
   
}


public static String getProgressFromSession(String arg){
        String track=null;
        if(arg.equals("s1")|| arg.equals("s2") || arg.equals("s3") ){
            track="Sitzung 1/Session ";
            return track+arg.substring(1)+"/3";
        }
        else if(arg.equals("s4") || arg.equals("s5")){
            track="Sitzung 2/Session ";
            return track+(Integer.parseInt(arg.substring(1))-3)+"/2";
        }
        else if(arg.equals("s6") || arg.equals("s7")){
            track="Sitzung 3/Session ";
            return track+(Integer.parseInt(arg.substring(1))-5)+"/2";
        }
        else if(arg.equals("s8") || arg.equals("s9")){
            track="Sitzung 4/Session ";
            return track+(Integer.parseInt(arg.substring(1))-7)+"/2";
        }
        else {
            track="Sitzung 5/Session ";
            return track+(Integer.parseInt(arg.substring(1))-9)+"/4";
        }
    }



    
    public boolean exists(){
        String sql="select userMail from users where userMail=?";
        ResultSet rs=null;
        Connection con=null;
        boolean result=false;
        PreparedStatement ps=null;
        try {  
             //Class.forName("com.mysql.jdbc.Driver");
             //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/epsydb?zeroDateTimeBehavior=convertToNull","root","19888891");
             con=datasource.getConnection();
             ps=con.prepareStatement(sql);
                ps.setString(1, userMail);
                rs=ps.executeQuery();
                result=rs.next();
                return result;
                
        } catch (SQLException ex) {
            
            
            FacesContext.getCurrentInstance().addMessage("warn", new FacesMessage("Es ist ein Fehler aufgetreten.","Bitte versuchen Sie es erneut.")); //Error while processing.Please try Again
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null,"Db error");
            redirect();
        } 
        
        finally{
            closeResultSet(rs);
            closeStatement(ps);
            closeConnection(con);
        }
        
        return result;
    }


public byte[] getHash(int iterationNb, String password, byte[] salt) throws NoSuchAlgorithmException {
       MessageDigest digest = MessageDigest.getInstance("SHA-1");
       digest.reset();
       digest.update(salt);
       byte[] input=null;
        try {
            input = digest.digest(password.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
       for (int i = 0; i < iterationNb; i++) {
           digest.reset();
           input = digest.digest(input);
       }
       return input;
   }



  private byte[] base64ToByte(String data) throws IOException {
       return base.decode(data);
   }
 
   private String byteToBase64(byte[] data){
       return base.encodeAsString(data);
   }


    
   public void closeConnection(Connection c){
       if(c!=null){
        try {
           c.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE,"Error while closing connection");
        }
       }
       
   }
   
   public void closeStatement(PreparedStatement ps){
       if(ps!=null){
        try {
           ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE,"Error while closing statement");
        }
       }
       
   }
   
   public void closeResultSet(ResultSet rs){
        if(rs!=null){
        try {
           rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE,"Error while closing resulset");
        }
       }
   }
   
   public void redirect(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE,"Error while redirecting to start.xhtml");
        }
   }
   
   
@PostConstruct
public void initialize() {
years=new LinkedHashMap<>();
    sexes=new LinkedHashMap<>();
    departments=new LinkedHashMap<>();
    base=new Base64();
    
    for(int i=1900;i<2016;i++){
            years.put(i,i);
        }
    
    sexes.put("Männlich",'m');
    sexes.put("Weiblich",'w');
    sexes.put("Andere",'a');
    sex='m';
    try {
            initialContext = new InitialContext();
            datasource = (DataSource)initialContext.lookup("java:jboss/datasources/MySQLDS");
        } catch (NamingException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE,"Data source error..", ex);
        }    
    String uuid=UUID.randomUUID().toString();

    StringBuilder bd=new StringBuilder();
    String [] id2=uuid.split("-");
      
       for(String id:id2){
           bd.append(id);
       }
       token=bd.toString();


    //departments..
    
    departments.put("Chemie(LA Grundschule/Unterrichtsfach)","Chemie(LA Grundschule/Unterrichtsfach)");
    departments.put("Chemie(LA Gymnasium/Unterrichtsfach)","Chemie(LA Gymnasium/Unterrichtsfach)");
    departments.put("Chemie(LA Mittelschule/Unterrichtsfach)","Chemie(LA Mittelschule/Unterrichtsfach)");
    departments.put("Chemie(LA Realschule/Unterrichtsfach)","Chemie(LA Realschule/Unterrichtsfach)");
    
    departments.put("Deutsch(LA Grundschule/Unterrichtsfach)","Deutsch(LA Grundschule/Unterrichtsfach)");
    departments.put("Deutsch(LA Gymnasium/Unterrichtsfach)","Deutsch(LA Gymnasium/Unterrichtsfach)");
    departments.put("Deutsch(LA Mittelschule/Unterrichtsfach)","Deutsch(LA Mittelschule/Unterrichtsfach)");
    departments.put("Deutsch(LA Realschule/Unterrichtsfach)","Deutsch(LA Realschule/Unterrichtsfach)");
    
    departments.put("Didaktik des Deutschen als Zweitsprache(Lehramt Grundschule/Unterrichtsfach)","Didaktik des Deutschen als Zweitsprache (Lehramt Grundschule/ Unterrichtsfach)");
    departments.put("Didaktik des Deutschen als Zweitsprache(Lehramt Mittelschule/Unterrichtsfach)","Didaktik des Deutschen als Zweitsprache (Lehramt Mittelschule/Unterrichtsfach))");
    
    departments.put("Evangelische Religionslehre(LA Grundschule/Unterrichtsfach)","Evangelische Religionslehre (LA Grundschule/Unterrichtsfach)");
    departments.put("Evangelische Religionslehre(LA Gymnasium/Unterrichtsfach)","Evangelische Religionslehre (LA Gymnasium/Unterrichtsfach)");
    departments.put("Evangelische Religionslehre(LA Mittelschule/Unterrichtsfach)","Evangelische Religionslehre (LA Mittelschule/Unterrichtsfach)");
    departments.put("Evangelische Religionslehre(LA Realschule/Unterrichtsfach)","Evangelische Religionslehre (LA Realschule/Unterrichtsfach)");
    
    departments.put("Französisch(LA Gymnasium/Unterrichtsfach)","Französisch(LA Gymnasium/Unterrichtsfach)");
    departments.put("Französisch(LA Realschulen/Unterrichtsfach)","Französisch(LA Realschulen/Unterrichtsfach)");
    
    departments.put("Geistigbehindertenbädagok(LA für Sonderpädagogik (GS)/Unterrichtsfach)","Geistigbehindertenbädagok(LA für Sonderpädagogik (GS)/Unterrichtsfach)");
    departments.put("Geistigbehindertenpädagogik(LA für Sonderpädagogik (MS)/Unterrichtsfach)","Geistigbehindertenpädagogik(LA für Sonderpädagogik (MS)/Unterrichtsfach)");
    
    departments.put("Geographie(LA Grundschule/Unterrichtsfach)","Geographie(LA Grundschule/Unterrichtsfach)");
    departments.put("Geographie(LA Gymnasium/Unterrichtsfach)","Geographie(LA Gymnasium/Unterrichtsfach)");
    departments.put("Geographie(LA Mittelschule/Unterrichtsfach)","Geographie(LA Mittelschule/Unterrichtsfach)");
    departments.put("Geographie(LA Realschule/Unterrichtsfach)","Geographie(LA Realschule/Unterrichtsfach)");
    
    departments.put("Geschichte(LA Grundschule/Unterrichtsfach)","Geschichte(LA Grundschule/Unterrichtsfach)");
    departments.put("Geschichte(LA Gymnasium/Unterrichtsfach)","Geschichte(LA Gymnasium/Unterrichtsfach)");
    departments.put("Geschichte(LA Mittelschule/Unterrichtsfach)","Geschichte(LA Mittelschule/Unterrichtsfach)");
    departments.put("Geschichte(LA Realschule/Unterrichtsfach)","Geschichte(LA Realschule/Unterrichtsfach)");
    
    departments.put("Griechisch (LA Gymnasium/Unterrichtsfach)","Griechisch (LA Gymnasium/Unterrichtsfach)");
   
    departments.put("Grundschuldidaktik(LA Grundschule/Didaktik/Unterrichtsfach)","Grundschuldidaktik(LA Grundschule/Didaktik/Unterrichtsfach)");
    departments.put("Grundschuldidaktik(LA für Sonderpädagogik (GS) mit Grundschuldidaktik/Unterrichtsfach","Grundschuldidaktik(LA für Sonderpädagogik (GS) mit Grundschuldidaktik/Unterrichtsfach");
    
    departments.put("Informatik(LA Gymnasium/Unterrichtsfach)","Informatik (LA Gymnasium/Unterrichtsfach)");
    departments.put("Informatik(LA Realschule/Unterrichtsfach)","Informatik (LA Realschule/Unterrichtsfach)");
   
    departments.put("Italienisch(LA Gymnasium/ Unterrichtsfach)","Italienisch (LA Gymnasium/ Unterrichtsfach)");

    departments.put("Katholische Theologie(LA Grundschule/Unterrichtsfach)","Katholische Theologie(LA Grundschule/Unterrichtsfach)");
    departments.put("Katholische Theologie(LA Gymnasium/Unterrichtsfach)","Katholische Theologie(LA Gymnasium/Unterrichtsfach)");
    departments.put("Katholische Theologie(LA Mittelschule/Unterrichtsfach)","Katholische Theologie(LA Mittelschule/Unterrichtsfach)");
    departments.put("Katholische Theologie(LA Realschule/Unterrichtsfach)","Katholische Theologie(LA Realschule/Unterrichtsfach)");
    
    departments.put("Kunst(LA Grundschule/Unterrichtsfach)","Kunst(LA Grundschule/Unterrichtsfach)");
    departments.put("Kunst(LA Gymnasium/Unterrichtsfach)","Kunst(LA Gymnasium/Unterrichtsfach)");
    departments.put("Kunst(LA Mittelschule/Unterrichtsfach)","Kunst(LA Mittelschule/Unterrichtsfach)");
    departments.put("Kunst(LA Realschule/Unterrichtsfach)","Kunst(LA Realschule/Unterrichtsfach)");
    
    departments.put("Latein(LA Gymnasium/Unterrichtsfach)","Latein(LA Gymnasium/Unterrichtsfach)");
    
    departments.put("Lehrnbehindertenpädagogik(LA für Sonderpädagogik(GS)/Unterrichtsfach)","Lehrnbehindertenpädagogik(LA für Sonderpädagogik(GS)/Unterrichtsfach)");
    departments.put("Lehrnbehindertenpädagogik(LA für Sonderpädagogik (MS)/Unterrichtsfach)","Lehrnbehindertenpädagogik(LA für Sonderpädagogik (MS)/Unterrichtsfach)");
    
    departments.put("Mathematik(LA Grundschule/Unterrichtsfach)","Mathematik(LA Grundschule/Unterrichtsfach)");
    departments.put("Mathematik(LA Gymnasium/Unterrichtsfach)","Mathematik(LA Gymnasium/Unterrichtsfach)");
    departments.put("Mathematik(LA Mittelschule/Unterrichtsfach)","Mathematik(LA Mittelschule/Unterrichtsfach)");
    departments.put("Mathematik(LA Realschule/Unterrichtsfach)","Mathematik(LA Realschule/Unterrichtsfach)");
    
    departments.put("Mittelschuldidaktik(LA für Mittelschule/Didaktik/Unterrichtsfach)","Mittelschuldidaktik(LA für Mittelschule/Didaktik/Unterrichtsfach)");
    departments.put("Mittelschuldidaktik(LA für Sonderpädagogik(MS)/Unterrichtsfach)","Mittelschuldidaktik(LA für Sonderpädagogik(MS)/Unterrichtsfach)");
    
    departments.put("Musik(LA Grundschule/Unterrichtsfach)","Musik(LA Grundschule/Unterrichtsfach)");
    departments.put("Musik(LA Gymnasium/Unterrichtsfach)","Musik LA Gymnasium/Unterrichtsfach)");
    departments.put("Musik(LA Mittelschule/Unterrichtsfach)","Musik(LA Mittelschule/Unterrichtsfach)");
    departments.put("Musik(LA Realschule/Unterrichtsfach)","Musik(LA Realschule/Unterrichtsfach)");
    
    departments.put("Pädagogik für Verhaltensstörungen(LA für Sonderpädagogik(GS)/Unterrichtsfach)","Pädagogik für Verhaltensstörungen(LA für Sonderpädagogik(GS)/Unterrichtsfach)");
    departments.put("Pädagogik für Verhaltensstörung(LA für Sonderpädagogik(MS)/Unterrichtsfach)","Pädagogik für Verhaltensstörung(LA für Sonderpädagogik(MS)/Unterrichtsfach)");
    
    departments.put("Physik(LA Grundschule/Unterrichtsfach)","Physik(LA Grundschule/Unterrichtsfach)");
    departments.put("Physik(LA Gymnasium/Unterrichtsfach)","Physik LA Gymnasium/Unterrichtsfach)");
    departments.put("Physik(LA Mittelschule/Unterrichtsfach)","Physik(LA Mittelschule/Unterrichtsfach)");
    departments.put("Physik(LA Realschule/Unterrichtsfach)","Physik(LA Realschule/Unterrichtsfach)");
    
    departments.put("Schulpsychologie(LA Grundschule/Unterrichtsfach)","Schulpsychologie(LA Grundschule/Unterrichtsfach)");
    departments.put("Schulpsychologie(LA Gymnasium/Unterrichtsfach)","Schulpsychologie LA Gymnasium/Unterrichtsfach)");
    departments.put("Schulpsychologie(LA Mittelschule/Unterrichtsfach)","Schulpsychologie(LA Mittelschule/Unterrichtsfach)");
    departments.put("Schulpsychologie(LA Realschule/Unterrichtsfach)","Schulpsychologie(LA Realschule/Unterrichtsfach)");
    
    departments.put("Russisch (LA Gymnasium/Unterrichtsfach)","Russisch (LA Gymnasium/Unterrichtsfach)");
    
    departments.put("Sozialkunde(LA Grundschule/Unterrichtsfach)","Sozialkunde(LA Grundschule/Unterrichtsfach)");
    departments.put("Sozialkunde(LA Gymnasium/Unterrichtsfach)","Sozialkunde LA Gymnasium/Unterrichtsfach)");
    departments.put("Sozialkunde(LA Mittelschule/Unterrichtsfach)","Sozialkunde(LA Mittelschule/Unterrichtsfach)");
    departments.put("Sozialkunde(LA Realschule/Unterrichtsfach)","Sozialkunde(LA Realschule/Unterrichtsfach)");
    
    departments.put("Spanisch(LA Gymnasium/ Unterrichtsfach)","Spanisch(LA Gymnasium/ Unterrichtsfach)");
    
    departments.put("Sprachheilpädagogi(LA für Sonderpädagogik(GS)/Unterrichtsfach)","Sprachheilpädagogi(LA für Sonderpädagogik(GS)/Unterrichtsfach)");
    departments.put("Sprachheilpädagogi(LA für Sonderpädagogik(MS)/Unterrichtsfach)","Sprachheilpädagogi(LA für Sonderpädagogik(MS)/Unterrichtsfach)");
    
    departments.put("Wirtschaftswissenschaft (LA Gymnasium/Unterrichtsfach)","Wirtschaftswissenschaft (LA Gymnasium/Unterrichtsfach)");
    departments.put("Wirtschaftswissenschaft (LA Gymnasium/Unterrichtsfach)","Wirtschaftswissenschaft (LA Gymnasium/Unterrichtsfach)");
    
    
    departments.put("Sonstiges","Sonstiges");
    
    
}



public void sendMail(){
        
         // Recipient's email ID needs to be mentioned.
      String to = userMail;

      // Sender's email ID needs to be mentioned
      //String from = "donotreply@otame.de";
      String from = "info@otame.de";
      final String username = "default@otame.de";
      //final String username = "canersir";//change accordingly
      //final String password = "@Caner19888891@";//change accordingly
      final String password="ma$otame!";
      //final String username="meltem.yildiz@campus.lmu.de";
      //final String password="tXojLWb2";

      //String host = "smtp.gmail.com";
      String host = "smtp.variomedia.de";
      //String host = "pro.turbosmtp.com";
      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.port", "587");

      // Get the Session object.
      Session session = Session.getInstance(props,
         new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(username, password);
            }
         });

      try {
         // Create a default MimeMessage object.
         Message message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(to));

         // Set Subject: header field
         message.setSubject("OTAME Aktivierungsmail");

         // Create the message part
         BodyPart messageBodyPart = new MimeBodyPart();
         String link="https://www.otame.de/activate.xhtml?mail="+userMail+"&token="+token;

         //String link="http://onlinetool2-redhatappv2.rhcloud.com/activate.xhtml?mail="+userMail+"&token="+token;
         String html = "Danke für die Registrierung bei OTAME. Klicken Sie auf folgenden <a href="+link+">link</a> um Ihr Konto zu aktivieren.";

         // Now set the actual message
         //messageBodyPart.setText(html,"UTF-8","html");
         messageBodyPart.setText(html);
         // set HTML Part
         messageBodyPart.setContent(html,"text/html; charset=utf-8");
         
         // Create a multipar message
         Multipart multipart = new MimeMultipart();

         // Set text message part
         multipart.addBodyPart(messageBodyPart);
/*
         // Part two is attachment
         messageBodyPart = new MimeBodyPart();
         String filename = "output.docx";
         DataSource source = new FileDataSource(filename);
         messageBodyPart.setDataHandler(new DataHandler(source));
         messageBodyPart.setFileName(filename);
         multipart.addBodyPart(messageBodyPart);
*/
         // Send the complete message parts
         message.setContent(multipart);

         // Send message
         Transport.send(message);

  
      } catch (MessagingException e) {
         throw new RuntimeException(e);
      }
   }
   
    
}

