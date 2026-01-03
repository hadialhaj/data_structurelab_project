/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject57;

/**
 *
 * @author Hadi
 */
public class login {
   static final String suser="secrete"; 
       static final String muser="Manager admin"; 
   static final String spass="s123"; 
   static final String mpass="Admin"; 

int who(String user,String pass){
    if(user.equalsIgnoreCase(suser)&&pass.equals(spass)){
        return 2;
    }else if(user.equalsIgnoreCase(muser)&&pass.equals(mpass)){
        return 1;
    }else{
        return -1;
    }
    
    
    
}}    
    

