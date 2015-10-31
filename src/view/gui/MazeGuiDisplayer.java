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

	/**
	 * <h1>MazeGuiDisplayer</h1>
	 * constructor for MazeGuiDisplayer
	 * @param parent the parent Composite
	 * @param style SWT style
	 */
	public MazeGuiDisplayer(Composite parent, int style) {
		super(parent, style);
        }

	/**
	 * <h1>initialize</h1>
	 * this method sets the maze and initializes our maze and the character
	 * starting position 
	 * @param maze3d the maze to display
	 */
	public void initilaize(Maze3d maze3d) {
		this.maze3d = maze3d;
		if (character != null)
			character.setPosition(maze3d.getStartPosition());
	}

	
	/**
	 * <h1>setCharacter</h1>
	 * This method is a setter for our character
	 * @param character a character to set to
	 */
	public void setCharacter(MazeCharacterGuiDisplayer character) {
		this.character = character;
	}
	
	/**
	 * <h1>MazeCharacterGuiDisplayer</h1>
	 * This method is a getter for our character in the maze
	 * @return character is the character in the maze.
	 */	
	public MazeCharacterGuiDisplayer getCharacter() {
		return character;
	}
	
	/**
	 * <h1>moveUp</h1>
	 * This method is moving the character up in the maze
	 * must be implemented
	 */
	public abstract void moveUp();
	
	/**
	 * <h1>moveDown</h1>
	 * This method is moving the character down in the maze
	 * must be implemented
	 */
	public abstract void moveDown();
	
	/**
	 * <h1>moveLeft</h1>
	 * This method is moving the character left in the maze
	 * must be implemented
	 */
	public abstract void moveLeft();
	
	/**
	 * <h1>moveRight</h1>
	 * This method is moving the character right in the maze
	 * must be implemented
	 */

	public abstract void moveRight();
	
	/**
	 * <h1>moveForward</h1>
	 * This method is moving the character forward in the maze
	 * must be implemented
	 */
	public abstract void moveForward();
	
	/**
	 *<h1>moveBackward</h1>
	 * This method is moving the character backwards in the maze
	 * must be implemented
	 */
	public abstract void moveBackward();
	
	/**
	 * <h1>displaySolution</h1>
	 * This method is for displaying the solution for the maze
	 *@param solution is the solution for the maze to display
	 */
	public abstract void displaySolution(Solution solution);
	
	/**
	 * <h1>displayHint</h1>
	 * This method is for displaying the hint for a specific Maze3dState
	 *@param hint is the hint for the Maze3dState to display
	 */
	public abstract void displayHint(Maze3dState hint);
}