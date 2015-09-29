package view;


import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Group;

import algorithms.mazeGenerators.Maze3d;


public class MazeWindow extends BasicWindow implements View{

	Timer timer;
	TimerTask task;
	String[] inputStrings;
	Maze3dGuiDisplayer maze; //להוריד את ה3D כדי שיהיה כללי יותר וא ז סטרטגי פטרן
	String mazeName;
	int counter=0;
	public MazeWindow(String title, int width, int height) {
		super(title, width, height);
	}

//	private void randomWalk(MazeGuiDisplayer maze){
//		Random r=new Random();
//		boolean b1,b2;
//		b1=r.nextBoolean();
//		b2=r.nextBoolean();
//		if(b1&&b2)
//			maze.moveUp();
//		if(b1&&!b2)
//			maze.moveDown();
//		if(!b1&&b2)
//			maze.moveRight();
//		if(!b1&&!b2)
//			maze.moveLeft();
//
//		maze.redraw();
//	}

	@Override
	void initWidgets() {
		shell.setLayout(new GridLayout(4,false));

		Button generateButton=new Button(shell, SWT.PUSH);
		generateButton.setText("Generate");
		generateButton.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 3, 1));
		generateButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				mazeName="ofir"+counter;
				inputStrings = new String[]{"generate3dMaze",mazeName,"my","10","10","10"}; 
				setChanged();
				notifyObservers();	
				inputStrings = new String[]{"display",mazeName};
				setChanged();
				notifyObservers();
				counter++;

			}
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});	

		maze=new Maze3dGuiDisplayer(shell, SWT.BORDER);
		maze.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true,1,3));

		Group axisGroup = new Group(shell, SWT.SHADOW_OUT);
		axisGroup.setText("Axis");
		axisGroup.setLayout(new GridLayout(3,true));
		axisGroup.setLayoutData(new GridData(SWT.FILL,SWT.NONE,false,false,3,1));
		Button axisX = new Button(axisGroup, SWT.RADIO);
		axisX.setText("X");
		axisX.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				//inputStrings = new String[]{"displayCrossSection","x",maze.characterX};
				setChanged();
				notifyObservers();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
			}
		});
		Button axisY = new Button(axisGroup, SWT.RADIO);
		axisY.setText("Y");
		axisY.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				inputStrings = new String[]{"displayCrossSection","y",""+maze.charcter.getY(),mazeName};
				setChanged();
				notifyObservers();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
			}
		});
		Button axisZ = new Button(axisGroup, SWT.RADIO);
		axisZ.setText("Z");
		axisZ.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				inputStrings = new String[]{"displayCrossSection","z",""+maze.charcter.getZ(),mazeName};
				setChanged();
				notifyObservers();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
			}
		});
		
		Button stopButton=new Button(shell, SWT.PUSH);
		stopButton.setText("Stop");
		stopButton.setLayoutData(new GridData(SWT.None, SWT.None, false, false, 1, 1));
		stopButton.setEnabled(false);

		stopButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				task.cancel();
				timer.cancel();
				//startButton.setEnabled(true);
				stopButton.setEnabled(false);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});


	}

	@Override
	public void start() {
		run();
	}

	@Override
	public void display(Object obj) {
		if(obj!=null)
		{
			switch (obj.getClass().getSimpleName()) {
			case "String":
				System.out.println((String)obj);
				if(((String)obj).equals("maze ofir is ready"))
				{
					//					inputStrings = new String[]{"displayCrossSection","x","0","ofir"+counter};
					//					setChanged();
					//					notifyObservers();

				}
				//				ui.display(obj, new StringCliDisplayer());
				break;
			case "Maze3d":
				Maze3d mazeObject =(Maze3d)obj;
				maze.charcter = mazeObject.getStartPosition();//האם זה הגיוני שהממשק משתמש יכיר את המחלקה maze3d
				maze.displayAxis = 'X';
				maze.setMazeData(mazeObject.getCrossSectionByX(mazeObject.getStartPosition().getX()));
				maze.setCharacterPosition(mazeObject.getStartPosition());

				display.syncExec(new Runnable() {
					public void run() {
						maze.redraw();	
					}
				});
				break;
			case "int[][]":
				
				display.syncExec(new Runnable() {

					@Override
					public void run() {
						maze.setMazeData((int[][])obj);
						maze.redraw();

					}
				});

				break;
				//			case "Solution":
				//				ui.display(obj, new SolutionCliDisplayer());
				//				break;
			default:
				break;
			}
		}		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	@Override
	public String[] getUserCommand() {
		return inputStrings;
	}

}
