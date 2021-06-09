package calendar.scheduleTable;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalTime;

import dataClass.ScheduleData;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import serialize.PDReadWrite;

public class AddDataAndLabel implements CreateDataAndLabel {
	
	public static StringProperty titleOfLabel;
	public static ObjectProperty<LocalTime> startTime ;
    public static ObjectProperty<LocalTime> finishTime;
    public static Stage stage;

	@Override
	public ScheduleData addData(ComboBox<String> packageSelect,ComboBox<String> year,ComboBox<String> month,ComboBox<String> day,TextField scheduleName,ComboBox<String> sHour,ComboBox<String> sMinute,ComboBox<String> fHour,ComboBox<String> fMinute,AnchorPane scheduleSelect,TextArea memo) {
		int year_ = Integer.parseInt(year.getValue());
    	int month_ = Integer.parseInt(month.getValue());
    	int day_ = Integer.parseInt(day.getValue());
    	String packageSelect_ = packageSelect.getValue();
    	String name = scheduleName.getText();
    	var sTime = LocalTime.of(Integer.parseInt(sHour.getValue()),Integer.parseInt(sMinute.getValue()));
    	var fTime = LocalTime.of(Integer.parseInt(fHour.getValue()),Integer.parseInt(fMinute.getValue()));
    	String detail = memo.getText();
       	var sd = new ScheduleData(LocalDate.of(year_, month_, day_),name, sTime, fTime, detail,packageSelect_);
    	return sd;
	}

	@SuppressWarnings("static-access")
	@Override
	public void createScheduleLabel(ScheduleData data,AnchorPane aPane) throws URISyntaxException {
		var sLabel = new Label();
 		double stNum = (data.startTimeProperty().get().getHour()+data.startTimeProperty().get().getMinute()/60)*30+4;
 	    double ftNum = (data.finishTimeProperty().get().getHour()+data.finishTimeProperty().get().getMinute()/60)*30+4;
 	    double tNum = ftNum-stNum;
 		String str = data.titleProperty().get()+"\n"+data.getTime()+"\n"+data.detailProperty().get();
 		sLabel.setText(str);
 		
 		String colStr = new PDReadWrite().findColor(data.packageSelectProperty().get());
 		var rx = "0x";
    	if(colStr.contains(rx)) {
    		colStr = colStr.replaceAll("0x","#");
    	}
 		sLabel.setStyle("-fx-text-fill: #006464; -fx-background-color: " +colStr+";");
 		
 		sLabel.setOnMouseClicked(event -> {
			try {
				titleOfLabel = data.titleProperty();
				startTime = data.startTimeProperty();
				finishTime = data.finishTimeProperty();
				showEditWindow();
				
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}); 		
 		aPane.getChildren().add(sLabel);
 		aPane.setTopAnchor(sLabel, stNum);
 		sLabel.setPrefHeight(tNum);
	}
	
	void showEditWindow() throws IOException {
    	try {
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ScheduleEdit.fxml"));
    		AnchorPane root = (AnchorPane) fxmlLoader.load();
    		Scene scene = new Scene(root);
    		stage = new Stage();
    		stage.setScene(scene);
    		stage.showAndWait();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }


}
