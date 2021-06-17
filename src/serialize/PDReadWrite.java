package serialize;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import dataClass.PackAndColorData;

public class PDReadWrite {
	static File file = new File("C:/Users/user/scheFiles/PackageFile.txt");
	public static ArrayList<PackAndColorData> packList = new  ArrayList<PackAndColorData>();
	
	public PDReadWrite()  {
		try {
			Files.createDirectories(Paths.get("C:/users/user/scheFiles"));
			Files.createFile(file.toPath());
			Files.createFile(Paths.get("C:/users/user/scheFiles/PackageFile.txt"));
			Files.createFile(Paths.get("C:/Users/user/scheFiles/calendarImage.txt"));
			Files.createFile(Paths.get("C:/Users/user/scheFiles/scheduleImage.txt"));
		} catch (IOException e) {
			return;
		}
	}
	
	
	// write a StringProperty to ObjectOutputStream
	
	
	public void write() throws FileNotFoundException, IOException {
		
		if(packList!=null) {
			for(var data :packList) {
				data.writePre();
			}
			try(var out = new ObjectOutputStream(new FileOutputStream(file))){			
				out.writeObject(packList);
				out.close();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void read() throws URISyntaxException  {
		try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))){			
			packList = (ArrayList<PackAndColorData>)in.readObject();
			in.close();
			for(var data: packList) {
				data.init();
			}
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
	}
	
	public void setColor(String pack, String col) {
		var judge = false;
		for(var data:packList) {
			if(data.packPriperty().getValue().equals(pack)) {
				var rx = "0x";
		    	if(col.contains(rx)) {
		    		col = col.replaceAll("0x","#");
		    		}
		    	packList.remove(data);
		    	judge = true;
		    	break;
			}
		}
		if(judge) {
			packList.add(new PackAndColorData(pack, col));
		}
	}
	
	public void update(String mae,String ato) {
		for(var data:packList) {
			if(data.packPriperty().getValue().equals(mae)) {
				packList.remove(data);
				packList.add(new PackAndColorData(ato, data.colorPriperty().getValue()));
				break;
			}
		}
	}
	
	public ArrayList<String> findPack(){
		var pList = new ArrayList<String>();
		for(var data:packList) {
			pList.add(data.packPriperty().getValue());
		}
		return pList;
	}
	
	public String findColor(String pack){
		String str = null;
		for(var data:packList) {
			if(data.packPriperty().getValue().equals(pack)) {
				str = data.colorPriperty().getValue();
				break;
			}
		}
		return str;
	}
	
	public boolean included(String pack) {
		for(var data:packList) {
			if(data.packPriperty().getValue().equals(pack)) {
				return true;
			}
		}
		return false;
	}
	
	public  void insert(String pack) {
		packList.add(new PackAndColorData(pack, null));
	}
	
	public void delete(String pack) {
		for(var data:packList) {
			if(data.packPriperty().getValue().equals(pack)) {
				packList.remove(data);
				break;
			}
		}
		
		
	}
}