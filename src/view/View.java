package view;

/**
 * <h1>View</h1> The View interface represents our View layer
 * <p>
 *
 * @author Ariel Rosenfeld,Ofir Calif
 *
 * 
 */
public interface View {
	/**
	 * <h1>start</h1>
	 * this method is used for starting interaction between the user and the CLI
	 */
	void start();

	/** 
	 * <h1>display</h1>
	 * This method is for displaying the object
	 * @param obj is the object that we need to display.
	 */
	void display(Object obj);

	/**
	 * <h1>close</h1>
	 * this method is for closing the view
	 */
	void close();
    /**
     * <h1>getUserCommand</h1>
     * this method is used for inserting the user command into a String Array
     * @return String Array of the user inserted command
     */
	String[] getUserCommand();
}
