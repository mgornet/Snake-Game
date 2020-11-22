package snakeTypes;

import java.util.ArrayList;
import java.util.Collections;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import snakeGame.Arena;
import snakeGame.GameAttributes;

/**
 * The super smart snake is a type of snake which chooses the "best direction"
 * ie the "most empty direction". Its counts the number of empty spots in an
 * direction and the "best direction" is the one with the greater number of
 * empty spots. It represents the third level of difficulty for an IA.
 * 
 * @author Melanie Gornet
 *
 */
public class SuperSmartSnake extends Snake {

	private static final double SIZE = GameAttributes.ELEMENT_SIZE;

	/**
	 * Constructor of the super smart snake.
	 * 
	 * @param color  Color of the snake.
	 * @param player an integer : if player = 1, the snake is player one, if player
	 *               = 2, the snake is second player.
	 */
	public SuperSmartSnake(Color color, int player) {
		super(color, player);
		this.setNextDirection(0);
	}

	/**
	 * Generate the next direction of the snake. The super smart snake chooses the "most empty direction".
	 * 
	 * @param arena   the Arena of the game.
	 * @param keycode a KeyCode used for the controlled snake.
	 * @return an integer representing the direction : 1 is LEFT, 2 is UP, 3 is
	 *         RIGHT, 4 is DOWN (according to the arrows of the keyboard taken
	 *         clockwise)
	 */
	public int generateDirection(Arena arena, KeyCode keycode) {
		
		// Initialization of the counters
		int counter_left = 0, counter_up = 0, counter_right = 0, counter_down = 0;
		
		// Implementation of the counters
		while (!arena.isOccupied(getHead().getx() - (counter_left + 1) * SIZE, getHead().gety())) {
			counter_left += 1;
		}
		while (!arena.isOccupied(getHead().getx(), getHead().gety() - (counter_up + 1) * SIZE)) {
			counter_up += 1;
		}
		while (!arena.isOccupied(getHead().getx() + (counter_right + 1) * SIZE, getHead().gety())) {
			counter_right += 1;
		}
		while (!arena.isOccupied(getHead().getx(), getHead().gety() + (counter_down + 1) * SIZE)) {
			counter_down += 1;
		}

		// Search of the maximum in the list of counters
		int[] counters = { counter_left, counter_up, counter_right, counter_down };
		int max = counters[0];

		for (int value : counters) {
			if (value > max) {
				max = value;
			}
		}

		// Generate an ArrayList of "best directions"
		ArrayList<Integer> directions = new ArrayList<>();

		if (max == counter_left) {
			directions.add(1);
		}
		if (max == counter_up) {
			directions.add(2);
		}
		if (max == counter_right) {
			directions.add(3);
		}
		if (max == counter_down) {
			directions.add(4);
		}

		// Elects randomly a direction among the bests
		Collections.shuffle(directions);
		return (directions.get(0));
	}
	
	/**
	 * Tells if a direction is forbidden. For the super smart snake, the directions
	 * forbidden are the ones which leads to an occupied spot.
	 * 
	 * @param arena     the Arena of the game.
	 * @param direction an integer representing the direction to check.
	 * @return true if the direction is forbidden, false otherwise.
	 */
	public boolean isForbidden(Arena arena, int direction) {

		switch (direction) {
		
		case 1: {
			if (arena.isOccupied(getHead().getx() - SIZE, getHead().gety())) {
				return true;
			}
			return false;
		}
		case 2: {
			if (arena.isOccupied(getHead().getx(), getHead().gety() - SIZE)) {
				return true;
			}
			return false;
		}
		case 3: {
			if (arena.isOccupied(getHead().getx() + SIZE, getHead().gety())) {
				return true;
			}
			return false;
		}
		case 4: {
			if (arena.isOccupied(getHead().getx(), getHead().gety() + SIZE)) {
				return true;
			}
			return false;
		}
		default: {
			break;
		}
		}
		return false;
	}

}
