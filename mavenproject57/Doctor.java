/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject57;

/**
 *
 * @author Hadi
 */
public class Doctor extends Person {

    private String Specialization;
    private String department;

    public Doctor(String Specialization, String department, int id, String name, int age, String gender, String phone, String address) {
        super(id, name, age, gender, phone, address);
        this.Specialization = Specialization;
        this.department = department;
    }


    public String getSpecialization() {
        return Specialization;
    }

    public void setSpecialization(String Specialization) {
        this.Specialization = Specialization;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void changeDepartment(String newDepartment) {
        this.department = newDepartment;

    }

    public void updateSpecialization(String newSpecialization) {
        this.Specialization = newSpecialization;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Specialization: " + getSpecialization());
        System.out.println("Department: " + getDepartment());

    }

}
