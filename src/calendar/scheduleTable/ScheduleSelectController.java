package calendar.scheduleTable;

import java.io.IOException;
import java.net.URISyntaxException;

import application.CalendarMain;
import calendar.CalendarController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import serialize.PDReadWrite;
import serialize.SDReadWrite;

public class ScheduleSelectController {
	

	 	@FXML // fx:id="scheduleName"
	    private TextField scheduleName; // Value injected by FXMLLoader

	    @FXML // fx:id="sHour"
	    private ComboBox<String> sHour; // Value injected by FXMLLoader

	    @FXML // fx:id="sakusei"
	    private Button sakusei; // Value injected by FXMLLoader

	    @FXML // fx:id="packageSelect"
	    private ComboBox<String> packageSelect; // Value injected by FXMLLoader

	    @FXML // fx:id="month"
	    private ComboBox<String> month; // Value injected by FXMLLoader

	    @FXML // fx:id="scheduleSelct"
	    private AnchorPane scheduleSelect; // Value injected by FXMLLoader

	    @FXML // fx:id="year"
	    private ComboBox<String> year; // Value injected by FXMLLoader

	    @FXML // fx:id="fHour"
	    private ComboBox<String> fHour; // Value injected by FXMLLoader

	    @FXML // fx:id="fMinute"
	    private ComboBox<String> fMinute; // Value injected by FXMLLoader

	    @FXML // fx:id="memo"
	    private TextArea memo; // Value injected by FXMLLoader

	    @FXML // fx:id="sMinute"
	    private ComboBox<String> sMinute; // Value injected by FXMLLoader

	    @FXML // fx:id="day"
	    private ComboBox<String> day; // Value injected by FXMLLoader
	    
	    
		CreateDataAndLabel adal = new AddDataAndLabel();
	    

	    
	    
	    @FXML
	    void jikkou(MouseEvent event) throws URISyntaxException {
	    	var editData = adal.addData(packageSelect, year, month, day, scheduleName, sHour, sMinute, fHour, fMinute, scheduleSelect, memo);
	    	adal.createScheduleLabel(editData, CalendarController.stController.getaPane());
	    	SDReadWrite.insert(editData);
	    	new PDReadWrite().insert(editData.packageSelectProperty().get());
	    	new PDReadWrite().setColor(editData.packageSelectProperty().getValue(), "#FEFEFE");
	    	System.out.println("追加しました。");
	    	
	    }
	    
	   
	    
	    @SuppressWarnings("static-access")
		@FXML private void initialize() throws IOException, URISyntaxException {
	    	var ld = CalendarMain.cController.ld;
	    	year.setValue(Integer.toString(ld.getYear()));
	    	month.setValue(Integer.toString(ld.getMonthValue()));
	    	day.setValue(Integer.toString(ld.getDayOfMonth()));
	    	ObservableList<String> pItems = FXCollections.observableArrayList();
	    	pItems.addAll(new PDReadWrite().findPack());
	    	packageSelect.setItems(pItems);
	    	
	    	ObservableList<String> hItems = FXCollections.observableArrayList();
	    	for(int i=0;i<24;i++) {
	    		hItems.add(Integer.toString(i));
	    	}
	    	ObservableList<String> mItems = FXCollections.observableArrayList();
	    	for(int i=0;i<60;i++) {
	    		mItems.add(Integer.toString(i));
	    	}
	    	sHour.setItems(hItems);
	    	sMinute.setItems(mItems);
	    	fHour.setItems(hItems);
	    	fMinute.setItems(mItems);
		}
	   
}
