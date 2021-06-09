package dataClass;

import java.io.Serializable;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PackAndColorData implements Serializable{
	private StringProperty pack = new SimpleStringProperty();
	private StringProperty color = new SimpleStringProperty();
	 
	
	public PackAndColorData(String pack, String color) {
		this.pack.set(pack);
		this.color.set(color);
	}
	
	public StringProperty packPriperty() {
		return pack;
	}
	
	public StringProperty colorPriperty() {
		return color;
	}
}


	/*Color col = Color.valueOf(colStr);
	var toDoLabel = new Label(data.titleProperty().get());
	toDoLabel.getStyleClass().add("toDoLabel");
	toDoLabel.setBackground(new Background(new BackgroundFill(col, null, null)));
	vb.getChildren().add(toDoLabel);*/