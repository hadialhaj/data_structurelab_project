/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject51;

/**
 *
 * @author Hadi
 */
public class ActionStack {
    Node top;

    public ActionStack() {
        this.top = null;
    }
    
    void push(Action action){
       Node n=new Node(action);
       if(top==null){
           top=n;
       }
       else{
       top.setNext(n);
       top=n;
       }
        
        
        
    }
    
    Action pop()
    {
        
        if(top==null){
            System.out.println("no data is found");
        }
        Action temp=top.getData();
        top=top.getNext();
        
        return temp;
        
    }    
    
    
    
    
}
