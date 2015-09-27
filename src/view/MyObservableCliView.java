package view;


import java.util.Observable;
import java.util.Observer;

/**
* <h1>MyView</h1>
* The MyView class implements our View interface
* <p>
*
* @author  Ariel Rosenfeld,Ofir Calif
*
* 
*/
public class MyObservableCliView extends Observable implements View, Observer{

	private CLI ui;
	/**
	* constructor for MyView
	*/
	public MyObservableCliView() {
	}
	/**
	* this method is used for starting interaction between the user and the CLI
	*/
	@Override
	public void start() {
		ui=new CLI(System.in, System.out);
		ui.addObserver(this);
		ui.start();
	}


	/**
	* This method is for displaying the object
	* @param obj is the object that we need to display.
	* @param out is from where we are going to display it.
	*/
	@Override
	public void display(Object obj) {
		if(obj!=null)
		{
			switch (obj.getClass().getSimpleName()) {
			case "String":
				ui.display(obj, new StringDisplayer());
				break;
			case "String[]":
				ui.display(obj, new StringArrayDisplayer());
				break;
			case "Maze3d":
				ui.display(obj, new Maze3dDisplayer());
				break;
			case "int[][]":
				ui.display(obj, new CrossSectionDisplayer());
				break;
			case "Solution":
				ui.display(obj, new SolutionDisplayer());
				break;
			default:
				break;
			}
		}
	}
	/**
	 * this method close the view
	 */
	@Override
	public void close() {
		ui.close();
	}
	@Override
	public String[] getUserCommand() {
		return ui.getUserCommand();
	}
	@Override
	public void update(Observable o, Object arg) {
		if(o==ui)
		{
			setChanged();
			notifyObservers();
		}
	}

}
