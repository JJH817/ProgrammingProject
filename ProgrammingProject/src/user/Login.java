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
	private JButton loginButton, joinButton;
	private JLabel loginbtnLogo, loginbtnSearch; 
	
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
		
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}

	public static void main(String[] args) {
		new Login();

	}

}
