package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class POSController {
	@FXML
	private TableView<Item> orderTable;
	@FXML
	private TableColumn<Item, String> itemColumn;
	@FXML
	private TableColumn<Item, Number> priceColumn;
	
	
	// Reference to main application
	private Main mainApp;
	
	// Constructor
	public POSController(){
	}
	
	@FXML
	private void Initialize(){
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
		orderTable.getItems();
	}
	
	@FXML
	private void handleDeleteItem(){
		int itemIndex = orderTable.getSelectionModel().getSelectedIndex();
		orderTable.getItems().remove(itemIndex);
	}
	
	@FXML
	private void handleReset(){
		orderTable.getItems().clear();
	}
	
	@FXML
	private void handleReceipt(){
		
	}
}
