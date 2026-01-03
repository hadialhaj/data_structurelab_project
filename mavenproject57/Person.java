/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject57;

/**
 *
 * @author Hadi
 */
public class Person {

    protected int id;
    protected String name;
    protected int age;
    protected String gender;
    protected String phone;
    protected String address;

    Person(int id, String name, int age, String gender, String phone, String address) {

        this.address = address;
        this.id = id;
        this.age = age;
        this.gender = gender;
        this.name = name;
        this.phone = phone;

    }

    public void setID(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void displayDetails() {

        System.out.println("ID: " + getID());
        System.out.println("Name: " + getName());
        System.out.println("Age: " + getAge());
        System.out.println("gender: " + getGender());
        System.out.println("Address: " + getAddress());
        System.out.println("Phone: " + getPhone());
    }
}
