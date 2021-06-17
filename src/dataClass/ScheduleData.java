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
	
    private transient StringProperty title = new SimpleStringProperty();
    private transient StringProperty time = new SimpleStringProperty();
    private transient StringProperty detail = new SimpleStringProperty();
    private transient ObjectProperty<LocalTime> startTime = new SimpleObjectProperty<LocalTime>();
    private transient ObjectProperty<LocalTime> finishTime = new SimpleObjectProperty<LocalTime>();
    private transient ObjectProperty<LocalDate> date = new SimpleObjectProperty<LocalDate>();
    private transient StringProperty packageSelect = new SimpleStringProperty();
    
    String titleS;
    String timeS;
    String detailS;
    LocalTime startTimeS;
    LocalTime finishTimeS;
    LocalDate dateS;
    String packageSelectS;
    
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
    	this.time.set(startTime+"～"+finishTime);
        this.detail.set(detail);
        this.startTime.set(startTime);
        this.finishTime.set(finishTime);
        this.packageSelect.set(packageSelect);
    }


    public void init() {
		title = new SimpleStringProperty();
		time = new SimpleStringProperty();
		detail = new SimpleStringProperty();
		startTime = new SimpleObjectProperty<LocalTime>();
		finishTime = new SimpleObjectProperty<LocalTime>();
		date = new SimpleObjectProperty<LocalDate>();
		packageSelect = new SimpleStringProperty();
		
		title.set(titleS);
		time.set(timeS);
		detail.set(detailS);
		startTime.set(startTimeS);
		finishTime.set(finishTimeS);
		date.set(dateS);
		packageSelect.set(packageSelectS);
	}
    
    public void writePre() {
		titleS = title.getValue();
		timeS = time.getValue();
		detailS = detail.getValue();
		startTimeS = startTime.getValue();
		finishTimeS = finishTime.getValue();
		dateS = date.getValue();
		packageSelectS = packageSelect.getValue();
	}
    
	public void setTitle(StringProperty title) {
		this.title = title;
	}
	
	public StringProperty titleProperty() {
		return title;
	}

	public StringProperty timeProperty() {
		return time;
	}

	public void setTime(StringProperty string) {
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