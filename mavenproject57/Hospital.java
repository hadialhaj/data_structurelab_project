/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject57;

/**
 *
 * @author Hadi
 */
public class Hospital {
    // Core fields
    private String hospitalName;
    private String address;
    patientDoublyLinkedList patientList;
    doctorDoublyLinkedList doctorList;
    AppointmentTree appointmentTree;
    PatientQueue waitingQueue;

    public Hospital(String hospitalName, String address) {
        this.hospitalName = hospitalName;
        this.address = address;
        this.patientList = new patientDoublyLinkedList();
        this.doctorList = new doctorDoublyLinkedList();
        this.appointmentTree = new AppointmentTree();
        this.waitingQueue = new PatientQueue();
    }

    // Patients
    public void addPatient(Patient p) {
        patientList.add(p);
    }

    public void removePatientById(int patientID) {
        if (patientID <= 0) throw new IllegalArgumentException("Patient ID must be positive.");
        Patient patient = patientList.findPatientById(patientID);
        if (patient == null) throw new IllegalArgumentException("Patient not found with ID: " + patientID);
        patientList.removePatientById(patientID);
    }

    public Patient findPatientById(int patientID) {
        if (patientID <= 0) throw new IllegalArgumentException("Patient ID must be positive.");
        Patient patient = patientList.findPatientById(patientID);
        if (patient == null) throw new IllegalArgumentException("Patient not found with ID: " + patientID);
        return patient;
    }

    public Patient findPatientByName(String patientName) {
        return patientList.findPatientByName(patientName);
    }

    public void updatePatient(int patientID, Patient newData) {
        boolean updated = patientList.updatePatientById(patientID, newData);
        if (!updated) {
            System.out.println("Patient not found!");
        }
    }

    public boolean hasPatientId(int id) {
        return patientList.findPatientById(id) != null;
    }

    public java.util.List<Patient> listPatients() {
        return patientList.getAllPatients();
    }

    // Doctors
    public void addDoctor(Doctor d) {
        doctorList.add(d);
    }

    public void removeDoctorById(int doctorID) {
        if (doctorID <= 0) throw new IllegalArgumentException("Doctor ID must be positive.");
        Doctor doctor = doctorList.findDoctorById(doctorID);
        if (doctor == null) throw new IllegalArgumentException("Doctor not found with ID: " + doctorID);
        doctorList.removeDoctorById(doctorID);
    }

    public Doctor findDoctorById(int doctorID) {
        if (doctorID <= 0) throw new IllegalArgumentException("Doctor ID must be positive.");
        Doctor doctor = doctorList.findDoctorById(doctorID);
        if (doctor == null) throw new IllegalArgumentException("Doctor not found with ID: " + doctorID);
        return doctor;
    }

    public java.util.List<Doctor> listDoctors() {
        return doctorList.getAllDoctors();
    }

    // Appointments
  public void addAppointment(Appointment a) {
    if (isScheduleConflict(a.getDateTime(), a.getDoctor(), a.getPatient())) {
        System.out.println("Schedule conflict! Appointment not added.");
        return;
    }
    appointmentTree.add(a);
}

    public void removeAppointmentById(int appointmentID) {
        if (appointmentID <= 0) throw new IllegalArgumentException("Appointment ID must be positive.");
        Appointment ap = appointmentTree.findById(appointmentID);
        if (ap == null) throw new IllegalArgumentException("Appointment not found with ID: " + appointmentID);
        appointmentTree.remove(appointmentID);
    }

    public Appointment findAppointmentById(int appointmentID) {
        if (appointmentID <= 0) throw new IllegalArgumentException("Appointment ID must be positive.");
        Appointment ap = appointmentTree.findById(appointmentID);
        if (ap == null) throw new IllegalArgumentException("Appointment not found with ID: " + appointmentID);
        return ap;
    }

    public void updateAppointment(int appointmentID, Appointment newData) {
        Appointment existing = appointmentTree.findById(appointmentID);
        if (existing == null) {
            System.out.println("Appointment not found!");
            return;
        }
        // Keep the BST consistent: remove then add the updated appointment
        appointmentTree.remove(appointmentID);
        appointmentTree.add(newData);
    }

    public boolean hasAppointmentId(int id) {
        return appointmentTree.findById(id) != null;
    }

    public java.util.List<Appointment> listAppointments() {
        return appointmentTree.getAllAppointments();
    }

    // Convenience updates
    public void cancelAppointmentById(int appointmentID) {
        Appointment ap = appointmentTree.findById(appointmentID);
        if (ap == null) {
            System.out.println("Appointment not found!");
            return;
        }
        ap.cancel();
        System.out.println("Appointment cancelled successfully");
    }

   public void rescheduleAppointmentById(int appointmentID, String newDateTime) {
    Appointment ap = appointmentTree.findById(appointmentID);

    if (ap == null) {
        System.out.println("Appointment not found!");
        return;
    }

    if (isScheduleConflict(newDateTime, ap.getDoctor(), ap.getPatient())) {
        System.out.println("Schedule conflict! Cannot reschedule.");
        return;
    }

    ap.reschedule(newDateTime);
    System.out.println("Appointment rescheduled to: " + newDateTime);
}

    public void updatePatientStatus(int patientID, String newStatus) {
        Patient patient = findPatientById(patientID);
        if (patient != null) {
            patient.updateStatus(newStatus);
        } else {
            System.out.println("Patient not found!");
        }
    }

    public void updateDoctorDepartment(int doctorID, String newDept) {
        Doctor doctor = findDoctorById(doctorID);
        if (doctor != null) {
            doctor.changeDepartment(newDept);
        } else {
            System.out.println("Doctor not found!");
        }
    }

    public void updatePatientRoom(int patientID, String newRoom) {
        Patient patient = findPatientById(patientID);
        if (patient != null) {
            patient.changeRoom(newRoom);
        } else {
            System.out.println("Patient not found!");
        }
    }

public boolean hasDoctorId(int id) {
        if (id <= 0) return false; // don't throw on pre-checks
        return doctorList.findDoctorById(id) != null;
    }

    

public boolean isScheduleConflict(String dateTime, Doctor doctor, Patient patient) {
    for (Appointment ap : appointmentTree.getAllAppointments()) {
        // If same doctor OR same patient has another appointment at the same time → conflict
        if (ap.getDateTime().equals(dateTime)) {
            if (ap.getDoctor().getID() == doctor.getID() || ap.getPatient().getID() == patient.getID()) {
                return true;
            }
        }
    }
    return false;
}

    // Waiting Queue Management
    public void addPatientToQueue(Patient p) {
        waitingQueue.enqueue(p);
    }

    public Patient getNextPatientFromQueue() {
        return waitingQueue.dequeue();
    }

    public java.util.List<Patient> listWaitingQueue() {
        return waitingQueue.getAllPatients();
    }

    /**
     * Checks if a patient has an active (non-cancelled) appointment.
     * @param patientID The ID of the patient.
     * @return true if an active appointment is found, false otherwise.
     */
    public boolean hasActiveAppointmentForPatient(int patientID) {
        for (Appointment ap : appointmentTree.getAllAppointments()) {
            if (ap.getPatient().getID() == patientID) {
                // Assuming "Cancelled" is the only status that makes an appointment inactive
                if (!"Cancelled".equalsIgnoreCase(ap.getStatus())) {
                    return true;
                }
            }
        }
        return false;
    }



}