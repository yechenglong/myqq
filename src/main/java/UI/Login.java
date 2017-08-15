package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.channels.ScatteringByteChannel;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import function.ChatBean;
import util.ChatUtil;

/*
 * yechenglong
 * time:20170727
 * v1.1.0
 * */
public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private ObjectOutputStream oos;
	public String u_name;
	public String u_pwd;
	public JButton loginbtn = new JButton();
	public JLabel lblNewLabel = new JLabel();
	public JButton zhucebnt = new JButton();
	public JLabel zhanghaolabel = new JLabel();
	public JLabel mimalabel = new JLabel();
	public Socket client;
	// 运行
	public static void main(String[] args) {
		Login login = new Login();
		login.setVisible(true);
	}

	// 登录界面
	public Login() {
		setTitle("myQQ_Login");
		setBounds(350, 250, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Image image = new ImageIcon("C:\\Users\\Public\\Pictures\\Sample Pictures\\企鹅.jpg").getImage();
		setIconImage(image);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(128, 109, 104, 21);
		textField.setOpaque(false);
		textField.setColumns(10);
		contentPane.add(textField);

		zhanghaolabel.setBounds(83, 109, 57, 21);
		zhanghaolabel.setText("账号");
		zhanghaolabel.setFont(new Font("宋体", Font.BOLD, 12));
		zhanghaolabel.setForeground(Color.BLUE);
		contentPane.add(zhanghaolabel);

		passwordField = new JPasswordField();
		passwordField.setBounds(128, 153, 104, 21);
		passwordField.setForeground(Color.BLACK);
		passwordField.setEchoChar('*');
		passwordField.setOpaque(true);
		passwordField.setColumns(10);
		contentPane.add(passwordField);

		mimalabel.setText("密码");
		mimalabel.setForeground(Color.GREEN);
		mimalabel.setFont(new Font("宋体", Font.BOLD, 12));
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

		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel.setBounds(20, 220, 212, 21);
		lblNewLabel.setForeground(Color.red);
		getContentPane().add(lblNewLabel);

		// 监听登录按钮
		loginbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				u_name = textField.getText();
				u_pwd = new String(passwordField.getPassword());
				if (u_name.length() != 0) {
					
					try {
						lblNewLabel.setText("正在查询");
						client=new Socket("127.0.0.1",8520);
						ChatBean clientBean=new ChatBean();
						clientBean.setType(6);
						clientBean.setName(u_name);
						String time = ChatUtil.getTimer();
						clientBean.setTimer(time);
						clientBean.setInfo("");
						clientBean.setPassword(u_pwd);
						
						try {
							oos = new ObjectOutputStream(client.getOutputStream());
							oos.writeObject(clientBean);
							oos.flush();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					lblNewLabel.setText("您输入的昵称不存在！");
					textField.setText("");
					passwordField.setText("");
					textField.requestFocus();
				}
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

	class LoginThread extends Thread {

		private Socket client;
		private ChatBean bean;
		private ObjectInputStream objectInputStream;

		public LoginThread(Socket client) {
			this.client = client;
		}

		public void run() {
			try {
				while (true) {
					objectInputStream = new ObjectInputStream(client.getInputStream());
					bean = (ChatBean) objectInputStream.readObject();

					switch (bean.getType()) {
					case -2:
						return;

					default:
						break;
					}

					// 判断用户名是否能够登录
					if (bean.getInfo().equals("用户名已注册且密码正确！")) {
						// 发送返回请求
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
						// 这里创建新的Socket连接，因为请求登录的连接在查询成功返回后会关闭
						Socket client = new Socket("127.0.0.1", 8520);
						loginbtn.setEnabled(false);
						ClientUI frame = new ClientUI(u_name, client);
						frame.setVisible(true);
						setVisible(false);

					}
					if (bean.getInfo().equals("用户名已注册但密码不正确！")) {
						// 发送返回请求
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
						lblNewLabel.setText("用户名已注册但密码不正确");
						textField.setText("");
						passwordField.setText("");
						textField.requestFocus();
					}
					if(bean.getInfo().equals("用户名尚未注册!")){
						//发送返回请求
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
						
						lblNewLabel.setText("用户名尚未注册!");
						textField.setText("");
						passwordField.setText("");
						textField.requestFocus();	
					}
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				close();
			}
		}

		private void close() {
			// TODO Auto-generated method stub
			if(oos!=null){
				try {
					oos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(objectInputStream!=null){
				try {
					objectInputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(client!=null){
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
