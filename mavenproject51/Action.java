/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject51;

/**
 *
 * @author Hadi
 */
public class Action {
    enum ActionType{
        Add,delete,update
    }
    
    ActionType type;
    Patient oldData;
    Patient newData;

    
    
            
    public Action(ActionType type, Patient newData) {
        this.type = type;
        this.newData = newData;
    }
    
    public Action(ActionType type, Patient oldData, boolean isdel) {
        this.type = type;
        this.oldData = oldData;
    }
    
    
     public Action(ActionType type, Patient oldData,Patient newData) {
        this.type = type;
        this.oldData = oldData;
        this.newData=newData;
    }
    
    
    
    
    
    
    
}
