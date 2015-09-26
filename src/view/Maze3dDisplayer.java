package view;

import java.io.PrintWriter;

import algorithms.mazeGenerators.Maze3d;
/**
* <h1>Maze3dDisplayer</h1>
* The Maze3dDisplayer class implements our Displayer interface
* <p>
*
* @author  Ariel Rosenfeld,Ofir Calif
*
* 
*/
public class Maze3dDisplayer implements Displayer {
	/**
	* This method is for displaying the 3d maze
	* @param obj is the object that we need to display.
	* @param out is from where we are going to display it.
	*/
	@Override
	public void display(Object obj, PrintWriter out) {
		int[][][] maze = ((Maze3d)obj).getMaze();
		for(int i=0;i<maze.length;i++)
		{
			for(int j=0;j<maze[i].length;j++)
			{
				out.println();
				out.print(maze[i][j][0]);
				for(int k=1;k<maze[i][j].length;k++)
					out.print(","+maze[i][j][k]);
			}
			out.println();
		}
		out.flush();
	}

}
