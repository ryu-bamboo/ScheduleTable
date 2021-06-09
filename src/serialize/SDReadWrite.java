package serialize;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;

import dataClass.ScheduleData;

public class SDReadWrite {
	public static ArrayList<ScheduleData> SDList;
	static File file;
	
	public SDReadWrite() throws URISyntaxException {
		final URL url = this.getClass().getResource("ScheduleFile.txt");
		file = new File(url.toURI());
	}
	
	public  void write(ScheduleData sd) throws FileNotFoundException, IOException {
		try(var out = new ObjectOutputStream(new FileOutputStream(file))){
			for(var data:SDList) {
				out.writeObject(data);
			}
		}
	}
	
	public ArrayList<ScheduleData> read() throws FileNotFoundException, IOException, ClassNotFoundException {
		try(var in = new ObjectInputStream(new FileInputStream(file))){
			SDList = new ArrayList<ScheduleData>();
			
			while (true) {
		        ScheduleData sd = (ScheduleData) in.readObject();
		        SDList.add(sd);
		        if(sd.equals(null)) {
		        	break;
		        }
			}
			return SDList;
		}
	}
	
	public ArrayList<ScheduleData> findByDate(LocalDate ld) throws FileNotFoundException, ClassNotFoundException, IOException {
		var dList = new ArrayList<ScheduleData>();
		if(SDList == null) {
			return null;
		}
		for(var data :SDList) {
			if(data.dateProperty().getValue().equals(ld)) {
				dList.add(data);
			}
		}
		return dList;
	}
	
	public static ArrayList<ScheduleData> findByDateCompare(LocalDate ld) throws FileNotFoundException, ClassNotFoundException, IOException {
		var dList = new ArrayList<ScheduleData>();
		for(var data :SDList) {
			if(data.dateProperty().getValue().isAfter(ld)) {
				dList.add(data);
			}
		}
		return dList;
	}
	
	public static ArrayList<ScheduleData> findByPackage(String pack) throws FileNotFoundException, ClassNotFoundException, IOException {
		var dList = new ArrayList<ScheduleData>();
		for(var data :SDList) {
			if(data.packageSelectProperty().getValue().equals(pack)) {
				dList.add(data);
			}
		}
		return dList;
	}
	
	public static ArrayList<ScheduleData> findByPackageFuture(String pack,LocalDate ld) throws FileNotFoundException, ClassNotFoundException, IOException {
		var dList = new ArrayList<ScheduleData>();
		for(var data :SDList) {
			if(data.packageSelectProperty().getValue().equals(pack)) {
				if(data.dateProperty().getValue().isAfter(ld)) {
					dList.add(data);
				}
			}
		}
		return dList;
	}
	
	public static void delete(LocalDate ld, String title) {
		for(var data:SDList) {
			if(data.dateProperty().getValue().equals(ld)) {
				if(data.titleProperty().getValue().equals(title)) {
					SDList.remove(data);
				}
			}
		}
	}
	
	public static void update(LocalDate oldD, String oldT, ScheduleData newData) {
		for(var data:SDList) {
			if(data.dateProperty().getValue().equals(oldD)) {
				if(data.titleProperty().getValue().equals(oldT)) {
					SDList.remove(data);
					SDList.add(newData);
				}
			}
		}
	}
	
	public static void insert(ScheduleData sd) {
		SDList.add(sd);
	}
}
