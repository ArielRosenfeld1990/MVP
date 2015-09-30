package view.gui;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Color;

public class EliExampleCharcter extends MazeCharacterGuiDisplayer {

	@Override
	public void drawCharcter(PaintEvent paintEvent, int x, int y, int characterWidth, int characterHeight) {
		paintEvent.gc.setBackground(new Color(null,200,0,0));
		paintEvent.gc.fillOval(x, y, characterWidth, characterHeight);
		paintEvent.gc.setBackground(new Color(null,255,0,0));
		paintEvent.gc.fillOval(x+2, y+2, (int)Math.round(characterWidth/1.5), (int)Math.round(characterHeight/1.5));
		paintEvent.gc.setBackground(new Color(null,0,0,0));

	}

}
