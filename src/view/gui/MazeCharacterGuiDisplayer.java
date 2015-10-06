package view.gui;

import org.eclipse.swt.events.PaintEvent;
import algorithms.mazeGenerators.Position;
/**
 * <h1>MazeCharacterGuiDisplayer</h1> 
 * The MazeCharacterGuiDisplayer abstract class represents functionality of a character
 * in our maze
 * <p>
 *
 * @author Ariel Rosenfeld,Ofir Calif
 *
 * 
 */
public abstract class MazeCharacterGuiDisplayer {

	private Position position;

	public MazeCharacterGuiDisplayer() {
	}
	/**
	 * This method is for drawing the character in the maze
	 * @param paintEvent is the given paint event.
	 * @param x is the value for the ratio	
	 * @param y is the value for the ratio
	 * @param characterWidth is the width of the character.
	 * @param characterHeight is the height of the character.
	 */
	public abstract void drawCharcter(PaintEvent paintEvent, int x, int y, int characterWidth, int characterHeight);
	/**
	 * This method is a getter for a position in the maze
	 * @return position is the position in the maze.
	 */
	public Position getPosition() {
		return position;
	}
	/**
	 * This method is a setter for a position in the maze
	 * @param position is the position in the maze.
	 */
	public void setPosition(Position position) {
		this.position = position;
	}

}
