package presenter;

import java.util.Observable;
import java.util.Observer;

import model.Model;
import view.View;

public class Presenter implements Observer{
	View ui;
	Model model;
	
	public Presenter(View ui,Model model) {
		this.ui = ui;
		this.model = model;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(o==ui)
		{
			int input=ui.getUSerCommand();
			switch(input)
			{
			//case 2:model.moveUp();
			}
		}
		if(o==model)
		{
			ui.displayData(model.getData());
		}
	}
}
