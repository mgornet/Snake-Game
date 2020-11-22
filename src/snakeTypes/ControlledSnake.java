package snakeTypes;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import snakeGame.Arena;
import snakeGame.GameAttributes;
import snakeGame.Keyboard;

/**
 * The controlled snake is a type of snake controlled by a human thanks to the
 * arrows of the keyboard.
 * 
 * @author Melanie Gornet
 *
 */
public class ControlledSnake extends Snake {

	private static final double SIZE = GameAttributes.ELEMENT_SIZE;

	/**
	 * Constructor of the controlled snake.
	 * 
	 * @param color  Color of the snake.
	 * @param player an integer : if player = 1, the snake is player one, if player
	 *               = 2, the snake is second player.
	 */
	public ControlledSnake(Color color, int player) {
		super(color, player);
	}

	/**
	 * Generate the next direction of the snake.
	 * 
	 * @param arena   the Arena of the game.
	 * @param keycode the KeyCode to turn into a direction.
	 * @return an integer representing the direction : 1 is LEFT, 2 is UP, 3 is
	 *         RIGHT, 4 is DOWN (according to the arrows of the keyboard taken
	 *         clockwise)
	 */
	public int generateDirection(Arena arena, KeyCode keycode) {
		if (!isForbidden(arena, 0)) {
			switch (keycode) {

			case LEFT: {
				return 1;
			}
			case UP: {
				return 2;
			}
			case RIGHT: {
				return 3;
			}
			case DOWN: {
				return 4;
			}
			default: {
				return 0;
			}
			}
		}
		return getNextDirection();
	}

	/**
	 * Tells if a direction is forbidden. For the controlled snake, the only
	 * direction forbidden is the one occupied by the last ring ie the snake cannot
	 * turn over in a single move.
	 * 
	 * @param arena     the Arena of the game.
	 * @param direction an integer representing the direction to check.
	 * @return true if the direction is forbidden, false otherwise.
	 */
	public boolean isForbidden(Arena arena, int direction) {
		switch (Keyboard.getLastKeyCode()) {
		case LEFT: {
			if (getLastring().getx() == getHead().getx() - SIZE && getLastring().gety() == getHead().gety()) {
				return true;
			}
			return false;
		}

		case UP: {
			if (getLastring().getx() == getHead().getx() && getLastring().gety() == getHead().gety() - SIZE) {
				return true;
			}
			return false;
		}

		case RIGHT: {
			if (getLastring().getx() == getHead().getx() + SIZE && getLastring().gety() == getHead().gety()) {
				return true;
			}
			return false;
		}

		case DOWN: {
			if (getLastring().getx() == getHead().getx() && getLastring().gety() == getHead().gety() + SIZE) {
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
