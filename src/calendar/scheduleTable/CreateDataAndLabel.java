package calendar.scheduleTable;

import java.net.URISyntaxException;

import dataClass.ScheduleData;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public interface CreateDataAndLabel {
	ScheduleData addData(ComboBox<String> packageSelect,ComboBox<String> year,ComboBox<String> month,ComboBox<String> day,TextField scheduleName,ComboBox<String> sHour,ComboBox<String> sMinute,ComboBox<String> fHour,ComboBox<String> fMinute,AnchorPane scheduleSelect,TextArea memo);
	void createScheduleLabel(ScheduleData sd,AnchorPane aPane) throws URISyntaxException;
}
