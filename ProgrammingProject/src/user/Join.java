package user;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;

public class Join extends JDialog{
	 
	 private JLabel idLabel = new JLabel("ID");
	 private JTextField idText = new JTextField();
	 private JLabel pwLabel = new JLabel("Password");
	 private JPasswordField pwText = new JPasswordField();
	 private JLabel pwCheckLabel = new JLabel("Confirm Password");
	 private JPasswordField pwCheckText = new JPasswordField();
	 private JLabel nameLabel = new JLabel("Name");
	 private JTextField nameText = new JTextField();
	 private JLabel birthDateLabel = new JLabel("Birth of Date");
	 private JTextField birthDateText = new JTextField();
	 private JLabel phoneNumLabel = new JLabel("Phone Number");
	 private JTextField phoneNumText = new JTextField();
	 private JButton joinBtn = new JButton("JOIN");

	 public Join() {
		
		getContentPane().setFont(new Font(null, ALLBITS, ABORT));
		setTitle("Create Account");
		setSize(WIDTH, HEIGHT);;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null); 
		setResizable(false); 
		getContentPane().setLayout(null);
		setVisible(true);
		
		idLabel.setFont(new Font(null, ALLBITS, ABORT));
		idLabel.setBounds(EXIT_ON_CLOSE, ABORT, WIDTH, HEIGHT);
		getContentPane().add(idLabel);
		
		idText.setBounds(EXIT_ON_CLOSE, ABORT, WIDTH, HEIGHT);
		idText.setColumns(10);
		getContentPane().add(idText);
		
		pwLabel.setFont(new Font(null, ALLBITS, ABORT));
		pwLabel.setBounds(EXIT_ON_CLOSE, ABORT, WIDTH, HEIGHT);
		getContentPane().add(pwLabel);
		
		pwText.setBounds(EXIT_ON_CLOSE, ABORT, WIDTH, HEIGHT);
		pwText.setColumns(10);
		getContentPane().add(pwText);
		
		pwCheckLabel.setFont(new Font(null, ALLBITS, ABORT));
		pwCheckLabel.setBounds(EXIT_ON_CLOSE, ABORT, WIDTH, HEIGHT);
		getContentPane().add(pwCheckLabel);
		
		pwCheckText.setBounds(EXIT_ON_CLOSE, ABORT, WIDTH, HEIGHT);
		pwCheckText.setColumns(10);
		getContentPane().add(pwCheckText);
		
		nameLabel.setFont(new Font(null, ALLBITS, ABORT));
		nameLabel.setBounds(EXIT_ON_CLOSE, ABORT, WIDTH, HEIGHT);
		getContentPane().add(nameLabel);
		
		nameText.setBounds(EXIT_ON_CLOSE, ABORT, WIDTH, HEIGHT);
		nameText.setColumns(10);
		getContentPane().add(nameText);
		
		birthDateLabel.setFont(new Font(null, ALLBITS, ABORT));
		birthDateLabel.setBounds(EXIT_ON_CLOSE, ABORT, WIDTH, HEIGHT);
		getContentPane().add(birthDateLabel);
		
		birthDateText.setBounds(EXIT_ON_CLOSE, ABORT, WIDTH, HEIGHT);
		birthDateText.setColumns(10);
		getContentPane().add(birthDateText);
		
		phoneNumLabel.setFont(new Font(null, ALLBITS, ABORT));
		phoneNumLabel.setBounds(EXIT_ON_CLOSE, ABORT, WIDTH, HEIGHT);
		getContentPane().add(phoneNumLabel);
		
		phoneNumText.setBounds(EXIT_ON_CLOSE, ABORT, WIDTH, HEIGHT);
		phoneNumText.setColumns(10);
		getContentPane().add(phoneNumText);
		
		joinBtn.setFont(new Font(null, ALLBITS, ABORT));
		joinBtn.setBounds(EXIT_ON_CLOSE, ABORT, WIDTH, HEIGHT);
		getContentPane().add(joinBtn);
		

	
	}
	 
	 
	 private void valueCheck(){
		 joinBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(idText.getText().trim().length()==0 || idText.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Message", "Enter ID", JOptionPane.WARNING_MESSAGE);
					idText.grabFocus();
					return;
				}
				
				if(pwText.getText().trim().length()==0) {
					JOptionPane.showMessageDialog(null, "Message", "Enter PASSWORD", JOptionPane.WARNING_MESSAGE);
					pwText.grabFocus();
					return;
				}
				
				if(pwCheckText.getText().trim().length()==0) {
					JOptionPane.showMessageDialog(null, "Message", "Enter CONFRIM PASSWORD", JOptionPane.WARNING_MESSAGE);
					pwCheckText.grabFocus();
					return;
				}
				
				if(!(pwText.getText().trim().equals(pwCheckText.getText().trim()))) {
					JOptionPane.showMessageDialog(null, "Message", "PASSWORD is not the same", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if(nameText.getText().trim().length()==0 || nameText.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "", "", JOptionPane.WARNING_MESSAGE);
					nameText.grabFocus();
					return;
				}
				
				if(birthDateText.getText().trim().length()==0 || birthDateText.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, ".", "", JOptionPane.WARNING_MESSAGE);
					birthDateText.grabFocus();
					return;
				}		
				
				if(phoneNumText.getText().trim().length()==0 || phoneNumText.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "", "", JOptionPane.WARNING_MESSAGE);
					phoneNumText.grabFocus();
					return;
				}
				
			}
		});
	 }
	 
	 
	
	 
	 
	 
}
