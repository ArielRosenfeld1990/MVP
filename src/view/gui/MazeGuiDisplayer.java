package view.gui;

import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerators.Maze3d;

public abstract class MazeGuiDisplayer extends Canvas {
	
	protected Maze3d maze3d;
	protected MazeCharacterGuiDisplayer character;
	protected int[][] currentCrossSection;
	protected char currentAxis;
	
	public MazeGuiDisplayer(Composite parent,int style) {
		super(parent,style);
	}

	public void setMazeData(Maze3d maze3d){
		this.maze3d = maze3d;
		if(character!=null)
			character.setPosition(maze3d.getStartPosition());
	}
	
	public void setCharacter(MazeCharacterGuiDisplayer character){
		this.character = character;
	}

	public MazeCharacterGuiDisplayer getCharacter() {
		return character;
	}

	public int[][] getCurrentCrossSection() {
		return currentCrossSection;
	}

	public void setCurrentCrossSection(int[][] currentCrossSection) {
		this.currentCrossSection = currentCrossSection;
	}

	public char getCurrentAxis() {
		return currentAxis;
	}

	public void setCurrentAxis(char currentAxis) {
		this.currentAxis = currentAxis;
	}
	
	public abstract void moveUp();
	
	public abstract void moveDown();
	
	public abstract void moveLeft();
	
	public abstract void moveRight();
	
	public abstract void moveForward();
	
	public abstract void moveBackward();
}
