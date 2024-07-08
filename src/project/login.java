package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class login extends JFrame implements ActionListener{

	public static void main(String[] args) {
		login lg = new login();
	}

	private Font mainFont, subFont, inputFont;
	private JTextField tfname, tfport, tfip;
	private JButton btnLogin;
	
	public login() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // 프레임 닫으면 종료되게 설정
		setSize(800, 600);
		setResizable(false);  //크기 조절 불가능하게 만듦
		setLocationRelativeTo(null);   //화면 중앙에 프레임이 뜨도록 설정

		mainFont = new Font("ROKAF SLAB SERIF MEDIUM", Font.BOLD, 50);
		subFont = new Font("ROKAF SLAB SERIF MEDIUM", 0, 19);
		inputFont = new Font("ROKAF SLAB SERIF MEDIUM", 0, 14);
		
		setLeft();
		setRight();
		
		setVisible(true);
	}

	private void setLeft() {
		JPanel left = new JPanel();
		left.setPreferredSize(new Dimension(325, 600));
		left.setBackground(Color.WHITE);
		left.setLayout(null);
		
		JLabel logo = new JLabel(new ImageIcon("image/logo.png"));
		logo.setBounds(33, 125,265, 250);
		left.add(logo);
		
		add(left, BorderLayout.WEST);
	}
	
	private void setRight() {
		JPanel right = new JPanel();
		right.setBackground(Color.WHITE);
		right.setPreferredSize(new Dimension(575, 600));
		right.setLayout(null);
		
		JLabel name1 = new JLabel("Catch Me");
		name1.setFont(mainFont);
		name1.setBounds(140, 110, 250, 60);
		right.add(name1);
		
		JLabel name2 = new JLabel("If You Can");
		name2.setFont(mainFont);
		name2.setBounds(240, 170, 270, 60);
		right.add(name2);
		
		JLabel run = new JLabel(new ImageIcon("image/run_img.png"));
		run.setBounds(410, 90, 87, 89);
		right.add(run);
		
		JLabel user = new JLabel("Username: ");
		user.setFont(subFont);
		user.setBounds(170, 340, 120, 30);
		right.add(user);
		
		JLabel port = new JLabel("Port: ");
		port.setFont(subFont);
		port.setBounds(170, 415, 120, 30);
		right.add(port);
		
		JLabel ip = new JLabel("IP Address: ");
		ip.setFont(subFont);
		ip.setBounds(275, 415, 120, 30);
		right.add(ip);
		
		JLabel name_backImg = new JLabel(new ImageIcon("image/addtextField.png"));
		name_backImg.setBounds(170, 375, 255, 30);
		right.add(name_backImg);
		
		JLabel port_backImg = new JLabel(new ImageIcon("image/portField.png"));
		port_backImg.setBounds(170, 445, 90, 30);
		right.add(port_backImg);
		
		JLabel ip_backImg = new JLabel(new ImageIcon("image/ipField.png"));
		ip_backImg.setBounds(275, 445, 150, 30);
		right.add(ip_backImg);
		
		tfname = new JTextField("", 20);
		tfname.setFocusTraversalKeysEnabled(false);
		tfname.addActionListener(this);
		tfname.setFont(inputFont);
		tfname.setBounds(178, 379, 240, 23);
		tfname.setBorder(BorderFactory.createEmptyBorder());
		right.add(tfname);
		
		tfport = new JTextField("", 20);
		tfport.setFocusTraversalKeysEnabled(false);
		tfport.addActionListener(this);
		tfport.setFont(inputFont);
		tfport.setBounds(178, 449, 70, 23);
		tfport.setBorder(BorderFactory.createEmptyBorder());
		right.add(tfport);
		
		tfip = new JTextField("", 20);
		tfip.setFocusTraversalKeysEnabled(false);
		tfip.addActionListener(this);
		tfip.setFont(inputFont);
		tfip.setBounds(280, 449, 138, 23);
		tfip.setBorder(BorderFactory.createEmptyBorder());
		right.add(tfip);
		
		btnLogin = new JButton(new ImageIcon("image/login_btn.png"));
		btnLogin.setBounds(450, 412, 62, 63);
		btnLogin.setBorderPainted(false);
		btnLogin.setContentAreaFilled(false);
		btnLogin.addActionListener(this);
		right.add(btnLogin);
		
		add(right, BorderLayout.EAST);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == btnLogin) {
			String username = tfname.getText();
			String ip = tfip.getText(); 
			int port = 0;
			if(!(tfport.getText().equals("")))
				port = Integer.parseInt(tfport.getText());
			
			if(username.equals("") || ip.equals("") || port == 0) {
				JOptionPane.showMessageDialog(this, 
						"정보를 모두 입력해주세요.", "입력 오류", JOptionPane.ERROR_MESSAGE);
			} else {
				mainMenu mf = new mainMenu(ip, port, username);
				mf.setLocationRelativeTo(this);
				this.dispose();
			}
		}
	}
}