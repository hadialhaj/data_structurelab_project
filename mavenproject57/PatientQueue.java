/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject57;

import java.util.ArrayList;

/**
 *
 * @author Hadi
 */


public class PatientQueue {
    private qNode front; 
    private qNode rear;  

    public boolean isEmpty() {
        return front == null;
    }

  
    public void enqueue(Patient patient) {
        qNode newNode = new qNode(patient);
        
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            rear.setNext(newNode);
            rear = newNode;
        }
    }

  
    public Patient dequeue() {
        if (isEmpty()) {
            return null;
        }
        
        Patient patient = front.getData();
        
        front = front.getNext();
        
        if (front == null) {
            rear = null;
        }
        
        return patient;
    }

   
    public Patient peek() {
        return isEmpty() ? null : front.getData();
    }
    
 
    public java.util.List<Patient> getAllPatients() {
        ArrayList<Patient> patients = new ArrayList<>();
        qNode current = front;
        while (current != null) {
            patients.add(current.getData());
            current = current.getNext();
        }
        return patients;
    }
}