/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject57;

/**
 *
 * @author Hadi
 */
public class AppointmentNode {

    private Appointment data;
    private AppointmentNode left;
    private AppointmentNode right;

    public AppointmentNode(Appointment data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

  
    public Appointment getData() {
        return data;
    }

    public void setData(Appointment data) {
        this.data = data;
    }

    public AppointmentNode getLeft() {
        return left;
    }

    public void setLeft(AppointmentNode left) {
        this.left = left;
    }

    public AppointmentNode getRight() {
        return right;
    }

    public void setRight(AppointmentNode right) {
        this.right = right;
    }
}



