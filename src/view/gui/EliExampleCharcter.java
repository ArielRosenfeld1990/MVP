package view.gui;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;

public class EliExampleCharcter extends MazeCharacterGuiDisplayer {

	@Override
	public void drawCharcter(PaintEvent paintEvent, int x, int y, int characterWidth, int characterHeight) {
		// paintEvent.gc.setBackground(new Color(null,200,0,0));
		// paintEvent.gc.fillOval(x, y, characterWidth, characterHeight);
		// paintEvent.gc.setBackground(new Color(null,255,0,0));
		// paintEvent.gc.fillOval(x+2, y+2, (int)Math.round(characterWidth/1.5),
		// (int)Math.round(characterHeight/1.5));
		// paintEvent.gc.setBackground(new Color(null,0,0,0));
		BufferedInputStream character;
		try {
			// character = new BufferedInputStream(new
			// FileInputStream("lib/minonCharacter.png"));
			character = new BufferedInputStream(new FileInputStream("lib/seaEmperor.png"));
			// character = new BufferedInputStream(new
			// FileInputStream("lib/hackchar.png"));
			Image im = new Image(null, character);
			paintEvent.gc.drawImage(im, 0, 0, im.getImageData().width, im.getImageData().height, x, y, characterWidth,
					characterHeight);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
