package view.gui;

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
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import algorithms.mazeGenerators.Maze3d;
import algorithms.search.Maze3dState;
import algorithms.search.Solution;
import presenter.Properties;
import view.View;

/**
 * <h1>MazeWindow</h1> The MazeWindow class represents our main GUI
 * window,extends BasicWindow,implements our View interface
 * <p>
 *
 * @author Ariel Rosenfeld,Ofir Calif
 *
 * 
 */

public class MazeWindow extends BasicWindow implements View {

	String[] inputStrings;
	String mazeName;
	MazeGuiDisplayer maze;
	Button generateButton;
	Button saveButton;
	Button loadButton;
	Button hintButton;
	Button solveButton;
	Button openPropertiesButton;
	Button exitButton;
	/**
	 * constructor for MazeWindow
	 */

	public MazeWindow(String title, int width, int height) {
		super(title, width, height);
	}
	/**
	 * this method sets the window and initializes our widgets
	 */
	@Override
	void initWidgets() {
		shell.setLayout(new GridLayout(2, false));

		generateButton = new Button(shell, SWT.PUSH);
		generateButton.setText("Generate");
		generateButton.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 1, 1));
		generateButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				generateButton.setEnabled(false);
				GenerateWindow();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});
		generateButton.setEnabled(true);

		maze = new Maze3dGuiDisplayer(shell, SWT.BORDER);
		maze.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 8));
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
		maze.setEnabled(false);
		maze.forceFocus();

		shell.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent arg0) {
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				maze.forceFocus();
			}
		});

		saveButton = new Button(shell, SWT.PUSH);
		saveButton.setText("Save");
		saveButton.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 1, 1));
		saveButton.setEnabled(false);
		saveButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				FileDialog saveDialog = new FileDialog(shell, SWT.SAVE);
				saveDialog.setText("Saving the maze");
				saveDialog.setFilterNames(new String[] { "Maze Files", "All Files (*.*)" });
				saveDialog.setFilterExtensions(new String[] { "*.*" }); 
				saveDialog.setFilterPath("lib\\");
				saveDialog.setFileName(mazeName);
				String selected = saveDialog.open();
				if (selected!=null){
					if (selected.contains(".")==false){
						inputStrings = new String[] { "saveMaze",mazeName,selected };

						setChanged();
						notifyObservers();
					}
					else {
						MessageBox mSaveBox = new MessageBox(shell, SWT.OK);
						mSaveBox.setMessage("invalid maze file name!");
						mSaveBox.open();
					}
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		loadButton = new Button(shell, SWT.PUSH);
		loadButton.setText("Load");
		loadButton.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 1, 1));
		loadButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				LoadWindow();
/*				FileDialog loadDialog = new FileDialog(shell, SWT.OPEN);
				loadDialog.setText("Open");
				loadDialog.setFilterPath("lib\\");
				String[] filterExt = { "*.*" };
				loadDialog.setFilterExtensions(filterExt);
				String selected = loadDialog.open();
				if (loadDialog.getFileName().equals("")==false){
					mazeName =  loadDialog.getFileName();
					if ((mazeName.contains(".")==false)){
						inputStrings = new String[] { "loadMaze",selected,mazeName };
						setChanged();
						notifyObservers();
					}
					else{
						MessageBox mLoadBox = new MessageBox(shell, SWT.OK);
						mLoadBox.setMessage("invalid maze file!");
						mLoadBox.open();
					}
				}*/

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		hintButton = new Button(shell, SWT.PUSH);
		hintButton.setText("Hint");
		hintButton.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 1, 1));
		hintButton.setEnabled(false);
		hintButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				requestHint();

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		solveButton = new Button(shell, SWT.PUSH);
		solveButton.setText("Solve");
		solveButton.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 1, 1));
		solveButton.setEnabled(false);
		solveButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
				requestSolve();

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		openPropertiesButton = new Button(shell, SWT.PUSH);
		openPropertiesButton.setText("Open Properties");
		openPropertiesButton.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 1, 1));
		openPropertiesButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				FileDialog OpenPropertiesDialog = new FileDialog(shell, SWT.OPEN);
				OpenPropertiesDialog.setText("Open");
				OpenPropertiesDialog.setFilterPath("");
				String[] filterExt = { "*.xml*" };
				OpenPropertiesDialog.setFilterExtensions(filterExt);
				String selected = OpenPropertiesDialog.open();
				String PropertiesFile =  OpenPropertiesDialog.getFileName();
				if (PropertiesFile.contains("Properties")==true){
					inputStrings = new String[] { "loadXML",selected };
					setChanged();
					notifyObservers();
				}
				else if (PropertiesFile.equals("")==false){
					MessageBox mLoadBox = new MessageBox(shell, SWT.OK);
					mLoadBox.setMessage("invalid properties file!!!");
					mLoadBox.open();
				}

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		exitButton = new Button(shell, SWT.PUSH);
		exitButton.setText("Exit");
		exitButton.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 1, 1));
		exitButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				close();

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		shell.addDisposeListener(new DisposeListener() {

			@Override
			public void widgetDisposed(DisposeEvent arg0) {
				inputStrings = new String[] { "exit"};
				setChanged();
				notifyObservers();
			}
		});
	}
	/**
	 * this method runs our shell,display and the widgets
	 */
	@Override
	public void start() {
		run();
	}
	/**
	 * this method displaying given results
	 */
	@Override
	public void display(Object obj) {
		if (obj != null) {
			switch (obj.getClass().getSimpleName()) {
			case "String":
				String message = (String) obj;
				if(message.equals("The maze is ready")) {
					inputStrings = new String[] { "display", mazeName };
					setChanged();
					notifyObservers();
					break;
				}
				if(message.endsWith("load successfuly")){
					mazeName=message.replace(" load successfuly" , "");
					inputStrings = new String[] { "display", mazeName };
					setChanged();
					notifyObservers();
					break;
				}
				MessageBox mBox = new MessageBox(shell, SWT.OK);
				mBox.setMessage(message);
				mBox.open();
				break;
			case "Maze3d":
				enableWidgets();
				Maze3d mazeObject = (Maze3d) obj;
				maze.initilaize(mazeObject);

				break;
			case "Solution":
				disableWidgets();
				maze.displaySolution((Solution)obj);
				break;
			case "Maze3dState":
				maze.displayHint((Maze3dState)obj);
				break;
			default:
				break;
			}
		}
		maze.forceFocus();
	}
	/**
	 * this method disposes our shell
	 */
	@Override
	public void close() {
		shell.dispose();
	}
	/**
	 * this method is used for getting a command from the user
	 */
	@Override
	public String[] getUserCommand() {
		return inputStrings;
	}
	/**
	 * this method is used for getting the requested hint and displaying it
	 */
	private void requestHint()	{
		inputStrings = new String[] { "displayHintFromPosition", mazeName, Properties.getSearcher(),maze.getCharacter().getPosition().toString()  };
		setChanged();
		notifyObservers();
	}
	/**
	 * this method is used for getting the requested solution and displaying it
	 */
	private void requestSolve()	{
		inputStrings = new String[] { "displaySolutionFromPosition", mazeName, Properties.getSearcher(),maze.getCharacter().getPosition().toString()  };
		setChanged();
		notifyObservers();
	}
	/**
	 * this method is used for enabling certain widgets
	 */
	private void enableWidgets() {
		generateButton.setEnabled(true);
		maze.setEnabled(true);
		hintButton.setEnabled(true);
		saveButton.setEnabled(true);
		hintButton.setEnabled(true);
		solveButton.setEnabled(true);
		maze.forceFocus();
	}
	/**
	 * this method is used for disabling certain widgets
	 */
	private void disableWidgets(){
		maze.setEnabled(false);
		hintButton.setEnabled(false);
		saveButton.setEnabled(false);
		hintButton.setEnabled(false);
		solveButton.setEnabled(false);
	}
	/**
	 * this method is used for generating a maze from a different shell
	 */
	private void GenerateWindow() {
		final Shell generateShell = new Shell(display, SWT.CLOSE | SWT.TITLE | SWT.MIN);
		generateShell.setText("Generating maze window");
		generateShell.setSize(300, 175);
		generateShell.setLayout(new GridLayout(2, false));
		Label NameLabel = new Label(generateShell, 1);
		NameLabel.setText("Please enter a name for the maze:");
		NameLabel.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 1, 1));
		Text mazeNameText = new Text(generateShell, SWT.BORDER);
		mazeNameText.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 1, 1));
		mazeNameText.setTextLimit(20);
		NameLabel = new Label(generateShell, 1);
		NameLabel.setText("Please enter the x dimension:");
		NameLabel.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 1, 1));
		Text xDimensionText = new Text(generateShell, SWT.BORDER);
		xDimensionText.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 1, 1));
		xDimensionText.setTextLimit(3);
		NameLabel = new Label(generateShell, 1);
		NameLabel.setText("Please enter the y dimension:");
		NameLabel.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 1, 1));
		Text yDimensionText = new Text(generateShell, SWT.BORDER);
		yDimensionText.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 1, 1));
		yDimensionText.setTextLimit(3);
		NameLabel = new Label(generateShell, 1);
		NameLabel.setText("Please enter the z dimension:");
		NameLabel.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 1, 1));
		Text zDimensionText = new Text(generateShell, SWT.BORDER);
		zDimensionText.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 1, 1));
		zDimensionText.setTextLimit(3);
		Button newgenerateButton = new Button(generateShell, SWT.PUSH);
		newgenerateButton.setText("Generate");
		newgenerateButton.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 0, 2));
		newgenerateButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				mazeName = mazeNameText.getText();
				inputStrings = new String[] { "generate3dMaze", mazeName, Properties.getMazeGenerator(), xDimensionText.getText(),
						yDimensionText.getText(), zDimensionText.getText() };
				setChanged();
				notifyObservers();
				generateShell.close();
				

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
	/**
	 * this method is used for loading a maze from a file in a different shell
	 */
	private void LoadWindow(){
		final Shell loadShell = new Shell(display, SWT.CLOSE | SWT.TITLE | SWT.MIN);
		loadShell.setText("Loading maze window");
		loadShell.setSize(250, 150);
		loadShell.setLayout(new GridLayout(2, false));
		shell.setEnabled(false);
		Label NameLabel = new Label(loadShell, 1);
		NameLabel.setText("Please enter a name for the maze:");
		NameLabel.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 2, 1));
		Text mazeNameText = new Text(loadShell, SWT.BORDER);
		mazeNameText.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 2,1));
		mazeNameText.setTextLimit(30);
		Button newloadButton = new Button(loadShell, SWT.PUSH);
		newloadButton.setText("Load");
		newloadButton.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false,1,1));
		newloadButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				String newMazeName =  mazeNameText.getText();
				if (newMazeName.equals("")==false){
				FileDialog loadDialog = new FileDialog(loadShell, SWT.OPEN);
				loadDialog.setText("Open");
				loadDialog.setFilterPath("lib\\");
				String[] filterExt = { "*.*" };
				loadDialog.setFilterExtensions(filterExt);
				String selected = loadDialog.open();
				
					String LoadedFileName=loadDialog.getFileName();
					if (LoadedFileName.equals("")==false){
					if ((LoadedFileName.contains(".")==false)){
						inputStrings = new String[] { "loadMaze",selected,newMazeName };
						setChanged();
						notifyObservers();
						loadShell.close();
					}
					else{
						MessageBox mLoadBox = new MessageBox(shell, SWT.OK);
						mLoadBox.setMessage("invalid maze file!");
						mLoadBox.open();
						loadShell.forceFocus();
					}
					}
					 
					
				}
				else {
					MessageBox mLoadBox = new MessageBox(shell, SWT.OK);
					mLoadBox.setMessage("you must enter a maze name!");
					mLoadBox.open();
					loadShell.forceFocus();
				}
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		loadShell.setVisible(true);
		loadShell.addDisposeListener(new DisposeListener() {
			
			@Override
			public void widgetDisposed(DisposeEvent arg0) {
				shell.setEnabled(true);
				
			}
		});
	}
}