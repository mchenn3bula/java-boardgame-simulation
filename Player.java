package project2;

public class Player {
	/** the total score of the player */
	public int total;
	/** the number of wins of the player */
	public int wins;
	/** the index of the Node that the player is on */
	public int location;
	/** the number of moves of the player */
	public int moves;
	/** the total number of moves of the player to win*/
	public int totalMoves;
	/** the number of games of the player played */
	public int games;
	/** the ID of the player */
	public String id;
	
    /**
     * Creates a player with the given ID.
     * @param id  the id of the player
     */
	public Player(String id) {
		total = 0;
		wins = 0;
		moves= 0;
		games = 0;
		location = -1;
		this.id = id;
	}
	
    /**
     * reset a player for the next game
     */
	public void reset() {
		total = 0;
		location = -1;
		games ++;
		moves = 0;
	}
	
    /**
     * @return the average moves of a player
     */
	public double averageMoves() {
		try {
			return (double)totalMoves/(double)wins;
		} catch (ArithmeticException ex) {
			return 0;
		}
	}
	
    /**
     * @return the win rate of a player
     */
	public double winRate() {
		return (double)wins/(double)games;
	}
}
