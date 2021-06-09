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
import java.util.ArrayList;

import dataClass.PackAndColorData;

public class PDReadWrite {
	static File file;
	public static ArrayList<PackAndColorData> packList;
	
	public PDReadWrite() throws URISyntaxException {
		final URL url = this.getClass().getResource("PackageFile.txt");
		file = new File(url.toURI());
	}
	
	
	
	public  static void write() throws FileNotFoundException, IOException {
		try(var out = new ObjectOutputStream(new FileOutputStream(file))){
			for(var data:packList) {
				out.writeObject(data);
			}
		}
	}
	
	public ArrayList<PackAndColorData> read() throws FileNotFoundException, IOException, ClassNotFoundException {
		try(var in = new ObjectInputStream(new FileInputStream(file))){
			packList = new ArrayList<PackAndColorData>();
			
			while (true) {
		        var pd = (PackAndColorData) in.readObject();
		        packList.add(pd);
		        if(pd.equals(null)) {
		        	break;
		        }
			}
			return packList;
		}
	}
	
	public void setColor(String pack, String col) {
		for(var data:packList) {
			if(data.packPriperty().getValue().equals(pack)) {
				packList.remove(data);
				packList.add(new PackAndColorData(pack, col));
			}
		}
	}
	
	public void update(String mae,String ato) {
		for(var data:packList) {
			if(data.packPriperty().getValue().equals(mae)) {
				packList.remove(data);
				packList.add(new PackAndColorData(ato, data.colorPriperty().getValue()));
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
			}
		}
	}
}