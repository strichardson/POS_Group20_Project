package application;

import java.text.DecimalFormat;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

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
	@FXML
	private ComboBox<String> menuItemsBox;
	
	
	// Reference to main application
	private Main mainApp;
	
	/**
	 * Constructor - Called before initialize() method
	 */
	public POSController(){
		
	}
	
	/**
	 * Initializes controller class - called once fxml file has been loaded
	 */
	@FXML
	private void initialize(){
		itemColumn.setCellValueFactory(cellData -> cellData.getValue().itemProperty());
		priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
	
	}

	
	// called by Main application to give a reference back to itself
/*	public void setMainApp(Main mainApp){
		this.mainApp = mainApp;
		orderTable.setItems(mainApp.getmenuItemBox());
	}
	*/
	@FXML
	private void handleMenuBox(){
		ObservableList<String> menuItemList = FXCollections
				.observableArrayList("snake", "cat", "rat");
		
		menuItemsBox.setValue("--Select Items--");
		menuItemsBox.setItems(menuItemList);
	}
	@FXML
	private void handleTotal(){
		ArrayList<Number> priceData = new ArrayList<>();
		DecimalFormat df2 = new DecimalFormat("#.00");
		double subtotal = 0.0, tax = 0.0, total = 0.0;
		
		// Calculating sub-total - store all price values in an array list
		for(Item item : orderTable.getItems()){
			priceData.add(priceColumn.getCellObservableValue(item).getValue());
		}
		// Add together prices in the array list
		for(int i = 0; i < priceData.size(); i++){
			subtotal += (double) priceData.get(i);
		}
		
		// Calculating tax
		tax = subtotal * 0.13;
		
		// Calculating total
		total = subtotal + tax;
		
		subtotalField.setText(df2.format(subtotal));
		taxField.setText(df2.format(tax));
		totalField.setText(df2.format(total));
	}
	
	@FXML
	private void handleDeleteItem(){
		int itemIndex = orderTable.getSelectionModel().getSelectedIndex();
		if(itemIndex >= 0){
			orderTable.getItems().remove(itemIndex);
			handleTotal();
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
		if(!orderTable.getItems().isEmpty()){
			orderTable.getItems().clear();
			subtotalField.clear();
			taxField.clear();
			totalField.clear();
		}
		else{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("Table Empty");
			alert.setHeaderText("Table is Empty");
			alert.setContentText("Table is already empty.");
			alert.showAndWait();
		}
	}
	
	@FXML
	private void handleReceipt(){
		if(!orderTable.getItems().isEmpty()){
			mainApp.showReceipt(orderTable);
		}
		else{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("Table Empty");
			alert.setHeaderText("Table is Empty");
			alert.setContentText("Table is already empty.");
			alert.showAndWait();
		}
	}
	
	@FXML
	private void handleItemPrint(){
		
	}
}

//@FXML 
//private void handle