package view.gui;

import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerators.Maze3d;
import algorithms.search.Maze3dState;
import algorithms.search.Solution;

/**
 * <h1>MazeGuiDisplayer</h1> The MazeGuiDisplayer abstract class represents our gui for maze,
 * extends Canvas
 * <p>
 *
 * @author Ariel Rosenfeld,Ofir Calif
 *
 * 
 */
public abstract class MazeGuiDisplayer extends Canvas {

	protected Maze3d maze3d;
	protected MazeCharacterGuiDisplayer character;
	//protected int[][] currentCrossSection;
	//protected char currentAxis;

	/**
	 * constructor for MazeGuiDisplayer
	 */
	public MazeGuiDisplayer(Composite parent, int style) {
		super(parent, style);
        }
	/**
	 * this method sets the maze and initializes our maze and the character
	 * starting position
	 */
	public void initilaize(Maze3d maze3d) {
		this.maze3d = maze3d;
		if (character != null)
			character.setPosition(maze3d.getStartPosition());
	}
	/**
	 * This method is a setter for our character
	 * @param crossSection is the cross section.
	 */

	public void setCharacter(MazeCharacterGuiDisplayer character) {
		this.character = character;
	}
	/**
	 * This method is a getter for our character in the maze
	 * @return character is the character in the maze.
	 */
	
	public MazeCharacterGuiDisplayer getCharacter() {
		return character;
	}

//	public int[][] getCurrentCrossSection() {
//		return currentCrossSection;
//	}
//
//	public void setCurrentCrossSection(int[][] currentCrossSection) {
//		this.currentCrossSection = currentCrossSection;
//		redraw();
//	}
//
//	public char getCurrentAxis() {
//		return currentAxis;
//	}
//
//	public void setCurrentAxis(char currentAxis) {
//		this.currentAxis = currentAxis;
//		redraw();
//	} 
	
	/**
	 * This method is moving the character up in the maze
	 * must be implemented
	 */
	
	public abstract void moveUp();
	/**
	 * This method is moving the character down in the maze
	 * must be implemented
	 */
	public abstract void moveDown();
	/**
	 * This method is moving the character left in the maze
	 * must be implemented
	 */
	public abstract void moveLeft();
	/**
	 * This method is moving the character right in the maze
	 * must be implemented
	 */
	public abstract void moveRight();
	/**
	 * This method is moving the character forward in the maze
	 * must be implemented
	 */
	public abstract void moveForward();
	/**
	 * This method is moving the character backwards in the maze
	 * must be implemented
	 */
	public abstract void moveBackward();
	/**
	 * This method is for displaying the solution for the maze
	 *@param solution is the solution for the maze
	 */
	public abstract void displaySolution(Solution solution);
	/**
	 * This method is for displaying the hint for a specific Maze3dState
	 *@param hint is the hint for the Maze3dState
	 */
	public abstract void displayHint(Maze3dState hint);
}