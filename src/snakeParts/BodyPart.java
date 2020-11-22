package snakeParts;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import snakeGame.GameAttributes;

/**
 * Abstract class BodyPart representing a body part of the snake (an egg). It
 * could be a simple ring at the middle of the snake, a head at the begging or a
 * tail at the end
 * 
 * @author Melanie Gornet
 *
 */
public abstract class BodyPart extends Rectangle {

	private static final double SIZE = GameAttributes.ELEMENT_SIZE;
	/* Attributes */
	private double x, y, dx, dy;

	/**
	 * Constructor for the class BodyPart. Creates a rectangle of coordinates (x,y)
	 * and of Color color.
	 * 
	 * @param x     Coordinate x of the body part.
	 * @param y     Coordinate y of the body part.
	 * @param color Color of the body part.
	 */
	public BodyPart(double x, double y, Color color) {
		super(SIZE, SIZE, color);
		this.x = x;
		this.y = y;
		this.dx = 0;
		this.dy = 0;
	}

	// *****************************************************************************
	// //
	// * * //
	// * GET & SET METHODS * //
	// * * //
	// *****************************************************************************
	// //

	/**
	 * Get the type of the body part.
	 * 
	 * @return a string telling the type of the body part.
	 */
	public abstract String getType();

	/**
	 * Get the value of dx.
	 * 
	 * @return the value of the dx attribute.
	 */
	public double getDx() {
		return dx;
	}

	/**
	 * Get the value of dy.
	 * 
	 * @return the value of the dy attribute.
	 */
	public double getDy() {
		return dy;
	}

	/**
	 * Set the value of dy.
	 * 
	 * @param dy the new value of the attribute.
	 */
	public void setDy(double dy) {
		this.dy = dy;
	}

	/**
	 * Set the value of dx.
	 * 
	 * @param dx the new value of the attribute.
	 */
	public void setDx(double dx) {
		this.dx = dx;
	}

	/**
	 * Get the value of x.
	 * 
	 * @return the value of the x attribute.
	 */
	public double getx() {
		return x;
	}

	/**
	 * Get the value of dy.
	 * 
	 * @return the value of the dy attribute.
	 */
	public double gety() {
		return y;
	}

	/**
	 * Set the value of y.
	 * 
	 * @param y the new value of the attribute.
	 */
	public void sety(double y) {
		this.y = y;
	}

	/**
	 * Set the value of x.
	 * 
	 * @param x the new value of the attribute.
	 */
	public void setx(double x) {
		this.x = x;
	}

}
