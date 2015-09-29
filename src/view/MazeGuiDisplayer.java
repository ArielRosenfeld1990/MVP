package view;

import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerators.Position;

public abstract class MazeGuiDisplayer extends Canvas {
	int[][] mazeData;

	MazeGuiDisplayer(Composite parent,int style) {
		super(parent,style);
	}

	public void setMazeData(int[][] mazeData){
		this.mazeData = mazeData;
	}
	
	public abstract void setCharacterPosition(Position position);
	
	public abstract void moveUp();
	
	public abstract void moveDown();
	
	public abstract void moveLeft();
	
	public abstract void moveRight();
}
