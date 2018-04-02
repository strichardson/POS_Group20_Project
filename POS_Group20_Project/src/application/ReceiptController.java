package application;

import java.text.DecimalFormat;

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
	
	/**
	 * Print ordered items to a new receipt window and display total
	 * @param orders
	 * @param total
	 */
	public void printOrder(TableView<Item> orders, double total) {
        itemColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("item"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Item, Number>("price"));
        
        for(int i = 0; i < orders.getItems().size(); i++){
        	receiptTable.getItems().add(orders.getItems().get(i));
        }
        
        DecimalFormat df2 = new DecimalFormat("#.00");
        totalField.setText(df2.format(total));
    }
}
