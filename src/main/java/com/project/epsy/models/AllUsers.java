/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.epsy.models;

/**
 *
 * @author Cuneyt
 */
public class AllUsers {
    
    private String mail,department,sex;
    private byte age;
   

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

   

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setAge(byte age) {
        this.age = age;
    }

      
    public String getMail() {
        return mail;
    }
   
    public String getDepartment() {
        return department;
    }

    public String getSex() {
        return sex;
    }

    public byte getAge() {
        return age;
    }

    
}
