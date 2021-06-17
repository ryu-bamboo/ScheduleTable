package calendar.packageChange;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import serialize.PDReadWrite;

public class PackageEditController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField sinki;

    @FXML
    private ListView<String> list;
    
 
    @FXML
    private TextField ato;

    @FXML
    private Label moto;
    
    


    
    private ObservableList<String> oList = FXCollections.observableArrayList();    

    @FXML
    void tuika(MouseEvent event) throws URISyntaxException {
    	if(!new PDReadWrite().included(sinki.getText())) {
    		new PDReadWrite().insert(sinki.getText());
        	new PDReadWrite().setColor(sinki.getText(), "#FEFEFE");
        	oList.add(sinki.getText());
        	sinki.clear();
    	}
    }
    
    @FXML
    void enter(KeyEvent e) {
    	switch(e.getCode()) {
		case ENTER:
			if(!new PDReadWrite().included(sinki.getText())) {
	    		new PDReadWrite().insert(sinki.getText());
	        	new PDReadWrite().setColor(sinki.getText(), "#FEFEFE");
	        	oList.add(sinki.getText());
	        	sinki.clear();
	    	}
			break;
		default:
			break;
		}
    }
    
    @FXML
    void execute(MouseEvent event) throws URISyntaxException {
    	if(!ato.getText().equals("\s*")) {
    		if(!new PDReadWrite().included(ato.getText())) {
    			new PDReadWrite().update(moto.getText(), ato.getText());
        		oList.removeAll(moto.getText());
        		oList.addAll(ato.getText());
        		ato.clear();
        		moto.setText("編集するパッケージ");
    		}
    	}else {
    		System.out.println("変更後の名前を入力してください。");
    	}
    }
    

    @FXML
    void sakujo(MouseEvent event) {
    		var alert = new Alert(AlertType.WARNING,"削除する？",ButtonType.OK,ButtonType.CANCEL);
    		alert.showAndWait().ifPresent(response -> {
    			if(response == ButtonType.OK) {
    				new PDReadWrite().delete(moto.getText());
    				oList.removeAll(moto.getText());
    				alert.close();
    			}else {
    				alert.close();
    			}
    		});
    }


    @FXML
    void initialize() throws URISyntaxException {
    	list.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			
			@Override
			public ListCell<String> call(ListView<String> arg0) {
				return new ButtonCell();
			}
		});
    	var datas = new PDReadWrite().findPack();  	
    	for(var data :datas) {
    		oList.add(data);
    	}
    	list.itemsProperty().setValue(oList);
    	
    }
    
    public class ButtonCell extends ListCell<String> {
    	
    	private Button b;
    	
    	public ButtonCell() {
    		initCompornent();
    	}
    	
    	private void initCompornent() {
    		b= new Button();
    	}
    	
    	@Override
		public void  updateItem(String item,boolean empty){
			super.updateItem(item, empty);
			if(empty) {
				setText(null);
				setGraphic(null);
			}else {
				b.setText(item);
				b.setOnAction(n -> {
					var value = getListView().getItems().get(getIndex());
					moto.setText(value);
				});
				setGraphic(b);
			}
		}
    	
    }
    
}
