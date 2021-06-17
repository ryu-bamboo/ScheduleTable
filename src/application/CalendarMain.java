package application;

import java.io.IOException;

import calendar.CalendarController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import serialize.PDReadWrite;
import serialize.SDReadWrite;

public class CalendarMain extends Application {
	public static CalendarController cController;

	@Override
	public void start(Stage primaryStage) throws Exception {
		new PDReadWrite().read();
		new SDReadWrite().read();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/calendar/CalendarIndividual.fxml"));
		Scene scene = new Scene((BorderPane)fxmlLoader.load(),700,600);	
		cController = fxmlLoader.getController();
		scene.getStylesheets().add(getClass().getResource("/calendar/Calendar.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		
		primaryStage.showingProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue == true && newValue == false) {
                try {
                	new PDReadWrite().write();
					new SDReadWrite().write();
				} catch (IOException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
            }
        });
	}

	public static void main(String[] args) {
		launch();
	}

}
