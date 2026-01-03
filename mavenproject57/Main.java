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

public class Main {
    public static void main(String[] args) {
       
        Hospital hospital = new Hospital("City Hospital", "Main Street");

        Doctor d1 = new Doctor("Cardiology", "Cardio Dept", 100,
                "Dr. Samir", 45, "Male", "123456789", "Beirut");
        hospital.addDoctor(d1);

        Patient p1 = new Patient(200, "Ali", 30, "Male",
                "987654321", "Tripoli", "Flu", "Admitted", "Room 12");
        hospital.addPatient(p1);

        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame(hospital);
            loginFrame.setVisible(true);
        });
    }
}
