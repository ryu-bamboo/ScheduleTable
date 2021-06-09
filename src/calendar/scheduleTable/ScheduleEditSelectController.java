package calendar.scheduleTable;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.regex.Pattern;

import application.CalendarMain;
import calendar.CalendarController;
import dataClass.ScheduleData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import serialize.PDReadWrite;
import serialize.SDReadWrite;

public class ScheduleEditSelectController {

	 @FXML
	    private TextField scheduleName;

	    @FXML
	    private ComboBox<String> oldMonth;

	    @FXML
	    private ComboBox<String> sHour;

	    @FXML
	    private ComboBox<String> packageSelect;

	    @FXML
	    private ComboBox<String> year;

	    @FXML
	    private ComboBox<String> fMinute;

	    @FXML
	    private TextArea memo;

	    @FXML
	    private ComboBox<String> sMinute;

	    @FXML
	    private ComboBox<String> oldYear;

	    @FXML
	    private AnchorPane scheduleSelect;

	    @FXML
	    private ComboBox<String> month;

	    @FXML
	    private ComboBox<String> fHour;

	    @FXML
	    private ComboBox<String> oldName;

	    @FXML
	    private ComboBox<String> day;
	    @FXML
	    private ComboBox<String> oldDay;
		
	    

	    @FXML
	    void edit(MouseEvent event) throws URISyntaxException {
	    	int oldYear = Integer.parseInt(this.oldYear.getValue());
	    	int oldMonth = Integer.parseInt(this.oldMonth.getValue());
	    	int oldDay = Integer.parseInt(this.oldDay.getValue());
	    	String oldName = this.oldName.getValue();
	    	SDReadWrite.update(LocalDate.of(oldYear,oldMonth,oldDay),oldName,addData());
	    	ScheduleEditController.editStage.close();
	    	AddDataAndLabel.stage.close();
	    	new AddDataAndLabel().createScheduleLabel(addData(), CalendarController.stController.getaPane());
	    }
	    
	    ScheduleData addData() {
	    	int year = Integer.parseInt(this.year.getValue());
	    	int month = Integer.parseInt(this.month.getValue());
	    	int date = Integer.parseInt(this.day.getValue());
	    	String packageSelect = this.packageSelect.getValue();
	    	var kuran = "\\s*";
	    	if(Pattern.matches(kuran,scheduleName.getText() )) {
	    		scheduleName.setText(oldName.getValue());
	    	}
	    	String name = scheduleName.getText();
	    	var sTime = LocalTime.of(Integer.parseInt(sHour.getValue()),Integer.parseInt(sMinute.getValue()));
	    	var fTime = LocalTime.of(Integer.parseInt(fHour.getValue()),Integer.parseInt(fMinute.getValue()));
	    	String detail = memo.getText();
	       	var sd = new ScheduleData(LocalDate.of(year, month, date),name, sTime, fTime, detail,packageSelect);
	    	return sd;
	    }
	    
	    @SuppressWarnings("static-access")
		@FXML private void initialize() throws IOException, URISyntaxException {
	    	var ld = CalendarMain.cController.ld;
	    	year.setValue(Integer.toString(ld.getYear()));
	    	oldYear.setValue(Integer.toString(ld.getYear()));
	    	month.setValue(Integer.toString(ld.getMonthValue()));
	    	oldMonth.setValue(Integer.toString(ld.getMonthValue()));
	    	day.setValue(Integer.toString(ld.getDayOfMonth()));
	    	oldDay.setValue(Integer.toString(ld.getDayOfMonth()));
	    	oldName.setValue(AddDataAndLabel.titleOfLabel.get());
	    	sHour.setValue(Integer.toString(AddDataAndLabel.startTime.get().getHour()));
	    	sMinute.setValue(Integer.toString(AddDataAndLabel.startTime.get().getMinute()));
	    	fHour.setValue(Integer.toString(AddDataAndLabel.finishTime.get().getHour()));
	    	fMinute.setValue(Integer.toString(AddDataAndLabel.finishTime.get().getMinute()));
	    	
	    	ObservableList<String> pItems = FXCollections.observableArrayList();
	    	pItems.addAll(new PDReadWrite().findPack());
	    	packageSelect.setItems(pItems);
		}
	    
}