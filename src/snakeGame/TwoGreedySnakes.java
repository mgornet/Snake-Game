package snakeGame;

import java.util.ArrayList;

import snakeGame.Arena;
import snakeParts.BodyPart;
import snakeTypes.ControlledSnake;
import snakeTypes.SuperSmartSnake;
import javafx.application.Application;
import javafx.scene.paint.Color;

/* Import only when used in the constructor
 
import snakeTypes.RandomSnake;
import snakeTypes.SmartSnake; */

/**
 * Main class representing the complete game.
 * 
 * @author Melanie Gornet
 *
 */
public class TwoGreedySnakes extends GameAttributes {

	/* Attributes */
	private Arena arena;

	/**
	 * Default constructor. One can modify the snakes inside according to the level
	 * they want.
	 */
	public TwoGreedySnakes() {
		ControlledSnake snake_1 = new ControlledSnake(Color.BLUE, 1);
		// SuperSmartSnake snake_1 = new SuperSmartSnake(Color.BLUE, 1);
		SuperSmartSnake snake_2 = new SuperSmartSnake(Color.AQUAMARINE, 2);
		// RandomSnake snake_2 = new SmartSnake(Color.AQUAMARINE, 2);
		// RandomSnake snake_2 = new RandomSnake(Color.AQUAMARINE, 2);
		this.arena = new Arena(snake_1, snake_2);
	}

	/** Application main method */
	public static void main(String[] args) {
		Application.launch(args);
	}

	/**
	 * Part of the program executed at each cycle of calculus.
	 * 
	 * @return ArrayList<BodyPart> a Collection of Node that will form the new
	 *         JavaFX graph scene. All theses nodes will be displayed on screen.
	 */
	public ArrayList<BodyPart> gameStep() {

		// Generates the next directions
		arena.getSnake_1().setNextDirection(arena.getSnake_1().generateDirection(arena, Keyboard.getLastKeyCode()));
		arena.getSnake_2().setNextDirection(arena.getSnake_2().generateDirection(arena, Keyboard.getLastKeyCode()));

		// Stop conditions
		if (!arena.getSnake_1().isKnockedOut(arena) && !arena.getSnake_2().isKnockedOut(arena)
				&& !arena.isHeadCollision()) {

			// Execute the snakes movements and update the graphical display
			arena.getSnake_1().move();
			arena.getSnake_2().move();
			arena.getSnake_1().updateSnake();
			arena.getSnake_2().updateSnake();

			return arena.getBodies();
		}

		// Display the result of the game
		else {

			if (arena.getSnake_1().isKnockedOut(arena) && !arena.getSnake_2().isKnockedOut(arena)) {
				System.out.println("You lost!");
			} else if (!arena.getSnake_1().isKnockedOut(arena) && arena.getSnake_2().isKnockedOut(arena)) {
				System.out.println("You won !");
			} else if (arena.getSnake_1().isKnockedOut(arena) && arena.getSnake_2().isKnockedOut(arena)
					|| !arena.isHeadCollision()) {
				System.out.println("It's a draw !");
			}
		}

		// Close the window
		System.exit(0);

		return arena.getBodies();
	}
}
