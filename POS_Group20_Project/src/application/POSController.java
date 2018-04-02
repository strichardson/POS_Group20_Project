package application;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
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
	
	/**
	 * Constructor - Called before initialize() method
	 */
	public POSController(){
	}
	
	/**
	 * Initializes controller class - called after fxml file has been loaded
	 */
	@FXML
	private void initialize(){
		itemColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("item"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Item, Number>("price"));
	}
	
	/**
	 * Called by Main application to give a reference back to itself
	 * @param mainApp
	 */
	public void setMainApp(Main mainApp){
		this.mainApp = mainApp;
		orderTable.setItems(mainApp.getOrderData());
	}
	
	/**
	 * Called when user presses the total button.
	 */
	@FXML
	private double handleTotal(){
		if(!orderTable.getItems().isEmpty()){
		ArrayList<Number> priceData = new ArrayList<>();
		DecimalFormat df2 = new DecimalFormat("#.00");	// Keep numbers rounded to 2 decimal places
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
		
		// Set the text fields to the calculated values
		subtotalField.setText(df2.format(subtotal));
		taxField.setText(df2.format(tax));
		totalField.setText(df2.format(total));
		
		return total;
		}
		else{
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Empty Order");
            alert.setHeaderText("No Items in Order");
            alert.setContentText("Please add something to the order.");
            alert.showAndWait();
            return 0;
		}
	}
	
	/**
	 * Called when user presses the delete button.
	 */
	@FXML
	private void handleDeleteItem(){
		int itemIndex = orderTable.getSelectionModel().getSelectedIndex();
		if(itemIndex >= 0){
			// Delete selected item and recalculate total
			orderTable.getItems().remove(itemIndex);
			handleTotal();
		}
		// Alert window is shown when no item in table is selected
		else{
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Item Selected");
            alert.setContentText("Please select an item to delete.");
            alert.showAndWait();
		}
	}
	
	/**
	 * Called when user presses the reset button.
	 */
	@FXML
	private void handleReset(){
		if(!orderTable.getItems().isEmpty()){
			// Clear table and all total fields
			orderTable.getItems().clear();
			subtotalField.clear();
			taxField.clear();
			totalField.clear();
		}
		// Shows alert window if table is already empty
		else{
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("Table Empty");
			alert.setHeaderText("Table is Empty");
			alert.setContentText("Table is already empty.");
			alert.showAndWait();
		}
	}
	
	/**
	 * Called when user presses receipt button.
	 * Opens new window to show receipt.
	 */
	@FXML
	private void handleReceipt(){
		if(!orderTable.getItems().isEmpty()){
			double total;
			total = handleTotal();
			mainApp.showReceiptPane(orderTable, total);
		}
		// Show alert window if nothing is in the order table
		else{
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("Nothing to Print");
			alert.setHeaderText("Table is Empty");
			alert.setContentText("There is nothing in the table to print.");
			alert.showAndWait();
		}
	}
}
