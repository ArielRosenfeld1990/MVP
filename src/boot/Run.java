package boot;

import model.MyModel;
import presenter.Presenter;
import view.MyObservableCliView;

public class Run {

	public static void main(String[] args) {
		MyObservableCliView ui = new MyObservableCliView();
		MyModel m = new MyModel();
		Presenter p = new Presenter(ui,m);
		ui.addObserver(p);
		m.addObserver(p);
		ui.start();
	}

}
