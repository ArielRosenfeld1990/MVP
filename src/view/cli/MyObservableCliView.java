package view.cli;

import java.util.Observable;
import java.util.Observer;

import view.View;

/**
 * <h1>MyObservableCliView</h1> The MyObservableCliView class implements our
 * View interface,extending Observable so our Presenter can observe it and see if
 * something happened,implements Observer so our MyObservableCliView can
 * observe the CLI to see if something happened
 * <p>
 * 
 * @author Ariel Rosenfeld,Ofir Calif
 *
 * 
 */
public class MyObservableCliView extends Observable implements View, Observer {

	private CLI ui;


	public MyObservableCliView() {
	}

	/**
	 * <h1>start</h1>
	 * this method is used for starting the interaction between the client and the CLI
	 */
	@Override
	public void start() {
		ui = new CLI(System.in, System.out);
		ui.addObserver(this);
		ui.start();
	} 

	/**
	 * <h1>display</h1>
	 * This method is for displaying the object
	 * @param obj is the object that we need to display.
	 */
	@Override
	public void display(Object obj) {
		if (obj != null) {
			switch (obj.getClass().getSimpleName()) {
			case "String":
				ui.display(obj, new StringCliDisplayer());
				break;
			case "String[]":
				ui.display(obj, new StringArrayCliDisplayer());
				break;
			case "Maze3d":
				ui.display(obj, new Maze3dCliDisplayer());
				break;
			case "int[][]":
				ui.display(obj, new CrossSectionCliDisplayer());
				break;
			case "Solution":
				ui.display(obj, new SolutionCliDisplayer());
				break;
			default:
				break;
			}
		}
	}

	/**
	 * <h1>close</h1>
	 * this method closes the view
	 */
	@Override
	public void close() {
		ui.close();
	}

	/**
	 * <h1>getUserCommand</h1>
	 * this method is used to get a command from the CLI and place it into 
	 * a String Array
	 * @return String Array that represents the command with its parameters
	 */
	@Override
	public String[] getUserCommand() {
		return ui.getUserCommand();
	}

	/**
	 * <h1>update</h1>
	 * This method is used when change has occurred on our Observable, to handle the
	 * change and react, in this case we notify the Presenter that something happened
	 * @param o is the Observable that notified us that something changed.
	 * @param arg is the object that we got from the change.
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (o == ui) {
			setChanged();
			notifyObservers();
		}
	}

}
