/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject51;

/**
 *
 * @author Hadi
 */
public class undo_redo_manager {
    ActionStack undo=new ActionStack();
     ActionStack redo=new ActionStack();

    public undo_redo_manager() {
    ActionStack undo=new ActionStack();
     ActionStack redo=new ActionStack();
    
    
    }
    
    

    
     void record(Action act){
         undo.push(act);
     }
     Action undo(){
         Action act=undo.pop();
         if(act!=null){
             redo.push(act);
         }
         return act;
     }
     
     Action redo(){
          Action act=redo.pop();
         if(act!=null){
             undo.push(act);
         }
         return act;
     }
     
     
     
}
