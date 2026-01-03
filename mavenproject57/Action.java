/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mavenproject57;

/**
 *
 * @author Hadi
 */
public class Action {
    private final ActionTarget target;
    public Action(ActionTarget t) { this.target = t; }
    public void undo() { target.revert(); }
    public void redo() { target.apply(); }
    public String toString() { return target.label(); }
}