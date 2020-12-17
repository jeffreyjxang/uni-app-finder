package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class CreateAccount extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField userNameField;
	private JPasswordField passwordField;
	private JPasswordField confirmPasswordField;
	private JButton createAccountBtn;
	static JFrame frame = new JFrame();
	private JButton returnBtn;
	public static String username;
	public static String password;
	public static boolean newAccount = false;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public CreateAccount() {
		frame.setVisible(true);
		frame.setBounds(100, 100, 257, 406);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		frame.getContentPane().add(contentPane);
		
		JLabel createAccount = new JLabel("Create a new Account");
		createAccount.setFont(new Font("Tahoma", Font.BOLD, 20));
		createAccount.setBounds(10, 19, 244, 37);
		contentPane.add(createAccount);
		
		JLabel userName = new JLabel("User Name");
		userName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		userName.setBounds(10, 67, 106, 14);
		contentPane.add(userName);
		
		userNameField = new JTextField();
		userNameField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		userNameField.setBounds(10, 86, 96, 20);
		contentPane.add(userNameField);
		userNameField.setColumns(10);
		
		JLabel passwordLbl = new JLabel("Password");
		passwordLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordLbl.setBounds(10, 117, 106, 14);
		contentPane.add(passwordLbl);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordField.setBounds(10, 142, 96, 20);
		contentPane.add(passwordField);
		
		JLabel retypePasswordLbl = new JLabel("Retype Password");
		retypePasswordLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		retypePasswordLbl.setBounds(10, 173, 130, 20);
		contentPane.add(retypePasswordLbl);
		
		confirmPasswordField = new JPasswordField();
		confirmPasswordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		confirmPasswordField.setBounds(10, 200, 96, 20);
		contentPane.add(confirmPasswordField);
		
		createAccountBtn = new JButton("Create Account");
		createAccountBtn.setBounds(10, 247, 130, 23);
		createAccountBtn.addActionListener(this);
		contentPane.add(createAccountBtn);
		
		returnBtn = new JButton("Return");
		returnBtn.setBounds(156, 333, 75, 23);
		returnBtn.addActionListener(this);
		contentPane.add(returnBtn);
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		if (event.getSource() == createAccountBtn) {
			
			username = userNameField.getText();
			password = String.valueOf(passwordField.getPassword());
			
			if (!VerifyLogin.existingUsername(username)) {
				JOptionPane.showMessageDialog(contentPane, "Username already exists.");
			} else if (!VerifyLogin.verifyPassword(password, String.valueOf(confirmPasswordField.getPassword()))){
				JOptionPane.showMessageDialog(contentPane, "Passwords do not match!");
			} else if (VerifyLogin.unwantedCharacter(username, password)) { 
				JOptionPane.showMessageDialog(contentPane, "Commas & Spaces are prohibited!");
			} else {
				try {
					VerifyLogin.saveLogin(username, password);
					newAccount = true;
					Welcome.GUI.setVisible(true);
					Welcome.welcomePanel.setVisible(false);
					Dashboard.CreateDashboard();
					Dashboard.dashboardPanel.setVisible(true);
				} catch (IOException e) {
					System.out.println("error");
				}
			}
		} 
		
		if(event.getSource() == returnBtn) {
			frame.dispose();
			Welcome.CreateWelcome();
		}
		
	}
}
