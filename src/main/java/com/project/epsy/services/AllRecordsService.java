/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.epsy.services;

import com.project.epsy.models.AllRecords;
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
public class AllRecordsService implements Serializable{
    private Connection con=null;
    private PreparedStatement ps=null;
    private List<AllRecords> users;
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
    public AllRecordsService(){
        
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
    
    
    
    public List<AllRecords> getUsers() {
        return users;
    }

    public void setUsers(List<AllRecords> users) {
        this.users = users;
    }
    
    
    public List getAllUsers(){
        ResultSet set = null;
        try {
            con=datasource.getConnection();
            String sql2="SELECT u.userMail,u.department,u.sex,u.age,p.q1,p.q2,p.q3,p.q4,p.q5,p.q6,p.q7,p.q8,p.q9,p.q10,\n" +
"p.q11,p.q12,p.q13,p.q14,p.q15,p.q16,p.q17,p.q18,p.q19,p.q20,\n" +
"p.q21,p.q22,p.q23,p.q24,p.q25,p.q26,p.q27,p.q28,p.q29,p.q30,\n" +
"p.q31,p.q32,p.q33,p.q34,p.q35,p.q36,p.q37,p.q38,p.q39,p.q40,\n" +
"p.q41,p.q42,p.q43,p.q44,p.q45,p.q46,p.q47,p.q48,p.q49,p.q50 from users as u,pretest as p\n" +
"where u.userMail=p.userMail";
            ps=con.prepareStatement(sql2);
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING,"Executing sql get users..");
            set=ps.executeQuery();
            
            while(set.next()){
                AllRecords user=new AllRecords();
                user.setMail(set.getString("userMail"));
                user.setDepartment(set.getString("department"));
                user.setSex(set.getString("sex"));
                user.setAge(set.getByte("age"));
                user.setQ1(set.getByte("q1"));
                user.setQ2(set.getByte("q2"));
                user.setQ3(set.getByte("q3"));
                user.setQ4(set.getByte("q4"));
                user.setQ5(set.getByte("q5"));
                user.setQ6(set.getByte("q6"));
                user.setQ7(set.getByte("q7"));
                user.setQ8(set.getByte("q8"));
                user.setQ9(set.getByte("q9"));
                user.setQ10(set.getByte("q10"));
                user.setQ11(set.getByte("q11"));
                user.setQ12(set.getByte("q12"));
                user.setQ13(set.getByte("q13"));
                user.setQ14(set.getByte("q14"));
                user.setQ15(set.getByte("q15"));
                user.setQ16(set.getByte("q16"));
                user.setQ17(set.getByte("q17"));
                user.setQ18(set.getByte("q18"));
                user.setQ19(set.getByte("q19"));
                user.setQ20(set.getByte("q20"));
                user.setQ21(set.getByte("q21"));
                user.setQ22(set.getByte("q22"));
                user.setQ23(set.getByte("q23"));
                user.setQ24(set.getByte("q24"));
                user.setQ25(set.getByte("q25"));
                user.setQ26(set.getByte("q26"));
                user.setQ27(set.getByte("q27"));
                user.setQ28(set.getByte("q28"));
                user.setQ29(set.getByte("q29"));
                user.setQ30(set.getByte("q30"));
                user.setQ31(set.getByte("q31"));
                user.setQ32(set.getByte("q32"));
                user.setQ33(set.getByte("q33"));
                user.setQ34(set.getByte("q34"));
                user.setQ35(set.getByte("q35"));
                user.setQ36(set.getByte("q36"));
                user.setQ37(set.getByte("q37"));
                user.setQ38(set.getByte("q38"));
                user.setQ39(set.getByte("q39"));
                user.setQ40(set.getByte("q40"));
                user.setQ41(set.getByte("q41"));
                user.setQ42(set.getByte("q42"));
                user.setQ43(set.getByte("q43"));
                user.setQ44(set.getByte("q44"));
                user.setQ45(set.getByte("q45"));
                user.setQ46(set.getByte("q46"));
                user.setQ47(set.getByte("q47"));
                user.setQ48(set.getByte("q48"));
                user.setQ49(set.getByte("q49"));
                user.setQ50(set.getByte("q50"));
                
                users.add(user);
               
            }
            
        } 
        catch (SQLException ex) {
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
