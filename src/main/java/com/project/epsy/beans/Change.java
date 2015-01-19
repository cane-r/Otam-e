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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import org.apache.commons.codec.binary.Base64;
import java.io.UnsupportedEncodingException;



public class Change implements Serializable {



private String mail;
private String token;
private String pass1,pass2;
private Context initialContext;
private DataSource datasource;
private Base64 base;
private boolean result=false;
    
private boolean valid;
private boolean error;



public boolean getValid(){
	return valid;
}

public void setValid(boolean valid){
     this.valid=valid;
}


public boolean getError(){
  return error;
}

public void setError(boolean error){
     this.error=error;
}


public String getMail(){
  return mail;
}

public void setMail(String mail){
     this.mail=mail;
}


public String getToken(){
  return token;
}

public void setToken(String token){
     this.token=token;
}


  public void setPass1(String pass1) {
        this.pass1 = pass1;
    }

  public void setPass2(String pass2) {
        this.pass2 = pass2;
    }

public String getPass1() {
        return pass1;
    }

    public String getPass2() {
        return pass2;
    }




@PostConstruct
    public void init() {
      Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"In PostConstruct"); 
      valid=false;
      
base=new Base64();
      
      try {
mail=(String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("mail");
   token=(String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("token");

            initialContext = new InitialContext();
            datasource = (DataSource)initialContext.lookup("java:jboss/datasources/MySQLDS");
        } catch (NamingException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Data source error..", ex);
            error=true;
        }
        catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Unknown error error..");
            error=true;
        }
 

   
  String tokenFromDatabase=getTokenFromDb();
Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Token from db is " + tokenFromDatabase); 



if(null==tokenFromDatabase){
   Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Token invalid...");
valid=false;

}



if(null!=tokenFromDatabase){
  Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Token valid...");
valid=true;

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

   public byte[] getHash(int iterationNb, String password, byte[] salt) throws NoSuchAlgorithmException {
    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"in get hash..");
        
       MessageDigest digest = MessageDigest.getInstance("SHA-1");
       digest.reset();
       digest.update(salt);
       byte[] input=null;
        try {
            input = digest.digest(password.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        
       for (int i = 0; i < iterationNb; i++) {
           digest.reset();
           input = digest.digest(input);
       }
       return input;
   }


private String getTokenFromDb(){
  String token2=null;
  String mail2=null;
  String token3=null;
PreparedStatement ps=null;
      Connection con=null;
      ResultSet rs=null;
      String sql="select token from users where token=? and userMail=?";
      Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"in token from db");
try { 
con=datasource.getConnection();
mail2=mail;
token2=token;
   
Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Request parameters : " + FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("mail") + " : " + FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("token")); 

ps=con.prepareStatement(sql);
  ps.setString(1,token2);
   Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"token is : " + token2);
  ps.setString(2,mail2);
   Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"mail is : " + mail2);
  rs=ps.executeQuery();
  

if(rs.next()){
token3=rs.getString("token");
Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"token found.. and is : " + token3);
}

}

//end try..

catch(SQLException e){
  Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"SQLException occured while grabbing token..");
error=true;
}

catch(NullPointerException e){
  Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"NullPointerException occured while grabbing token..");
error=true;
}

finally{
 closeConnection(con);
 closeStatement(ps);
 closeResultSet(rs);
}

return token3;

}


  private void updatePassword(){
    String sql="update users set psssssrd=?,salt=?,token=? where userMail=?";
      Connection con = null;
           PreparedStatement ps = null;
           ResultSet rs=null;
          

           try{


            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
               // Salt generation 64 bits long
               byte[] bSalt = new byte[8];
               random.nextBytes(bSalt);
               // Digest computation
               byte[] bDigest = getHash(1000,pass1,bSalt);
               String passs = byteToBase64(bDigest);
               String salt = byteToBase64(bSalt);
               Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "salt is : " + salt);
               Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "pass is : " + pass1);
Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "pass hash is : " + passs);

            con=datasource.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(sql);
            ps.setString(1,passs);
            ps.setString(2,salt);
            ps.setString(3,newToken());
            ps.setString(4,mail);
            ps.executeUpdate();
            con.commit();
            result=true;
         
        }

           catch(SQLException e){
            if(con!=null){

                   try {
                       con.rollback();
                       result=false;
                   } catch (SQLException ex1) {
                       Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex1);
                       
                   }
               }


           } catch (NoSuchAlgorithmException ex) {
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
    }


  }



  private byte[] base64ToByte(String data) throws IOException {
       return base.decode(data);
   }
 
   private String byteToBase64(byte[] data){
       return base.encodeAsString(data);
   }



   public String changePass(){
 String path="";

 
 String tokenFromDb=getTokenFromDb();

if(null==token || mail==null){
  Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Token or mail null...");
  FacesContext.getCurrentInstance().addMessage("warn", new FacesMessage("Warnung","Token oder Email null.."));
error=false;
return"index";
}



else if(null== tokenFromDb || !tokenFromDb.equals(token)){
  Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Retrying...");
  result=false;
  return "index"; 
}
  

  else if(!pass1.equals(pass2)){
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Passwörter stimmen nicht überein.."));
path="";

  }

else if(pass1.length()<5 || pass1.length()>12 || pass2.length()<5 || pass2.length()>12 ) {
  FacesContext.getCurrentInstance().addMessage("Warn", new FacesMessage("Hinweis","Passwörter müssen mindestens 5 und maximal 12 Zeichen haben und dürfen keine Leerzeichen enthalten")); 
             
path="";

}

else
{



updatePassword();
Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "result is : " + result);

if(result){
FacesContext.getCurrentInstance().addMessage("r-form:growl", new FacesMessage("Hinweis","Passwort wurde erfolgreich geändert."));
FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Password has been changed successfully.");
return "index";

}

else{

 FacesContext.getCurrentInstance().addMessage("warn", new FacesMessage("Fehler aufgetreten","Versuchen Sie es erneut www.otame.de/recovery.xhtml.")); 
}

return "index";

}

return path;

}}
