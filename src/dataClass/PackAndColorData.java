package dataClass;

import java.io.Serializable;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PackAndColorData implements Serializable{
	public static final long serialVersionUID = 1L;
	
	private transient StringProperty pack = new SimpleStringProperty();
	private transient StringProperty color = new SimpleStringProperty();
	String packS;
	String colS;
	 
	
	public PackAndColorData(String pack, String color) {
		this.pack.set(pack);
		this.color.set(color);
	}
	
	public void init() {
		pack = new SimpleStringProperty();
		color = new SimpleStringProperty();
		this.pack.set(packS);
		this.color.set(colS);
	}
	
	public void writePre() {
		packS = pack.getValueSafe();
		colS = color.getValueSafe();
	}
	
	public StringProperty packPriperty() {
		return pack;
	}
	
	public StringProperty colorPriperty() {
		return color;
	}
	
	
	public String getPack() {
		return packS;
	}
	
	public String getCol() {
		return colS;
	}
	
}


	/*Color col = Color.valueOf(colStr);
	var toDoLabel = new Label(data.titleProperty().get());
	toDoLabel.getStyleClass().add("toDoLabel");
	toDoLabel.setBackground(new Background(new BackgroundFill(col, null, null)));
	vb.getChildren().add(toDoLabel);*/