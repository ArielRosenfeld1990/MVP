package boot;

import model.MyModel;
import presenter.Presenter;
import presenter.Properties;
import view.View;
import view.cli.MyObservableCliView;
import view.gui.MazeWindow;
import view.gui.MyObservableGuiView;

public class Run {
	static Properties properties=new Properties();
	public static void main(String[] args) {
		try{
		properties.loadFromXML();
		}
		catch(Exception e){ properties.saveToXML();
		}   
		if (Properties.getTypeOfView().equals("GUI")){
		// MyObservableCliView ui = new MyObservableCliView();
		MazeWindow ui = new MazeWindow("MyMaze", 1000, 600);
		MyModel m = new MyModel(Properties.getNumOfThreads());
		Presenter p = new Presenter(ui, m);
		ui.addObserver(p);
		m.addObserver(p);
		ui.start();
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
