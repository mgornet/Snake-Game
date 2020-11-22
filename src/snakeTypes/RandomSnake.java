package snakeTypes;

import java.util.ArrayList;
import java.util.Collections;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import snakeGame.Arena;
import snakeGame.GameAttributes;

/**
 * The random snake is a type of snake which chooses its direction randomly,
 * without checking if the spot is occupied or not. It represents the first
 * level of difficulty for an IA.
 * 
 * @author Melanie Gornet
 *
 */
public class RandomSnake extends Snake {

	private static final double SIZE = GameAttributes.ELEMENT_SIZE;

	/**
	 * Constructor of the random snake.
	 * 
	 * @param color  Color of the snake.
	 * @param player an integer : if player = 1, the snake is player one, if player
	 *               = 2, the snake is second player.
	 */
	public RandomSnake(Color color, int player) {
		super(color, player);
		this.setNextDirection(0);
	}

	/**
	 * Generate the next direction of the snake. The random snake chooses its
	 * direction randomly among the three directions possibles.
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
		int chosenDirection = directions.get(0);
		while (isForbidden(arena, chosenDirection)) {
			Collections.shuffle(directions);
			chosenDirection = directions.get(0);
		}

		return (chosenDirection);
	}

	/**
	 * Tells if a direction is forbidden. For the random snake, the only direction
	 * forbidden is the one occupied by the last ring ie the snake cannot turn over
	 * in a single move.
	 * 
	 * @param arena     the Arena of the game.
	 * @param direction an integer representing the direction to check.
	 * @return true if the direction is forbidden, false otherwise.
	 */
	public boolean isForbidden(Arena arena, int direction) {

		switch (direction) {

		case 1: {
			if (getLastring().getx() == getHead().getx() - SIZE && getLastring().getY() == getHead().getY()) {
				return true;
			}
			return false;
		}
		case 2: {
			if (getLastring().getx() == getHead().getx() && getLastring().getY() == getHead().getY() - SIZE) {
				return true;
			}
			return false;
		}
		case 3: {
			if (getLastring().getx() == getHead().getx() + SIZE && getLastring().getY() == getHead().getY()) {
				return true;
			}
			return false;
		}
		case 4: {
			if (getLastring().getx() == getHead().getx() && getLastring().getY() == getHead().getY() + SIZE) {
				return true;
			}
			return false;
		}
		default: {
			return false;
		}
		}
	}
}
