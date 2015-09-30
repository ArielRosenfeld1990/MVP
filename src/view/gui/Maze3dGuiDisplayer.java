package view.gui;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerators.Position;


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
		Position current = character.getPosition();
		int x = current.getX()+1;
		int y = current.getY();
		int z = current.getZ();
		moveCharacter(new Position(x, y, z));
	}

	/* (non-Javadoc)
	 * @see view.MazeDisplayer#moveDown()
	 */
	@Override
	public void moveDown() {
		Position current = character.getPosition();
		int x = current.getX()-1;
		int y = current.getY();
		int z = current.getZ();
		moveCharacter(new Position(x, y, z));
	}

	/* (non-Javadoc)
	 * @see view.MazeDisplayer#moveLeft()
	 */
	@Override
	public void moveLeft() {
		Position current = character.getPosition();
		int x = current.getX();
		int y = current.getY();
		int z = current.getZ()-1;
		moveCharacter(new Position(x, y, z));
	}

	/* (non-Javadoc)
	 * @see view.MazeDisplayer#moveRight()
	 */
	@Override
	public void moveRight() {
		Position current = character.getPosition();
		int x = current.getX();
		int y = current.getY();
		int z = current.getZ()+1;
		moveCharacter(new Position(x, y, z));
	}

	/* (non-Javadoc)
	 * @see view.MazeDisplayer#moveForward()
	 */
	@Override
	public void moveForward() {
		Position current = character.getPosition();
		int x = current.getX();
		int y = current.getY()-1;
		int z = current.getZ();
		moveCharacter(new Position(x, y, z));

	}

	/* (non-Javadoc)
	 * @see view.MazeDisplayer#moveBackward()
	 */
	@Override
	public void moveBackward() {
		Position current = character.getPosition();
		int x = current.getX();
		int y = current.getY()+1;
		int z = current.getZ();
		moveCharacter(new Position(x, y, z));

	}


	private void drawMaze(PaintEvent e)
	{
		if(currentCrossSection!=null && maze3d!=null)
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

					if(i==getCrossYDisplay(character.getPosition()) && j==getCrossXDisplay(character.getPosition())){
						character.drawCharcter(e,(int)Math.round(dpoints[0]), (int)Math.round(dpoints[1]-cheight/2), (int)Math.round((w0+w1)/2), (int)Math.round(h));
						}	
					
					if(i==getCrossYDisplay(maze3d.getGoalPosition()) && j==getCrossXDisplay(maze3d.getGoalPosition())){
						e.gc.setBackground(new Color(null,130,120,200));
						e.gc.fillOval((int)Math.round(dpoints[0]), (int)Math.round(dpoints[1]-cheight/2), (int)Math.round((w0+w1)/2), (int)Math.round(h));
						e.gc.setBackground(new Color(null,0,0,0));
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

	private int getCrossXDisplay(Position position) {
		switch (currentAxis) {
		case 'X':
		case 'x':
			if(position.getX()==character.getPosition().getX())
				return position.getZ();
			break;
		case 'Y':
		case 'y':
			if(position.getY()==character.getPosition().getY())
				return position.getZ();
			break;
		case 'Z':
		case 'z':
			if(position.getZ()==character.getPosition().getZ())
				return position.getY();
			break;
		default:
			return -1;
		}
		return -1;
	}

	private int getCrossYDisplay(Position position) {
		switch (currentAxis) {
		case 'X':
		case 'x':
			if(position.getX()==character.getPosition().getX())
				return position.getY();
			break;
		case 'Y':
		case 'y':
			if(position.getY()==character.getPosition().getY())
				return position.getX();
			break;
		case 'Z':
		case 'z':
			if(position.getZ()==character.getPosition().getZ())
				return position.getX();
			break;
		default:
			return -1;
		}
		return -1;
	}

	private void moveCharacter(Position position){
		if(maze3d.InMaze(position) && maze3d.getCell(position)==0){
			if(getCrossXDisplay(position)==-1)
				updateCross(position);
			character.setPosition(position);
			getDisplay().syncExec(new Runnable() {
				@Override
				public void run() {
					redraw();
				}
			});
		}
	}

	private void updateCross(Position position){
		switch (getCurrentAxis()) {
		case 'X':
			setCurrentCrossSection(maze3d.getCrossSectionByX(position.getX()));
			break;
		case 'Y':
			setCurrentCrossSection(maze3d.getCrossSectionByY(position.getY()));
			break;
		case 'Z':
			setCurrentCrossSection(maze3d.getCrossSectionByZ(position.getZ()));
			break;
		default:
			break;
		}
	}
}