package user;

import concertReservationSystem.*;

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
	
	private JLabel idLabel = new JLabel("아이디");
	private JLabel pwLabel = new JLabel("비밀번호");
	private JTextField idTextField = new JTextField();
	private JPasswordField pwTextField = new JPasswordField();
	private JButton loginBtn = new JButton("로그인");
	private JButton idfindBtn = new JButton("아이디 찾기");
	private JButton pwfindBtn = new JButton("비밀번호 찾기");
	private JButton joinBtn = new JButton("회원가입");
	

	
	
	
	public Login() {
		
		super("LOGIN");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(550, 350); // 사이즈
		setTitle("예매 프로그램");
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		logoPanel.setBounds(10, 10, 530, 140);
		getContentPane().add(logoPanel);
		logoPanel.setBackground(Color.WHITE);
		
		idpwPanel.setBounds(10, 160, 530, 120);
		idpwPanel.setLayout(null);
		idpwPanel.setBackground(Color.WHITE);
		getContentPane().add(idpwPanel);
		
		idLabel.setFont(new Font(null, Font.PLAIN, 15));
		idLabel.setBounds(20, 30, 60, 20);
		idpwPanel.add(idLabel);
		
		idTextField.setColumns(20);
		idTextField.setBounds(80, 30, 280, 20);
		idpwPanel.add(idTextField);
		
		
		loginBtn.setBounds(385, 25, 120, 70);
		loginBtn.setFont(new Font(null, Font.PLAIN, 20));
		idpwPanel.add(loginBtn);
		
		pwLabel.setFont(new Font(null, Font.PLAIN, 15));
		pwLabel.setBounds(20, 70, 60, 20);
		idpwPanel.add(pwLabel);
		
		pwTextField.setColumns(20);
		pwTextField.setBounds(80, 70, 280, 20);
		idpwPanel.add(pwTextField);
		
		
		
		joinfindPanel.setBounds(10, 280, 530, 30);
		joinfindPanel.setLayout(null);
		joinfindPanel.setBackground(Color.WHITE);
		getContentPane().add(joinfindPanel);
		
		
		idfindBtn.setBounds(10, 1, 100, 20);
		joinfindPanel.add(idfindBtn);

		pwfindBtn.setBounds(210, 1, 100, 20);
		joinfindPanel.add(pwfindBtn);
		
		joinBtn.setBounds(420, 1, 100, 20);
		joinfindPanel.add(joinBtn);
		
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id = idTextField.getText().trim();
				String pw = pwTextField.getText().trim();
				String login = "";
				
				if(id.length()==0 || pw.length()==0) {
					JOptionPane.showMessageDialog(null, "아이디와 비밀번호를 입력하세요", "Message", JOptionPane.DEFAULT_OPTION);
					return;
				}
				
				try{
					BufferedReader reader = new BufferedReader(new FileReader("Users/boou/git/ProgrammingProject/ProgrammingProject/src/resources/idpw.txt"));
					
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
					JOptionPane.showMessageDialog(null, "로그인 성공", "Message", JOptionPane.DEFAULT_OPTION);
					new ChooseDayAndTime(id);
					return;
				}else {
					JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호가 일치하지 않습니다", "Message", JOptionPane.DEFAULT_OPTION);	
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
