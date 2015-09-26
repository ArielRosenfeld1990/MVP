package view;

import java.io.PrintWriter;
/**
* <h1>Displayer</h1>
* The Displayer interface represents our way to display differenct objects in the CLI
* <p>
*
* @author  Ariel Rosenfeld,Ofir Calif
*
* 
*/
public interface Displayer {
	/**
	* This method is for displaying the object
	* @param obj is the object that we need to display.
	* @param out is from where we are going to display it.
	*/
	void display(Object obj,PrintWriter out);
}
