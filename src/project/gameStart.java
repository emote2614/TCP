package project;

import java.awt.Color;
import java.awt.Font;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class gameStart extends JFrame{
	private Socket socket;
	private Font mainFont;
	private JLabel count;
	private int cd_val;
	private gameStart gs = this;
	private String username;
	static String sign;
	
	public gameStart(Socket socekt, String username) {
		this.socket = socekt;
		this.username = username;
		
		receiveThread receive = new receiveThread(socekt, this);
		receive.startThread();
		
		Thread sendThread = new SendThread(socket, this); // 전송
		sendThread.start();
		
		mainFont = new Font("ROKAF SLAB SERIF MEDIUM", Font.BOLD, 70);
		
		setTitle("get Reday");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // 프레임 닫으면 종료되게 설정
		setSize(800, 600);
		setResizable(false);  //크기 조절 불가능하게 만듦
	
		setMain();
		
		setVisible(true);
		
		while(true) {
			try {
			if (sign.equals(null)) {
			}
			else if(sign.equals("start"))
				break;
			} catch (NullPointerException e) {
				continue;
			}
			
		}
			countDown();
	}
	
	private void setMain() {
		JPanel mainFrame = new JPanel();
		mainFrame.setBackground(Color.white);
		mainFrame.setLayout(null);
		
		count = new JLabel("대기중...");
		count.setFont(mainFont);
		count.setBackground(Color.blue);
		count.setBounds(275, 225, 400, 100);
		mainFrame.add(count);
	
		add(mainFrame);
	}
	
	private void countDown() {
		cd_val = 5;
		Timer timer = new Timer();
		
		TimerTask task = new TimerTask() {
			public void run() {
				if(cd_val > 0) {
					count.setBounds(365, 225, 400, 100);
					count.setText(Integer.toString(cd_val));
					cd_val--;
				}
				else {
					timer.cancel();
					callProgram();
				}
			}
		};
		
		timer.schedule(task, 2500, 1000);
	}
	
	public void setString(String str) {
		sign = str;
	}
	
	public void callProgram() {
		gameFrame gf = new gameFrame(socket, username);
		gf.setLocationRelativeTo(this);
		gs.dispose();
	}
}
