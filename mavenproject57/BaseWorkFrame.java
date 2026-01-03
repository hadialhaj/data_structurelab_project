/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject57;

/**
 *
 * @author Hadi
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public abstract class BaseWorkFrame extends JFrame {
    protected final Hospital hospital;
    protected final undo_redo_manager history = new undo_redo_manager();

    protected final DefaultTableModel patientModel = new DefaultTableModel(
            new String[] {"ID","Name","Age","Gender","Phone","Address","Illness","Status","Room"}, 0);
    protected final JTable patientTable = new JTable(patientModel);

    protected final DefaultTableModel apptModel = new DefaultTableModel(
            new String[] {"ID","Date/Time","Status","Doctor","Patient"}, 0);
    protected final JTable apptTable = new JTable(apptModel);

    protected final DefaultTableModel scheduleModel = new DefaultTableModel(
            new String[] {"ID","Date/Time","Doctor","Patient"}, 0);
    protected final JTable scheduleTable = new JTable(scheduleModel);

    public BaseWorkFrame(String title, Hospital hospital) {
        super(title);
        this.hospital = hospital;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
    }

    protected void refreshPatients() {
        patientModel.setRowCount(0);
        for (Patient p : hospital.listPatients()) {
            patientModel.addRow(new Object[]{
                p.getID(), p.getName(), p.getAge(), p.getGender(), p.getPhone(), p.getAddress(),
                p.getIllness(), p.getStatus(), p.getRoom()
            });
        }
    }

    protected void refreshAppointments() {
        apptModel.setRowCount(0);
        scheduleModel.setRowCount(0); // mirror schedule from appointment list
        for (Appointment a : hospital.listAppointments()) {
            apptModel.addRow(new Object[]{
                a.getAppointmentID(), a.getDateTime(), a.getStatus(),
                a.getDoctor()!=null ? a.getDoctor().getName() : "",
                a.getPatient()!=null ? a.getPatient().getName() : ""
            });
            scheduleModel.addRow(new Object[]{
                a.getAppointmentID(), a.getDateTime(),
                a.getDoctor()!=null ? a.getDoctor().getName() : "",
                a.getPatient()!=null ? a.getPatient().getName() : ""
            });
        }
    }
}