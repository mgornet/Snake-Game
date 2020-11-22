package snakeGame;

import java.util.ArrayList;
import snakeParts.BodyPart;
import snakeTypes.Snake;

/**
 * The Arena represents the game grid and the snakes who fight in it. It is
 * possible to know the state (occupied or not) of any of its spots at any time.
 * 
 * @author Melanie Gornet
 *
 */

public class Arena {

	/* Attributes */
	private Snake snake_2, snake_1;

	private static final double WINDOW_SIZE = GameAttributes.WINDOW_SIZE;

	/**
	 * Arena constructor with two snakes in argument.
	 * 
	 * @param snake_1 First snake to fight (player 1)
	 * @param snake_2 Second snake to fight (player 2)
	 */
	public Arena(Snake snake_1, Snake snake_2) {
		this.snake_1 = snake_1;
		this.snake_2 = snake_2;
	}

	/**
	 * Default constructor.
	 */
	public Arena() {
	}

	/**
	 * Get the first snake attribute.
	 * 
	 * @return snake_1 the first snake.
	 */
	public Snake getSnake_1() {
		return snake_1;
	}

	/**
	 * Get the second snake attribute.
	 * 
	 * @return snake_2 the second snake.
	 */
	public Snake getSnake_2() {
		return snake_2;
	}

	/**
	 * Check if a spot entered as an argument is an edge of the grid.
	 * 
	 * @param x Coordinate x of the spot to check.
	 * @param y Coordinate y of the spot to check.
	 * @return true if the spot is an edge, false otherwise.
	 */
	private boolean isTheEdge(double x, double y) {
		if ((x < 0 || y < 0) || (x >= WINDOW_SIZE || y >= WINDOW_SIZE)) {
			return true;
		}
		;
		return false;
	}

	/**
	 * Verifies whether the spot entered as an argument is occupied by one snake.
	 * 
	 * @param x     Coordinate x of the spot to check.
	 * @param y     Coordinate y of the spot to check.
	 * @param snake Snake of which we want to know if it occupies the spot.
	 * 
	 * @return true if the spot is occupied by the snake, false otherwise.
	 */
	private boolean isOccupiedBySnake(double x, double y, Snake snake) {

		for (BodyPart ring : snake.getBody()) {
			if (ring.getx() == x && ring.gety() == y) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Verifies if the spot entered as an argument is occupied by a snake of the
	 * arena or is an edge.
	 * 
	 * @param x Coordinate x of the spot to check.
	 * @param y Coordinate y of the spot to check.
	 *
	 * @return true if the spot is occupied by a snake or is an edge, false
	 *         otherwise.
	 */
	public boolean isOccupied(double x, double y) {
		if (isTheEdge(x, y) || isOccupiedBySnake(x, y, snake_1) || isOccupiedBySnake(x, y, snake_2)) {
			return true;
		}
		return false;
	}

	/**
	 * Get all the body parts of both snakes.
	 * 
	 * @return An ArrayList containing all the body parts of the snakes.
	 */
	public ArrayList<BodyPart> getBodies() {
		ArrayList<BodyPart> bodies = new ArrayList<>();
		for (BodyPart ring : snake_1.getBody()) {
			bodies.add(ring);
		}
		for (BodyPart ring : snake_2.getBody()) {
			bodies.add(ring);
		}
		return bodies;
	}

	/**
	 * Tells if there is an head collision. This test is necessary because otherwise
	 * the snakes aren't considered "knocked out".
	 * 
	 * @return true if the heads of the two snakes collided, false otherwise.
	 */
	public boolean isHeadCollision() {
		if (snake_1.getHead().getx() == snake_2.getHead().getx()
				&& snake_1.getHead().gety() == snake_2.getHead().gety()) {
			return true;
		}
		return false;
	}

}
