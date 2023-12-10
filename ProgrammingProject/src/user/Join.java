package user;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Pattern;

import javax.swing.*;


public class Join extends JDialog  {
	
	 
	 private JPanel panel = new JPanel();
	 private JLabel idLabel = new JLabel("아이디");
	 protected static JTextField idText = new JTextField();
	 private JLabel pwLabel = new JLabel("비밀번호");
	 protected static JPasswordField pwText = new JPasswordField();
	 private JLabel pwCheckLabel = new JLabel("비밀번호 확인");
	 private JPasswordField pwCheckText = new JPasswordField();
	 private JLabel nameLabel = new JLabel("이름");
	 protected static JTextField nameText = new JTextField();
	 private JLabel birthDateLabel = new JLabel("생년월일");
	 protected static JTextField birthDateText = new JTextField();
	 private JLabel phoneNumLabel = new JLabel("핸드폰 번호");
	 protected static JTextField phoneNumText = new JTextField();
	 private JLabel emailLabel = new JLabel("이메일");
	 protected static JTextField emailText = new JTextField();
	 private JButton joinBtn = new JButton("회원가입 하기");
	 private JButton idDupBtn = new JButton("중복확인");
	 
	 private boolean IDcheck;
	 private boolean userProgress = false;
	 private boolean idDup = false;
	 private boolean emailDup = false;

	 public Join() {
		
		// frame 사이즈 위치 지정
		getContentPane().setFont(new Font(null, Font.PLAIN, 10));
		setTitle("회원가입 하기");
		setSize(350, 400);;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null); 
		setResizable(false); 
		getContentPane().setLayout(null);
		this.setVisible(true);
		
		panel.setBounds(10, 10, 330, 580);
		getContentPane().add(panel);
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		
		idLabel.setFont(new Font(null, Font.PLAIN, 15));
		idLabel.setBounds(62, 20, 40, 20);
		panel.add(idLabel);
		
		idText.setBounds(120, 20, 180, 20);
		idText.setColumns(20);
		panel.add(idText);
		
		idDupBtn.setFont(new Font(null, Font.PLAIN, 13));
		idDupBtn.setBounds(124, 50, 173, 20);
		panel.add(idDupBtn);
		
		pwLabel.setFont(new Font(null, Font.PLAIN, 15));
		pwLabel.setBounds(50, 80, 60, 20);
		panel.add(pwLabel);
		
		pwText.setBounds(120, 80, 180, 20);
		pwText.setColumns(20);
		panel.add(pwText);
		
		pwCheckLabel.setFont(new Font(null, Font.PLAIN, 15));
		pwCheckLabel.setBounds(20, 115, 100, 20);
		panel.add(pwCheckLabel);
		
		pwCheckText.setBounds(120, 115, 180, 20);
		pwCheckText.setColumns(20);
		panel.add(pwCheckText);
		
		nameLabel.setFont(new Font(null, Font.PLAIN, 15));
		nameLabel.setBounds(77, 150, 40, 20);
		panel.add(nameLabel);
		
		nameText.setBounds(120, 150, 180, 20);
		nameText.setColumns(20);
		panel.add(nameText);
		
		birthDateLabel.setFont(new Font(null, Font.PLAIN, 15));
		birthDateLabel.setBounds(50, 185, 60, 20);
		panel.add(birthDateLabel);
		
		birthDateText.setBounds(120, 185, 180, 20);
		birthDateText.setColumns(20);
		panel.add(birthDateText);
		
		phoneNumLabel.setFont(new Font(null, Font.PLAIN, 15));
		phoneNumLabel.setBounds(32, 220, 100, 20);
		panel.add(phoneNumLabel);
		
		phoneNumText.setBounds(120, 220, 180, 20);
		phoneNumText.setColumns(20);
		panel.add(phoneNumText);
		
		emailLabel.setFont(new Font(null, Font.PLAIN, 15));
		emailLabel.setBounds(62, 255, 40, 20);
		panel.add(emailLabel);
		
		emailText.setBounds(120, 255, 180, 20);
		emailText.setColumns(20);
		panel.add(emailText);
		
		joinBtn.setFont(new Font(null, Font.PLAIN, 20));
		joinBtn.setBounds(15, 290, 300, 50);
		panel.add(joinBtn);
		
		// 아이디 중복확인 버튼 클릭시
		idDupBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = idText.getText();
                String pw = new String(pwText.getPassword());
                

                
                try {
					if (idDuplicate(id)) {
					    JOptionPane.showMessageDialog(null, "이미 존재하는 아이디입니다.", "Message", JOptionPane.WARNING_MESSAGE);
					   
					    return;
					}
					else {
						JOptionPane.showMessageDialog(null, "사용 가능한 아이디입니다.", "Message", JOptionPane.WARNING_MESSAGE);
						idDup = true;
						return;
				}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		FocusEvent();
		emptyCheck();
		

	
	}
	 

	 // 아이디 파일 읽은 후 중복확인 메소드
	 private static boolean idDuplicate(String id) throws IOException {
	        try (BufferedReader reader = new BufferedReader(new FileReader("/Users/boou/git/ProgrammingProject/ProgrammingProject/src/resources/idpw.txt"))) {
	            String line;
	            
	            try {
					while ((line = reader.readLine()) != null) {
					    if (line.contains("" + id + "/")) {
					        return true;
					    }
					    else {
					    	
					    }
					}
	            } catch (FileNotFoundException e) {
	            	e.printStackTrace();
	        		}
	            return false;
	        }
	    }
	 private static boolean emailDuplicate(String email) throws IOException {
		 try (BufferedReader reader = new BufferedReader(new FileReader("/Users/boou/git/ProgrammingProject/ProgrammingProject/src/resources/idpw.txt"))) {
	            String line;
	            
	            try {
					while ((line = reader.readLine()) != null) {
					    if (line.contains(email)) {
					    	System.out.println(line);
					        return true;
					    }
					    else {
					    	
					    }
					}
	            } catch (FileNotFoundException e) {
	            	e.printStackTrace();
	        		}
	            return false;
	        }
	    }
	 
	 private void FocusEvent() {
			idText.addFocusListener(new FocusListener() {
				public void focusLost(FocusEvent e) {				
					if(idText.getText().trim().length()==0) {
						idText.setText("아이디");
					}
				}
				public void focusGained(FocusEvent e) {				
					if(idText.getText().trim().equals("아이디")) {
						idText.setText("");
					}				
				}
			});
			
			nameText.addFocusListener(new FocusListener() {
				public void focusLost(FocusEvent e) {
					if(nameText.getText().trim().length()==0) {
						nameText.setText("이름");
					}
				}
				
				public void focusGained(FocusEvent e) {			
					if(nameText.getText().trim().equals("이름")) {
						nameText.setText("");
					}
				}
			});
			
			pwText.addFocusListener(new FocusListener() {
				public void focusLost(FocusEvent e) {
					if(pwText.getText().trim().length()==0) {
						pwText.setText("생일 년도");
					}
				}

				public void focusGained(FocusEvent e) {
					if(pwText.getText().trim().equals("생일 년도")) {
						pwText.setText("");
					}
				}
			});
			
			birthDateText.addFocusListener(new FocusListener() {
				public void focusLost(FocusEvent e) {
					if(birthDateText.getText().trim().length()==0) {
						birthDateText.setText("생일 일자");
					}
				}

				public void focusGained(FocusEvent e) {
					if(birthDateText.getText().trim().equals("생일 일자")) {
						birthDateText.setText("");
					}
				}
			});
			
			phoneNumText.addFocusListener(new FocusListener() {
				public void focusLost(FocusEvent e) {
					if(phoneNumText.getText().trim().length()==0) {
						phoneNumText.setText("핸드폰 번호");
					}
				}

				public void focusGained(FocusEvent e) {
					if(phoneNumText.getText().trim().equals("핸드폰 번호")) {
						phoneNumText.setText("");
					}
				}
			});	
	 }
	 
	 // 회원가입시 정보 값이 비어있을 때와 유효성 검사
	 private void emptyCheck(){
		 
		 joinBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IDcheck idchk = new IDcheck();
				PWcheck pwchk = new PWcheck();
				Namecheck namechk = new Namecheck();
				Birthcheck birthchk = new Birthcheck();
				PhoneNumcheck phonenumchk = new PhoneNumcheck();
				Emailcheck emailchk = new Emailcheck();
				
				String email = emailText.getText();
				
			
	
               

                
				
				
				if(idText.getText().trim().length()==0 || idText.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "아이디를 입력하시오.", "Message", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(!idchk.idcheck()) {
					JOptionPane.showMessageDialog(null, "올바른 아이디 형식이 아닙니다.", "Message", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if (idDup == false) {
					JOptionPane.showMessageDialog(null, "중복확인을 하십시오.", "Message", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if(pwText.getText().trim().length()==0) {
					JOptionPane.showMessageDialog(null, "비밀번호를 입력하시오.", "Message", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(!pwchk.pwcheck()) {
					JOptionPane.showMessageDialog(null, "최소 8자리이며 숫자, 문자, 특수문자가 포함되어야 합니다.", "Message", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if(pwCheckText.getText().trim().length()==0) {
					JOptionPane.showMessageDialog(null, "비밀번호 확인을 입력하시오.", "Message", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if(!(pwText.getText().trim().equals(pwCheckText.getText().trim()))) {
					JOptionPane.showMessageDialog(null, "비밀번호가 같지 않습니다.", "Message", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if(nameText.getText().trim().length()==0 || nameText.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "이름을 입력하시오.", "Message", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if(!namechk.namecheck()) {
					JOptionPane.showMessageDialog(null, "ex)홍길동 \n 이름이 올바른 형식이 아닙니다.", "Message", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if(birthDateText.getText().trim().length()==0 || birthDateText.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "생년월일을 입력하시오.", "Message", JOptionPane.WARNING_MESSAGE);
					return;
				}		
				
				if(!birthchk.birthcheck()) {
					JOptionPane.showMessageDialog(null, "ex)19101212 \n 생년월일을 8자리로 입력하시오.", "Message", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if(phoneNumText.getText().trim().length()==0 || phoneNumText.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "핸드폰 번호를 입력하시오.", "Message", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if(!phonenumchk.phonenumcheck()) {
					JOptionPane.showMessageDialog(null, "ex)01012345678 \n 올바른 핸드폰 번호 형식이 아닙니다. ", "Message", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if(emailText.getText().trim().length()==0 || emailText.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "이메일을 입력하시오.", "Message", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if(!emailchk.emailcheck()) {
					JOptionPane.showMessageDialog(null, "ex)perfect123@harmorny.com \n 올바른 이메일 형식이 아닙니다. ", "Message", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				try {
					if (emailDuplicate(email)) {
					    JOptionPane.showMessageDialog(null, "이미 가입된 아이디가 있습니다.", "Message", JOptionPane.WARNING_MESSAGE);
					    return;
					}
					else {
						emailDup = true;
						return;
				}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				

				
				String txt = idText.getText()+"/"+pwText.getText()+"/"+emailText.getText();
				txt+="\n";
				
				String fileName = "/Users/boou/git/ProgrammingProject/ProgrammingProject/src/resources/idpw.txt" ;
				
				
				// 문제 없을 시 아이디 비밀번호 이메일 .txt 파일로 저장
				try{
					BufferedWriter fw = new BufferedWriter(new FileWriter(fileName, true));
					
					// 파일안에 문자열 쓰기
					fw.write(txt);
					fw.flush();

					// 객체 닫기
					fw.close();					
					
				}catch(Exception errmsg){
					errmsg.printStackTrace();
				}
				
				
								
				userProgress = true;				
				
				JOptionPane.showMessageDialog(null, "회원 가입이 완료 되었습니다.","회원 가입 완료.", JOptionPane.WARNING_MESSAGE);
								
				if(userProgress = true) {
					setVisible(false);
				}
			}
		});
		 
	 
		 
		 
	 
	 }
				
			
		
	
	 

	
	 
	 
	
	 
	 
	 
}
