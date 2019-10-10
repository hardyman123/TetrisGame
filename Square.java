package Tetris;

import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/*
 * This is my "smart square" class and is comprised of all the components/characteristics
 * that each square in our board and pieces have. Like color, position, and status of
 * whether or not it's part of the board.
 */
public class Square{
	
	boolean _isBoard;
	Rectangle _square;
	Paint _outstroke;
	
	// Constructor takes in a color, creates a square, gives it that color and sets
	// a default status of not being "part of the board"
	public Square(Paint fill, Paint lining) {
		_outstroke = fill;
		_square = new Rectangle(Constants.SQUARE_SIZE, Constants.SQUARE_SIZE, _outstroke);
		this.setOutline(lining);
		_isBoard = false;
	}
	
	// Allows you to change the status of the square by passing in a boolean
	public void setStatus(Boolean status) {
		_isBoard = status;
	}
	
	// Returns whether or not the square is part of the board
	public boolean getStatus() {
		return _isBoard;
	}

	// Returns the javafx.Rectangle
	public Node getRoot() {
		return _square;
	}
	
	// Sets the x position of the square
	public void setX(double xpos) {
		_square.setX(xpos*Constants.SQUARE_SIZE);
	}
	
	// Returns the x position of the square
	public int getX() {
		return (int) (_square.getX()/Constants.SQUARE_SIZE);
	}
	
	// Sets the y position of the square
	public void setY(double ypos) {
		_square.setY(ypos*Constants.SQUARE_SIZE);
	}
	
	// Returns the y position of the square
	public int getY() {
		return (int) (_square.getY()/Constants.SQUARE_SIZE);
	}
	
	public Paint getOutstroke() {
		return _outstroke;
	}
	
	// Takes in a color and sets the color of the stroke of the square to that color
	public void setOutline(Paint fill) {
		_outstroke = fill;
		_square.setStroke(_outstroke);
	}
	
	// Takes in a color and sets the color of the square to that color.
	public void setColor(Paint fill) {
		_square.setFill(fill);
	}
	
}