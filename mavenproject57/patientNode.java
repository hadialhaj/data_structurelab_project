/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject57;

/**
 *
 * @author Hadi
 */
public class patientNode {

    Patient data;
    patientNode next;
    patientNode prev;

    patientNode(Patient data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    public Patient getData() {
        return data;
    }

    public void setData(Patient data) {
        this.data = data;
    }

    public patientNode getNext() {
        return next;
    }

    public void setNext(patientNode next) {
        this.next = next;
    }

    public patientNode  getPrev() {
        return prev;
    }

    public void setPrev(patientNode prev) {
        this.prev = prev;
    }

}

