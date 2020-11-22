package snakeParts;

import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import snakeGame.GameAttributes;
import snakeParts.BodyPart;

/**
 * Class Head, a body part at the begging of the snake. It leads the snake
 * movement.
 * 
 * @author Melanie Gornet
 *
 */
public class Head extends BodyPart {

	private static final double SIZE = GameAttributes.ELEMENT_SIZE;

	/**
	 * Constructor for the class Head. Creates a rectangle of coordinates (x,y) and
	 * of Color color.
	 * 
	 * @param x     Coordinate x of the head.
	 * @param y     Coordinate y of the head.
	 * @param color Color of the head.
	 */
	public Head(double x, double y, Color color) {
		super(x, y, color);

		setTranslateX(x);
		setTranslateY(y);

		// a few unnecessary esthetic calls
		setArcWidth(SIZE / 1);
		setArcHeight(SIZE / 1);
		setStroke(Color.RED);
		setStrokeWidth(3.0);
		setStrokeLineCap(StrokeLineCap.ROUND);
	}

	/**
	 * Get the type of the body part : "Head".
	 * 
	 * @return a string telling the type of the body part : "Head".
	 */
	public String getType() {
		return "Head";
	}

}
