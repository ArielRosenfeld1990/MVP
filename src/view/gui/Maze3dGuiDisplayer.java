package view.gui;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;

import algorithms.mazeGenerators.Position;
import algorithms.search.Maze3dState;
import algorithms.search.Solution;
import algorithms.search.State;
/**
 * <h1>Maze3dGuiDisplayer</h1> The Maze3dGuiDisplayer class extends our MazeGuiDisplayer
 * <p>
 *
 * @author Ariel Rosenfeld,Ofir Calif
 *
 * 
 */

public class Maze3dGuiDisplayer extends MazeGuiDisplayer {
	Timer timer;
	TimerTask task;
	CrossDisplayer crossX;
	CrossDisplayer crossY;
	/**
	 * constructor for Maze3dGuiDisplayer
	 */ 
	public Maze3dGuiDisplayer(Composite parent, int style) {
		super(parent, style);

		setCharacter(new MyCharcter());

		setLayout(new GridLayout(2, false));
		crossX = new CrossXDisplayer(this, style, character);
		crossY = new CrossYDisplayer(this, style, character);
		crossX.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		crossY.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		try{
			BufferedInputStream InputStream = new BufferedInputStream(new FileInputStream("resources/exit.png"));
			Image Image = new Image(null, InputStream);
			crossX.setGoalImage(Image);
			crossY.setGoalImage(Image);
			InputStream.close();
			
			InputStream = new BufferedInputStream(new FileInputStream("resources/myStar.png"));
			Image = new Image(null, InputStream);
			crossX.setHintImage(Image);
			crossY.setHintImage(Image);
			InputStream.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		addPaintListener(new PaintListener() {
			@Override
			public void paintControl(PaintEvent e) {
				crossX.redraw();
				crossY.redraw();
			}
		});

		addDisposeListener(new DisposeListener() {

			@Override
			public void widgetDisposed(DisposeEvent arg0) {
				if(timer!=null){
					task.cancel();
					timer.cancel();
				};

			}
		});
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see view.MazeDisplayer#moveUp()
	 */
	@Override
	public void moveUp() {
		Position current = character.getPosition();
		int x = current.getX() + 1;
		int y = current.getY();
		int z = current.getZ();
		moveCharacter(new Position(x, y, z));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.MazeDisplayer#moveDown()
	 */
	@Override
	public void moveDown() {
		Position current = character.getPosition();
		int x = current.getX() - 1;
		int y = current.getY();
		int z = current.getZ();
		moveCharacter(new Position(x, y, z));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.MazeDisplayer#moveLeft()
	 */
	@Override
	public void moveLeft() {
		Position current = character.getPosition();
		int x = current.getX();
		int y = current.getY();
		int z = current.getZ() - 1;
		moveCharacter(new Position(x, y, z));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.MazeDisplayer#moveRight()
	 */
	@Override
	public void moveRight() {
		Position current = character.getPosition();
		int x = current.getX();
		int y = current.getY();
		int z = current.getZ() + 1;
		moveCharacter(new Position(x, y, z));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.MazeDisplayer#moveForward()
	 */
	@Override
	public void moveForward() {
		Position current = character.getPosition();
		int x = current.getX();
		int y = current.getY() - 1;
		int z = current.getZ();
		moveCharacter(new Position(x, y, z));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.MazeDisplayer#moveBackward()
	 */
	@Override
	public void moveBackward() {
		Position current = character.getPosition();
		int x = current.getX();
		int y = current.getY() + 1;
		int z = current.getZ();
		moveCharacter(new Position(x, y, z));

	}
	/**
	 * This method is for displaying the solution for the maze
	 *@param solution is the solution for the maze
	 */
	@Override
	public void displaySolution(Solution solution) {	
		if(timer!=null){
			task.cancel();
			timer.cancel();
		}

		LinkedList<State> path= solution.getPath();
		if(path.isEmpty())
			return;
		path.poll();
		timer = new Timer();
		task = new TimerTask() {
			@Override
			public void run() {
				if(path.isEmpty()){
					task.cancel();
					timer.cancel();
					return;
				}

				Position to=((Maze3dState)path.poll()).getPosition();
				if(!isDisposed())
					getDisplay().syncExec(new Runnable() {

						@Override
						public void run() {
							if(to.getX()>character.getPosition().getX()){
								moveUp();
							}
							if(to.getX()<character.getPosition().getX()){
								moveDown();
							}
							if(to.getY()>character.getPosition().getY()){
								moveBackward();
							}
							if(to.getY()<character.getPosition().getY()){
								moveForward();
							}
							if(to.getZ()>character.getPosition().getZ()){
								moveRight();
							}
							if(to.getZ()<character.getPosition().getZ()){
								moveLeft();
							}

						}
					});

			}
		};
		timer.scheduleAtFixedRate(task, 0, 500);
	}
	/**
	 * This method is for displaying the hint for a specific Maze3dState
	 *@param hint is the hint for the Maze3dState
	 */
	@Override
	public void displayHint(Maze3dState hint) {
		crossX.displayHint(hint.getPosition());
		crossY.displayHint(hint.getPosition());
		redraw();
	}
	/**
	 * this method sets the maze and initializes our maze and the character
	 * starting position
	 */
	@Override
	public void initilaize(algorithms.mazeGenerators.Maze3d maze3d) {
		super.initilaize(maze3d);
		if(timer!=null){
			task.cancel();
			timer.cancel();
		}
		crossX.setGoal(maze3d.getGoalPosition());
		crossY.setGoal(maze3d.getGoalPosition());
		updateCross();

	}
	/**
	 * This method is for moving a character from a certain position to another position
	 * @param position is the given position.
	 */
	private void moveCharacter(Position position) {
		if (maze3d.InMaze(position) && maze3d.getCell(position) == 0) {
			character.setPosition(position);
			updateCross();
			redraw();
			checkIfSolved();
		}
	}
	/**
	 * This method is for updating the cross section
	 */
	private void updateCross() {
		crossX.setCrossSection(maze3d.getCrossSectionByX(character.getPosition().getX()));
		crossY.setCrossSection(maze3d.getCrossSectionByY(character.getPosition().getY()));
		redraw();
	} 
	/**
	 * This method is for checking if we reached our goal position in the maze
	 */
	private void checkIfSolved() {
		if (character.getPosition().equals(maze3d.getGoalPosition())) {
			MessageBox mBox = new MessageBox(getShell(), SWT.OK);
			mBox.setMessage("You solve the Maze");
			mBox.open();
		}
	}

}