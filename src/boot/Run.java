package boot;

import model.MazeModel;
import presenter.Presenter;
import view.MazeView;

public class Run {

	public static void main(String[] args) {
		MazeView ui = new MazeView();
		MazeModel m = new MazeModel();
		Presenter p = new Presenter(ui,m);
		ui.addObserver(p);
		m.addObserver(p);
	}

}
