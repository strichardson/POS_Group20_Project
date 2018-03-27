package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;



public class Main extends Application {
	private ObservableList<Item> orderData = FXCollections.observableArrayList();
	
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
	
	public Main(){
		orderData.add(new Item("Jellied Eels", 20.0));
		orderData.add(new Item("Takoyaki", 15.0));
	}
	
	
	public ObservableList<Item> getOrderData(){
		return orderData;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
