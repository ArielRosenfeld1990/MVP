package view.gui;


import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.plaf.synth.Region;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Widget;

import algorithms.mazeGenerators.Maze3d;
import view.View;


public class MazeWindow extends BasicWindow implements View{

	Timer timer; //להבין את השימוש
	TimerTask task;

	String[] inputStrings; 

	String mazeName;
	int counter=0;//for deletion

	MazeGuiDisplayer maze;
	Button generateButton;
	Group axisGroup;
	Button axisX;
	Button axisY;
	Button axisZ;
	public MazeWindow(String title, int width, int height) {
		super(title, width, height);
	}

	/*	private void randomWalk(MazeGuiDisplayer maze){
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
	}*/

	@Override
	void initWidgets() {
		shell.setLayout(new GridLayout(4,false));

		generateButton=new Button(shell, SWT.PUSH);
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

		SelectionListener axisSelectionListener = new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Button radioButton =(Button)arg0.widget; 
				int indexForCross;
				if(radioButton.getSelection()==true){
					switch (radioButton.getText()) {
					case "X":
						indexForCross=maze.getCharacter().getPosition().getX();
						break;
					case "Y":
						indexForCross=maze.getCharacter().getPosition().getY();
						break;
					case "Z":
						indexForCross=maze.getCharacter().getPosition().getZ();
						break;
					default:
						indexForCross=-1;
						break;
					}

					inputStrings = new String[]{"displayCrossSection",radioButton.getText(),""+indexForCross,mazeName};
					setChanged();
					notifyObservers();
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		};
		axisGroup = new Group(shell, SWT.SHADOW_OUT | SWT.SINGLE);
		axisGroup.setText("Axis");
		axisGroup.setLayout(new GridLayout(3,true));
		axisGroup.setLayoutData(new GridData(SWT.FILL,SWT.NONE,false,false,3,1));


		axisX = new Button(axisGroup, SWT.RADIO);
		axisX.setText("X");
		axisX.setSelection(true);
		axisX.addSelectionListener(axisSelectionListener);

		axisY = new Button(axisGroup, SWT.RADIO);
		axisY.setText("Y");
		axisY.addSelectionListener(axisSelectionListener);

		axisZ = new Button(axisGroup, SWT.RADIO);
		axisZ.setText("Z");
		axisZ.addSelectionListener(axisSelectionListener);
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
				MessageBox mBox =new MessageBox(shell,SWT.OK);
				mBox.setMessage((String)obj);
				mBox.open();
				break;
			case "Maze3d":
				Maze3d mazeObject =(Maze3d)obj;
				maze.setMazeData(mazeObject);

				axisX.setSelection(true);
				axisY.setSelection(false);
				axisZ.setSelection(false);
				axisX.notifyListeners(SWT.Selection, null);
				break;
			case "int[][]":
				maze.setCurrentCrossSection((int[][])obj);
				maze.setCurrentAxis(getCurrentAxis());
				display.syncExec(new Runnable() {
					@Override
					public void run() {
						maze.redraw();
					}
				});
				System.out.println(maze.getCharacter().getPosition());
				System.out.println(maze.maze3d.getMaze()[maze.getCharacter().getPosition().getX()][maze.getCharacter().getPosition().getY()][maze.getCharacter().getPosition().getZ()]);
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
	
	private char getCurrentAxis()
	{
		if(axisX.getSelection())
			return 'x';
		if(axisY.getSelection())
			return 'y';
		if(axisZ.getSelection())
			return 'z';
		return 'x';
	}
}
