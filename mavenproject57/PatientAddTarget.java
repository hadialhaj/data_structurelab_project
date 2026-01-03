/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject57;

/**
 *
 * @author Hadi
 */

// Patient add/delete/update targets
 class PatientAddTarget implements ActionTarget {
    private final Hospital hospital;
    private final Patient newData;
    public PatientAddTarget(Hospital h, Patient p) { this.hospital = h; this.newData = p; }
    public void apply()  { hospital.addPatient(newData); }
    public void revert() { hospital.removePatientById(newData.getID()); }
    public String label(){ return "Patient ADD " + newData.getID(); }
}

 class PatientDeleteTarget implements ActionTarget {
    private final Hospital hospital;
    private final Patient oldData;
    public PatientDeleteTarget(Hospital h, Patient p) { this.hospital = h; this.oldData = p; }
    public void apply()  { hospital.removePatientById(oldData.getID()); }
    public void revert() { hospital.addPatient(oldData); }
    public String label(){ return "Patient DELETE " + oldData.getID(); }
}

 class PatientUpdateTarget implements ActionTarget {
    private final Hospital hospital;
    private final Patient oldData;
    private final Patient newData;
    public PatientUpdateTarget(Hospital h, Patient oldP, Patient newP) {
        this.hospital = h; this.oldData = oldP; this.newData = newP;
    }
    public void apply()  { hospital.updatePatient(newData.getID(), newData); }
    public void revert() { hospital.updatePatient(oldData.getID(), oldData); }
    public String label(){ return "Patient UPDATE " + oldData.getID(); }
}

 class AppointmentAddTarget implements ActionTarget {
    private final Hospital hospital;
    private final Appointment ap;
    public AppointmentAddTarget(Hospital h, Appointment ap){ this.hospital=h; this.ap=ap; }
    public void apply()  { hospital.addAppointment(ap); }
    public void revert() { hospital.removeAppointmentById(ap.getAppointmentID()); }
    public String label(){ return "Appointment ADD " + ap.getAppointmentID(); }
}

 class AppointmentDeleteTarget implements ActionTarget {
    private final Hospital hospital;
    private final Appointment ap;
    public AppointmentDeleteTarget(Hospital h, Appointment ap){ this.hospital=h; this.ap=ap; }
    public void apply()  { hospital.removeAppointmentById(ap.getAppointmentID()); }
    public void revert() { hospital.addAppointment(ap); }
    public String label(){ return "Appointment DELETE " + ap.getAppointmentID(); }
}

 class AppointmentUpdateTarget implements ActionTarget {
    private final Hospital hospital;
    private final Appointment oldAp;
    private final Appointment newAp;
    public AppointmentUpdateTarget(Hospital h, Appointment oldAp, Appointment newAp){ this.hospital=h; this.oldAp=oldAp; this.newAp=newAp; }
     public void apply()  { hospital.updateAppointment(newAp.getAppointmentID(), newAp); }
     public void revert() { hospital.updateAppointment(oldAp.getAppointmentID(), oldAp); }
     public String label(){ return "Appointment UPDATE " + oldAp.getAppointmentID(); }
}

