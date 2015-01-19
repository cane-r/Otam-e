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



public class Activation implements Serializable {


private String mail,token;

private Context initialContext;
private DataSource datasource;
private boolean valid=false;


public boolean getValid(){
	return valid;
}

public void setValid(boolean valid){
     this.valid=valid;
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


@PostConstruct
    public void init() {

    	mail=(String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("mail");
      token=(String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("token");

if(mail==null || token==null){
  redirect();
}

    	PreparedStatement ps=null;
    	Connection con=null;
    	int c=0;
    	ResultSet rs=null;
   
String sql="select token from users where userMail=?";
String sql2="update users set token=?,active=? where userMail=?";
String tokenFromDatabase=null;


      try {
            initialContext = new InitialContext();
            datasource = (DataSource)initialContext.lookup("java:jboss/datasources/MySQLDS");
        } catch (NamingException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Data source error..", ex);
        }

        
        

        String uuid=UUID.randomUUID().toString();

        StringBuilder bd=new StringBuilder();
        String [] id2=uuid.split("-");
      
       for(String id:id2){
           bd.append(id);
       }
       //new token..
       String newToken=bd.toString();


 try{
        con=datasource.getConnection();           
        ps=con.prepareStatement(sql);
        con.setAutoCommit(false);
        ps.setString(1,mail);
        rs=ps.executeQuery();

        //token exists..
        if(rs.next()){

        	Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Exists from database");

           tokenFromDatabase=rs.getString("token");
      
          

          if(tokenFromDatabase.equals(token)){
              Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"equals check passed..");
        	 
        	 ps=con.prepareStatement(sql2);
        	 ps.setString(1,newToken);
        	 ps.setBoolean(2,true);
        	 ps.setString(3,mail);
        	 c=ps.executeUpdate();
           con.commit();
        	 if(c==1){
        	 	valid=true;
        	 }
        	 else{
        	 	//error..
        	 	
        	 }

        }//end id equals..
 
        
        else {
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sie haben Ihren Zugang schon aktiviert..")); //You already activated your account..
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"already activated..");
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        

        redirect();
        return;
        }
        


        }
        else{
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Falsche Eingabe.."));
          redirect();
        }

//end if rs.next()
 
    }
    catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Sql exception : "+ ex.getMessage());
            FacesContext.getCurrentInstance().addMessage("warn", new FacesMessage("Es ist ein Fehler aufgetreten","Bitte erneut versuchen.")); //Error while processing.Please try Again..
                try {
                    if(con!=null){
                    con.rollback();
                    }
                } catch (SQLException ex1) {
                    Logger.getLogger(Activation.class.getName()).log(Level.SEVERE, null, ex1);
                }
        }

        finally{
        closeConnection(con);
	closeStatement(ps);
  	closeResultSet(rs);
        }
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


   public void redirect(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Error while redirecting to start.xhtml");
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



}