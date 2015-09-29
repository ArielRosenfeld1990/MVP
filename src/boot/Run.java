package boot;

import model.MyModel;
import presenter.Presenter;
import view.MazeWindow;
import view.MyObservableGuiView;
import view.cli.MyObservableCliView;

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
