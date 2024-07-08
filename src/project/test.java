package project;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class test extends JFrame implements ActionListener{
	
	private String username, recv_username; 
	private Font nameFont, scoreFont, drawFont;
	private Color winColor, loseColor;
	private JButton btnExit;
	private JLabel winner, loser, win_score, lose_score;
	private int min = 999, max = 0;
	private String winnerName = null, loserName = null;

	public test() {
		setTitle("draw");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setResizable(false);
		
		drawFont = new Font("ROKAF SLAB SERIF MEDIUM", Font.BOLD, 60);
		nameFont = new Font("ROKAF SLAB SERIF MEDIUM", 0, 25);
		scoreFont = new Font("ROKAF SLAB SERIF MEDIUM", Font.BOLD, 120);
		winColor = new Color(95, 131, 211);
		loseColor = new Color(255, 75, 75);
		
		set();
		
		setVisible(true);
	}

	private void set() {
		JPanel drawPanel = new JPanel();
		drawPanel.setBackground(Color.white);
		drawPanel.setLayout(null);
		
		JLabel drawImg = new JLabel(new ImageIcon("image/draw.png"));
		drawImg.setBounds(300, 55, 154, 146);
		drawPanel.add(drawImg);
		
		JLabel drawText = new JLabel("무승부");
		drawText.setBounds(290, 210, 200, 200);
		drawText.setFont(drawFont);
		drawPanel.add(drawText);
		
		JLabel drawscore = new JLabel(Integer.toString(max));
		drawscore.setBounds(290, 310, 200, 200);
		drawscore.setFont(drawFont);
		drawscore.setHorizontalAlignment(JLabel.CENTER);
		drawPanel.add(drawscore);
		
		btnExit = new JButton(new ImageIcon("image/exitBtn.png"));
		btnExit.setBounds(670, 460, 89, 83);
		btnExit.setBorderPainted(false);
		btnExit.setContentAreaFilled(false);
		btnExit.addActionListener(this);
		btnExit.setFocusable(false);
		drawPanel.add(btnExit);
		
		add(drawPanel);
	}
	
	public static void main(String[] args) {
		test tt = new test();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
