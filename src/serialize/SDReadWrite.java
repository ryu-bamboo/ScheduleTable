package serialize;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;

import dataClass.ScheduleData;

public class SDReadWrite {
	
	static File file = new File("C:/Users/user/scheFiles/ScheduleFile.txt");
	public static ArrayList<ScheduleData> SDList = new  ArrayList<ScheduleData>();
	
	public SDReadWrite()  {
		try {
			Files.createFile(file.toPath());
		} catch (IOException e) {
			return;
		}
	}
	
	public  void write() throws FileNotFoundException, IOException {
		
		if(SDList != null) {
			for(var data:SDList) {
				data.writePre();
			}
			try(var out = new ObjectOutputStream(new FileOutputStream(file))){
				out.writeObject(SDList);
				out.close();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void read() {
		try(var in = new ObjectInputStream(new FileInputStream(file))){
			SDList  = (ArrayList<ScheduleData>)in.readObject();
			in.close();
			for(var data: SDList) {
				data.init();
			}
			
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
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
	
	public  ArrayList<ScheduleData> findByDateCompare(LocalDate ld) throws FileNotFoundException, ClassNotFoundException, IOException {
		var dList = new ArrayList<ScheduleData>();
		for(var data :SDList) {
			if(data.dateProperty().getValue().isAfter(ld)) {
				dList.add(data);
			}
		}
		return dList;
	}
	
	public  ArrayList<ScheduleData> findByPackage(String pack) throws FileNotFoundException, ClassNotFoundException, IOException {
		var dList = new ArrayList<ScheduleData>();
		for(var data :SDList) {
			if(data.packageSelectProperty().getValue().equals(pack)) {
				dList.add(data);
			}
		}
		return dList;
	}
	
	public ArrayList<ScheduleData> findByPackageFuture(String pack,LocalDate ld) throws FileNotFoundException, ClassNotFoundException, IOException {
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
	
	public void delete(LocalDate ld, String title) {
		for(var data:SDList) {
			if(data.dateProperty().getValue().equals(ld)) {
				if(data.titleProperty().getValue().equals(title)) {
					SDList.remove(data);
					break;
				}
			}
		}
	}
	
	public void update(LocalDate oldD, String oldT, ScheduleData newData) {
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
