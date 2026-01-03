/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject57;

/**
 *
 * @author Hadi
 */
public class DoctorNode {

    Doctor data;
    DoctorNode next;
    DoctorNode prev;

    DoctorNode(Doctor data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    public Doctor getData() {
        return data;
    }

    public void setData(Doctor data) {
        this.data = data;
    }

    public DoctorNode getNext() {
        return next;
    }

    public void setNext(DoctorNode next) {
        this.next = next;
    }

    public DoctorNode getPrev() {
        return prev;
    }

    public void setPrev(DoctorNode prev) {
        this.prev = prev;
    }

}


