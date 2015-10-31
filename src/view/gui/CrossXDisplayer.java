package view.gui;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerators.Position;

/**
 * <h1>CrossXDisplayer</h1> The CrossXDisplayer class represents our way to represent the
 * cross section in the X dimension,extends CrossDisplayer
 * <p>
 *
 * @author Ariel Rosenfeld,Ofir Calif
 *
 * 
 */
public class CrossXDisplayer extends CrossDisplayer {
	/**
	 * <h1>CrossXDisplayer constructor</h1>
	 * constructor for CrossXDisplayer
	 */
	public CrossXDisplayer(Composite parent, int style, MazeCharacterGuiDisplayer character) {
		super(parent, style, character);
		final Color white = new Color(null, 255, 255, 255);
		setBackground(white);
		addPaintListener(new PaintListener() {
			@Override
			public void paintControl(PaintEvent e) {
				e.gc.setForeground(new Color(null, 0, 0, 0));
				e.gc.setBackground(new Color(null, 0, 0, 0));
				drawMaze(e);
			}
		});
	}
	
	/**
	 * <h1>drawMaze</h1>
	 * This method is for drawing the maze when Paint Event occurred
	 * @param e is the Paint Event.
	 */
	private void drawMaze(PaintEvent e) {
		if (crossSection != null ) {
			int startHeight = resize/2;
			int width = getSize().x-resize;
			int height = getSize().y-resize;
			int mx = (width / 2)+resize/2;
			double w = (double) width / crossSection[0].length;
			double h = (double) height / crossSection.length;
			
			
			for (int i = 0; i < crossSection.length; i++) {
				double w0 = 0.7 * w + 0.3 * w * i / crossSection.length;
				double w1 = 0.7 * w + 0.3 * w * (i + 1) / crossSection.length;
				double start = mx - w0 * crossSection[i].length / 2;
				double start1 = mx - w1 * crossSection[i].length / 2;
				for (int j = 0; j < crossSection[i].length; j++) {
					double[] dpoints = { start + j * w0, (i * h)+startHeight, start + j * w0 + w0, (i * h)+startHeight, start1 + j * w1 + w1,
							(i * h + h)+startHeight, start1 + j * w1, (i * h + h)+startHeight };
					double cheight = h / 2;

					if (crossSection[i][j] != 0)
						paintCube(dpoints, cheight, e);

					if (i == getCrossYDisplay(goal) && j == getCrossXDisplay(goal)) 
						e.gc.drawImage(goalImage,0, 0, goalImage.getImageData().width, goalImage.getImageData().height,
								(int) Math.round(dpoints[0]), (int) Math.round(dpoints[1] - cheight / 2),
								(int) Math.round((w0 + w1) / 2), (int) Math.round(h));

					if (i == getCrossYDisplay(character.getPosition()) && j == getCrossXDisplay(character.getPosition()))
						character.drawCharcter(e, (int) Math.round(dpoints[0]),
								(int) Math.round(dpoints[1] - cheight / 2), (int) Math.round((w0 + w1) / 2),
								(int) Math.round(h));
 
					if(drawHint&&currentHint!=null&&i==getCrossYDisplay(currentHint)&&j==getCrossXDisplay(currentHint)) 
						e.gc.drawImage(hintImage,0, 0, hintImage.getImageData().width, hintImage.getImageData().height,
								(int) Math.round(dpoints[0]), (int) Math.round(dpoints[1] - cheight / 2),
								(int) Math.round((w0 + w1) / 2), (int) Math.round(h));

				}
			}
			if (drawHint) drawHint=false;
		}
	}
	
	/**
	 * <h1>paintCube</h1>
	 * This method is for drawing the maze walls when Paint Event Occurred
	 * @param p is the set of points that we need to draw as a wall
	 * @param h repesents the height of the wall
	 * @param e is the Paint Event.
	 */
	private void paintCube(double[] p, double h, PaintEvent e) {
		int[] f = new int[p.length];
		for (int k = 0; k < f.length; f[k] = (int) Math.round(p[k]), k++);
		e.gc.drawPolygon(f);
		int[] r = f.clone();
		for (int k = 1; k < r.length; r[k] = f[k] - (int) (h), k += 2);
		int[] b = { r[0], r[1], r[2], r[3], f[2], f[3], f[0], f[1] };
		e.gc.drawPolygon(b);
		int[] fr = { r[6], r[7], r[4], r[5], f[4], f[5], f[6], f[7] };
		e.gc.drawPolygon(fr);

		e.gc.fillPolygon(r);

	}
	
	/**
	 * <h1>getCrossXDisplay</h1>
	 * This method is for getting the display of x position in the cross section 
	 * @param position is our position.
	 * @return int represents the index.
	 */
	private int getCrossXDisplay(Position position) {

		if (position.getX() == character.getPosition().getX())
			return position.getZ();

		return -1;
	}
	
	/**
	 * <h1>getCrossYDisplay</h1>
	 * This method is for getting the display of y position in the cross section
	 * @param position is our position.
	 * @return int represents the index.
	 */
	private int getCrossYDisplay(Position position) {

		if (position.getX() == character.getPosition().getX())
			return position.getY();

		return -1;
	}
}
