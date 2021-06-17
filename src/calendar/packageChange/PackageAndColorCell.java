package calendar.packageChange;

import dataClass.PackAndColorData;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class PackageAndColorCell extends ListCell<PackAndColorData> {
	
	private Label pack;
	
	public PackageAndColorCell() {
		initComponent();
        initStyle();
	}
	
	private void initComponent() {
		pack = new Label();
	}
	
	public void initStyle() {
		   pack.setFont(new Font("System Bold", 10.0));
	   }
	
	 @Override
	   public void updateItem(PackAndColorData data, boolean empty) {
		   super.updateItem(data, empty);
		   
		   if (empty) {
	            setText(null);
	            setGraphic(null);
	        } else { // タイトルやら詳細やらを設定
	        	pack.textProperty().bind(data.packPriperty());
	        	pack.setAlignment(Pos.CENTER);
	        	pack.setPrefWidth(getListView().getWidth()-20);
	        	StringProperty colStr = data.colorPriperty();
	        	Color col = Color.valueOf(colStr.getValue());
	        	pack.setBackground(new Background(new BackgroundFill(col, null, null)));
	        	
	        	setGraphic(pack);
	        }
	 }
}
