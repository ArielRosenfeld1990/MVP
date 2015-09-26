package view;

import java.io.PrintWriter;
/**
* <h1>CrossSectionDisplayer</h1>
* The CrossSectionDisplayer class implements our Displayer interface
* <p>
*
* @author  Ariel Rosenfeld,Ofir Calif
*
* 
*/
public class CrossSectionDisplayer implements Displayer {
	/**
	* This method is for displaying the cross section
	* @param obj is the object that we need to display.
	* @param out is from where we are going to display it.
	*/
	@Override
	public void display(Object obj, PrintWriter out) {
		int[][] crossSection = ((int[][])obj);
		for(int[] array:crossSection)
		{
			for(int cell :array)
				out.print(cell);
			out.println();
		}
		out.flush();
	
		
		
		
		
		
	}

}
