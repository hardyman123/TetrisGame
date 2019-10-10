package Tetris;

import javafx.scene.paint.Color;

/*
 * This is my constants where I have a series of constants to use throughout my program
 */
public class Constants {
	
	public static final double DURATION = 0.5;
	
	public static final int SQUARE_SIZE = 30;
	public static final Color BOARD_COLOR = Color.CORNFLOWERBLUE;
	public static final Color BOARD_STROKE = Color.SKYBLUE;

	public static final double[][] LINE = { {0,0}, {1,0}, {2,0}, {3,0} };
	public static final double[][] J = { {0,0}, {1,0}, {2,0}, {2,1} };
	public static final double[][] L = { {0,1}, {0,0}, {1,0}, {2,0} };
	public static final double[][] SQUARE = { {0,0}, {1,0}, {0,1}, {1,1} };
	public static final double[][] Z = { {0,0}, {1,0}, {1,1}, {2,1} };
	public static final double[][] S = { {0,1}, {1,1}, {1,0}, {2,0} };
	
	public static final Color LINE_COL = Color.RED;
	public static final Color LINE_STR = Color.LIGHTCORAL;
	public static final Color J_COL = Color.GREEN;
	public static final Color J_STR = Color.LIGHTGREEN;
	public static final Color L_COL = Color.BLUE;
	public static final Color L_STR = Color.LIGHTBLUE;
	public static final Color SQUARE_COL = Color.DARKORCHID;
	public static final Color SQUARE_STR = Color.ORCHID;
	public static final Color Z_COL = Color.ORANGE;
	public static final Color Z_STR = Color.PEACHPUFF;
	public static final Color S_COL = Color.GOLD;
	public static final Color S_STR = Color.LIGHTGOLDENRODYELLOW;
	
}