package dataClass;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


//DTOクラス

public class ScheduleData implements Serializable{
	private static final long serialVersionID = 1L;
	
    private StringProperty title = new SimpleStringProperty();
    private String time = new String();
    private StringProperty detail = new SimpleStringProperty();
    private ObjectProperty<LocalTime> startTime = new SimpleObjectProperty<LocalTime>();
    private ObjectProperty<LocalTime> finishTime = new SimpleObjectProperty<LocalTime>();
    private ObjectProperty<LocalDate> date = new SimpleObjectProperty<LocalDate>();
    private StringProperty packageSelect = new SimpleStringProperty();
    
    /**
     * コンストラクタ.
     * @param year 年
     * @param month 月
     * @param date 日
     * @param title スケジュールのタイトル
     * @param startTime 開始時間
     * @param finishTime 終了時刻
     * @param detail 詳細
     * @param packageSelect パッケージ
     */
    public ScheduleData(LocalDate ld,String title,LocalTime startTime, LocalTime finishTime, String detail,String packageSelect) {
    	this.date.set(ld);
    	this.title.set(title);    	
    	this.setTime(startTime+"～"+finishTime);
        this.detail.set(detail);
        this.startTime.set(startTime);
        this.finishTime.set(finishTime);
        this.packageSelect.set(packageSelect);
    }


   
    
    
    
	public void setTitle(StringProperty title) {
		this.title = title;
	}
	
	public StringProperty titleProperty() {
		return title;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String string) {
		this.time = string;
	}

	public void setDetail(StringProperty detail) {
		this.detail = detail;
	}
	
	public StringProperty detailProperty() {
		return detail;
	}

	public void setStartTime(ObjectProperty<LocalTime> startTime) {
		this.startTime = startTime;
	}
	
	public ObjectProperty<LocalTime> startTimeProperty() {
		return startTime;
	}

	public void setFinishTime(ObjectProperty<LocalTime> finishTime) {
		this.finishTime = finishTime;
	}
	
	public ObjectProperty<LocalTime> finishTimeProperty() {
		return finishTime;
	}

	public void setDate(ObjectProperty<LocalDate> date) {
		this.date = date;
	}

	public ObjectProperty<LocalDate> dateProperty() {
		return date;
	}
	
	public void setPackageSelect(StringProperty packageSelect) {
		this.packageSelect = packageSelect;
	}
	
	public StringProperty packageSelectProperty() {
		return packageSelect;
	}

	
    
}