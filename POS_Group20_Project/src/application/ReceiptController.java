package application;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ReceiptController {
	@FXML
	private TableView<Item> receiptTable;
	@FXML
	private TableColumn<Item, String> itemColumn;
	@FXML
	private TableColumn<Item, Number> priceColumn;
	@FXML
	private TextField totalField;
	@FXML
	private TextField subtotalField;
	@FXML
	private TextField taxField;
	
	POSController control;
	
	/**
	 * Print ordered items to a new receipt window and display total
	 * @param orders
	 * @param total
	 */
	public void printOrder(TableView<Item> orders) {
        itemColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("item"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Item, Number>("price"));
        
        // Print all ordered items to the receipt table
        for(int i = 0; i < orders.getItems().size(); i++){
        	receiptTable.getItems().add(orders.getItems().get(i));
        }
        
        // Create array list to add up prices
        ArrayList<Number> priceData = new ArrayList<>();
		DecimalFormat df2 = new DecimalFormat("#.00");	// Keep numbers rounded to 2 decimal places
		double subtotal = 0.0, tax = 0.0, total = 0.0;
		
		// Calculating sub-total - store all price values in an array list
		for(Item item : receiptTable.getItems()){
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
}
