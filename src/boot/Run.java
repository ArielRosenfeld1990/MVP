package boot;

import model.MyModel;
import presenter.Presenter;
import view.cli.MyObservableCliView;
import view.gui.MazeWindow;
import view.gui.MyObservableGuiView;

public class Run {

	public static void main(String[] args) {
		//MyObservableCliView ui = new MyObservableCliView();
		MazeWindow ui = new MazeWindow("MyMaze", 500, 300);
		MyModel m = new MyModel();
		Presenter p = new Presenter(ui,m);
		ui.addObserver(p);
		m.addObserver(p);
		ui.start();
		
	}

}
