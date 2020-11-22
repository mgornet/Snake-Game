package snakeParts;

import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import snakeGame.GameAttributes;
import snakeParts.BodyPart;

/**
 * Class Ring, a body part in the middle of the snake.
 * 
 * @author Melanie Gornet
 *
 */
public class Ring extends BodyPart {

	private static final double SIZE = GameAttributes.ELEMENT_SIZE;

	/**
	 * Constructor for the class Ring. Creates a rectangle of coordinates (x,y) and
	 * of Color color.
	 * 
	 * @param x     Coordinate x of the ring.
	 * @param y     Coordinate y of the ring.
	 * @param color Color of the ring.
	 */
	public Ring(double x, double y, Color color) {
		super(x, y, color);

		setTranslateX(x);
		setTranslateY(y);

		// a few unnecessary esthetic calls
		setArcWidth(SIZE / 3);
		setArcHeight(SIZE / 3);
		setStroke(Color.BLACK);
		setStrokeWidth(3.0);
		setStrokeLineCap(StrokeLineCap.ROUND);
	}

	/**
	 * Get the type of the body part : "Ring".
	 * 
	 * @return a string telling the type of the body part : "Ring".
	 */
	public String getType() {
		return "Ring";
	}
}
