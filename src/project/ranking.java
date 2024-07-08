package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.security.KeyStore.PrivateKeyEntry;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.xml.stream.events.Comment;

public class ranking extends JFrame {

	private Font mainFont, inputFont;
	private JLabel firstrank, secondrank, thirdrank, firstcmt;
	private String[] rankResult;
	
	public ranking() {
		
		setTitle("Ranking");
		setSize(500,600);
		setResizable(false);
		
		mainFont = new Font("ROKAF SLAB SERIF MEDIUM", Font.BOLD, 25);
		inputFont = new Font("ROKAF SLAB SERIF MEDIUM", 0, 14);
		
		setUp();
		setDown();
		
		DB db = new DB();
		rankResult = db.rank();
		
		firstrank.setText(rankResult[0]);
		secondrank.setText(rankResult[1]);
		thirdrank.setText(rankResult[2]);
		
		setVisible(true);
	}

	public void setUp() {
		JPanel up = new JPanel();
		up.setPreferredSize(new Dimension(500,330));
		up.setBackground(Color.white);
		up.setLayout(null);
		
		JLabel step = new JLabel(new ImageIcon("image/step.png"));
		step.setBounds(50,30,388,300);
		up.add(step);
		
		firstrank = new JLabel();
		firstrank.setFont(inputFont);
		firstrank.setBounds(195, 115, 100, 50);
		firstrank.setHorizontalAlignment(JLabel.CENTER);
		up.add(firstrank);
		
		secondrank = new JLabel();
		secondrank.setFont(inputFont);
		secondrank.setBounds(55, 185, 100, 50);
		secondrank.setHorizontalAlignment(JLabel.CENTER);
		up.add(secondrank);
		
		thirdrank = new JLabel();
		thirdrank.setFont(inputFont);
		thirdrank.setBounds(325, 185, 100, 50);
		thirdrank.setHorizontalAlignment(JLabel.CENTER);
		up.add(thirdrank);
		
		add(up, BorderLayout.NORTH);
		
	}

	public void setDown() {
		JPanel down = new JPanel();
		down.setPreferredSize(new Dimension(500,270));
		down.setBackground(Color.white);
		down.setLayout(null);
		
		JLabel man = new JLabel(new ImageIcon("image/man.png"));
		man.setBounds(30,65,136,148);
		down.add(man);
		
		JLabel cmt = new JLabel(new ImageIcon("image/cmt.png"));
		cmt.setBounds(145,95,304,120);
		down.add(cmt);
		
		firstcmt = new JLabel("명예의 전당");
		firstcmt.setFont(mainFont);
		firstcmt.setBounds(215, 110, 200, 100);
		firstcmt.setHorizontalAlignment(JLabel.CENTER);
		down.add(firstcmt);
		
		add(down, BorderLayout.SOUTH);
		
	}
}