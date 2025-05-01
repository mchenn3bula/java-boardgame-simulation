package project2;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Game implements ActionListener {
	private Player p1;
	private Player p2;
	private Player p3;
	private Player p4;
	private int numPlayer;
	private int numGame = 1000;
	private int curGameid = 1;
	private DLL gameBoard;
	private JFrame autoGameWindow;
	private JFrame gameReportWindow;
	private JButton nextGame;
	JTextArea p1Report;
	JTextArea p2Report;
	JTextArea p3Report;
	JTextArea p4Report;
	
	 /**
     * Construct a game board
     * @param the number of players that joins the game
     */
	public Game(int n) {
		if (n == 4) {
			p1 = new Player("A");
			p2 = new Player("B");
			p3 = new Player("C");
			p4 = new Player("D");
		} else if (n == 3) {
			p1 = new Player("A");
			p2 = new Player("B");
			p3 = new Player("C");
		} else if (n == 2) {
			p1 = new Player("A");
			p2 = new Player("B");
		} else if (n == 1) {
			p1 = new Player("A");
		}
		numPlayer = n;
		gameBoard = new DLL();
	}
	
	 /**
     * Roll the dice
     * @return a integer from 1 to 6
     */
	private int roll() {return (int) (Math.random()* 6) + 1;}
	
	 /**
     * play 100 games.
     */
	public void autoPlay() {
		oneGame();
		createNewBoard();
		resetGame();
		curGameid ++;
		for (int i = 0; i < 99; i++) {
			oneGame();
			resetGame();
			curGameid ++;
		}
	}
	
	 /**
     * play one entire game.
     */
	public void oneGame() {
		while (true) {
			boolean isWinner = false;
			for (int j = 0; j < numPlayer; j++) {
				if (j == 0) {
					isWinner = play(p1);
					if (isWinner == true) break;
				} else if (j == 1) {
					isWinner = play(p2);
					if (isWinner == true) break;
				} else if (j == 2) {
					isWinner = play(p3);
					if (isWinner == true) break;
				} else if (j == 3) {
					isWinner = play(p4);
					if (isWinner == true) break;
				}
			}
			if (isWinner == true) break;
		}
	}
	
	 /**
     * play one round for a player
     * @param the player to play
     * @return return true is a player wins the game
     */
	public boolean play(Player player) {
		
		int loc = player.location;
		if (gameBoard.getNode(loc).getPlayer() == player.id) {
			gameBoard.getNode(loc).removePlayer();
		}
		int r = roll();
		player.moves ++;
		player.location += r;
		loc = player.location;
		// case when the location is previously occupied by another player
		if (gameBoard.getNode(loc).getOccupation() == true) {
			String prevPlayer = gameBoard.getNode(loc).getPlayer();
			if (prevPlayer.equals("A")) {
				moveBackward(p1);
			} else if (prevPlayer.equals("B")) {
				moveBackward(p2);
			} else if (prevPlayer.equals("C")) {
				moveBackward(p3);
			} else {
				moveBackward(p4);
			}
		}
		player.total += gameBoard.getNode(loc).getScore();
		gameBoard.getNode(loc).setPlayer(player.id);
		return checkWin(player);
	}
	
	 /**
     * move a player backward
     * @param the player to move backward
     */
	private void moveBackward(Player p) {
		// case when If the player is moved back beyond the first square, put the player on Start circle 
		if (p.location - 7 < -1) {
			p.location = -1;
		} else {
			p.location -= 7;
		}
		gameBoard.getNode(p.location).setPlayer(p.id);
	}
	
	 /**
     * check if a player has win the game
     * @param the player check
     * @return true if the player has win the game
     */
	private boolean checkWin(Player p) {
		if (p.location > 24) {
			if (p.total >= 44) {
				p.totalMoves += p.moves;
				p.wins++;
				return true;
			}
			p.location = -1;
		} 
		return false;
	}
	
	 /**
     * report the statistics of the game
     * @return a string that describes the result of the game
     */
	private void gameReport() {
		gameReportWindow = new JFrame();
		gameReportWindow.setSize(1280,720);
		gameReportWindow.setTitle("Report");
		gameReportWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameReportWindow.getContentPane().setBackground(Color.white);
		gameReportWindow.setLayout(null);
		gameReportWindow.setVisible(true);
		for (int k = 0; k < numPlayer; k++) {
			 StringBuffer sb = new StringBuffer("Player ");
			 JTextArea result = new JTextArea();
			 sb.append(k + 1);
			 sb.append(": ");
			 switch (k){
	            case 0 : 
	            	sb.append("Win Rate "); 
	            	sb.append(p1.winRate());  
	            	sb.append(", Average Moves "); 
	            	sb.append(p1.averageMoves());
	            	result = new JTextArea(sb.toString());
	            	result.setBounds(50,50,1180,84);
	        		result.setBackground(Color.red);
	        		result.setForeground(Color.black);
	            	break;
	            case 1 : 
	            	sb.append("Win Rate "); 
	            	sb.append(p2.winRate());  
	            	sb.append(", Average Moves "); 
	            	sb.append(p2.averageMoves());
	            	result = new JTextArea(sb.toString());
	            	result.setBounds(50,184,1180,84);
	        		result.setBackground(Color.green);
	        		result.setForeground(Color.black);
	            	break;
	            case 2 : 
	            	sb.append("Win Rate "); 
	            	sb.append(p3.winRate());  
	            	sb.append(", Average Moves "); 
	            	sb.append(p3.averageMoves());
	            	result = new JTextArea(sb.toString());
	            	result.setBounds(50,318,1180,84);
	        		result.setBackground(Color.yellow);
	        		result.setForeground(Color.black);
	            	break;
	            case 3 : 
	            	sb.append("Win Rate "); 
	            	sb.append(p4.winRate());  
	            	sb.append(", Average Moves "); 
	            	sb.append(p4.averageMoves());
	            	result = new JTextArea(sb.toString());
	            	result.setBounds(50,452,1180,84);
	        		result.setBackground(Color.blue);
	        		result.setForeground(Color.black);
	            	break;
	         }
			 result.setEditable(false);
			 result.setLineWrap(true);
			 result.setWrapStyleWord(true);
			 result.setFont(new Font("Book Antiqua", Font.PLAIN, 24));
			 gameReportWindow.add(result);
		}
	}
	
	 /**
     * create new GUI for the game.
     */
	private void createNewBoard() {
		autoGameWindow = new JFrame();
		gameBoard.createBoard(autoGameWindow);
		autoGameWindow.setSize(1280,720);
		autoGameWindow.setTitle("Board Game");
		autoGameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		autoGameWindow.getContentPane().setBackground(Color.white);
		autoGameWindow.setLayout(null);
		autoGameWindow.setVisible(true);
		
		StringBuffer sb = new StringBuffer("Game ID: ");
		sb.append(curGameid);
		JTextArea gameIDText = new JTextArea(sb.toString());
		gameIDText.setBounds(665,608,565,62);
		gameIDText.setBackground(Color.cyan);
		gameIDText.setForeground(Color.black);
		gameIDText.setEditable(false);
		gameIDText.setLineWrap(true);
		gameIDText.setWrapStyleWord(true);
		gameIDText.setFont(new Font("Book Antiqua", Font.PLAIN, 24));
		
		nextGame = new JButton();
		nextGame.setBounds(50,608,565,62);
		if ((curGameid < numGame)) {
			nextGame.setText("Next 100th Game");
		} else {
			nextGame.setText("Display Game Report");
		}
		nextGame.addActionListener(this);
		autoGameWindow.add(gameIDText);
		autoGameWindow.add(nextGame);
	}
	
	 /**
     * reset the game for a new game.
     */
	private void resetGame() {
		gameBoard = new DLL();
		for (int i = 0; i < numPlayer; i++) {
			if (i == 0) {
				p1.reset();
			} else if (i == 1) {
				p2.reset();
			} else if (i == 2) {
				p3.reset();
			} else if (i == 3) {
				p4.reset();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == nextGame) {
			autoGameWindow.dispose();
			if (curGameid < numGame) {	
				autoPlay();
			} else {
				gameReport();
			}
		}
	}
}
