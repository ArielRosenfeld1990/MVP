package view.gui;

import java.util.Observable;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * <h1>BasicWindow</h1> The BasicWindow abstract class represents our basic window widget,
 * defining our shell and display
 * <p>
 *
 * @author Ariel Rosenfeld,Ofir Calif
 *
 * 
 */ 

public abstract class BasicWindow extends Observable implements Runnable {

	Display display;
	Shell shell;
	
	/**
	 * <h1>BasicWindow constructor</h1>
	 * @param title the title for the window
	 * @param width the width for the window
	 * @param height the height for the window
	 */
	public BasicWindow(String title, int width, int height) {
		display = new Display();
		shell = new Shell(display);
		shell.setSize(width, height);
		shell.setText(title);
	}
	
	/**
	 * <h1>initWidgets</h1>
	 * this method sets the window and initializes our widgets,
	 * every class that extends this class must implement this for the widgets that he wants in the window
	 */
	abstract void initWidgets();
	
	/**
	 * <h1>run</h1>
	 * this method runs our shell,display and the widgets
	 * start the main loop
	 */
	@Override
	public void run() {
		initWidgets();
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();

	}
}
