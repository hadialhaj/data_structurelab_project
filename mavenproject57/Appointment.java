/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject57;

/**
 *
 * @author Hadi
 */
public class Appointment {

    private int appointmentID;
    private Doctor doctor;
    private Patient patient;
    private String dateTime;
    private String status;

    Appointment(int appointmentID, String dateTime, String Status, Doctor doctor, Patient patient) {
        this.appointmentID = appointmentID;
        this.status = Status;
        this.dateTime = dateTime;
        this.patient = patient;
        this.doctor = doctor;

    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;

    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(int AppointmentID) {
        this.appointmentID = AppointmentID;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String Status) {
        this.status = Status;
    }

    public void reschedule(String newDateTime) {

        this.dateTime = newDateTime;
    }

    public void cancel() {

        this.status = "Cancelled";
    }

    public void displayDetails() {

        System.out.println("Doctor Details: ");
        doctor.displayDetails();
        System.out.println("Patient Details: ");
        patient.displayDetails();
        System.out.println("Appointment ID: " + getAppointmentID());
        System.out.println("Date Time: " + getDateTime());
        System.out.println("Status: " + getStatus());

    }

}