package model;

import java.io.IOException;

/**
 * <h1>Model</h1> The Model interface represents our Model layer
 * <p>
 *
 * @author Ariel Rosenfeld,Ofir Calif
 *
 * 
 */
public interface Model {
	
	/**
	 * <h1>dir method</h1>
	 * This method get a directory and get all the files in the given directory
	 * 
	 * @param path the directory to get the files from
	 */
	void dir(String path);

	/**
	 * <h1>generate3dMaze method</h1>
	 * This method is for generating a 3d maze
	 * 
	 * @param name
	 *            is the name of the maze.
	 * @param generator
	 *            is the how we would generate the maze.
	 * @param x
	 *            is the x dimension for the maze
	 * @param y
	 *            is the y dimension for the maze
	 * @param z
	 *            is the z dimension for the maze
	 */
	void generate3dMaze(String name, String generator, int x, int y, int z);

	/**
	 * <h1>getMazeByName</h1>
	 * This method is for getting the maze by his name
	 * 
	 * @param name
	 *            is the name of the maze.
	 */
	void getMazeByName(String name);

	/**
	 * <h1>getCrossSection</h1>
	 * This method is getting the cross section of a 3d maze
	 * 
	 * @param axis
	 *            is the dimension of the 3d maze.
	 * @param index
	 *            is the index in the array.
	 * @param mazeName
	 *            is the name of the 3d maze.
	 */
	void getCrossSection(char axis, int index, String mazeName);

	/**
	 * <h1>saveMaze</h1>
	 * This method is for saving the maze into a file
	 * 
	 * @param mazeName
	 *            is the name of the maze.
	 * @param fileName
	 *            is the path of the file.
	 * @throws IOException throws if there is a problem to save the maze
	 */
	void saveMaze(String mazeName, String fileName) throws IOException;

	/**
	 * <h1>loadMaze</h1>
	 * This method is for loading the maze from a file
	 * 
	 * @param mazeName
	 *            is the name of the maze.
	 * @param fileName
	 *            is the name of the file.
	 * @throws IOException throws if there is a problem to load the given file
	 */
	void loadMaze(String fileName, String mazeName) throws IOException;

	/**
	 * <h1>getMazeSize</h1>
	 * This method is for getting the size of the maze in the memory
	 * 
	 * @param mazeName
	 *            is the name of the maze.
	 */
	void getMazeSize(String mazeName);

	/**
	 * <h1>getFileSize</h1>
	 * This method is for getting the size of a file
	 * 
	 * @param fileName
	 *            is the path of the file.
	 */
	public void getFileSize(String fileName);

	/**
	 * <h1>solve</h1>
	 * This method is for solving the maze with the different algorithms
	 * 
	 * @param mazeName
	 *            is the name of the maze.
	 */
	void solve(String mazeName);

	/**
	 * <h1>getSolutionForName</h1>
	 * This method is for getting the solution for the 3d maze
	 * 
	 * @param name
	 *            is the name of the maze.
	 */
	void getSolutionForName(String name);
	
	/**
	 * <h1>getSolutionFromPosition</h1>
	 * This method is for getting the solution from a certain position in the maze
	 * @param mazeName is the name of the maze
	 * @param position is the given position.
	 */
	void getSolutionFromPosition(String mazeName,String position);
	
	/**
	 * <h1>getHintFromPosition</h1>
	 * This method is for getting a hint for a certain position in the maze
	 * @param mazeName is the name of the maze
	 * @param position is the given position.
	 */
	public void getHintFromPosition(String mazeName,String position);

	/** 
	 * <h1>close</h1>
	 * This method is for closing the model
	 */
	void close();
	
	/**
	 * <h1>loadXML</h1>
	 * This method is for loading the XML from a file
	 * 
	 * @param name
	 *            is the path of the Properties file.
	 */
	void loadXML(String name);
}
