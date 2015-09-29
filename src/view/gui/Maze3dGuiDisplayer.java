package view.gui;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;


public class Maze3dGuiDisplayer extends MazeGuiDisplayer {
	
	

	Maze3dGuiDisplayer(Composite parent, int style) {
		super(parent, style);
		setCharacter(new EliExampleCharcter());

		final Color white = new Color(null, 255,255,255);
		//final Color black = new Color(null, 150,150,150);
		setBackground(white);

		addPaintListener(new PaintListener() {
			@Override
			public void paintControl(PaintEvent e) {
				e.gc.setForeground(new Color(null, 0,0,0));
				e.gc.setBackground(new Color(null, 0,0,0));
				drawMaze(e);
			}
		});
	}


	/* (non-Javadoc)
	 * @see view.MazeDisplayer#moveUp()
	 */
	@Override
	public void moveUp() {
		//		int x=characterX;
		//		int y=characterY;
		//		y=y-1;
		//		moveCharacter(x, y);
	}
	
	/* (non-Javadoc)
	 * @see view.MazeDisplayer#moveDown()
	 */
	@Override
	public void moveDown() {
		//		int x=characterX;
		//		int y=characterY;
		//		y=y+1;
		//		moveCharacter(x, y);
	}
	
	/* (non-Javadoc)
	 * @see view.MazeDisplayer#moveLeft()
	 */
	@Override
	public void moveLeft() {
		//		int x=characterX;
		//		int y=characterY;
		//		x=x-1;
		//		moveCharacter(x, y);
	}

	/* (non-Javadoc)
	 * @see view.MazeDisplayer#moveRight()
	 */
	@Override
	public void moveRight() {
		//		int x=characterX;
		//		int y=characterY;
		//		x=x+1;
		//		moveCharacter(x, y);
	}

	/* (non-Javadoc)
	 * @see view.MazeDisplayer#moveForward()
	 */
	@Override
	public void moveForward() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see view.MazeDisplayer#moveBackward()
	 */
	@Override
	public void moveBackward() {
		// TODO Auto-generated method stub

	}
	
	
	private void drawMaze(PaintEvent e)
	{
		if(currentCrossSection!=null)
		{
			int width = getSize().x;
			int height = getSize().y;
			int mx = width/2; 
			double w =(double)width/currentCrossSection[0].length;
			double h = (double)height/currentCrossSection.length;

			for(int i=0;i<currentCrossSection.length;i++){
				double w0 = 0.7*w+0.3*w*i/currentCrossSection.length;
				double w1 = 0.7*w+0.3*w*(i+1)/currentCrossSection.length;
				double start = mx-w0*currentCrossSection[i].length/2;
				double start1 = mx-w1*currentCrossSection[i].length/2;
				for(int j=0;j<currentCrossSection[i].length;j++){
					double []dpoints={start+j*w0,i*h,start+j*w0+w0,i*h,start1+j*w1+w1,i*h+h,start1+j*w1,i*h+h};
					double cheight=h/2;

					if(currentCrossSection[i][j]!=0)
						paintCube(dpoints,cheight,e);

					if(i==getCharacterYDisplay() && j==getCharacterXDisplay()){
						character.drawCharcter(e,(int)Math.round(dpoints[0]), (int)Math.round(dpoints[1]-cheight/2), (int)Math.round((w0+w1)/2), (int)Math.round(h));

					}
				}
			}
		}
	}
	



	private void paintCube(double[] p,double h,PaintEvent e){
		int[] f=new int[p.length];
		for(int k=0;k<f.length;f[k]=(int)Math.round(p[k]),k++);

		e.gc.drawPolygon(f);

		int[] r=f.clone();
		for(int k=1;k<r.length;r[k]=f[k]-(int)(h),k+=2);


		int[] b={r[0],r[1],r[2],r[3],f[2],f[3],f[0],f[1]};
		e.gc.drawPolygon(b);
		int[] fr={r[6],r[7],r[4],r[5],f[4],f[5],f[6],f[7]};
		e.gc.drawPolygon(fr);

		e.gc.fillPolygon(r);
	}
	
	private int getCharacterXDisplay() {
		switch (currentAxis) {
		case 'X':
		case 'x':
			return character.getPosition().getZ();
		case 'Y':
		case 'y':
			return character.getPosition().getZ();
		case 'Z':
		case 'z':
			return character.getPosition().getY();
		default:
			return -1;
		}
	}
	
	private int getCharacterYDisplay() {
		switch (currentAxis) {
		case 'X':
		case 'x':
			return character.getPosition().getY();
		case 'Y':
		case 'y':
			return character.getPosition().getX();
		case 'Z':
		case 'z':
			return character.getPosition().getX();
		default:
			return -1;
		}
	}
	
//	private void moveCharacter(int x,int y){
//
//		if(x>=0 && x<mazeData[0].length && y>=0 && y<mazeData.length && mazeData[y][x]==0){
//			//characterX=x;
//			//characterY=y;
//			getDisplay().syncExec(new Runnable() {
//
//				@Override
//				public void run() {
//					redraw();
//				}
//			});
//		}
//	}

}
