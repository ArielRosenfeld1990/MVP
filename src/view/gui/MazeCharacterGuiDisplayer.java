package view.gui;

import org.eclipse.swt.events.PaintEvent;
import algorithms.mazeGenerators.Position;

public abstract class MazeCharacterGuiDisplayer {

	private Position position;

	public MazeCharacterGuiDisplayer() {
	}

	public abstract void drawCharcter(PaintEvent paintEvent, int x, int y, int characterWidth, int characterHeight);
 
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

}
