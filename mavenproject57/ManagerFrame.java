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

public class ManagerFrame extends ReceptionFrame {
    private final DefaultTableModel doctorModel = new DefaultTableModel(
            new String[] {"ID","Name","Age","Gender","Phone","Address","Specialization","Department"}, 0);
    private final JTable doctorTable = new JTable(doctorModel);

    public ManagerFrame(Hospital hospital) {
        super(hospital);
        setTitle("Manager");

        JPanel doctorPanel = new JPanel(new BorderLayout());
        doctorPanel.add(new JLabel("Doctors"), BorderLayout.NORTH);
        doctorPanel.add(new JScrollPane(doctorTable), BorderLayout.CENTER);

        JPanel doctorBtns = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton addD = new JButton("Add Doctor");
        JButton editD = new JButton("Edit Doctor");
        JButton delD = new JButton("Delete Doctor");
        doctorBtns.add(addD);
        doctorBtns.add(editD);
        doctorBtns.add(delD);

        doctorPanel.add(doctorBtns, BorderLayout.SOUTH);
        getContentPane().add(doctorPanel, BorderLayout.SOUTH);

        addD.addActionListener(e -> onAddDoctor());
        editD.addActionListener(e -> onEditDoctor());
        delD.addActionListener(e -> onDeleteDoctor());

        refreshDoctors();
    }

    @Override
    protected void refreshDoctors() {
        doctorModel.setRowCount(0);
        for (Doctor d : hospital.listDoctors()) {
            doctorModel.addRow(new Object[]{
                d.getID(), d.getName(), d.getAge(), d.getGender(), d.getPhone(), d.getAddress(),
                d.getSpecialization(), d.getDepartment()
            });
        }
    }

    private Doctor getSelectedDoctor() {
        int row = doctorTable.getSelectedRow();
        if (row < 0) return null;
        int id = (int) doctorModel.getValueAt(row, 0);
        try { return hospital.findDoctorById(id); } catch (Exception e) { return null; }
    }

   private void onAddDoctor() {
    // ID
    Integer id = promptPositiveInt("Doctor ID:");
    if (id == null) return;
    if (hospital.hasDoctorId(id)) {
        JOptionPane.showMessageDialog(this, "Doctor ID already exists! Choose another ID.");
        return;
    }

    // Details
    String name = promptText("Name:", "");
    if (name == null) return;
    Integer age = promptPositiveInt("Age:");
    if (age == null) return;
    String gender = promptText("Gender:", "");
    if (gender == null) return;
    String phone = promptText("Phone:", "");
    if (phone == null) return;
    String address = promptText("Address:", "");
    if (address == null) return;
    String spec = promptText("Specialization:", "");
    if (spec == null) return;
    String dept = promptText("Department:", "");
    if (dept == null) return;

    Doctor d = new Doctor(spec, dept, id, name, age, gender, phone, address);
    hospital.addDoctor(d);

    // Update UI
    refreshDoctors();
    JOptionPane.showMessageDialog(this, "Doctor added successfully!");
}

    private void onEditDoctor() {
        Doctor old = getSelectedDoctor();
        if (old == null) {
            JOptionPane.showMessageDialog(this, "Select a doctor first");
            return;
        }
        try {
            String name = JOptionPane.showInputDialog(this, "Name:", old.getName());
            int age = Integer.parseInt(JOptionPane.showInputDialog(this, "Age:", old.getAge()));
            String gender = JOptionPane.showInputDialog(this, "Gender:", old.getGender());
            String phone = JOptionPane.showInputDialog(this, "Phone:", old.getPhone());
            String address = JOptionPane.showInputDialog(this, "Address:", old.getAddress());
            String spec = JOptionPane.showInputDialog(this, "Specialization:", old.getSpecialization());
            String dept = JOptionPane.showInputDialog(this, "Department:", old.getDepartment());

            // Update doctor 
            old.setName(name);
            old.setAge(age);
            old.setGender(gender);
            old.setPhone(phone);
            old.setAddress(address);
            old.setSpecialization(spec);
            old.setDepartment(dept);

            refreshDoctors();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input: " + ex.getMessage());
        }
    }

    private void onDeleteDoctor() {
        Doctor old = getSelectedDoctor();
        if (old == null) {
            JOptionPane.showMessageDialog(this, "Select a doctor first");
            return;
        }
        hospital.removeDoctorById(old.getID());
        refreshDoctors();
    }
    
    private Integer promptPositiveInt(String title) {
    while (true) {
        String s = JOptionPane.showInputDialog(this, title);
        if (s == null) return null; // cancelled
        s = s.trim();
        if (s.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a value.");
            continue;
        }
        try {
            int v = Integer.parseInt(s);
            if (v <= 0) {
                JOptionPane.showMessageDialog(this, "Value must be a positive integer.");
                continue;
            }
            return v;
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.");
        }
    }
}

private String promptText(String title, String defaultValue) {
    String s = JOptionPane.showInputDialog(this, title, defaultValue);
    if (s == null) return null;
    s = s.trim();
    if (s.isEmpty()) return null;
    return s;
}
}