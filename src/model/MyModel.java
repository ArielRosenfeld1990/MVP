package model;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Observable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import algorithms.demo.Maze3dSearchable;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import algorithms.mazeGenerators.SimpleMaze3dGenerator;
import algorithms.search.AStar;
import algorithms.search.Bfs;
import algorithms.search.MazeAirDistance;
import algorithms.search.MazeManhattanDistance;
import algorithms.search.Searcher;
import algorithms.search.Solution;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;
/**
* <h1>MyModel</h1>
* The MyModel class implements our Model interface
* <p>
*
* @author  Ariel Rosenfeld,Ofir Calif
*
* 
*/
public class MyModel extends Observable implements Model {
//
	private HashMap<String, Maze3d> mazes;
	private HashMap<String, Solution> mazesSolution;
	ExecutorService threadPool;
	
	/**
	* constructor for MyModel
	*/
	public MyModel() {
		mazes = new HashMap<String, Maze3d>();
		mazesSolution = new HashMap<String,Solution>();
		threadPool = Executors.newFixedThreadPool(5);
	}

	/**
	* This method is for displaying the directory in the CLI
	* @param path is the path for the files.
	*/
	@Override
	public void dir(String path) {

		String[] directories =new File(path).list();
		setChanged();
		if( directories!=null)
			notifyObservers(directories);
		else
			notifyObservers("directory not found");
	}

	/**
	* This method is for generating a 3d maze
	* @param name is the name of the maze.
	* @param generator is the how we would generate the maze.
	* @param x is the x dimension for the maze
	* @param y is the y dimension for the maze
	* @param z is the z dimension for the maze
	*/
	@Override
	public void generate3dMaze(String name,String generator,int x,int y,int z) {
		threadPool.execute(new Runnable() {
			@Override
			public void run() {
				Maze3d maze;
				setChanged();
				if(!mazes.containsKey(name))
				{
					if(generator.startsWith("simple"))
						maze = new SimpleMaze3dGenerator().generate(x, y, z);
					else
						maze = new MyMaze3dGenerator().generate(x, y, z);

					mazes.put(name, maze);
					notifyObservers("maze "+ name +" is ready");
				}
				else
					notifyObservers("maze with name "+name+" exsits");
			}
		});
	}

	/**
	* This method is for getting the maze by his name
	* @param name is the name of the maze.
	*/
	@Override
	public void getMazeByName(String name) {
		try{
			setChanged();
			Maze3d maze = getMaze(name);
			notifyObservers(maze);
		}catch(Exception e){
			notifyObservers(e.getMessage());	
		}
	}

//	/**
//	* This method is getting the cross section
//	* of a 3d maze
//	* @param axis is the dimension of the 3d maze.
//	* @param index is the index in the array.
//	* @param mazeName is the name of the 3d maze.
//	*/
//	@Override
//	public void getCrossSection(char axis, int index, String mazeName){
//		int[][] crossSection;
//		try{
//			Maze3d maze = getMaze(mazeName);
//			switch (axis) {
//			case 'x':
//			case 'X':
//				crossSection=maze.getCrossSectionByX(index);
//				break;
//			case 'y':
//			case 'Y':
//				crossSection=maze.getCrossSectionByY(index);
//				break;
//			case 'z':
//			case 'Z':
//				crossSection=maze.getCrossSectionByZ(index);
//				break;
//			default:
//				throw new InvalidParameterException("invalid Axis");	
//			}
//		}
//		catch(IndexOutOfBoundsException e){			
//			controller.update("not a valid index for this maze");
//			return;
//		}
//		catch (Exception e) {
//			controller.update(e.getMessage());
//			return;
//		}
//
//		controller.update(crossSection);
//	}
//
//	/**
//	* This method is for saving the maze to a file
//	* @param mazeName is the name of the maze.
//	* @param fileName is the name of the file.
//	*/
//	@Override
//	public void saveMaze(String mazeName, String fileName) throws IOException  {
//		OutputStream out=null;
//		try {
//			Maze3d maze = getMaze(mazeName);
//			out=new MyCompressorOutputStream(new FileOutputStream(fileName));
//			out.write(maze.toByteArray());
//			out.flush();	
//			controller.update("file saved");
//		} 
//		catch (FileNotFoundException e) {
//			controller.update(e.getMessage());
//		}
//		catch (IOException e) {
//			controller.update(e.getMessage());
//		}
//		catch (Exception e) {
//			controller.update(e.getMessage());
//		}
//		finally {
//			if(out!=null)
//				out.close();
//		}
//
//	}
//
//	/**
//	* This method is for loading the maze from a file
//	* @param mazeName is the name of the maze.
//	* @param fileName is the name of the file.
//	*/
//	@Override
//	public void loadMaze(String fileName, String mazeName) throws IOException {
//		InputStream in = null;
//		ByteArrayOutputStream buffer=new ByteArrayOutputStream();;
//		byte[] data=new byte[100];
//
//		if(mazes.containsKey(mazeName)){
//			controller.update("maze with name "+mazeName+" exsits");
//			return;
//		}
//		try{
//			in =new MyDecompressorInputStream(new FileInputStream(fileName));
//
//			while(in.read(data)!=-1){
//				buffer.write(data);
//			}
//			Maze3d loaded=new Maze3d(buffer.toByteArray());
//			mazes.put(mazeName, loaded);
//			controller.update("load successfuly");
//		}
//		catch (FileNotFoundException e) {
//			controller.update(e.getMessage());
//		}
//		catch (IOException e) {
//			controller.update(e.getMessage());
//		}
//		catch (ClassNotFoundException e) {
//			controller.update(e.getMessage());
//		}
//		finally {
//			if(in!=null)
//				in.close();
//			buffer.close();
//		}
//	}	
//
//	/**
//	 * This method is for getting the size of the maze 
//	 * in the memory
//	 * @param mazeName is the name of the maze.
//	 */
//	@Override
//	public void getMazeSize(String mazeName) {
//
//		try {
//			Maze3d maze = getMaze(mazeName);
//			controller.update(maze.toByteArray().length +" bytes");
//		}
//		catch (Exception e) {
//			controller.update(e.getMessage());
//		}
//	}
//	
//	/**
//	* This method is for getting the size of a file
//	* @param fileName is the name of the file.
//	*/
//
//	@Override
//	public void getFileSize(String fileName) {
//		try {
//			
//				File file=new File(fileName);
//				if(file.exists()){
//					controller.update(file.length()+" bytes");
//				}
//				else throw new FileNotFoundException("file"+ fileName + "not found");		
//		} catch (FileNotFoundException e) {
//			controller.update(e.getMessage());
//		}
//		
//	}
//	
//	/**
//	* This method is for solving the maze
//	* with the different algorithms
//	* @param mazeName is the name of the maze.
//	* @param algorithm is the name of the algorithm.
//	*/
//	@Override
//	public void solve(String mazeName, String algorithm) {
//		threadPool.execute(new Runnable() {
//			@Override
//			public void run() {
//				Searcher searcher;
//				try{
//					Maze3d maze = getMaze(mazeName);
//					switch(algorithm)
//					{
//					case "bfs":
//						searcher = new Bfs();
//						break;
//					case "aStarAir":
//						searcher = new AStar(new MazeManhattanDistance());
//						break;
//					case "aStarManhattan":
//						searcher = new AStar(new MazeAirDistance());
//						break;
//					default:
//						throw new InvalidParameterException("invalid Axis");
//					} 
//					mazesSolution.put(mazeName, searcher.search(new Maze3dSearchable(maze)));
//					controller.update("solution for "+ mazeName +" is ready");
//				}
//				catch (Exception e) {
//					e.printStackTrace();
//					controller.update(e.getMessage());
//				}		
//			}
//		});
//	}
//
//	/**
//	* This method is for getting the solution
//	* for the 3d maze
//	* @param name is the name of the maze.
//	*/
//	@Override
//	public void getSolutionForName(String name) {
//		try{
//			Solution solution = mazesSolution.get(name);
//			if(solution==null) throw new Exception("solution dosent exsist");
//			controller.update(solution);
//		}catch(Exception e){
//			controller.update(e.getMessage());	
//		}
//	}
//
//	/**
//	* This method is for closing myModel
//	*/
//	@Override
//	public void close() {
//		threadPool.shutdown();
//		try {
//			while(!threadPool.awaitTermination(10, TimeUnit.SECONDS));
//			controller.update("all the tasks have finished");
//		} catch (InterruptedException e) {
//			controller.update(e.getMessage());
//		}
//		controller.update("model is safely closed");
//	}
//	
//	/**
//	* This method is for getting a maze 
//	* @param name is the name of the maze.
//	*@return maze is our 3d maze.
//	*/
	private Maze3d getMaze(String mazeName) throws Exception
	{
		Maze3d maze = mazes.get(mazeName);
		if(maze==null) throw new Exception("maze dosent exsist");
		return maze;
	}
}
