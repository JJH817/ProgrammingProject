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
		setSize(500, 500); // 사이즈
		setTitle("예매 프로그램 로그인");
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		logoPanel.setBounds(EXIT_ON_CLOSE, ABORT, WIDTH, HEIGHT);
		getContentPane().add(logoPanel);
		logoPanel.setBackground(Color.WHITE);
		
		idpwPanel.setBounds(EXIT_ON_CLOSE, ABORT, WIDTH, HEIGHT);
		getContentPane().add(idpwPanel);
		idpwPanel.setBackground(Color.WHITE);
		
		idLabel.setFont(new Font(null, ALLBITS, ABORT));
		idLabel.setBounds(EXIT_ON_CLOSE, ABORT, WIDTH, HEIGHT);
		idpwPanel.add(idLabel);
		
		idTextField.setColumns(10);
		idTextField.setBounds(EXIT_ON_CLOSE, ABORT, WIDTH, HEIGHT);
		idpwPanel.add(idTextField);
		
		loginBtn.setBounds(EXIT_ON_CLOSE, ABORT, WIDTH, HEIGHT);
		idpwPanel.add(loginBtn);
		
		pwTextField.setColumns(10);
		pwTextField.setBounds(EXIT_ON_CLOSE, ABORT, WIDTH, HEIGHT);
		idpwPanel.add(pwTextField);
		
		pwLabel.setFont(new Font(null, ALLBITS, ABORT));
		pwLabel.setBounds(EXIT_ON_CLOSE, ABORT, WIDTH, HEIGHT);
		idpwPanel.add(pwLabel);
		
		joinfindPanel.setBounds(EXIT_ON_CLOSE, ABORT, WIDTH, HEIGHT);
		getContentPane().add(joinfindPanel);
		joinfindPanel.setBackground(Color.WHITE);
		getContentPane().add(joinfindPanel);
		
		idfindBtn.setBounds(EXIT_ON_CLOSE, ABORT, WIDTH, HEIGHT);
		joinfindPanel.add(idfindBtn);

		pwfindBtn.setBounds(EXIT_ON_CLOSE, ABORT, WIDTH, HEIGHT);
		joinfindPanel.add(pwfindBtn);
		
		joinBtn.setBounds(EXIT_ON_CLOSE, ABORT, WIDTH, HEIGHT);
		joinfindPanel.add(joinBtn);
		
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id = idTextField.getText().trim();
				String pw = pwTextField.getText().trim();
				String login = "";
				
				if(id.length()==0 || pw.length()==0) {
					JOptionPane.showMessageDialog(null, "Message", "Enter your ID or password!", JOptionPane.DEFAULT_OPTION);
					return;
				}
				
				try{
					BufferedReader reader = new BufferedReader(new FileReader(/* idpw.txt 경로 */));
					
				        String str;
				        ArrayList<String> txtmember = new ArrayList<>();
				        while ((str = reader.readLine()) != null) {
				        	txtmember.add(str);
				        }   
				       
				        reader.close();
				        
				        
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
					
				}catch(Exception errmsg){
					errmsg.printStackTrace();
				}
				
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


}
