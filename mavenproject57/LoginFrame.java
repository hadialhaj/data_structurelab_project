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

public class LoginFrame extends JFrame {
    private final JTextField userField = new JTextField(15);
    private final JPasswordField passField = new JPasswordField(15);
    private final JButton loginBtn = new JButton("Login");

    public LoginFrame(Hospital hospital) {
        super("Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(350, 200);
        setLocationRelativeTo(null);

        JPanel p = new JPanel(new GridLayout(3,2,8,8));
        p.add(new JLabel("Username")); p.add(userField);
        p.add(new JLabel("Password")); p.add(passField);
        p.add(new JLabel()); p.add(loginBtn);
        add(p);

        loginBtn.addActionListener(e -> {
            String user = userField.getText();
            String pass = new String(passField.getPassword());
            login auth = new login();
            int who = auth.who(user, pass);
            if (who == 2) {
                new ReceptionFrame(hospital).setVisible(true);
                dispose();
            } else if (who == 1) {
                new ManagerFrame(hospital).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials");
            }
        });
    }
}