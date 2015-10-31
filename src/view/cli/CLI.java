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
	 * <h1>CLI constructor</h1>
	 * CLI constructor is the consturctor for our class, initiliazing in and out
	 * data members
	 * @param in is an InputStream parameter
	 * @param out is an OutputStream parameter
	 */
	public CLI(InputStream in, OutputStream out) {
		this.in = new BufferedReader(new InputStreamReader(in));
		this.out = new PrintWriter(out);
		splitedCommand = null;

	}

	/**
	 * <h1>start</h1>
	 * this method is used to start the main loop of the cli and the interaction
	 * between the CLI and the client
	 */
	public void start() {
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
	 * <h1>display</h1>
	 * This method is for displaying the result in the CLI
	 * @param obj is the object that we got.
	 * @param displayer is the kind of displayer we need to show in the CLI.
	 */
	public void display(Object obj, CliDisplayer displayer) {
		displayer.display(obj, out);
	} 

	/**
	 * <h1>close</h1>
	 * this method stops the main loop and stopping the interaction between the CLI 
	 * and the client
	 */
	public void close() {
		stop = true;
		out.println("bye");
		out.flush();
	}

	/**
	 * <h1>getUserCommand</h1>
	 * this method is used to get the user command
	 * @return splitedCommand is the String Array that represents the command with the parameters
	 */

	public String[] getUserCommand() {
		return splitedCommand;
	}

}
