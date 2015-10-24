package boot;

import model.MyModel;
import presenter.Presenter;
import presenter.Properties;
import view.cli.MyObservableCliView;
import view.gui.MazeWindow;

public class Run {
	public static Properties properties=new Properties();
	public static void main(String[] args) {
		try{
		//	properties=new Properties(new FileInputStream("lib\\Properties.xml"));
	    	properties.loadFromXML();
		}
		catch(Exception e){ properties.saveToXML(); 
		}   
		

		if (Properties.getTypeOfView().equals("GUI")){
		MazeWindow ui = new MazeWindow("MyMaze", 1000, 600);
		MyModel m = new MyModel(Properties.getNumOfThreads());
		Presenter p = new Presenter(ui, m);
		ui.addObserver(p);
		m.addObserver(p); 
		new Thread(ui).run();
		//ui.start();
	}
		else if (Properties.getTypeOfView().equals("CLI")){
			MyObservableCliView CliView=new MyObservableCliView();
			MyModel m = new MyModel(Properties.getNumOfThreads());
			Presenter p = new Presenter(CliView, m);
			CliView.addObserver(p);
			m.addObserver(p);
			CliView.start();
		}
		

	}

}