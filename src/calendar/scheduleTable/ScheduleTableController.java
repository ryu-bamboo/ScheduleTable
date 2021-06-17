/**
 * Sample Skeleton for 'ScheduleIndividual.fxml' Controller Class
 */

package calendar.scheduleTable;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

import application.CalendarMain;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ScheduleTableController  {

	static LocalDate ld;
	static ScheduleSelectController ssController;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="pane"
    private VBox pane; // Value injected by FXMLLoader
    
    @FXML // fx:id="aPane"
    public static AnchorPane aPane; // Value injected by FXMLLoader
    
    @FXML // fx:id="aPane"
    private VBox scheduleIndividual; // Value injected by FXMLLoader
    
    @FXML 
    private Label hizuke;
    
    @FXML 
    private AnchorPane ap;
    
    @FXML
    private Label image;
    
    @FXML
    void sets(MouseEvent event) {
    	try {
			showSecondWindow();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
   
    
    void showSecondWindow() throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ScheduleSelect.fxml"));
		AnchorPane root = (AnchorPane) fxmlLoader.load();
		ssController = fxmlLoader.getController();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.showAndWait();
    }
    
	public AnchorPane getaPane() {
		return aPane;
	}



	@SuppressWarnings("static-access")
	@FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws IOException, URISyntaxException {
   		ld = CalendarMain.cController.ld;
   		hizuke.setText(ld.format(DateTimeFormatter.ofPattern("M月d日E曜日",Locale.JAPAN)));
   		
   		var str = "";  	  
  	  try(var reader = Files.newBufferedReader(
  			  Paths.get("C:/Users/user/scheFiles/calendarImage.txt"))){
  		  var line = "";
  		  while((line = reader.readLine()) != null) {
  			  str = line;
  		  }
  	  }
  	  
  	  	var file = new File(str);
   		Image img = new Image(file.toURI().toString(),100,0,true,false);
   		
   		var bimg = new BackgroundImage(img, null, null, null, null);
   		var bg = new Background(bimg);
   		image.setBackground(bg);
        
       
    }
	
}
