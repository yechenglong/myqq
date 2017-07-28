package client;

import java.awt.Frame;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Font;

/*
 * yechenglong
 * time:20170727
 * v1.1.0
 * */
public class Login extends JFrame{
 
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	
	public String u_name;
	public String u_pwd;
	public JButton loginbtn = new JButton();
	public JLabel lblNewLabel = new JLabel();
	public JButton zhucebnt = new JButton();
	public JLabel zhanghaolabel = new JLabel();
	public JLabel mimalabel = new JLabel();
	//运行
	public static void main(String[] args) {
		Login login = new Login();
		login.setVisible(true);
	}
	
	//登录界面
	public  Login()  {		
		setTitle("myQQ_Login");
		setBounds(350, 250, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Image image=new ImageIcon("C:\\Users\\Public\\Pictures\\Sample Pictures\\企鹅.jpg").getImage();
		setIconImage(image);
		
		contentPane=new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField=new JTextField();
		textField.setBounds(128, 109, 104, 21);
		textField.setOpaque(false);
		textField.setColumns(10);
		contentPane.add(textField);
		
		zhanghaolabel.setBounds(83, 109, 57, 21);
		zhanghaolabel.setText("账号");
		zhanghaolabel.setFont(new Font("宋体", Font.BOLD,12));
		zhanghaolabel.setForeground(Color.BLUE);
		contentPane.add(zhanghaolabel);
		
		passwordField=new JPasswordField();
		passwordField.setBounds(128, 153, 104, 21);
		passwordField.setForeground(Color.BLACK);
		passwordField.setEchoChar('*');
		passwordField.setOpaque(true);
		passwordField.setColumns(10);
		contentPane.add(passwordField);
		
		mimalabel.setText("密码");
		mimalabel.setForeground(Color.GREEN);
		mimalabel.setFont(new Font("宋体", Font.BOLD,12));
		mimalabel.setBounds(83, 150, 57, 26);
		contentPane.add(mimalabel);
		
		loginbtn.setFont(new Font("宋体", Font.BOLD, 12));
		loginbtn.setText("登录");
		loginbtn.setBounds(246, 216, 61, 25);
		getRootPane().setDefaultButton(loginbtn);
		contentPane.add(loginbtn);
		
		zhucebnt.setFont(new Font("宋体", Font.BOLD, 12));
		zhucebnt.setText("注册");
		zhucebnt.setBounds(316, 216, 61, 25);
		getRootPane().setDefaultButton(zhucebnt);
		contentPane.add(zhucebnt);
		
		
		//监听登录按钮
		loginbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		zhucebnt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				loginbtn.setEnabled(false);
				Resign resign = new Resign();
				resign.setVisible(true);
				setVisible(false);// 隐藏掉登陆界面
			}
		});
		
	}
	
}
