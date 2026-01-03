/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject57;

/**
 *
 * @author Hadi
 */
public class doctorDoublyLinkedList {

    private DoctorNode head;
    private DoctorNode tail;

    public boolean isEmpty() {
        return head == null;
    }

    
    
    
    public void add(Doctor data) {
        if (findDoctorById(data.getID()) != null) {
            System.err.println("Error: Doctor with ID " + data.getID() + " already exists.");
            return;
        }
        DoctorNode newNode = new DoctorNode(data);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        }
    }

    public void removeDoctorById(int id) {
        DoctorNode current = head;

        while (current != null) {
            if (current.getData().getID() == id) {
                if (current.getPrev() != null) {
                    current.getPrev().setNext(current.getNext());
                } else {
                    head = current.getNext();
                    if (head != null) {
                        head.setPrev(null);
                    }
                }
                return;
            }
            current = current.next;
        }
    }

    
    
    
    
    
    public Doctor findDoctorById(int id) {
        DoctorNode current = head;
        while (current != null) {
            if (current.getData().getID() == id) {
                return current.getData();
            }
            current = current.next;
        }
        return null;
    }
    
    
    
    
    
    
    

    public void displayAllNodes() {
        DoctorNode current = head;
        System.out.println("All Doctors Details");
        while (current != null) {
            current.getData().displayDetails();
            current = current.next;
        }
    }

    public java.util.List<Doctor> getAllDoctors() {
        java.util.List<Doctor> doctors = new java.util.ArrayList<>();
        DoctorNode current = head;
        while (current != null) {
            doctors.add(current.getData());
            current = current.next;
        }
        return doctors;
    }
}







