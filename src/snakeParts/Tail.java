package snakeParts;

import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import snakeGame.GameAttributes;

/**
 * Class Tail, a body part at the end of the snake. Its coordinates are settle
 * in advance according if it belongs to the snake first player (upper left
 * corner) or the second player (bottom right corner).
 * 
 * @author Melanie Gornet
 *
 */
public class Tail extends BodyPart {

	private static final double SIZE = GameAttributes.ELEMENT_SIZE;

	/**
	 * Constructor for the class Tail. Creates a rectangle of coordinates (x,y) and
	 * of Color color.
	 * 
	 * @param x     Coordinate x of the tail.
	 * @param y     Coordinate y of the tail.
	 * @param color Color of the tail.
	 */
	public Tail(double x, double y, Color color) {
		super(x, y, color);

		setTranslateX(x);
		setTranslateY(y);

		// a few unnecessary esthetic calls
		setArcWidth(SIZE / 1);
		setArcHeight(SIZE / 1);
		setStroke(Color.BLACK);
		setStrokeWidth(3.0);
		setStrokeLineCap(StrokeLineCap.ROUND);
	}

	/**
	 * Get the type of the body part : "Tail".
	 * 
	 * @return a string telling the type of the body part : "Tail".
	 */
	public String getType() {
		return "Tail";
	}

}
