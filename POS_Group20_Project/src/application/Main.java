package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;



public class Main extends Application {
	private Stage primaryStage;
	private Pane root;
	private ObservableList<Item> orderData = FXCollections.observableArrayList();
	
	public Main(){
		orderData.add(new Item("Jellied Eels", 20.0));
		orderData.add(new Item("Takoyaki", 15.0));
	}
	
	@Override
	public void start(Stage primaryStage){
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Du Poisson");
		initRoot();
	}
	/*
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = (Pane)FXMLLoader.load(getClass().getResource("PosDuPossin.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	*/
	
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
	 * Opens receipt pane
	 */
	public void showReceipt(){
		
	}
	
	public ObservableList<Item> getOrderData(){
		return orderData;
	}
	
	public Stage getPrimaryStage(){
		return primaryStage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
