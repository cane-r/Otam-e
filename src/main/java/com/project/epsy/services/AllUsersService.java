/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.epsy.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import com.project.epsy.models.AllUsers;
import java.io.Serializable;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
/**
 *
 * @author Cuneyt
 */
public class AllUsersService implements Serializable{
    private Connection con=null;
    private PreparedStatement ps=null;
    private List<AllUsers> users;
      private Context initialContext;
    private DataSource datasource;

/*
    public List<User> getUsers() {
        if(users==null){
            users=getAllRecords();
        }
        return users;
    }
  */  
    public AllUsersService(){
        
        //users=getAllRecords();
    }
    
    @PostConstruct
    public void initialize(){
        users=new ArrayList<>();

try {
            initialContext = new InitialContext();
            datasource = (DataSource)initialContext.lookup("java:jboss/datasources/MySQLDS");
        } catch (NamingException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Data source error..", ex);
        }

        users=getAllUsers();
    }
    
    
    
    public List<AllUsers> getUsers() {
        return users;
    }

    public void setUsers(List<AllUsers> users) {
        this.users = users;
    }
    
    
    public List getAllUsers(){
        ResultSet set = null;
        try {
            con=datasource.getConnection();
            String sql="SELECT userMail,department,sex,age from users";
            ps=con.prepareStatement(sql);
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING,"Executing sql get users..");
            set=ps.executeQuery();
            
            while(set.next()){
                AllUsers user2=new AllUsers();
                user2.setMail(set.getString("userMail"));
                user2.setDepartment(set.getString("department"));
                user2.setSex(set.getString("sex"));
                user2.setAge(set.getByte("age"));
                users.add(user2);
               
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "");
        }
        finally{
            try {
                if(set!=null){
                set.close();
                }
                if(con!=null){
                con.close();
                }
                if(ps!=null){
                ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"SQL Exception has occured..");
            }
        }
        
       
            return users;
    }
    
    
}
