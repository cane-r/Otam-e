 package  com.project.epsy.beans;


import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.activation.DataHandler;
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

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import java.io.IOException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;

import java.util.Properties;
import java.util.UUID;
import java.io.Serializable;




public class Resend implements Serializable{

    private String userMail;
    private boolean active=false;
    String token=null;
    private Context initialContext;
    private DataSource datasource;
    private ExternalContext ec;
    private boolean error;

public Resend(){

}



public String getUserMail(){
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }



    public boolean exists(){
        String sql="select active from users where userMail=?";
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
                if(rs.next()){
                 active=rs.getBoolean("active");
                 result=true;
                }
                return result;
                
        } catch (SQLException ex) {
            
            error=true;
            FacesContext.getCurrentInstance().addMessage("warn", new FacesMessage("Es ist ein Fehler aufgetreten"," Bitte versuchen Sie es erneut.")); //Error while processing.Please try Again
            
        } 
        
        finally{
            closeResultSet(rs);
            closeStatement(ps);
            closeConnection(con);
        }
        
        return result;
    }



 @PostConstruct
public void initialize(){
ec=FacesContext.getCurrentInstance().getExternalContext();

     try {
            initialContext = new InitialContext();
            datasource = (DataSource)initialContext.lookup("java:jboss/datasources/MySQLDS");
        } catch (NamingException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Data source error..", ex);
        }

} 




public String send(){
    String val=null;

boolean isExist=exists();

if(isExist && active){

    FacesContext.getCurrentInstance().addMessage("Warn", new FacesMessage("Hinweis","Sie haben Ihr Konto bereits aktiviert...")); //Your account is not activated yet.Check the mail adress you provided to activate your account..
 
    val="";
}

if(isExist && !active){
    token=getToken();
    boolean valid=updateToken();
    

    if(valid){
     sendMail();
     FacesContext.getCurrentInstance().addMessage("Warn", new FacesMessage("Hinweis","Eine Aktivierungsmail wurde Ihnen gesendet. Bitte prüfen Sie Ihren Posteingang."));
    
     val="";
 }

 else{

    FacesContext.getCurrentInstance().addMessage("Warn", new FacesMessage("Ein Fehler ist aufgetreten","Bitte erneut versuchen.."));
    
    val="";
 }

}

if(error){
  FacesContext.getCurrentInstance().addMessage("Warn", new FacesMessage("Ein Fehler ist aufgetreten","Bitte erneut versuchen.."));
  val="";
}

if(!isExist){
    FacesContext.getCurrentInstance().addMessage("Warn", new FacesMessage("Hinweis","Diese E-Mail existiert nicht.")); //Your account is not activated yet.Check the mail adress you provided to activate your account..
 return "";
}
return val;

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
         //MimeMessage message2=new MimeMessage(session);
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

         String html = "Klicken Sie folgenden <a href="+link+">link</a> um Ihr Konto zu aktivieren.";
         message.setContent(html, "text/html; charset=utf-8");
         // Now set the actual message
         //messageBodyPart.setText(html,"UTF-8","html");
         
         //messageBodyPart.setText(html);
         // Create a multipar message
         //Multipart multipart = new MimeMultipart();
       
         // Set text message part
         //multipart.addBodyPart(messageBodyPart);
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
         //message.setContent(multipart);

         // Send message
         Transport.send(message);

         System.out.println("Sent message successfully....");
  
      } catch (MessagingException e) {
         
      }


   }

   public String getToken(){

    String uuid=UUID.randomUUID().toString();
    StringBuilder bd=new StringBuilder();
    String [] id2=uuid.split("-");
      
       for(String id:id2){
           bd.append(id);
       }
       return bd.toString();
   }

   



   public boolean updateToken(){
    boolean result=false;
    Connection con = null;
    PreparedStatement ps = null;
  String sql="update users set token=? where userMail=?";

     try {  
             con=datasource.getConnection();
             con.setAutoCommit(false);
          
             ps=con.prepareStatement(sql);
                ps.setString(2, userMail);
                ps.setString(1, token);
                ps.executeUpdate();
                con.commit();
                result=true;
                
            } 

            catch (SQLException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE,"Sql exception : "+ ex.getMessage());
            FacesContext.getCurrentInstance().addMessage("warn", new FacesMessage("Es ist ein Fehler aufgetreten","Bitte versuchen Sie es erneut.")); //Error while processing.Please try Again
            result=false;
           try {
            if(con!=null){
            con.rollback();
            }

        } catch (SQLException ex1) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Rollback error..");
        }  
        }
           
           finally{
            closeStatement(ps);
            closeConnection(con);
        }
return result;


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




}