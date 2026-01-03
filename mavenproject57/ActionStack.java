/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject57;

/**
 *
 * @author Hadi
 */
public class ActionStack {
    private Node top;

    public ActionStack() {
        this.top = null;
    }

    public void push(Action action) {
        Node n = new Node(action);
        n.setNext(top);
        top = n;
    }

    public Action pop() {
        if (top == null) {
            System.out.println("No actions.");
            return null;
        }
        Action temp = top.getData();
        top = top.getNext();
        return temp;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void clear() {
        top = null;
    }
}