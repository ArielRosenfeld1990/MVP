package model;

import java.io.IOException;
/**
* <h1>Model</h1>
* The Model interface represents our Model layer
* <p>
*
* @author  Ariel Rosenfeld,Ofir Calif
*
* 
*/
public interface Model {
/**
* This method is for displaying the directory in the CLI
* @param path is the path for the files.
*/
void dir(String path);
/**
* This method is for generating a 3d maze
* @param name is the name of the maze.
* @param generator is the how we would generate the maze.
* @param x is the x dimension for the maze
* @param y is the y dimension for the maze
* @param z is the z dimension for the maze
*/
void generate3dMaze(String name,String generator,int x,int y,int z);
/**
* This method is for getting the maze by his name
* @param name is the name of the maze.
*/
void getMazeByName(String name);
/**
* This method is getting the cross section
* of a 3d maze
* @param axis is the dimension of the 3d maze.
* @param index is the index in the array.
* @param mazeName is the name of the 3d maze.
*/
void getCrossSection(char axis,int index,String mazeName);
/**
* This method is for saving the maze in a file
* @param mazeName is the name of the maze.
* @param fileName is the name of the file.
*/
void saveMaze(String mazeName, String fileName) throws IOException;
/**
* This method is for loading the maze from a file
* @param mazeName is the name of the maze.
* @param fileName is the name of the file.
*/
void loadMaze(String fileName,String mazeName) throws IOException;
/**
* This method is for getting the size of the maze 
* in the memory
* @param mazeName is the name of the maze.
*/
void getMazeSize(String mazeName);
/**
* This method is for getting the size of a file
* @param fileName is the name of the file.
*/
public void getFileSize(String fileName);
/**
* This method is for solving the maze
* with the different algorithms
* @param mazeName is the name of the maze.
* @param algorithm is the name of the algorithm.
*/
void solve(String mazeName,String algorithm);
/**
* his method is for getting the solution
* for the 3d maze
* @param name is the name of the maze.
*/
void getSolutionForName(String name);
/**
* This method is for closing the model
*/
void close();
}
