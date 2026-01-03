/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject57;

/**
 *
 * @author Hadi
 */
public class patientDoublyLinkedList {

    private patientNode head;
    private patientNode tail;

    public patientNode getHead() {
        return head;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void add(Patient data) {
        if (findPatientById(data.getID()) != null) {
            System.err.println("Error: Patient with ID " + data.getID() + " already exists.");
            return;
        }
        patientNode newNode = new patientNode(data);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        }
    }

    public void removePatientById(int id) {
        patientNode current = head;

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
                if (current.getNext() != null) {
                    current.getNext().setPrev(current.getPrev());
                } else {
                    tail = current.getPrev();
                }
                return;
            }
            current = current.next;
        }
    }

    public Patient findPatientById(int id) {
        patientNode current = head;
        while (current != null) {
            if (current.getData().getID() == id) {
                return current.getData();
            }
            current = current.next;
        }
        return null;
    }

    public Patient findPatientByName(String name) {
        patientNode current = head;
        while (current != null) {
            if (current.getData().getName().equals(name)) {
                return current.getData();
            }
            current = current.next;
        }
        return null;
    }

    public void displayAllNodes() {
        patientNode current = head;
        System.out.println("All Patients Details");
        while (current != null) {
            current.getData().displayDetails();
            current = current.next;
        }
    }

    public java.util.List<Patient> getAllPatients() {
        java.util.List<Patient> patients = new java.util.ArrayList<>();
        patientNode current = head;
        while (current != null) {
            patients.add(current.getData());
            current = current.next;
        }
        return patients;
    }

    // Update patient by ID
  public boolean updatePatientById(int id, Patient newData) {
    patientNode current = head;
    while (current != null) {
        if (current.getData().getID() == id) {
            current.setData(newData);  // Replace old patient data
            return true;
        }
        current = current.next;
    }
    return false; 
}
}

