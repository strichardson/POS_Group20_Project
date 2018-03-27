package application;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class POSController {
	@FXML
	private TableView<Item> orderTable;
	@FXML
	private TableColumn<Item, String> itemColumn;
	@FXML
	private TableColumn<Item, Number> priceColumn;
	@FXML
	private TextField subtotalField;
	@FXML
	private TextField taxField;
	@FXML
	private TextField totalField;
	
	
	// Reference to main application
	private Main mainApp;
	
	// Constructor
	public POSController(){
	}
	
	@FXML
	private void initialize(){
		itemColumn.setCellValueFactory(cellData -> cellData.getValue().itemProperty());
		priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
	}
	
	// called by Main application to give a reference back to itself
	public void setMainApp(Main mainApp){
		this.mainApp = mainApp;
		orderTable.setItems(mainApp.getOrderData());
	}
	
	@FXML
	private void handleTotal(){
		List<Number> priceData = new ArrayList<>();
		int itemIndex = orderTable.getSelectionModel().getSelectedIndex();
		double total = 0.0;
		for(Item item : orderTable.getItems()){
			priceData.add(priceColumn.getCellObservableValue(item).getValue());
		}
		
	}
	
	@FXML
	private void handleDeleteItem(){
		int itemIndex = orderTable.getSelectionModel().getSelectedIndex();
		if(itemIndex >= 0){
			orderTable.getItems().remove(itemIndex);
		}
		else{
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Item Selected");
            alert.setContentText("Please select an item to delete.");
            alert.showAndWait();
		}
	}
	
	@FXML
	private void handleReset(){
		orderTable.getItems().clear();
	}
	
	@FXML
	private void handleReceipt(){
		
		
	}
}
