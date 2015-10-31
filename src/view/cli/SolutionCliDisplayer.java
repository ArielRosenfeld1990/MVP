package view.cli;

import java.io.PrintWriter;

import algorithms.search.Solution;
import algorithms.search.State;

/**
 * <h1>SolutionDisplayer</h1> The SolutionDisplayer class implements our
 * Displayer interface
 * <p>
 *
 * @author Ariel Rosenfeld,Ofir Calif
 * 
 * 
 */
public class SolutionCliDisplayer implements CliDisplayer {
	/**
	 * <h1>display</h1>
	 * This method is for displaying the object
	 * @param obj is the object that we need to display.
	 * @param out is from where we are going to display it.
	 */
	@Override
	public void display(Object obj, PrintWriter out) {
		Solution solution = ((Solution) obj);
		for (State current : solution.getPath()) {
			out.println(current.getState());
		}
		out.flush();
	}

}
