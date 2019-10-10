package Tetris;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/*
 * This is the top level object. Here I create a borderpane and add an instance of
 * my Tetris game to it.
 */
public class PaneOrganizer{
	BorderPane _bPane;
	
	// I create an instance of my Tetris game and add it to a borderpane
	public PaneOrganizer() {
		_bPane = new BorderPane();
		Tetris tetris = new Tetris();
		_bPane.setCenter(tetris.getRoot());
	}
	
	// Returns the root borderpane
	public Pane getRoot() {
		return _bPane;
	}
	
}