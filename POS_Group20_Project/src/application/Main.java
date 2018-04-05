//COIS 2240_Project_Group20
//Authors: Maggie Kikkert, Stednisha Richardson, Micheala Palmer, Jason McNab
//Date: April 6th, 2018

package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	private Stage primaryStage;
	private Pane root;
	public Connection conn;
	Statement statement;
	
	
	
	private ObservableList<Item> orderData = FXCollections.observableArrayList();
	
	/**
	 * Constructor
	 */
	public Main(){
	}
	
	
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
			// Load FXML file and root layout
			FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("PosDuPossin.fxml"));
            root = loader.load();

            // Give controller access to main application
            POSController controller = loader.getController();
            controller.setMainApp(this);
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Opens new window to show order receipt
	 * @param orders
	 */
	public void showReceiptPane(TableView<Item> orders){
		try{
			// Load fxml file and receipt pane layout
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("ReceiptPane.fxml"));
			AnchorPane pane = (AnchorPane) loader.load();
			
			// Create dialogue stage
			Stage dialogueStage = new Stage();
			dialogueStage.setTitle("Receipt");
			dialogueStage.initModality(Modality.NONE);
			dialogueStage.initOwner(primaryStage);
			Scene scene = new Scene(pane);
			dialogueStage.setScene(scene);
			
			// Set controller
			ReceiptController controller = loader.getController();
			controller.printOrder(orders);
			
			// Show receipt window
			dialogueStage.showAndWait();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns observable list of Items
	 * @return
	 */
	public ObservableList<Item> getOrderData(){
		return orderData;
	}
	
	/**
	 * Returns the primary stage for the application
	 * @return
	 */
	public Stage getPrimaryStage(){
		return primaryStage;
	}
	
	public static void main(String[] args) {
		launch(args); }
			

}
