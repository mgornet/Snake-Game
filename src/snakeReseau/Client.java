package snakeReseau;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.paint.Color;
import snakeGame.Arena;
import snakeGame.GameAttributes;
import snakeGame.Keyboard;
import snakeParts.BodyPart;
import snakeTypes.ControlledSnake;
import snakeTypes.SuperSmartSnake;

/**
 * Client for the multiplayer game.
 * 
 * @author Melanie Gornet
 *
 */
public class Client extends GameAttributes {

	/* Attributes */
	private int port;
	private String machine;
	private Socket socket;
	private InputStream inputStream;
	private OutputStream outputStream;
	private PrintWriter writer;
	private BufferedReader reader;
	private Arena arena;

	/**
	 * Constructor of the client.
	 * 
	 * @throws IOException
	 */
	public Client() throws IOException {

		// Initialization of the attributes

		this.port = 1996;
		this.machine = "localhost";

		this.socket = new Socket(machine, port);

		this.inputStream = socket.getInputStream();
		this.outputStream = socket.getOutputStream();

		this.writer = new PrintWriter(outputStream);
		this.reader = new BufferedReader(new InputStreamReader(inputStream));
		this.arena = new Arena(new ControlledSnake(Color.BLUE, 1), new SuperSmartSnake(Color.AQUAMARINE, 2));
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

		// Stop conditions
		if (!arena.getSnake_1().isKnockedOut(arena) && !arena.getSnake_2().isKnockedOut(arena)
				&& !arena.isHeadCollision()) {

			// Generates the next direction
			arena.getSnake_2().setNextDirection(arena.getSnake_2().generateDirection(arena, Keyboard.getLastKeyCode()));

			// Sending direction
			Integer direction_snake_2 = arena.getSnake_2().getNextDirection();
			String message = direction_snake_2.toString();
			writer.println(message);
			writer.flush();

			// Reception of the information
			try {
				message = reader.readLine();
				arena.getSnake_1().setNextDirection(Integer.valueOf(message));
			} catch (IOException e) {
				e.printStackTrace();
			}

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
