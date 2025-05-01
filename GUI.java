package project2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class GUI extends JFrame implements ActionListener{
	
	
	public GameManager gm;
	private JFrame window;
	private JButton p1;
	private JButton p2;
	private JButton p3;
	private JButton p4;
	private ImageIcon board;
	private JLabel label;
	public JTextArea messageText;
	
	public GUI(GameManager gm) {
		this.gm = gm;
		
		createMainField();
		window.setVisible(true);
	}
	
	public void createMainField() {
		window = new JFrame();
		window.setTitle("Board Game");
		window.setSize(1280,720);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.white);
		window.setLayout(null);
		
		messageText = new JTextArea("Board Game\nSelect The Number Of Players ");
		messageText.setBounds(50, 500, 1180, 150);
		messageText.setBackground(Color.gray);
		messageText.setForeground(Color.black);
		messageText.setEditable(false);
		messageText.setLineWrap(true);
		messageText.setWrapStyleWord(true);
		messageText.setFont(new Font("Book Antiqua", Font.PLAIN, 32));
		window.add(messageText);
		
		try {
			board = new ImageIcon("board.png");
		} catch (NullPointerException ex) {
			
		}
		label = new JLabel(board);
		label.setBounds(408,91,464,117);
		window.add(label);
		
		
		p1 = new JButton();
		p1.setBounds(52,300,255,150);
		p1.setText("1 Player");
		p1.addActionListener(this);
		p2 = new JButton();
		p2.setBounds(359,300,255,150);
		p2.setText("2 Players");
		p2.addActionListener(this);
		p3 = new JButton();
		p3.setBounds(666,300,255,150);
		p3.setText("3 Players");
		p3.addActionListener(this);
		p4 = new JButton();
		p4.setBounds(973,300,255,150);
		p4.setText("4 Players");
		p4.addActionListener(this);
		
		window.add(p1);
		window.add(p2);
		window.add(p3);
		window.add(p4);
	}
	
	// this is the game launcher
	@Override
	public void actionPerformed(ActionEvent e) {
			if (e.getSource() == p1) {
				Game auto = new Game(1);
				auto.autoPlay();
			} else if (e.getSource() == p2) {
				Game auto = new Game(2);
				auto.autoPlay();
			} else if (e.getSource() == p3) {
				Game auto = new Game(3);
				auto.autoPlay();
			} else if (e.getSource() == p4) {
				Game auto = new Game(4);
				auto.autoPlay();
			}
			window.dispose();
	}
}
