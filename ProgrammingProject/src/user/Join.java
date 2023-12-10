package user;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.*;
import javax.swing.*;


public class Join extends JDialog  {
	
	 
	 private JPanel panel = new JPanel();
	 private JLabel idLabel = new JLabel("아이디");
	 private JTextField idText = new JTextField();
	 public static String idcheck = "";
	 private JLabel pwLabel = new JLabel("비밀번호");
	 private JPasswordField pwText = new JPasswordField();
	 private JLabel pwCheckLabel = new JLabel("비밀번호 확인");
	 private JPasswordField pwCheckText = new JPasswordField();
	 private JLabel nameLabel = new JLabel("이름");
	 private JTextField nameText = new JTextField();
	 private JLabel birthDateLabel = new JLabel("생년월일");
	 private JTextField birthDateText = new JTextField();
	 private JLabel phoneNumLabel = new JLabel("핸드폰 번호");
	 private JTextField phoneNumText = new JTextField();
	 private JButton joinBtn = new JButton("회원가입 하기");
	 private JButton idDupBtn = new JButton("중복확인");
	 
	 private boolean userProgress = false;
	 private boolean idDup = false;

	 public Join() {
		
		getContentPane().setFont(new Font(null, Font.PLAIN, 10));
		setTitle("회원가입 하기");
		setSize(350, 350);;
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
		
		joinBtn.setFont(new Font(null, Font.PLAIN, 20));
		joinBtn.setBounds(15, 250, 300, 50);
		panel.add(joinBtn);
		
		
		idDupBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = idText.getText();
                String pw = new String(pwText.getPassword());
                
//                if (!isValidInput(id, password)) {
//                    JOptionPane.showMessageDialog(null, "입력이 유효하지 않습니다", "Message", JOptionPane.WARNING_MESSAGE);
//                    return;
//                }
                
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
		
		emptyCheck();
		

	
	}
	 

	 
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
	 
	 
	 
	 
	 private void emptyCheck(){
		 
		 joinBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean idcheck = idText.getText().matches("^[a-zA-Z][a-zA-Z0-9]{3,19}$");
				boolean pwcheck = pwText.getText().matches("^[ㄱ-ㅎ가-힣]*$");
				boolean namecheck = nameText.getText().matches("^[ㄱ-ㅎ가-힣]*$");
				
				if(idText.getText().trim().length()==0 || idText.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "아이디를 입력하시오.", "Message", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(idcheck = false) {
					JOptionPane.showMessageDialog(null, "id", "Message", JOptionPane.WARNING_MESSAGE);
					return;
				}
				System.out.println(idcheck);
				
				if (idDup == false) {
					JOptionPane.showMessageDialog(null, "중복확인을 하십시오.", "Message", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if(pwText.getText().trim().length()==0) {
					JOptionPane.showMessageDialog(null, "비밀번호를 입력하시오.", "Message", JOptionPane.WARNING_MESSAGE);
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
				System.out.println(namecheck);
				
				if(birthDateText.getText().trim().length()==0 || birthDateText.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "생년월일을 입력하시오.", "Message", JOptionPane.WARNING_MESSAGE);
					return;
				}		
				
				if(phoneNumText.getText().trim().length()==0 || phoneNumText.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "핸드폰 번호를 입력하시오.", "Message", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				String txt = idText.getText()+"/"+pwText.getText();
				txt+="\n";
				
				String fileName = "/Users/boou/git/ProgrammingProject/ProgrammingProject/src/resources/idpw.txt" ;
				
				
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
