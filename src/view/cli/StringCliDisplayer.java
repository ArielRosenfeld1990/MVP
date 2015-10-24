package view.cli;

import java.io.PrintWriter;

/**
 * <h1>StringDisplayer</h1> The StringDisplayer class implements our Displayer
 * interface
 * <p>
 *
 * @author Ariel Rosenfeld,Ofir Calif
 *
 *  
 */
public class StringCliDisplayer implements CliDisplayer {
	/**
	 * This method is for displaying the String
	 *  
	 * @param obj
	 *            is the object that we need to display.
	 * @param out
	 *            is from where we are going to display it.
	 */
	@Override
	public void display(Object obj, PrintWriter out) {
		out.println((String) obj);
		out.flush();
	}

}
