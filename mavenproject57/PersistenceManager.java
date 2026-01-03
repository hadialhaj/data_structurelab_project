/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject57;

/**
 *
 * @author Hadi
 */
import java.io.*;

public class PersistenceManager {

    private static final String FILE_NAME = "hospital_data.ser";


    public static void saveHospital(Hospital hospital) {
        try (FileOutputStream fileOut = new FileOutputStream(FILE_NAME);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(hospital);
            System.out.println("Hospital data saved to " + FILE_NAME);
        } catch (IOException i) {
            System.err.println("Error saving hospital data: " + i.getMessage());
        }
    }

   
    public static Hospital loadHospital() {
        try (FileInputStream fileIn = new FileInputStream(FILE_NAME);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            Hospital hospital = (Hospital) objectIn.readObject();
            System.out.println("Hospital data loaded from " + FILE_NAME);
            return hospital;
        } catch (FileNotFoundException f) {
            System.out.println("No saved data found. Starting with new data.");
            return null;
        } catch (IOException i) {
            System.err.println("Error loading hospital data: " + i.getMessage());
            return null;
        } catch (ClassNotFoundException c) {
            System.err.println("Hospital class not found: " + c.getMessage());
            return null;
        }
    }
}

