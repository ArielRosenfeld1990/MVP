
//for deletion
package view.gui;

import java.util.Observable;

import view.View;
import view.cli.Maze3dCliDisplayer;

public class MyObservableGuiView extends Observable implements View {
	MazeWindow win;

	public MyObservableGuiView(String title, int width, int height) {
		win = new MazeWindow(title, width, height);
	}

	@Override
	public void start() {
		win.run();
	}

	@Override
	public void display(Object obj) {
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	@Override
	public String[] getUserCommand() {
		// TODO Auto-generated method stub
		return null;
	}

}
