/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject57;

/**
 *
 * @author Hadi
 */
public class AppointmentTree {

    private AppointmentNode root;

    public AppointmentTree() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

  
    public void add(Appointment data) {
        root = insert(root, data);
    }

    private AppointmentNode insert(AppointmentNode node, Appointment data) {

        if (node == null) {
            return new AppointmentNode(data);
        }

        int newID = data.getAppointmentID();
        int currentID = node.getData().getAppointmentID();

        if (newID < currentID) {
            node.setLeft(insert(node.getLeft(), data));
        } 
        else if (newID > currentID) {
            node.setRight(insert(node.getRight(), data));
        }

        return node;
    }
    
public void remove(int id) {
    root = delete(root, id);
}

private AppointmentNode delete(AppointmentNode node, int id) {

    if (node == null) return null;

    int curr = node.getData().getAppointmentID();

   
    if (id < curr) {
        node.setLeft(delete(node.getLeft(), id));
    }
    else if (id > curr) {
        node.setRight(delete(node.getRight(), id));
    }
    else {
        

       
        if (node.getLeft() == null && node.getRight() == null)
            return null;

        
        if (node.getLeft() == null)
            return node.getRight();

        
        if (node.getRight() == null)
            return node.getLeft();

        
        AppointmentNode min = findMin(node.getRight());
        node.setData(min.getData());
        node.setRight(delete(node.getRight(), min.getData().getAppointmentID()));
    }

    return node;
}

private AppointmentNode findMin(AppointmentNode node) {
    while (node.getLeft() != null) {
        node = node.getLeft();
    }
    return node;
}
    
    



    
    public Appointment findById(int id) {
        AppointmentNode current = root;

        while (current != null) {

            int currentID = current.getData().getAppointmentID();

            if (id == currentID) {
                return current.getData();
            } 
            else if (id < currentID) {
                current = current.getLeft();
            } 
            else {
                current = current.getRight();
            }
        }

        return null;
    }

  
    public void displayInOrder() {
        System.out.println("All Appointments (sorted by ID):");
        inOrder(root);
    }

    private void inOrder(AppointmentNode node) {
        if (node != null) {
            inOrder(node.getLeft());
            node.getData().displayDetails();
            inOrder(node.getRight());
        }
    }

    public java.util.List<Appointment> getAllAppointments() {
        java.util.List<Appointment> appointments = new java.util.ArrayList<>();
        getAllAppointmentsRecursive(root, appointments);
        return appointments;
    }

    private void getAllAppointmentsRecursive(AppointmentNode node, java.util.List<Appointment> appointments) {
        if (node != null) {
            getAllAppointmentsRecursive(node.getLeft(), appointments);
            appointments.add(node.getData());
            getAllAppointmentsRecursive(node.getRight(), appointments);
        }
    }
}

