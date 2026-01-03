/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject57;

/**
 *
 * @author Hadi
 */
public class undo_redo_manager {
    private final ActionStack undo;
    private final ActionStack redo;

    public undo_redo_manager() {
        this.undo = new ActionStack();
        this.redo = new ActionStack();
    }

    public void record(Action act) {
        undo.push(act);
        redo.clear();
    }

    public Action undo() {
        Action act = undo.pop();
        if (act != null) {
            act.undo();
            redo.push(act);
        }
        return act;
    }

    public Action redo() {
        Action act = redo.pop();
        if (act != null) {
            act.redo();
            undo.push(act);
        }
        return act;
    }
}