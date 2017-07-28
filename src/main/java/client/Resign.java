package client;


import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JPasswordField;;


public class Resign extends JFrame{

	private JPanel contentpanel;
	private JButton zhucebtn=new JButton();
	private JLabel zhanghaolabel=new JLabel();
	private JLabel mimalabel=new JLabel();
	private JTextField zhanghao=new JTextField();
	private JPasswordField mima=new JPasswordField();
	private JPasswordField mima2=new JPasswordField();
	public static void  main(String[] args) {
		Resign resign=new Resign();
		resign.setVisible(true);
	}
	public  Resign() {
		// TODO Auto-generated constructor stub
		setTitle("myQQ_Resign");
		setBounds(350, 250, 450, 300);	
		Image image=new ImageIcon("C:\\Users\\Public\\Pictures\\Sample Pictures\\企鹅.jpg").getImage();
		setIconImage(image);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		contentpanel=new JPanel();
		contentpanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentpanel);
		contentpanel.setLayout(null);
		
		zhanghao.setBounds(148, 42, 104, 21);
		zhanghao.setOpaque(false);
		contentpanel.add(zhanghao);
		zhanghao.setColumns(10);

		
		mima.setEchoChar('*');
		mima.setOpaque(false);
		mima.setBounds(177, 89, 104, 21);
		contentpanel.add(mima);

		mima2 = new JPasswordField();
		mima2.setBounds(177, 132, 104, 21);
		mima2.setOpaque(false);
		contentpanel.add(mima2);

		//注册按钮
		zhucebtn.setFont(new Font("宋体", Font.BOLD, 12));
		zhucebtn.setText("注册");
		zhucebtn.setBounds(327, 211, 61, 25);
		getRootPane().setDefaultButton(zhucebtn);
		contentpanel.add(zhucebtn);

		//返回按钮
		final JButton btnNewButton = new JButton("返回");
		btnNewButton.setFont(new Font("宋体", Font.BOLD, 12));
		btnNewButton.setBounds(250, 211, 61, 25);
		contentpanel.add(btnNewButton);

		
		zhanghaolabel = new JLabel("账号");
		zhanghaolabel.setFont(new Font("幼圆", Font.BOLD, 16));
		zhanghaolabel.setBounds(104, 43, 51, 19);
		contentpanel.add(zhanghaolabel);
		
		mimalabel = new JLabel("密码");
		mimalabel.setFont(new Font("幼圆", Font.BOLD, 16));
		mimalabel.setBounds(95, 90, 85, 19);
		contentpanel.add(mimalabel);
		
		JLabel label_2 = new JLabel("再次确认密码");
		label_2.setFont(new Font("幼圆", Font.BOLD, 16));
		label_2.setBounds(95, 133, 85, 19);
		contentpanel.add(label_2);
		
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnNewButton.setEnabled(false);
				Login login = new Login();
				login.setVisible(true);
				setVisible(false);// 隐藏掉登陆界面
			}
		});
		
	}
}
