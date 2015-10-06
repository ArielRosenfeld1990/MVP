package view.gui;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Image;

/**
 * <h1>MyCharcter</h1> The MyCharcter class represents functionality of a character
 * in our maze,extends MazeCharacterGuiDisplayer
 * <p>
 *
 * @author Ariel Rosenfeld,Ofir Calif
 *
 * 
 */
public class MyCharcter extends MazeCharacterGuiDisplayer {
	BufferedInputStream character;;
	Image im;
	/**
	 * constructor for MyCharcter
	 */
	public MyCharcter() {
		try {
			character = new BufferedInputStream(new FileInputStream("lib/minionSuper.png"));
			im = new Image(null, character);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * This method is for drawing the character in the maze
	 * @param paintEvent is the given paint event.
	 * @param x is the value for the ratio	
	 * @param y is the value for the ratio
	 * @param characterWidth is the width of the character.
	 * @param characterHeight is the height of the character.
	 */

	@Override
	public void drawCharcter(PaintEvent paintEvent, int x, int y, int characterWidth, int characterHeight) {
		paintEvent.gc.drawImage(im, 0, 0, im.getImageData().width, im.getImageData().height, x, y, characterWidth,
				characterHeight);
	}

}