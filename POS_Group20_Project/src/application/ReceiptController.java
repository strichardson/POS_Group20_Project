package application;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ReceiptController {
	@FXML
	private TextArea receiptArea;
	
	private TableView<Item> orders;
	public Stage dialogStage;
	
	@FXML
    private void initialize() {
    }
	
	public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
	
	public void printReceipt(TableView<Item> orders){
		this.orders = orders;
		System.out.println("Hello");
	}
}
