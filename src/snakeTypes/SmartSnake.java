package snakeTypes;

import java.util.ArrayList;
import java.util.Collections;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import snakeGame.Arena;
import snakeGame.GameAttributes;

/**
 * The smart snake is a type of snake which chooses its direction randomly, but
 * which checks if the spot is occupied or not beforehand. It represents the
 * second level of difficulty for an IA.
 * 
 * @author Melanie Gornet
 *
 */
public class SmartSnake extends Snake {

	private static final double SIZE = GameAttributes.ELEMENT_SIZE;

	/**
	 * Constructor of the smart snake.
	 * 
	 * @param color  Color of the snake.
	 * @param player an integer : if player = 1, the snake is player one, if player
	 *               = 2, the snake is second player.
	 */
	public SmartSnake(Color color, int player) {
		super(color, player);
		this.setNextDirection(0);
	}

	/**
	 * Generate the next direction of the snake. The random snake chooses its
	 * direction randomly among the directions possibles ie the directions of an
	 * empty spot.
	 * 
	 * @param arena   the Arena of the game.
	 * @param keycode a KeyCode used for the controlled snake.
	 * @return an integer representing the direction : 1 is LEFT, 2 is UP, 3 is
	 *         RIGHT, 4 is DOWN (according to the arrows of the keyboard taken
	 *         clockwise)
	 */
	public int generateDirection(Arena arena, KeyCode keycode) {

		// Initialize an ArrayList with the four directions
		ArrayList<Integer> directions = new ArrayList<>();
		directions.add(1);
		directions.add(2);
		directions.add(3);
		directions.add(4);

		// Shuffle the directions and return one not forbidden
		Collections.shuffle(directions);
		for (int chosenDirection : directions) {
			if (!isForbidden(arena, chosenDirection)) {
				return (chosenDirection);
			}
		}
		return (1);
	}

	/**
	 * Tells if a direction is forbidden. For the smart snake, the directions
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
