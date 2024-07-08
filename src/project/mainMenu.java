package project;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class mainMenu extends JFrame implements ActionListener {
	private Socket socket;
	private Font mainFont;
	private JButton btnStart, btnRank;
	static String ip, username;
	static int port;
	
	public mainMenu() {
		mainFont = new Font("ROKAF SLAB SERIF MEDIUM", Font.BOLD, 50);
		setTitle("mainMenu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // 프레임 닫으면 종료되게 설정
		setSize(800, 600);
		setResizable(false);  //크기 조절 불가능하게 만듦
		
		setMain();
		btnStart.setEnabled(false);
		setVisible(true);
	}
	
	public mainMenu(String ip, int port, String username) {
		this.ip = ip;
		this.port = port;
		this.username = username;
		mainFont = new Font("ROKAF SLAB SERIF MEDIUM", Font.BOLD, 50);
		
		try {
			socket = new Socket(ip, port);
			System.out.println("[서버와 연결되었습니다.]");
			
			
			
			setTitle("mainMenu");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // 프레임 닫으면 종료되게 설정
			setSize(800, 600);
			setResizable(false);  //크기 조절 불가능하게 만듦
			
			setMain();
			setVisible(true);
		} catch (IOException e) {
			login loginForm = new login();
			this.dispose();
			JOptionPane.showMessageDialog(loginForm, 
					"네트워크 정보를 확인해주세요.", "네트워크 오류", JOptionPane.ERROR_MESSAGE);
		}
	}
	private void setMain() {
		JPanel main = new JPanel();
		main.setLayout(null);
		main.setBackground(Color.WHITE);

		JLabel name1 = new JLabel("Catch Me");
		name1.setFont(mainFont);
		name1.setBounds(200, 110, 250, 60);
		main.add(name1);
		
		JLabel name2 = new JLabel("If You Can");
		name2.setFont(mainFont);
		name2.setBounds(300, 170, 270, 60);
		main.add(name2);
		
		JLabel run = new JLabel(new ImageIcon("image/run_img.png"));
		run.setBounds(470, 90, 87, 89);
		main.add(run);
		
		btnStart = new JButton(new ImageIcon("image/btnStart.png"));
		btnStart.setBounds(300, 300, 192, 75);
		btnStart.setBorderPainted(false);
		btnStart.setContentAreaFilled(false);
		btnStart.addActionListener(this);
		btnStart.setFocusable(false);
		main.add(btnStart);
		
		btnRank = new JButton(new ImageIcon("image/btnRank.png"));
		btnRank.setBounds(305, 400, 192, 75);
		btnRank.setBorderPainted(false);
		btnRank.setContentAreaFilled(false);
		btnRank.addActionListener(this);
		btnRank.setFocusable(false);
		main.add(btnRank);
		
		add(main);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == btnStart) {
			this.dispose();
			gameStart ready = new gameStart(socket, username);
			ready.setLocationRelativeTo(this);
		} else if(obj == btnRank) {
			ranking rk = new ranking();
			rk.setLocationRelativeTo(this);
		}
	}
}