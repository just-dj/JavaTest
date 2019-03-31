package project;

import javafx.scene.control.ListCell;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MyCell extends ListCell<String> {
	@Override
	public void updateItem(String item, boolean empty) {
		super.updateItem(item, empty);
		Rectangle rect = new Rectangle(308, 44);
		rect.setFill(Color.rgb(100,10,100));
	}
}
