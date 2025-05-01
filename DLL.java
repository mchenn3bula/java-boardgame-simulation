package project2;
import javax.swing.*;
import java.awt.*;
public class DLL {

  //---------------- nested Node class ----------------
  /**
   * Node of a doubly linked list, which stores a reference to its
   * element and to both the previous and next node in the list.
   */
  public static class Node {
	private JTextArea messageText;
	private int index;
    private int score;
    private boolean occupation;
    private JTextArea grid;
    private String playerID = null;

    /** A reference to the preceding node in the list */
    private Node prev;         

    /** A reference to the subsequent node in the list */
    private Node next;
	         

    /**
     * Creates a node with the given element and next node.
     *
     * @param e  the element to be stored
     * @param p  reference to a node that should precede the new node
     * @param n  reference to a node that should follow the new node
     */
    public Node(int e, int id, Node p, Node n) {
    	occupation = false;
    	score = e;
    	index = id;
    	prev = p;
    	next = n;
    	messageText = new JTextArea(String.valueOf(score));
		messageText.setBackground(Color.gray);
		messageText.setForeground(Color.black);
		messageText.setEditable(false);
		messageText.setLineWrap(true);
		messageText.setWrapStyleWord(true);
		messageText.setFont(new Font("Book Antiqua", Font.PLAIN, 24));
    }

    // public accessor methods
    /**
     * Returns the score stored at the node.
     * @return the score stored at the node
     */
    public int getScore() { return score; }
    
    /**
     * Returns if the node is occupied by a player.
     * @return the occupation stored at the node
     */
    public boolean getOccupation() { return occupation; }
    
    /**
     * Returns the player that occupied the node.
     * @return the player stored at the node
     */
    public String getPlayer() { return playerID; }
    
    /**
     * Set a Node if occupied by a player
     * @param the player that occupied the node.
     */
    public void setPlayer(String id) {
    	playerID = id;
    	occupation = true;
    }
    
    /**
     * remove a player from a node
     */
    public void removePlayer() {
    	playerID = null;
    	occupation = false;
    }

    /**
     * Returns the node that precedes this one (or null if no such node).
     * @return the preceding node
     */
    public Node getPrev() { return prev; }

    /**
     * Returns the node that follows this one (or null if no such node).
     * @return the following node
     */
    public Node getNext() { return next; }

    // Update methods
    /**
     * Sets the node's previous reference to point to Node n.
     * @param p    the node that should precede this one
     */
    public void setPrev(Node p) { prev = p; }

    /**
     * Sets the node's next reference to point to Node n.
     * @param n    the node that should follow this one
     */
    public void setNext(Node n) { next = n; }
  } //----------- end of nested Node class -----------

  // instance variables of the DoublyLinkedList
  /** Sentinel node at the beginning of the list */
  private Node header;                    // header sentinel

  /** Sentinel node at the end of the list */
  private Node trailer;                   // trailer sentinel
  
  /** marks the index of the node */
  private int id = 0;

  /** Constructs a new list that simulates the board. */
  public DLL() {
    header = new Node(0, -1, null, null);      // create header
    trailer = new Node(0, -2, header, null);   // trailer is preceded by header
    header.setNext(trailer);                    // header is followed by trailer
    add(5);
    add(10);
    add(8);
    add(10);
    add(7);
    add(5);
    add(9);
    add(10);
    add(6);
    add(7);
    add(10);
    add(6);
    add(5);
    add(8);
    add(9);
    add(5);
    add(10);
    add(5);
    add(9);
    add(6);
    add(8);
    add(7);
    add(10);
    add(6);
    add(8);
    header = header.next;
    header.prev = null;
    trailer = trailer.prev;
    trailer.next = null;
  }

  // private update methods
  private void add(int n) {
    addBetween(n, trailer.getPrev(), trailer);  // place just before the trailer
  }


  /**
   * Adds an element to the linked list in between the given nodes.
   * The given predecessor and successor should be neighboring each
   * other prior to the call.
   *
   * @param predecessor   node just before the location where the new element is inserted
   * @param successor     node just after the location where the new element is inserted
   */
  private void addBetween(int n, Node predecessor, Node successor) {
    // create and link a new node
    Node newest = new Node(n, id, predecessor, successor);
    id ++;
    predecessor.setNext(newest);
    successor.setPrev(newest);
  }
  
  /**
   * access a node by its index
   *
   * @param predecessor   node just before the location where the new element is inserted
   * @return the node on the given index
   */
  public Node getNode(int index) {
	  Node node = header;
	  while (node != trailer) {
	      if (node.index == index) break;
	      node = node.next;
	    }
	  return node;
  }
  
  /**
   * Move a player forward to the Node on location index. 
   *
   * @param index the location to move to
   * @param player the player to move
   */
  public void movePlayer(int index, String player) {
	  getNode(index).setPlayer(player);
  }
  
  public void createBoard(JFrame JF) {
	  int xCoordinate = 50;
	  int yCoordinate = 50;
	  for (int i = 0; i < 25; i++) {
		  Node node = getNode(i);
		  node.grid = new JTextArea(String.valueOf(node.score));
		  node.grid.setBounds(xCoordinate, yCoordinate, 196,62);
		  if (node.playerID == null) {
			  node.grid.setBackground(Color.gray);
		  } else if (node.playerID.equals("A")) {
			  node.grid.setBackground(Color.red);
		  } else if (node.playerID.equals("B")) {
			  node.grid.setBackground(Color.green);
		  } else if (node.playerID.equals("C")) {
			  node.grid.setBackground(Color.yellow);
		  } else if (node.playerID.equals("D")) {
			  node.grid.setBackground(Color.blue);
		  }
		  node.grid.setForeground(Color.black);
		  node.grid.setEditable(false);
		  node.grid.setLineWrap(true);
		  node.grid.setWrapStyleWord(true);
		  node.grid.setFont(new Font("Book Antiqua", Font.PLAIN, 24));
		  JF.add(node.grid);
		  if (xCoordinate < 1034) {
			  xCoordinate += 246;
		  } else {
			  xCoordinate = 50;
			  yCoordinate += 112;
		  }
	  }
  }
  
  public String toString() {
	    StringBuilder sb = new StringBuilder("(");
	    Node walk = header.next;
	    while (walk != trailer) {
	      sb.append(walk.score);
	      sb.append(", ");
	      walk = walk.getNext();
	    }
	    sb.append(")");
	    return sb.toString();
	  }
} //----------- end of DoublyLinkedList class -----------

