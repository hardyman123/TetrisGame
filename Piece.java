package Tetris;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/*
 * This class models all the things that each Tetris piece can do, like be created,
 * rotate, move, and generate a random piece
 */
public class Piece{
	
	Square[] _squareArray;
	boolean _isNotSquare;
	
	public Piece(Pane pane) {
		_squareArray = new Square[4];
		_isNotSquare = true;
		this.generateShape(pane);
	}
	
	// Takes in a color, array and offset and creates one of the 6 shapes based
	// on the information passed.
	public void makeShape(Color fill, double[][] shape, int xOffset, Pane pane) {
		for(int i=0; i<4; i++) {
			Square component = new Square(fill, Color.BLACK);
			component.setX(shape[i][0]+xOffset);
			component.setY(shape[i][1]+1);
			pane.getChildren().add(component.getRoot());
			_squareArray[i] = component;
		}
	}
	
	// Rotates the shape around a center square
	public void rotate() {
		if(_isNotSquare) {
			int centerX = _squareArray[1].getX();
			int centerY = _squareArray[1].getY();
			for(int i=0; i<4; i++) {
				int currY = _squareArray[i].getY();
				int currX = _squareArray[i].getX();
				_squareArray[i].setX(centerX - centerY + currY);
				_squareArray[i].setY(centerY + centerX - currX);
			}
		}
	}
	
	
	// Moves every square in shape 1 unit left
	public void moveLeft() {
		for(int i=0; i<4; i++) {
			_squareArray[i].setX(_squareArray[i].getX()-1);
		}
	}
	
	// Moves every square in shape 1 unit right
	public void moveRight() {
		for(int i=0; i<4; i++) {
			_squareArray[i].setX(_squareArray[i].getX()+1);
		}
	}
	
	// Moves every square in shape 1 unit down
	public void moveDown() {
		for(int i=0; i<4; i++) {
			_squareArray[i].setY(_squareArray[i].getY()+1);
		}
	}
	
	// Makes a random shape out of the 6 possible shapes
	public void generateShape(Pane pane) {
		int piece = (int) (6*Math.random());
		System.out.println(piece);
		switch(piece) {
		case 0:
			this.makeShape(Constants.LINE_COL, Constants.LINE, 3, pane);
			break;
		case 1:
			this.makeShape(Constants.J_COL, Constants.J, 3, pane);
			break;
		case 2:
			this.makeShape(Constants.L_COL, Constants.L, 3, pane);
			break;
		case 3:
			this.makeShape(Constants.SQUARE_COL, Constants.SQUARE, 4, pane);
			this.isSquare();
			break;
		case 4:
			this.makeShape(Constants.Z_COL, Constants.Z, 4, pane);
			break;
		case 5:
			this.makeShape(Constants.S_COL, Constants.S, 3, pane);
			break;
		}
	}
	
	public void isSquare() {
		_isNotSquare = false;
	}
	
	// Returns the array of squares that make up a shape
	public Square[] getShapeArray() {
		return _squareArray;
	}
	
	
	
}