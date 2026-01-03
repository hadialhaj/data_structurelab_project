/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject57;

/**
 *
 * @author Hadi
 */
public class qNode {
    public Patient data;
    public qNode next;
    
    public qNode(Patient data) {
        this.data = data;
        this.next = null;
    }

    public Patient getData() {
        return data;
    }

    public void setData(Patient data) {
        this.data = data;
    }

    public qNode getNext() {
        return next;
    }

    public void setNext(qNode next) {
        this.next = next;
    }
    
    
    
}
