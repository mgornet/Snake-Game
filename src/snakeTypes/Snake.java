package snakeTypes;

import java.util.ArrayList;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import snakeGame.Arena;
import snakeGame.GameAttributes;
import snakeParts.BodyPart;
import snakeParts.Head;
import snakeParts.Ring;
import snakeParts.Tail;

/**
 * The snake is composed of an head, a tail, a first ring and at each cycle of
 * calculus of a new ring added in the ArrayList body. It also has a color, a
 * direction and an integer representing the player. If the snake is player one,
 * it will start at the upper left corner ; if it's the second player, it will
 * start at the bottom right corner.
 * 
 * @author Melanie Gornet
 *
 */
public abstract class Snake {

	private static final double SIZE = GameAttributes.ELEMENT_SIZE;
	private static final double WINDOW_SIZE = GameAttributes.WINDOW_SIZE;

	/* Attributes */
	private Color color;
	private ArrayList<BodyPart> body;
	private BodyPart head;
	private BodyPart lastring;
	private BodyPart tail;
	private int player;
	private int nextDirection;

	/**
	 * Constructor of the snake.
	 * 
	 * @param color  Color of the snake.
	 * @param player an integer : if player = 1, the snake is player one, if player
	 *               = 2, the snake is second player.
	 */
	public Snake(Color color, int player) {

		// Initialization of the attributes
		this.color = color;
		this.player = player;
		this.body = new ArrayList<BodyPart>();

		// Construct the player one in the upper left corner
		if (player == 1) {
			this.body.add(new Tail(WINDOW_SIZE / 4, WINDOW_SIZE / 4, color));
			this.body.add(new Ring(WINDOW_SIZE / 4 + SIZE, WINDOW_SIZE / 4, color));
			this.body.add(new Head(WINDOW_SIZE / 4 + 2 * SIZE, WINDOW_SIZE / 4, color));
		}

		// Construct the second player in the bottom right corner
		if (player == 2) {
			this.body.add(new Tail(3 * WINDOW_SIZE / 4, 3 * WINDOW_SIZE / 4, color));
			this.body.add(new Ring(3 * WINDOW_SIZE / 4 - SIZE, 3 * WINDOW_SIZE / 4, color));
			this.body.add(new Head(3 * WINDOW_SIZE / 4 - 2 * SIZE, 3 * WINDOW_SIZE / 4, color));
		}

		// Initialization of the attributes
		this.head = body.get(2);
		this.lastring = body.get(1);
		this.tail = body.get(0);
		this.nextDirection = 0;
	}

	/** Update the snake. Translate the head and add a new ring to the body. */
	public void updateSnake() {

		// Translate the head
		head.setTranslateX(head.getx());
		head.setTranslateY(head.gety());

		// Add a new ring to the body of the snake
		switch (nextDirection) {
		case 1: {
			this.body.add(body.size() - 1, new Ring(head.getx() + SIZE, head.gety(), color));
		}
			break;
		case 2: {
			this.body.add(body.size() - 1, new Ring(head.getx(), head.gety() + SIZE, color));
		}
			break;
		case 3: {
			this.body.add(body.size() - 1, new Ring(head.getx() - SIZE, head.gety(), color));
		}
			break;
		case 4: {
			this.body.add(body.size() - 1, new Ring(head.getx(), head.gety() - SIZE, color));
		}
			break;
		default: {
			if (player == 1) {
				this.body.add(body.size() - 1, new Ring(head.getx() - SIZE, head.gety(), color));
			} else if (player == 2) {
				this.body.add(body.size() - 1, new Ring(head.getx() + SIZE, head.gety(), color));
			}

		}
			break;
		}

		// Update what is called "head" and what is called "lastring"
		head = body.get(body.size() - 1);
		lastring = body.get(body.size() - 2);

	}

	/**
	 * Tells if the snake is knocked out by checking if the spot it wants to go to
	 * is occupied.
	 * 
	 * @param arena the Arena of the game.
	 * @return true if the snake will hit an obstacle or an edge, false otherwise.
	 */
	public boolean isKnockedOut(Arena arena) {

		switch (nextDirection) {

		case 1: {
			if (arena.isOccupied(head.getx() - SIZE, head.gety())) {
				return true;
			}
		}
			break;
		case 2: {
			if (arena.isOccupied(head.getx(), head.gety() - SIZE)) {
				return true;
			}
		}
			break;
		case 3: {
			if (arena.isOccupied(head.getx() + SIZE, head.gety())) {
				return true;
			}
		}
			break;
		case 4: {
			if (arena.isOccupied(head.getx(), head.gety() + SIZE)) {
				return true;
			}
		}
			break;
		default: {
			if (player == 1) {
				if (arena.isOccupied(head.getx() + SIZE, head.gety())) {
					return true;
				}
			}
			if (player == 2) {
				if (arena.isOccupied(head.getx() - SIZE, head.gety())) {
					return true;
				}
			}

		}
			break;
		}
		return false;
	}

	/**
	 * Generates the dx and dy needed for the movement according to the direction
	 * chosen and changes the coordinates of the head accordingly.
	 */
	public void move() {

		// Set dx and dy
		switch (nextDirection) {

		case 1: {
			head.setDx(-SIZE);
			head.setDy(0);
		}
			break; // dx-STEP //left
		case 2: {
			head.setDy(-SIZE);
			head.setDx(0);
		}
			break; // dy-STEP //up
		case 3: {
			head.setDx(SIZE);
			head.setDy(0);
		}
			break; // dx+STEP //right
		case 4: {
			head.setDy(SIZE);
			head.setDx(0);
		}
			break; // dy+STEP //down
		default: {
			if (player == 1) {
				head.setDx(SIZE);
				head.setDy(0);
			}
			if (player == 2) {
				head.setDx(-SIZE);
				head.setDy(0);
			}

		}

		}
		
		// Set the coodinates of the head
		head.setx(head.getx() + head.getDx());
		head.sety(head.gety() + head.getDy());
	}

	// *****************************************************************************
	// //
	// * * //
	// * ABSTRACT METHODS * //
	// * * //
	// *****************************************************************************
	// //

	/**
	 * Generate the next direction of the snake. The next direction is chosen
	 * accordingly to the type of snake.
	 * 
	 * @param arena   the Arena of the game.
	 * @param keycode a KeyCode used for the controlled snake.
	 * @return an integer representing the direction : 1 is LEFT, 2 is UP, 3 is
	 *         RIGHT, 4 is DOWN (according to the arrows of the keyboard taken
	 *         clockwise)
	 */
	public abstract int generateDirection(Arena arena, KeyCode keycode);

	/**
	 * Tells if a direction is forbidden. Depends on the type of snake.
	 * 
	 * @param arena     the Arena of the game.
	 * @param direction an integer representing the direction to check.
	 * @return true if the direction is forbidden, false otherwise.
	 */
	public abstract boolean isForbidden(Arena arena, int direction);
	
	// *****************************************************************************
	// //
	// * * //
	// * GET & SET METHODS * //
	// * * //
	// *****************************************************************************
	// //

	/**
	 * Get the color of the snake.
	 * 
	 * @return the color.
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Set the color of the snake.
	 * 
	 * @param the new color.
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Get the body of the snake.
	 * 
	 * @return an ArrayList representing the body.
	 */
	public ArrayList<BodyPart> getBody() {
		return body;
	}

	/**
	 * Get the head.
	 * 
	 * @return the head.
	 */
	public BodyPart getHead() {
		return head;
	}

	/**
	 * Set the head of the snake.
	 * 
	 * @param the new head.
	 */
	public void setHead(BodyPart head) {
		this.head = head;
	}

	/**
	 * Get the last ring.
	 * 
	 * @return the last ring.
	 */
	public BodyPart getLastring() {
		return lastring;
	}

	/**
	 * Set the last ring of the snake.
	 * 
	 * @param the new last ring.
	 */
	public void setLastring(BodyPart lastring) {
		this.lastring = lastring;
	}

	/**
	 * Get the tail.
	 * 
	 * @return the tail.
	 */
	public BodyPart getTail() {
		return tail;
	}

	/**
	 * Get the player.
	 * 
	 * @return an integer representing the player.
	 */
	public int getPlayer() {
		return player;
	}

	/**
	 * Get the next direction.
	 * 
	 * @return an integer representing the next direction.
	 */
	public int getNextDirection() {
		return nextDirection;
	}

	/**
	 * Set the next direction of the snake.
	 * 
	 * @param an integer representing the new value for the next direction.
	 */
	public void setNextDirection(int nextDirection) {
		this.nextDirection = nextDirection;
	}

	// *****************************************************************************
	// //
	// * * //
	// * DISPLAY FUNCTIONS USED FOR DEBUGGING (not used currently in the code)* //
	// * * //
	// *****************************************************************************
	// //

	/** Display the body of the snake. */
	public void displayBody() {
		System.out.println("{ ");
		for (BodyPart ring : body) {
			System.out.println("[" + ring.getx() + ", " + ring.gety() + "]");
		}
		System.out.println(" }");
	}

	/** Display the head of the snake. */
	public void displayHead() {
		System.out.println("tête : {" + head.getx() + ", " + head.gety() + "}");
	}

	/** Display the last ring of the snake. */
	public void displayLastRing() {
		System.out.println("lastring : [" + lastring.getx() + ", " + lastring.gety() + "]");
	}
}
