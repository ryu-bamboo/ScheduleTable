package calendar.toDo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dataClass.ScheduleData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import serialize.PDReadWrite;
import serialize.SDReadWrite;

public class ToDoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<ScheduleData, LocalDate> date;

    @FXML
    private TableColumn<ScheduleData, String> toDo;
    
    @FXML
    private CheckBox hudan;

    @FXML
    private CheckBox mukasi;

    @FXML
    private VBox packages;

    @FXML
    private TableView<ScheduleData> table;
    
    @FXML
    private ScrollPane sp;
    
    @FXML
    private VBox vb;
    
    private ObservableList<ScheduleData> oList;

   	
   	@FXML
    void reset(MouseEvent event) throws FileNotFoundException, ClassNotFoundException, IOException {
   		oList.clear();
   		oList.addAll(SDReadWrite.findByDateCompare(LocalDate.now()));
    }
   	
   	

    @FXML
    void initialize() throws FileNotFoundException, ClassNotFoundException, IOException, URISyntaxException {
    	//表に追加
    	oList = FXCollections.observableArrayList();
    	for(ScheduleData data :SDReadWrite.findByDateCompare(LocalDate.now())) {
    		oList.add(data);
    	}   	
        date.setCellValueFactory(x -> x.getValue().dateProperty());
        toDo.setCellValueFactory(x -> x.getValue().titleProperty());
        table.itemsProperty().setValue(oList);       
        ArrayList<String> packList = new ArrayList<String>();
        packList.addAll(new PDReadWrite().findPack());
        for(String data :packList) {
        	Button button = new Button(data);
        	button.setOnAction(new EventHandler<ActionEvent>() {				
				@Override
				public void handle(ActionEvent var1) {
					try {
						choice(data);
					} catch (FileNotFoundException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					} catch (IOException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					}					
				}
			});
        	packages.getChildren().add(button);
        }
        table.setEditable(true);
        

    }
    
    private void choice(String pack) throws FileNotFoundException, ClassNotFoundException, IOException {
    	oList.clear();
    	if(mukasi.selectedProperty().get()) {
    		for(ScheduleData data :SDReadWrite.findByPackage(pack)) {
        		oList.add(data);
        	} 
    	}else {
    		for(ScheduleData data :SDReadWrite.findByPackageFuture(pack,LocalDate.now())) {
        		oList.add(data);
        	} 
    	}
    }
}
