package presenter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import model.Model;
import view.View;

/**
 * <h1>Presenter</h1> The Presenter class implements the Observer interface so
 * that we can Observe the View and Moder layers
 * <p>
 *
 * @author Ariel Rosenfeld,Ofir Calif
 *
 * 
 */
public class Presenter implements Observer {
	private View view;
	private Model model;
	private HashMap<String, Command> commands;

	/**
	 * <h1>Presenter constructor</h1>
	 * Presenter constructor initiliazing view and model data member
	 * @param view is the given value for the view data member
	 * @param model is the given value for the model data member
	 */
	public Presenter(View view, Model model) {
		this.view = view;
		this.model = model;
		initilaizeCommands();
	}

	/**
	 * <h1>update</h1>
	 * This method is used when change has occurred either on the Model or the
	 * View layer to handle the change and react, in this case we distinguish
	 * between the Model and the View layer and we response differently
	 * 
	 * @param o is the Observable that notified us that something changed.
	 * @param arg is the object that we got from the change.
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (o == view) {
			String[] input = view.getUserCommand();
			if (commands.containsKey(input[0]))
				commands.get(input[0]).doCommand(extractParamters(input, 1));
			else {
				view.display("not a valid command");
			}

		}
		if (o == model) {
			view.display(arg);
		}
	}

	/**
	 * <h1>extractParamters</h1>
	 * This method is for separating between the command and the parameters of the command
	 * 
	 * @param stringArray is the full command that was inserted.
	 * @param numberOfcellsToIgnore is the number of cells in the array that we need to ignore in
	 * order to perform the separation.
	 * @return the same given string array without the "numberOfCellsToIgnore" cells from the start
	 */
	private String[] extractParamters(String[] stringArray, int numberOfcellsToIgnore) {
		String[] params = new String[stringArray.length - numberOfcellsToIgnore];
		for (int i = numberOfcellsToIgnore; i < stringArray.length; i++) {
			params[i - numberOfcellsToIgnore] = stringArray[i];
		}
		return params;
	}

	/**
	 * <h1>initilaizeCommands</h1>
	 * this method initializing the command in our hashmap
	 */
	public void initilaizeCommands() {
		commands = new HashMap<String, Command>();
		commands.put("dir", new DirCommand());
		commands.put("generate3dMaze", new Generate3dMazeCommand());
		commands.put("display", new DisplayCommand());
		commands.put("displayCrossSection", new DisplayCrossSectionCommand());
		commands.put("saveMaze", new SaveMazeCommand());
		commands.put("loadMaze", new LoadMazeCommand());
		commands.put("mazeSize", new MazeSizeCommand());
		commands.put("fileSize", new FileSizeCommand());
		commands.put("solve", new SolveCommand());
		commands.put("displaySolution", new DisplaySolutionCommand());
		commands.put("displaySolutionFromPosition", new DisplaySolutionFromPositionCommand());
		commands.put("displayHintFromPosition", new DisplayHintSolutionFromPositionCommand());
		commands.put("exit", new ExitCommand());
		commands.put("loadXML", new LoadXMLCommand());  
	}

	/**
	 * <h1>getView</h1>
	 * a getter for the view layer
	 * @return view is the view data member
	 */
	public View getView() {
		return view;
	}

	/**
	 * <h1>getModel</h1>
	 * a getter for the model layer
	 * @return model is the model data member
	 */
	public Model getModel() {
		return model;
	}

	/**<h1>setModel</h1>
	 * This method is a setter for the model layer
	 * @param model is the value for the model data member.
	 */
	public void setModel(Model model) {
		this.model = model;
	}

	/**
	 * <h1>Command</h1> The Command interface represents a command
	 * 
	 */
	public interface Command {
		/**
		 * <h1>doCommand</h1>
		 * this method performs the inserted command
		 * @param args is the String array that represents the command with the parameters
		 */
		void doCommand(String[] args);
	}

	/**
	 * <h1>DirCommand</h1> The DirCommand class implements our Command interface
	 * for displaying the directory
	 * 
	 */
	public class DirCommand implements Command {
		@Override
		public void doCommand(String[] args) {
			try {
				model.dir(args[0]);
			} catch (ArrayIndexOutOfBoundsException e) {
				view.display("must insert a path");
			}
		}
	}

	/**
	 * <h1>Generate3dMazeCommand</h1> The Generate3dMazeCommand class implements
	 * our Command interface for generating 3d maze
	 * 
	 */
	public class Generate3dMazeCommand implements Command {
		@Override
		public void doCommand(String[] args) {
			try {
				model.generate3dMaze(args[0], args[1], Integer.decode(args[2]), Integer.decode(args[3]),
						Integer.decode(args[4]));
			} catch (ArrayIndexOutOfBoundsException e) {
				view.display("paramters missing");
			} catch (NumberFormatException e) {
				view.display("invalid paramters");
			}
		}
	}

	/**
	 * <h1>DisplayCommand</h1> The DisplayCommand class implements our Command
	 * interface for displaying the maze
	 * 
	 */
	public class DisplayCommand implements Command {
		@Override
		public void doCommand(String[] args) {
			try {
				model.getMazeByName(args[0]);
			} catch (ArrayIndexOutOfBoundsException e) {
				view.display("must insert maze name");
			}
		}
	}

	/**
	 * <h1>DisplayCrossSectionCommand</h1> The DisplayCrossSectionCommand class
	 * implements our Command interface for displaying a cross section for our
	 * 3d maze
	 * 
	 */
	public class DisplayCrossSectionCommand implements Command {
		@Override
		public void doCommand(String[] args) {
			try {
				model.getCrossSection(args[0].charAt(0), Integer.decode(args[1]), args[2]);
			} catch (ArrayIndexOutOfBoundsException e) {
				view.display("paramters missing");
			} catch (NumberFormatException e) {
				view.display("invalid paramters");
			}
		}
	}

	/**
	 * <h1>SaveMazeCommand</h1> The SaveMazeCommand class implements our Command
	 * interface for saving the maze
	 * 
	 */
	public class SaveMazeCommand implements Command {
		@Override
		public void doCommand(String[] args) {
			try {
				model.saveMaze(args[0], args[1]);
			} catch (ArrayIndexOutOfBoundsException e) {
				view.display("paramters missing");
			} catch (IOException e) {
				view.display(e.getMessage());
			}
		}
	}

	/**
	 * <h1>LoadMazeCommand</h1> The LoadMazeCommand class implements our Command
	 * interface for loading the maze
	 * 
	 */
	public class LoadMazeCommand implements Command {
		@Override
		public void doCommand(String[] args) {
			try {
				model.loadMaze(args[0], args[1]);
			} catch (ArrayIndexOutOfBoundsException e) {
				view.display("paramters missing");
			} catch (IOException e) {
				view.display(e.getMessage());
			}
		}
	}

	/**
	 * <h1>MazeSizeCommand</h1> The MazeSizeCommand class implements our Command
	 * interface for displaying the maze size
	 * 
	 */
	public class MazeSizeCommand implements Command {
		@Override
		public void doCommand(String[] args) {
			try {
				model.getMazeSize(args[0]);
			} catch (ArrayIndexOutOfBoundsException e) {
				view.display("must insert maze name");
			}
		}
	}

	/**
	 * <h1>FileSizeCommand</h1> The FileSizeCommand class implements our Command
	 * interface for displaying the size of a file
	 * 
	 */
	public class FileSizeCommand implements Command {
		@Override
		public void doCommand(String[] args) {
			try {
				model.getFileSize(args[0]);
			} catch (ArrayIndexOutOfBoundsException e) {
				view.display("must insert file name");
			}
		}
	}

	/**
	 * <h1>SolveCommand</h1> The SolveCommand class implements our Command
	 * interface for solving the maze for the different algorithms
	 * 
	 */
	public class SolveCommand implements Command {
		@Override
		public void doCommand(String[] args) {
			try {
				model.solve(args[0]);
			} catch (ArrayIndexOutOfBoundsException e) {
				view.display("paramters missing");
			}
		}
	}

	/**
	 * <h1>DisplaySolutionCommand</h1> The DisplaySolutionCommand class
	 * implements our Command interface for displaying the solution for a maze
	 * 
	 */
	public class DisplaySolutionCommand implements Command {
		@Override
		public void doCommand(String[] args) {
			try {
				model.getSolutionForName(args[0]);
			} catch (ArrayIndexOutOfBoundsException e) {
				view.display("must insert maze name");
			}
		}
	}
	/**
	 * <h1>DisplaySolutionCommand</h1> The DisplaySolutionCommand class
	 * implements our Command interface for displaying the solution from a specific
	 * position in the maze
	 * 
	 */
	public class DisplaySolutionFromPositionCommand implements Command {
		@Override
		public void doCommand(String[] args) {
			try {
				model.getSolutionFromPosition(args[0], args[1]);
			} catch (ArrayIndexOutOfBoundsException e) {
				view.display("paramters missing");
			}
		}
	}
	/**
	 * <h1>DisplaySolutionCommand</h1> The DisplaySolutionCommand class
	 * implements our Command interface for displaying a hint for a specific
	 * position in the maze
	 * 
	 */
	public class DisplayHintSolutionFromPositionCommand implements Command {
		@Override
		public void doCommand(String[] args) {
			try {
				model.getHintFromPosition(args[0], args[1]);
			} catch (ArrayIndexOutOfBoundsException e) {
				view.display("paramters missing");
			}
		}
	}
	/**
	 * <h1>ExitCommand</h1> The ExitCommand class implements our Command
	 * interface for safely exiting the program
	 * 
	 */
	public class ExitCommand implements Command {
		@Override
		public void doCommand(String[] args) {
			model.close();
			view.close();
		}
	}
	/**
	 * <h1>LoadXMLCommand</h1> The LoadXMLCommand class
	 * implements our Command interface for loading XML Properties file
	 * 
	 */
	public class LoadXMLCommand implements Command {
		@Override
		public void doCommand(String[] args) {
			try {
				model.loadXML(args[0]);
			} catch (ArrayIndexOutOfBoundsException e) {
				view.display("Unsupported XML file");
			}
		}
	}

}
