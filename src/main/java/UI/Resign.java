package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import function.ChatBean;
import util.ChatUtil;

import javax.swing.JPasswordField;;

public class Resign extends JFrame {

	private JLabel lblNewLabel;
	private JPanel contentpanel;
	public ObjectOutputStream oos;
	private JButton zhucebtn = new JButton();
	private JLabel zhanghaolabel = new JLabel();
	private JLabel mimalabel = new JLabel();
	private JTextField zhanghao = new JTextField();
	private JPasswordField mima = new JPasswordField();
	private JPasswordField mima2 = new JPasswordField();
	public Socket client;
	public String u_name;
	public String u_pwd;
	public String u_pwd_ag;

	public static void main(String[] args) {
		Resign resign = new Resign();
		resign.setVisible(true);
	}

	public Resign() {
		// TODO Auto-generated constructor stub
		setTitle("myQQ_Resign");
		setBounds(350, 250, 450, 300);
		Image image = new ImageIcon("C:\\Users\\Public\\Pictures\\Sample Pictures\\企鹅.jpg").getImage();
		setIconImage(image);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentpanel = new JPanel();
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

		// 注册按钮
		zhucebtn.setFont(new Font("宋体", Font.BOLD, 12));
		zhucebtn.setText("注册");
		zhucebtn.setBounds(327, 211, 61, 25);
		getRootPane().setDefaultButton(zhucebtn);
		contentpanel.add(zhucebtn);

		// 返回按钮
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

		lblNewLabel = new JLabel();
		lblNewLabel.setBounds(55, 218, 185, 20);
		lblNewLabel.setForeground(Color.red);
		contentpanel.add(lblNewLabel);
		// 返回按钮
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (client != null) {
					ChatBean clientBean = new ChatBean();
					clientBean.setType(-2);
					String time = ChatUtil.getTimer();
					clientBean.setTimer(time);
					try {
						oos = new ObjectOutputStream(client.getOutputStream());
						oos.writeObject(clientBean);
						oos.flush();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					// reg.interrupt();
				}
				btnNewButton.setEnabled(false);
				// 返回登陆界面
				Login frame = new Login();
				frame.setVisible(true);
				setVisible(false);

			}

		});
		// 注册按钮监听
		zhucebtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				u_name = zhanghao.getText();
				u_pwd = new String(mima.getPassword());
				u_pwd_ag = new String(mima2.getPassword());
				try {
					if (u_name.length() != 0) {
						if (u_pwd.length() != 0) {
							if (u_pwd_ag.length() != 0) {
								// 请求注册

								client = new Socket("127.0.0.1", 8520);
								new resignThread(client).start();
								ChatBean clientBean = new ChatBean();
								clientBean.setType(5);
								clientBean.setName(u_name);
								String time = ChatUtil.getTimer();
								clientBean.setTimer(time);
								clientBean.setInfo("");
								clientBean.setPassword(u_pwd_ag);
								try {
									oos = new ObjectOutputStream(client.getOutputStream());
									oos.writeObject(clientBean);
									oos.flush();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								zhanghao.setText("");
								mima.setText("");
								mima2.setText("");

							} else {
								lblNewLabel.setText("密码不一致！");
								zhanghao.setText("");
								mima.setText("");
								mima2.setText("");
							}
						} else {
							lblNewLabel.setText("密码为空！");
							zhanghao.setText("");
							mima.setText("");
							mima2.setText("");
						}

					} else {
						lblNewLabel.setText("用户名不能为空！");
						zhanghao.setText("");
						mima.setText("");
						mima2.setText("");
					}

				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

	}
	class resignThread extends Thread{
		private Socket client;
		private ChatBean bean;
		private ObjectInputStream ois;
		public resignThread(Socket client) {
			// TODO Auto-generated constructor stub
			this.client=client;
		}
		public void run() {
			try {	
				while(true) {
					ois = new ObjectInputStream(client.getInputStream());
					bean = (ChatBean) ois.readObject();
					
					switch (bean.getType()) {
						case -2: {
							return;//返回 
							}
						default: {
							break;
						}
					}
					
					// 判断用户名是否在普通用户中已存在
					if (bean.getInfo().equals("用户名已存在!")) {
						lblNewLabel.setText("用户名已存在!");
						
						zhanghao.setText("");
						mima.setText("");
						mima2.setText("");
						zhucebtn.setEnabled(false);
						
					}
					if(bean.getInfo().equals("注册成功!")){
						lblNewLabel.setText("注册成功!");
						
						zhanghao.setText("");
						mima.setText("");
						mima2.setText("");
						zhucebtn.setEnabled(false);
																
					}											
				}
					
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			finally {
				close();//发送返回请求
			}
		}
		
		private void close() {
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (client != null) {
				try {
					client.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}				
	}
	
}
