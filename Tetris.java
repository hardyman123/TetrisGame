package Tetris;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/*
 * This class contains all of the logic for my game, including clearing lines, moving pieces,
 * making new pieces, and a timeline
 */
public class Tetris{
	Pane _mainPane;
	Square[][] _board;
	Piece _piece;
	KeyHandler _keyHandler;
	Timeline _timeline;
	int _rowClear;
	
	public Tetris() {
		_mainPane = new Pane();
		_board = new Square[10][20];
		this.setupBoard();
		
//		System.out.println(_board[1][2]);
		
		_piece = new Piece(_mainPane);
		
		_keyHandler = new KeyHandler();
		_mainPane.addEventHandler(KeyEvent.KEY_PRESSED, _keyHandler);
		_mainPane.setFocusTraversable(true);
		this.setupTimeline();
	}
	
	// Starts the timeline for animation
	public void setupTimeline() {
		KeyFrame kf = new KeyFrame(Duration.seconds(Constants.DURATION), new TimeHandler());
		_timeline = new Timeline(kf);
		_timeline.setCycleCount(Animation.INDEFINITE);
		_timeline.play();
	}
	
	
	// Checks if piece can move to the left
	public boolean checkLeftValid() {
		for(int i=0; i<4; i++) {
			int currentY = _piece.getShapeArray()[i].getY();
			int nextX = _piece.getShapeArray()[i].getX()-1;
			if(nextX < 0 || _board[nextX][currentY].getStatus()) {
				System.out.println("Can't move left");
				return false;
			}
		}
		return true;
	}
	
	// Checks if the piece can move to the right
	public boolean checkRightValid() {
		for(int i=0; i<4; i++) {
			int currentY = _piece.getShapeArray()[i].getY();
			int nextX = _piece.getShapeArray()[i].getX()+1;
			if(nextX > 9 || _board[nextX][currentY].getStatus()) {
				System.out.println("Can't move right");
				return false;
			}
		}
		return true;
	}
	
	// Checks if the piece can move down
	public boolean checkDownValid() {
		for(int i=0; i<4; i++) {
			int nextY = _piece.getShapeArray()[i].getY()+1;
			int currentX = _piece.getShapeArray()[i].getX();
			if(nextY > 19 || _board[currentX][nextY].getStatus()) {
				System.out.println("Can't move down");
				return false;
			}
		}
		return true;
	}
	
	// Checks if the piece can rotate in that direction
	public boolean checkRotateValid() {
		int centerX = _piece.getShapeArray()[1].getX();
		int centerY = _piece.getShapeArray()[1].getY();
		for(int i=0; i<4; i++) {
			int currY = _piece.getShapeArray()[i].getY();
			int currX = _piece.getShapeArray()[i].getX();
			int newX = centerX - centerY + currY;
			int newY = centerY + centerX - currX;
			if(newX < 0 || newX > 9 ||newY > 19 || newY<0 || _board[newX][newY].getStatus()) {
				return false;
			}
		}
		return true;
	}
	
	
	
	
	// Makes each square of your shape a part of the board
	public void makeWall() {
		for(int i=0; i<4; i++) {
			Square square = _piece.getShapeArray()[i];
			square.setStatus(true);
			_board[square.getX()][square.getY()] = square;
			
		}
	}
	
	private class TimeHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			boolean goDown = checkDownValid();
			
			
			if(goDown == false) {
				Tetris.this.makeWall();
				
				if(Tetris.this.checkFullLines()) {
					Tetris.this.clearLines();
				}
				
				_piece = new Piece(_mainPane);
			
			} else {
				_piece.moveDown();
			}
	
		Tetris.this.checkGameOver();
			
		}
		
	}
	
	// Checks if game is over, and if so stops timeline and keyhandler
	public void checkGameOver() {
		
		for(int i=0; i<4; i++) {
			
			int xpos = _piece.getShapeArray()[i].getX();
			int ypos = _piece.getShapeArray()[i].getY();
			
			if(_board[xpos][ypos].getStatus()) {
				System.out.println("GAME OVER");
				_timeline.stop();
				_mainPane.removeEventHandler(KeyEvent.KEY_PRESSED, _keyHandler);
				
			}
			
		}
	}
	
	
	
	

	// Clears lines
	public void clearLines() {
//		System.out.println(_rowClear);
//		System.out.println("the boi^");
		for(int i=_rowClear; i>4; i--) {
			for(int j=0; j<10; j++) {
				
				if(_board[j][i-1].getStatus()==false) {
					_board[j][i] = new Square(Constants.BOARD_COLOR, Constants.BOARD_STROKE);
					_mainPane.getChildren().add(_board[j][i].getRoot());
				} else {
					_board[j][i] = _board[j][i-1];
				}
				Square square = _board[j][i];
				square.setX(j);
				square.setY(i);
				square.setOutline(_board[j][i-1].getOutstroke());
			}
		}
	}
	
	
	
	// Checks all lines to see if any are full
	public boolean checkFullLines() {
		for(int i=0; i<20; i++) {
			if(isRowFull(i)) {
//				System.out.println("Line " + i + " is full");
				_rowClear = i;
				return true;
			}
		}
		return false;
	}
	
	// This method checks if a particular row is full
	public boolean isRowFull(int row) {
		
		for(int i=0; i<10; i++) {
			if(_board[i][row].getStatus() == false) {
				return false;
			}
		}
		return true;
	}
	
	
	
	
	
	// Returns the root pane
	public Node getRoot() {
		return _mainPane;
	}
	
	// This method creates a board grid with all of the background squares
	public void setupBoard() {
		for(int i=0; i<10; i++) {
			for(int j=0; j<20; j++) {
				Square square = new Square(Constants.BOARD_COLOR, Constants.BOARD_STROKE);
				
				square.setX(i);
				square.setY(j);
				_board[i][j] = square;
				
				_mainPane.getChildren().addAll(square.getRoot());
			}
		}
		
	}
	
	
	// Handle's moving the pieces to the sides or rotating based off of key input
	private class KeyHandler implements EventHandler<KeyEvent>{

		@Override
		public void handle(KeyEvent event) {
			KeyCode keyPressed = event.getCode();
			switch(keyPressed) {
			case LEFT:
				if(Tetris.this.checkLeftValid()) {
					_piece.moveLeft();
				}
				break;
			case RIGHT:
				if(Tetris.this.checkRightValid()) {
					_piece.moveRight();
				}
				break;
			case UP:
				// where we will rotate
				if(Tetris.this.checkRotateValid()) {
					_piece.rotate();
				}
				break;
			case DOWN:
				if(Tetris.this.checkDownValid()) {
					_piece.moveDown();
				}
				break;
			}
			
		}
		
	}
	
	
	
	
	
}