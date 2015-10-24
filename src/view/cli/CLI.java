package view.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Observable;

/**
 * <h1>CLI</h1> The CLI class represents our Command Line Interface,this class
 * is extending Observable so that our MyObservableCliView can observe the CLI
 * <p>
 *
 * @author Ariel Rosenfeld,Ofir Calif
 *
 * 
 */
public class CLI extends Observable {
	private BufferedReader in; 
	private PrintWriter out;
	private String[] splitedCommand;
	Thread mainThread;
	volatile boolean stop;

	/**
	 * constructor for CLI
	 */
	public CLI(InputStream in, OutputStream out) {
		this.in = new BufferedReader(new InputStreamReader(in));
		this.out = new PrintWriter(out);
		splitedCommand = null;

	}

	/**
	 * this method is used to start the main loop of the cli
	 */
	public void start() {// run in thread
		mainThread = new Thread(new Runnable() {
			@Override
			public void run() {
				String inputString;
				try {
					while (!stop) {
						inputString = in.readLine();
						splitedCommand = inputString.split(" ");
						setChanged();
						notifyObservers();
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		mainThread.start();
	}

	/**
	 * This method is for displaying the result in the CLI
	 * 
	 * @param obj
	 *            is the object that we got.
	 * @param displayer
	 *            is the kind of displayer we need to show in the CLI.
	 */
	public void display(Object obj, CliDisplayer displayer) {
		displayer.display(obj, out);
	} 

	/**
	 * this method stops the main loop
	 */
	public void close() {
		stop = true;
		out.println("bye");
		out.flush();
	}

	/**
	 * this method is used to get the user command
	 */

	public String[] getUserCommand() {
		return splitedCommand;
	}

}
