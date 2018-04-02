package application;

import java.util.List;

import javafx.application.Application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
//import javafx.scene.control.ComboBox;



public class Main extends Application {
	private Stage primaryStage;
	private Pane root;
	
	//private ObservableList<String> menuItemList = FXCollections
		//	.observableArrayList("snake", "cat", "rat");
	
//	private ObservableList<Item> menuItemsList = FXCollections.observableArrayList();
	
	@FXML
	private ComboBox<String> menuItemsBox;
	/**
	 * Constructor
	 */
	
	public Main(){
		// Sample data
		//orderData.add(new Item("Jellied Eels", 50.0));
		//orderData.add(new Item("Takoyaki", 25.0));
		
	}
	
	/**
	 * 
	 */
	@Override
	public void start(Stage primaryStage){
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Du Poisson");
		initRoot();
	}
	
	/**
	 * Initialize root pane
	 */
	public void initRoot(){
		try{
			FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("PosDuPossin.fxml"));
            root = loader.load();

            // Give controller access to main application
            POSController controller = loader.getController();
           // controller.setMainApp(this);
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Opens receipt pane
	 */
	public void showReceipt(TableView<Item> orders){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("ReceiptPane.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Receipt");
			dialogStage.initModality(Modality.NONE);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            /*
            ReceiptController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.printReceipt(orders);
            */
            dialogStage.showAndWait();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//public ObservableList<Item> getOrderData(){
		//return orderData;
	//}
	
	public Stage getPrimaryStage(){
		return primaryStage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
//added last
	/*public ObservableList<Item> getmenuItemBox() {
		// TODO Auto-generated method stub
		return null;
	}
}*/
