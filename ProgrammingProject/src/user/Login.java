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

public class Login extends JFrame {
	
	private JFrame frame = new JFrame();
	private JPanel contentPane;
	private JTextField idTextField;
	private JPasswordField passwordTextField;
	private JTextField nametextField;
	private JTextField phonetextField;
	private JTextField birthtextField;
	private JTextField emailtextField;
	
	public static String id = "";
	public static String password = "";
	
	public static String name = "";
	public static String phone_num = "";
	public static String birth_date = "";
	public static String email = "";

	
	
	
	public void Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(211, 211, 211));
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(10, 10, 1000, 700);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		JPanel login = new JPanel();
		login.setBackground(new Color(255, 255, 255));
		layeredPane.add(login, "");
		login.setLayout(null);

		JLabel id_label = new JLabel("아이디");
		id.setFont(new Font("폰트 이름", Font.BOLD, 30));
		id.setBounds(EXIT_ON_CLOSE, ABORT, WIDTH, HEIGHT);
		login.add(id);
		
		JLabel password_label = new JLabel("비밀번호");
		id.setFont(new Font("폰트 이름", Font.BOLD, 30));
		id.setBounds(EXIT_ON_CLOSE, ABORT, WIDTH, HEIGHT);
		login.add(password);
		
		idTextField = new JTextField();
		idTextField.setFont(new Font("폰트 이름", Font.BOLD, 30));
		idTextField.setBounds(EXIT_ON_CLOSE, ABORT, WIDTH, HEIGHT);
		login.add(idTextField);
		
		passwordTextField = new JPasswordField();
		passwordTextField.setFont(new Font("폰트 이름", Font.BOLD, 30));
		passwordTextField.setBounds(EXIT_ON_CLOSE, ABORT, WIDTH, HEIGHT);
		login.add(passwordTextField);
		
		JButton loginbtn = new JButton("로그인");
		loginbtn.setBackground(new Color(211, 211, 211));
		loginbtn.setForeground(Color.WHITE);
		loginbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				id = idTextField.getText();
				password = passwordTextField.getText();
				
				// 로그인 데이터베이스
				
				System.out.println("로그인 성공 or 실패" ); // + 로그인 정보 데이터베이스 결과
				
			}
		});
		
		
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}

	public static void main(String[] args) {
		new Login();

	}

}
