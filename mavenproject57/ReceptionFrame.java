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
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class ReceptionFrame extends BaseWorkFrame {

    protected final DefaultTableModel queueModel = new DefaultTableModel(
            new String[] {"ID","Name","Illness"}, 0);
    protected final JTable queueTable = new JTable(queueModel);
    
    protected final DefaultTableModel doctorModel = new DefaultTableModel(
            new String[] {"ID","Name","Specialization","Phone"}, 0);
    protected final JTable doctorTable = new JTable(doctorModel);
    
    
    public ReceptionFrame(Hospital hospital) {
        super("Reception", hospital);
        JPanel root = new JPanel(new BorderLayout(8,8));
        add(root);

        // Left: Patients
        JPanel left = new JPanel(new BorderLayout());
        left.add(new JScrollPane(patientTable), BorderLayout.CENTER);
        JPanel leftBtns = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton addP = new JButton("Add Patient");
        JButton editP = new JButton("Edit Patient");
        JButton delP = new JButton("Delete Patient");
        leftBtns.add(addP); leftBtns.add(editP); leftBtns.add(delP);
        left.add(leftBtns, BorderLayout.SOUTH);

        // Top right: Appointments
        JPanel topRight = new JPanel(new BorderLayout());
        topRight.add(new JScrollPane(apptTable), BorderLayout.CENTER);
        JPanel topRightBtns = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton addA = new JButton("Add Appointment");
        JButton editA = new JButton("Edit Appointment");
        JButton delA = new JButton("Delete Appointment");
        JButton resA = new JButton("Reschedule");
        JButton cancelA = new JButton("Cancel");
        topRightBtns.add(addA); topRightBtns.add(editA); topRightBtns.add(delA);
        topRightBtns.add(resA); topRightBtns.add(cancelA);
        topRight.add(topRightBtns, BorderLayout.SOUTH);

        // Center: Doctors List
        JPanel center = new JPanel(new BorderLayout());
        center.add(new JLabel("Doctor Information"), BorderLayout.NORTH);
        center.add(new JScrollPane(doctorTable), BorderLayout.CENTER);
        
        // Right bottom: Waiting Queue
        // 2. Right bottom: Schedule mirror is replaced by the Waiting Queue
        JPanel rightBottom = new JPanel(new BorderLayout());
        rightBottom.add(new JLabel("Waiting Queue"), BorderLayout.NORTH); // New Title
        rightBottom.add(new JScrollPane(queueTable), BorderLayout.CENTER); // Add the queue table

        JPanel queueBtns = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton addToQueueBtn = new JButton("Add Selected Patient to Queue");
        JButton callNextBtn = new JButton("Call Next Patient");
        queueBtns.add(addToQueueBtn);
        queueBtns.add(callNextBtn);
        rightBottom.add(queueBtns, BorderLayout.SOUTH);
        
        JPanel right = new JPanel(new GridLayout(3,1,8,8));
        right.add(topRight);
        right.add(center);
        right.add(rightBottom);

        // Top bar: undo/redo
        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton undoBtn = new JButton("Undo");
        JButton redoBtn = new JButton("Redo");
        topBar.add(undoBtn); topBar.add(redoBtn);

        root.add(topBar, BorderLayout.NORTH);
        root.add(left, BorderLayout.WEST);
        root.add(right, BorderLayout.CENTER);

        addP.addActionListener(e -> onAddPatient());
        editP.addActionListener(e -> onEditPatient());
        delP.addActionListener(e -> onDeletePatient());

        addA.addActionListener(e -> onAddAppointment());
        editA.addActionListener(e -> onEditAppointment());
        delA.addActionListener(e -> onDeleteAppointment());
        resA.addActionListener(e -> onRescheduleAppointment());
        cancelA.addActionListener(e -> onCancelAppointment());

        undoBtn.addActionListener(e -> { history.undo(); refreshPatients(); refreshAppointments(); refreshQueue(); });
        redoBtn.addActionListener(e -> { history.redo(); refreshPatients(); refreshAppointments(); refreshQueue(); });
        
        addToQueueBtn.addActionListener(e -> onAddToQueue());
        callNextBtn.addActionListener(e -> onCallNextPatient());
      

        refreshPatients();
        refreshAppointments();
        refreshQueue(); 
        if (!(this instanceof ManagerFrame)) { refreshDoctors(); }
    }

    private void onAddPatient() {
        try {
            String idStr = JOptionPane.showInputDialog(this, "Patient ID:");
            if (idStr == null) return;
            int id = Integer.parseInt(idStr);
            if (hospital.hasPatientId(id)) {
                JOptionPane.showMessageDialog(this, "ID already exists");
                return;
            }
            String name = JOptionPane.showInputDialog(this, "Name:");
            int age = Integer.parseInt(JOptionPane.showInputDialog(this, "Age:"));
            String gender = JOptionPane.showInputDialog(this, "Gender:");
            String phone = JOptionPane.showInputDialog(this, "Phone:");
            String address = JOptionPane.showInputDialog(this, "Address:");
            String illness = JOptionPane.showInputDialog(this, "Illness:");
            String status = JOptionPane.showInputDialog(this, "Status:");
            String room = JOptionPane.showInputDialog(this, "Room:");
            Patient p = new Patient(id, name, age, gender, phone, address, illness, status, room);
            Action act = new Action(new PatientAddTarget(hospital, p));
            act.redo(); // apply
            history.record(act);
            refreshPatients();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input: " + ex.getMessage());
        }
    }

    private Patient getSelectedPatient() {
        int row = patientTable.getSelectedRow();
        if (row < 0) return null;
        int id = (int) patientModel.getValueAt(row, 0);
        try { return hospital.findPatientById(id); } catch (Exception e) { return null; }
    }

    private void onEditPatient() {
        Patient old = getSelectedPatient();
        if (old == null) { JOptionPane.showMessageDialog(this, "Select a patient"); return; }
        try {
            String name = JOptionPane.showInputDialog(this, "Name:", old.getName());
            int age = Integer.parseInt(JOptionPane.showInputDialog(this, "Age:", old.getAge()));
            String gender = JOptionPane.showInputDialog(this, "Gender:", old.getGender());
            String phone = JOptionPane.showInputDialog(this, "Phone:", old.getPhone());
            String address = JOptionPane.showInputDialog(this, "Address:", old.getAddress());
            String illness = JOptionPane.showInputDialog(this, "Illness:", old.getIllness());
            String status = JOptionPane.showInputDialog(this, "Status:", old.getStatus());
            String room = JOptionPane.showInputDialog(this, "Room:", old.getRoom());
            Patient newP = new Patient(old.getID(), name, age, gender, phone, address, illness, status, room);
            Action act = new Action(new PatientUpdateTarget(hospital, old, newP));
            act.redo();
            history.record(act);
            refreshPatients();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input: " + ex.getMessage());
        }
    }

    private void onDeletePatient() {
        Patient old = getSelectedPatient();
        if (old == null) { JOptionPane.showMessageDialog(this, "Select a patient"); return; }
        Action act = new Action(new PatientDeleteTarget(hospital, old));
        act.redo();
        history.record(act);
        refreshPatients();
    }

    private Appointment getSelectedAppointment() {
        int row = apptTable.getSelectedRow();
        if (row < 0) return null;
        int id = (int) apptModel.getValueAt(row, 0);
        try { return hospital.findAppointmentById(id); } catch (Exception e) { return null; }
    }

    private void onAddAppointment() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Appointment ID:"));
            if (hospital.hasAppointmentId(id)) {
                JOptionPane.showMessageDialog(this, "Appointment ID exists");
                return;
            }
            String dateTime = JOptionPane.showInputDialog(this, "Date/Time:");
            String status = JOptionPane.showInputDialog(this, "Status:");
            // For simplicity, select patient/doctor by existing IDs
            int pid = Integer.parseInt(JOptionPane.showInputDialog(this, "Patient ID:"));
            Patient p = hospital.findPatientById(pid);
            int did = Integer.parseInt(JOptionPane.showInputDialog(this, "Doctor ID:"));
            Doctor d = hospital.findDoctorById(did);

            Appointment ap = new Appointment(id, dateTime, status, d, p);
            Action act = new Action(new AppointmentAddTarget(hospital, ap));
            act.redo();
            history.record(act);
            refreshAppointments();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input: " + ex.getMessage());
        }
    }

    private void onEditAppointment() {
        Appointment old = getSelectedAppointment();
        if (old == null) { JOptionPane.showMessageDialog(this, "Select an appointment"); return; }
        try {
            String dateTime = JOptionPane.showInputDialog(this, "Date/Time:", old.getDateTime());
            String status = JOptionPane.showInputDialog(this, "Status:", old.getStatus());
            int pid = Integer.parseInt(JOptionPane.showInputDialog(this, "Patient ID:", old.getPatient().getID()));
            Patient p = hospital.findPatientById(pid);
            int did = Integer.parseInt(JOptionPane.showInputDialog(this, "Doctor ID:", old.getDoctor().getID()));
            Doctor d = hospital.findDoctorById(did);
            Appointment newAp = new Appointment(old.getAppointmentID(), dateTime, status, d, p);
            Action act = new Action(new AppointmentUpdateTarget(hospital, old, newAp));
            act.redo();
            history.record(act);
            refreshAppointments();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input: " + ex.getMessage());
        }
    }

    private void onDeleteAppointment() {
        Appointment old = getSelectedAppointment();
        if (old == null) { JOptionPane.showMessageDialog(this, "Select an appointment"); return; }
        Action act = new Action(new AppointmentDeleteTarget(hospital, old));
        act.redo();
        history.record(act);
        refreshAppointments();
    }

    private void onRescheduleAppointment() {
        Appointment old = getSelectedAppointment();
        if (old == null) { JOptionPane.showMessageDialog(this, "Select an appointment"); return; }
        String newDT = JOptionPane.showInputDialog(this, "New Date/Time:", old.getDateTime());
        if (newDT == null) return;
        Appointment newAp = new Appointment(old.getAppointmentID(), newDT, old.getStatus(), old.getDoctor(), old.getPatient());
        Action act = new Action(new AppointmentUpdateTarget(hospital, old, newAp));
        act.redo();
        history.record(act);
        refreshAppointments();
    }

    private void onCancelAppointment() {
        Appointment old = getSelectedAppointment();
        if (old == null) { JOptionPane.showMessageDialog(this, "Select an appointment"); return; }
        Appointment newAp = new Appointment(old.getAppointmentID(), old.getDateTime(), "Cancelled", old.getDoctor(), old.getPatient());
        Action act = new Action(new AppointmentUpdateTarget(hospital, old, newAp));
        act.redo();
        history.record(act);
        refreshAppointments();
    }
    
    private void onAddToQueue() {
        Patient p = getSelectedPatient();
        if (p == null) {
            JOptionPane.showMessageDialog(this, "Select a patient from the Patient table first.");
            return;
        }
        
        // New validation: Check if the patient has an active appointment
        if (!hospital.hasActiveAppointmentForPatient(p.getID())) {
            JOptionPane.showMessageDialog(this, "Patient " + p.getName() + " cannot be added to the waiting queue. They must have an active appointment.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            hospital.addPatientToQueue(p);
            refreshQueue();
            JOptionPane.showMessageDialog(this, "Patient " + p.getName() + " added to waiting queue.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error adding patient to queue: " + ex.getMessage());
        }
    }

    private void onCallNextPatient() {
        try {
            Patient p = hospital.getNextPatientFromQueue();
            if (p == null) {
                JOptionPane.showMessageDialog(this, "The waiting queue is empty.");
                return;
            }
            refreshQueue();
            JOptionPane.showMessageDialog(this, "Calling next patient: " + p.getName() + " (ID: " + p.getID() + ")");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error calling next patient: " + ex.getMessage());
        }
    }

	    protected void refreshQueue() {
	        queueModel.setRowCount(0);
	for (Patient p : hospital.listWaitingQueue()) { 
	            queueModel.addRow(new Object[]{
	                p.getID(), p.getName(), p.getIllness()
	            });
	        }
	    }
	    
	    protected void refreshDoctors() {
	        doctorModel.setRowCount(0);
	        for (Doctor d : hospital.listDoctors()) {
	            doctorModel.addRow(new Object[]{
	                d.getID(), d.getName(), d.getSpecialization(), d.getPhone()
	            });
	        }
	    }

}