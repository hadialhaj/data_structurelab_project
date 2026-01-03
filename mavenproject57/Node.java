/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject57;

/**
 *
 * @author Hadi
 */
 public class Node {
    private Node next;
    private Action data;

    public Node(Action data) {
        this.data = data;
        this.next = null;
    }

    public Node getNext() { return next; }
    public void setNext(Node next) { this.next = next; }
    public Action getData() { return data; }
    public void setData(Action data) { this.data = data; }
}