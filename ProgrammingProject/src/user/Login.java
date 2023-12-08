package user;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.io.*;

import java.util.*;

public class Login extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private LogoPanel logoPanel = new LogoPanel();
	private JPanel idpwPanel = new JPanel();
	private JPanel joinfindPanel = new JPanel();
	
	private JLabel idLabel = new JLabel("ID");
	private JLabel pwLabel = new JLabel("PASSWORD ");
	private JTextField idTextField = new JTextField();
	private JPasswordField pwTextField = new JPasswordField();
	private JButton loginBtn = new JButton("LOGIN");
	private JButton idfindBtn = new JButton("ID FIND");
	private JButton pwfindBtn = new JButton("PW FIND");
	private JButton joinBtn = new JButton("JOIN");
	

	
	
	
	public Login() {
		
		super("LOGIN");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(550, 350); // 사이즈
		setTitle("예매 프로그램");
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		logoPanel.setBounds(10, 10, 550, 140);
		getContentPane().add(logoPanel);
		logoPanel.setBackground(Color.WHITE);
		
		idpwPanel.setBounds(10, 160, 550, 120);
		getContentPane().add(idpwPanel);
		idpwPanel.setBackground(Color.WHITE);
		
		idLabel.setFont(new Font(null, 20, 20));
		idLabel.setBounds(20, 170, 20, 20);
		idpwPanel.add(idLabel);
		
		idTextField.setColumns(20);
		idTextField.setBounds(50, 170, 20, 20);
		idpwPanel.add(idTextField);
		
		
		loginBtn.setBounds(400, 200, 200, 200);
		idpwPanel.add(loginBtn);
		
		pwLabel.setFont(new Font(null, 20, 20));
		pwLabel.setBounds(20, 220, 20, 20);
		idpwPanel.add(pwLabel);
		
		pwTextField.setColumns(20);
		pwTextField.setBounds(50, 220, 20, 20);
		idpwPanel.add(pwTextField);
		
		
		
		joinfindPanel.setBounds(10, 280, 530, 30);
		getContentPane().add(joinfindPanel);
		joinfindPanel.setBackground(Color.WHITE);
		
		idfindBtn.setBounds(10, 70, 100, 100);
		joinfindPanel.add(idfindBtn);

		pwfindBtn.setBounds(10, 70, 50, 50);
		joinfindPanel.add(pwfindBtn);
		
		joinBtn.setBounds(10, 140, 50, 50);
		joinfindPanel.add(joinBtn);
		
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id = idTextField.getText().trim();
				String pw = pwTextField.getText().trim();
				String login = "";
				
				if(id.length()==0 || pw.length()==0) {
					JOptionPane.showMessageDialog(null, "Enter your ID or password!", "Message", JOptionPane.DEFAULT_OPTION);
					return;
				}
				
				try{
					BufferedReader reader = new BufferedReader(new FileReader("resources/idpw.txt"));
					
				        String str;
				        ArrayList<String> txtmember = new ArrayList<>();
				        try {
							while ((str = reader.readLine()) != null) {
								txtmember.add(str);
							}
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}   
				        
				        //reader.close();
				        
				        
				        HashMap<String,String> memberlist = new HashMap<>();
				        
				        for(int i=0; i<txtmember.size(); i++) {
				        	String[] tempresult = txtmember.get(i).toString().split("\\/");
				        	memberlist.put(tempresult[0],tempresult[1]);
				        }
				        		        
				        for ( String key : memberlist.keySet() ) {
				            if(id.equals(key.trim()) && pw.equals(memberlist.get(key))) {
				            	login = "Succeed";
				            }
				        }				        
				}catch(FileNotFoundException errmsg){
					errmsg.printStackTrace();
				}
				
//				catch(FileNotFoundException ) {
//					e.getStackTrace();
//				}
				
				if(login.equals("Succeed")) {
					JOptionPane.showMessageDialog(null, "Succeed", "Login Succeed", JOptionPane.DEFAULT_OPTION);
					return;
				}else {
					JOptionPane.showMessageDialog(null, "Failed", "Login Failed", JOptionPane.DEFAULT_OPTION);	
				}
				
			}
		});
		
		idfindBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		
		joinBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Join join = new Join();
	
				
				
			}
		});
		
		setResizable(false);
		setVisible(true);
		
		

		
		
		
	}

	
	public static void main(String[] args) {
		new Login();
	};

}
