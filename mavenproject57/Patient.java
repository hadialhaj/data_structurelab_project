/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject57;

/**
 *
 * @author Hadi
 */
public class Patient extends Person {

    private String illness;
    private String room;
    private String status;

    Patient(int id, String name, int age, String gender, String phone, String address, String illness, String status,String room) {

        super(id, name, age, gender, phone, address);
        this.illness = illness;
        this.status = status;
        this.room=room;
    }

    public String getIllness() {
        return illness;
    }

    public void setIllness(String illness) {
        this.illness = illness;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void updateStatus(String newStatus) {

        this.status = newStatus;
    }

    public void changeIllness(String newIllness) {

        this.illness = newIllness;

    }
    
      public void changeRoom(String newRoom) {

        this.room= newRoom;

    }

    public String getRoom() {
        return room;
    }
    
    
    
      
      
      

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Illness: " + getIllness());
        System.out.println("Status: " + getStatus());
        System.out.println("Room: " + room);


    }

}

