package view.cli;

import java.util.Observable;
import java.util.Observer;

import view.View;

/**
 * <h1>MyObservableCliView</h1> The MyObservableCliView class implements our
 * View interface,extending Observable so out Present can observe it and see if
 * something has changed,implements Observer so our MyObservableCliView can
 * observe the CLI to see if something happened
 * <p>
 * 
 * @author Ariel Rosenfeld,Ofir Calif
 *
 * 
 */
public class MyObservableCliView extends Observable implements View, Observer {

	private CLI ui;

	/**
	 * constructor for MyView
	 */
	public MyObservableCliView() {
	}

	/**
	 * this method is used for starting interaction between the user and the CLI
	 */
	@Override
	public void start() {
		ui = new CLI(System.in, System.out);
		ui.addObserver(this);
		ui.start();
	} 

	/**
	 * This method is for displaying the object
	 * 
	 * @param obj
	 *            is the object that we need to display.
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
	 * this method closes the view
	 */
	@Override
	public void close() {
		ui.close();
	}

	/**
	 * this method is used to get a command from the CLI
	 */
	@Override
	public String[] getUserCommand() {
		return ui.getUserCommand();
	}

	/**
	 * This method is used when change has occured on the CLI to handle the
	 * change and react, in this case we notify the Presenter that something
	 * happened
	 * 
	 * @param o
	 *            is the Observable that notified us that something changed.
	 * @param o
	 *            is the object that we got from the change.
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (o == ui) {
			setChanged();
			notifyObservers();
		}
	}

}
