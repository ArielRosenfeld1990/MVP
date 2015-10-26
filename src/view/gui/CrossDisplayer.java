package view.gui;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerators.Position;

/**
 * <h1>CrossDisplayer</h1> The CrossDisplayer abstract class represents our way to represent the
 * cross section in our 3d maze on the window,extends Canvas
 * <p>
 *
 * @author Ariel Rosenfeld,Ofir Calif
 *
 * 
 */
public abstract class CrossDisplayer extends Canvas {
	protected int resize;
	protected int[][] crossSection;
	protected MazeCharacterGuiDisplayer character;
	protected Position goal;
	protected boolean drawHint;
	protected Position currentHint;
	protected Image goalImage;
	/**
	 * This method is a getter for our goal image
	 * @return goalImage is the goal image.
	 */
	public Image getGoalImage() {
		return goalImage;
	}
	/**
	 * This method is a setter for our Goal image
	 * @param goalImage is the name of the goal image.
	 */
	public void setGoalImage(Image goalImage) {
		this.goalImage = goalImage;
	}
	/**
	 * This method is a getter for our hint image
	 * @return hintImage is the hint image.
	 */
	public Image getHintImage() {
		return hintImage;
	}
	/**
	 * This method is a setter for our hint image
	 * @param goalImage is the name of the hint image.
	 */
	public void setHintImage(Image hintImage) {
		this.hintImage = hintImage;
	}

	protected Image hintImage;
	/**
	 * This method represents our cross section
	 * @param parent is the component parent.
	 * @param style is the style that we will use.
	 * @param character represents our character
	 */
	public CrossDisplayer(Composite parent, int style,MazeCharacterGuiDisplayer character) {
		super(parent, style);
		this.character=character;
		resize=0;
	}
	public int getResize() {
		return resize;
	}
	public void setResize(int resize) {
		this.resize = resize;
	}
	/**
	 * This method is a getter for our cross section
	 * @return crossSection is the cross section.
	 */
	public int[][] getCrossSection() {
		return crossSection;
	} 
	/**
	 * This method is a setter for our cross section
	 * @param crossSection is the cross section.
	 */

	public void setCrossSection(int[][] crossSection) {
		this.crossSection = crossSection;
	}
	/**
	 * This method is a getter for our character in the maze
	 * @return character is the character.
	 */
	public MazeCharacterGuiDisplayer getCharacter() {
		return character;
	}
	/**
	 * This method is a setter for our character in the maze
	 * @param character is the character in the maze.
	 */
	public void setCharacter(MazeCharacterGuiDisplayer character) {
		this.character = character;
	}
	/**
	 * This method is a getter for our goal position
	 * @return goal is the goal position.
	 */
	public Position getGoal() {
		return goal;
	}
	/**
	 * This method is a setter for our goal position in the maze
	 * @param goal is the character in the goal position in the maze.
	 */
	public void setGoal(Position goal) {
		this.goal = goal;
	}
	/**
	 * This method is for displaying the hint in the maze
	 * @param hint is the requested hint in the maze.
	 */
	public void displayHint(Position hint){
		currentHint=hint;
		drawHint=true;
	}
	
}
