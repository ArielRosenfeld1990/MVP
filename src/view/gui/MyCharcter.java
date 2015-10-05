package view.gui;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Image;

public class MyCharcter extends MazeCharacterGuiDisplayer {

	@Override
	public void drawCharcter(PaintEvent paintEvent, int x, int y, int characterWidth, int characterHeight) {
		BufferedInputStream character;
		try {
			character = new BufferedInputStream(new FileInputStream("lib/minonCharacter.png"));
			Image im = new Image(null, character);
			paintEvent.gc.drawImage(im, 0, 0, im.getImageData().width, im.getImageData().height, x, y, characterWidth,
					characterHeight);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
 
	}

}