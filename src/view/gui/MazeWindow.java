package view.gui;


import java.awt.TextField;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.plaf.synth.Region;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
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
				generateButton.setEnabled(false);
				GenerateWindow(shell);
/*				mazeName="ofir"+counter;
				inputStrings = new String[]{"generate3dMaze",mazeName,"my","7","7","7"}; 
				setChanged();
				notifyObservers();	
				inputStrings = new String[]{"display",mazeName};
				setChanged();
				notifyObservers();*/

				counter++;

			}
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});	
		generateButton.setEnabled(true);
		maze=new Maze3dGuiDisplayer(shell, SWT.BORDER);
		maze.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true,1,3));
		maze.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent arg0) {
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.keyCode) {
				case 16777217:
					maze.moveForward();
					break;
				case 16777218:
					maze.moveBackward();
					break;
				case 16777219:
					maze.moveLeft();
					break;
				case 16777220:
					maze.moveRight();
					break;
				case 16777221:
					maze.moveUp();
					break;
				case 16777222:
					maze.moveDown();
					break;
				default:
					break;
				}
			}
		});
		maze.setFocus();
		
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
		
		shell.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent arg0) {	
			}
			@Override
			public void focusGained(FocusEvent arg0) {
				maze.setFocus();
			}
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
				break;
				//			case "Solution":
				//				ui.display(obj, new SolutionCliDisplayer());
				//				break;
			default:
				break;
			}
		}	
		maze.setFocus();
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
			return 'X';
		if(axisY.getSelection())
			return 'Y';
		if(axisZ.getSelection())
			return 'Z';
		return 'x';
	}
	private void GenerateWindow(Shell shell){
		final Shell generateShell = new Shell(display,SWT.CLOSE | SWT.TITLE | SWT.MIN);
		generateShell.setSize(300, 175);
		generateShell.setLayout(new GridLayout(2,false));
        Label NameLabel=new Label(generateShell,1);
        NameLabel.setText("Please enter a name for the maze:");
        NameLabel.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 1, 1));
		Text mazeNameText=new Text(generateShell, SWT.BORDER);
		mazeNameText.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 1, 1));
		mazeNameText.setTextLimit(20);
		NameLabel=new Label(generateShell,1);
		NameLabel.setText("Please enter the x dimension:");
		NameLabel.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 1, 1));
		Text xDimensionText=new Text(generateShell,SWT.BORDER);
		xDimensionText.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 1, 1));
		xDimensionText.setTextLimit(3);
		NameLabel=new Label(generateShell,1);
		NameLabel.setText("Please enter the y dimension:");
		NameLabel.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 1, 1));
		Text yDimensionText=new Text(generateShell, SWT.BORDER);
		yDimensionText.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 1, 1));
		yDimensionText.setTextLimit(3);
		NameLabel=new Label(generateShell,1);
		NameLabel.setText("Please enter the z dimension:");
		NameLabel.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 1, 1));
		Text zDimensionText=new Text(generateShell, SWT.BORDER);
		zDimensionText.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 1, 1));
		zDimensionText.setTextLimit(3);
		Button newgenerateButton=new Button(generateShell, SWT.PUSH);
		newgenerateButton.setText("Generate");
		newgenerateButton.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 0, 2));
		newgenerateButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
			// 	System.out.println(mazeNameText.getText());
			 	mazeName=mazeNameText.getText();
				inputStrings = new String[]{"generate3dMaze",mazeName,"my",xDimensionText.getText(),yDimensionText.getText(),zDimensionText.getText()}; 
				setChanged();
				notifyObservers();	
				inputStrings = new String[]{"display",mazeName};
				setChanged();
				notifyObservers();
				generateShell.close();
				generateButton.setEnabled(true);
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		generateShell.setVisible(true);
		generateShell.addDisposeListener(new DisposeListener() {
			
			@Override
			public void widgetDisposed(DisposeEvent arg0) {
				generateButton.setEnabled(true);
				
			}
		});
	}
}
