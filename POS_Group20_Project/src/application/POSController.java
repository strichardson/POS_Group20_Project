package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;

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
	private Button btnAddItem;
	@FXML
	private Button exitApp;
	@FXML
	private ComboBox<String> menuItemsBox;
	private Connection conn;
	private Statement stm;
	private ResultSet rs;
	
	//populates the combo box
	
	ObservableList<String> menuItemList = FXCollections.observableArrayList();
			
						
	
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
		/** connection to database **/
        try {
			conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\smart\\Desktop\\project\\POS_Group20_Project\\databases.db");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Something went wrong: " + e.getMessage());
		}
		menuItemsBox.setValue("--Select Items--");
		fillComboBox();	
		menuItemsBox.setItems(menuItemList);
		
		
		
	}
	
	/**
	 * Called by Main application to give a reference back to itself
	 * @param mainApp
	 */
	public void setMainApp(Main mainApp){
		this.mainApp = mainApp;
		orderTable.setItems(mainApp.getOrderData());
	}
	
	/** Fill combo box
	 * 
	 */
	public void fillComboBox(){
		/* select query connects to database and populates the combo box*/
			
		String query = "select productDescr from poisson_price";
		
		try {
			conn.createStatement();
			stm =conn.createStatement();
			rs = stm.executeQuery(query);
			while (rs.next()){
				menuItemList.add(rs.getString("productDescr"));
			}
			stm.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Something went wrong: " + e.getMessage());
		}
	}
	
	/**
	 * Called when user presses the total button.
	 */
	@FXML
	private void handleTotal(){
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
		}
		else{
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Empty Order");
            alert.setHeaderText("No Items in Order");
            alert.setContentText("Please add something to the order.");
            alert.showAndWait();
		}
	}
	
	/**
	 * Called when user presses the add button
	 * Add selected items from menu to the order table
	 */
	@FXML
	private void handleAddItem(){
		String itemName = menuItemsBox.getSelectionModel().getSelectedItem();
		if(itemName != "--Select Items--"){
			String query = "select productPrice from poisson_price where productDescr = '" + itemName + "'";
			
			try {
				conn.createStatement();
				stm =conn.createStatement();
				rs = stm.executeQuery(query);
				Item newItem = new Item(itemName, rs.getDouble("productPrice"));
				orderTable.getItems().add(newItem);
				
				stm.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Something went wrong: " + e.getMessage());
			}
				
			
		}
		else{
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Item Selected");
            alert.setContentText("Please select an item from the list to add.");
            alert.showAndWait();
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
			mainApp.showReceiptPane(orderTable);
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
	
	/**
	 * Called when user presses the exit button.
	 * Gives the option of exiting the program
	 */
	public void exitProgram(){
		// Show exit confirmation window
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirm Exit Application");
		alert.setHeaderText("Confirm Exit Application");
		alert.setContentText("Are you sure you want to exit the application?");

		Optional<ButtonType> result = alert.showAndWait();
		// Exit program if user presses ok
		if (result.get() == ButtonType.OK){
			Platform.exit();
		}
	}
}