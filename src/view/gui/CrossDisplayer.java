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
	 * <h1>getGoalImage</h1>
	 * This method is a getter for our goal image
	 * @return goalImage is the goal image.
	 */
	public Image getGoalImage() {
		return goalImage;
	}
	
	/**
	 * <h1>setGoalImage</h1>
	 * This method is a setter for our Goal image
	 * @param goalImage is the name of the goal image.
	 */
	public void setGoalImage(Image goalImage) {
		this.goalImage = goalImage;
	}

	/**
	 * <h1>getHintImage</h1>
	 * This method is a getter for our hint image
	 * @return hintImage is the hint image.
	 */
	public Image getHintImage() {
		return hintImage;
	}

	/**
	 * <h1>setHintImage</h1>
	 * This method is a setter for our hint image
	 * @param goalImage is the image of the hint image.
	 */
	public void setHintImage(Image hintImage) {
		this.hintImage = hintImage;
	}

	protected Image hintImage;
	
	/**
	 * <h1>CrossDisplayer constructor</h1>
	 * @param parent is the component parent.
	 * @param style is the style that we will use.
	 * @param character represents our character
	 */
	public CrossDisplayer(Composite parent, int style,MazeCharacterGuiDisplayer character) {
		super(parent, style);
		this.character=character;
		resize=0;
	}
	
	/**
	 * <h1>getResize</h1>
	 * this getter is for the resize dataMember
	 * @return resize dataMember value
	 */
	public int getResize() {
		return resize;
	}
	
	/**
	 * <h1>setResize</h1>
	 * this setter is for the resize dataMember
	 * @param resize the value to insert into resize
	 */
	public void setResize(int resize) {
		this.resize = resize;
	}
	
	/**
	 * <h1>getCrossSection</h1>
	 * This method is a getter for our cross section
	 * @return crossSection is the cross section.
	 */
	public int[][] getCrossSection() {
		return crossSection;
	} 
	
	/**
	 * <h1>setCrossSection</h1>
	 * This method is a setter for our cross section
	 * @param crossSection is the cross section.
	 */
	public void setCrossSection(int[][] crossSection) {
		this.crossSection = crossSection;
	}
	
	/**
	 * <h1>getCharacter</h1>
	 * This method is a getter for our character in the maze
	 * @return character is the character.
	 */
	public MazeCharacterGuiDisplayer getCharacter() {
		return character;
	}
	
	/**
	 * <h1>setCharacter</h1>
	 * This method is a setter for our character in the maze
	 * @param character is the character in the maze.
	 */
	public void setCharacter(MazeCharacterGuiDisplayer character) {
		this.character = character;
	}
	
	/**
	 * <h1>getGoal</h1>
	 * This method is a getter for our goal position
	 * @return goal is the goal position.
	 */
	public Position getGoal() {
		return goal;
	}
	
	/**
	 * <h1>setGoal</h1>
	 * This method is a setter for our goal position in the maze
	 * @param goal is the character in the goal position in the maze.
	 */
	public void setGoal(Position goal) {
		this.goal = goal;
	}
	
	/**
	 * <h1>displayHint</h1>
	 * This method is for displaying the hint in the maze
	 * @param hint is the requested hint in the maze.
	 */
	public void displayHint(Position hint){
		currentHint=hint;
		drawHint=true;
	}
	
}
