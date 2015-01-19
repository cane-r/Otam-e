package  com.project.epsy.beans;


import java.io.Serializable;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.bean.ManagedProperty;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
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
import java.util.Properties;



public class Recovery implements Serializable {



private String mail;
private Context initialContext;
private DataSource datasource;
private String token;
private boolean active=false;
private boolean exists=false;
private boolean error=false;


    

    

public Recovery(){

}


    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }



@PostConstruct
    public void init() {

    	


      try {
            initialContext = new InitialContext();
            datasource = (DataSource)initialContext.lookup("java:jboss/datasources/MySQLDS");
        } catch (NamingException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Data source error..", ex);
        }

        
        

}


public String newToken(){

  String uuid=UUID.randomUUID().toString();

        StringBuilder bd=new StringBuilder();
        String [] id2=uuid.split("-");
      
       for(String id:id2){
           bd.append(id);
       }
       //new token..
       return bd.toString();
}


public void closeConnection(Connection c){
       if(c!=null){
        try {
           c.close();
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Error while closing connection");
        }
       }
       
   }
   
   public void closeStatement(PreparedStatement ps){
       if(ps!=null){
        try {
           ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Error while closing statement");
        }
       }
       
   }
   
   public void closeResultSet(ResultSet rs){
        if(rs!=null){
        try {
           rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Error while closing resulset");
        }
       }
   }


    public void exists(){
        String sql="select active from users where userMail=?";
        ResultSet rs=null;
        Connection con=null;
        PreparedStatement ps=null;
        try {  
             con=datasource.getConnection();
             ps=con.prepareStatement(sql);
                ps.setString(1,mail);
                rs=ps.executeQuery();
               if(rs.next()){
                exists=true;
                active=rs.getBoolean("active");
               }
                
                
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null,"Db error");
            FacesContext.getCurrentInstance().addMessage("warn", new FacesMessage("Ein Fehler ist aufgetreten","Bitte versuchen Sie es erneut..")); //Error while processing.Please try Again..
            error=true;
            
        } 
        
        finally{
            closeResultSet(rs);
            closeStatement(ps);
            closeConnection(con);
        }
        
    }


  public boolean updateToken(){
       String sql="update users set token=? where userMail=?";
      PreparedStatement ps=null;
      Connection con=null;
      int c=0;
      boolean result=false;
       token=newToken();
try{
      con=datasource.getConnection();
      con.setAutoCommit(false);
  ps=con.prepareStatement(sql);
  ps.setString(1,token);
  ps.setString(2,mail);
  c=ps.executeUpdate();
  con.commit();
  if(c==1){
    result=true;
  }
}

    catch (SQLException ex) {
           try {
               Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Sql exception : "+ ex.getMessage());
               FacesContext.getCurrentInstance().addMessage("warn", new FacesMessage("Ein Fehler ist aufgetreten","Bitte versuchen Sie es erneut..")); //Error while processing.Please try Again..
               error=true;
               if(con!=null){
               con.rollback();
               }
           } catch (SQLException ex1) {
               Logger.getLogger(Recovery.class.getName()).log(Level.SEVERE, null, ex1);
           }
        }

        finally{

                 closeConnection(con);
                 closeStatement(ps);
                 
        }

return result;

  }




    public String send(){
      exists();
      
      String html=null;
      String link=null;
      String outcome=null;

     String to = mail;

      // Sender's email ID needs to be mentioned
      String from = "info@otame.de";
      //String from = "canersir@gmail.com";
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
   

 try{

        
if(!exists){
FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Email gesendet.."));
Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"No record..");
  html = "Ihre angegebene Email Adresse exsitiert nicht. Registrieren Sie sich <a href='https://www.otame.de'>hier</a>";
  link="";
  outcome="";

}
else if(exists && !active){
  Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Hasnt verified yet...");
FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Email gesendet.."));
html = "Ihr Konto wurde noch nicht bestätigt. Besuchen sie folgen <a href='https://www.otame.de/resend.xhtml'>link</a> falls Sie noch keine Aktivierungsmail erhalten haben.";
outcome="";
}

else if(error){
  Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Error..");
  FacesContext.getCurrentInstance().addMessage("Warn", new FacesMessage("Ein Fehler ist aufgetreten","Bitte versuchen Sie es erneut.."));
return "";
}

else{
boolean ok=updateToken();
if(ok){
  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Email gesendet.."));
  Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Success..");
  link="https://www.otame.de/change.xhtml?mail="+mail+"&token="+token;
  html = "Klicken Sie folgenden <a href="+link+">link</a> um Ihr Passwort zu ändern. Sollten Sie das Passwort zurücksetzen nicht angefordet haben, können Sie diese E-Mail ignorieren.";
  outcome="";
}
else{
  FacesContext.getCurrentInstance().addMessage("warn", new FacesMessage("Ein Fehler ist aufgetreten","Bitte versuchen Sie es erneut.."));
  return "";
}
  
}

      Message message = new MimeMessage(session);
      message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(to));

         // Set Subject: header field
         message.setSubject("OTAME Wiederherstellung");

         // Create the message part
         BodyPart messageBodyPart = new MimeBodyPart();

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         
         
         // Now set the actual message
         //messageBodyPart.setText(html,"UTF-8","html");
         messageBodyPart.setText(html);
         
        // set HTML Part
         messageBodyPart.setContent(html,"text/html; charset=utf-8");
         // Create a multipar message
         Multipart multipart = new MimeMultipart();

         // Set text message part
         multipart.addBodyPart(messageBodyPart);

         // Send the complete message parts
         message.setContent(multipart);

         // Send message
         Transport.send(message);

}



        catch (MessagingException ex) {
         Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Sql exception : "+ ex.getMessage());
         FacesContext.getCurrentInstance().addMessage("warn", new FacesMessage("Ein Fehler ist aufgetreten","Bitte versuchen Sie es erneut..")); //Error while processing.Please try Again..
         return "";

      }
        
        return outcome;


    }


}