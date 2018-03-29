package application;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Item {
	private StringProperty item;
	private DoubleProperty price;
	
	public Item(){
		this(null, 0.0);
	}
	
	public Item(String item, double price){
		this.item = new SimpleStringProperty(item);
		this.price = new SimpleDoubleProperty(price);
	}
	
	public String getItem(){
		return item.get();
	}
	public Double getPrice(){
		return price.get();
	}
	
	public void setItemProperty(String item){
		this.item.set(item);
	}
	public StringProperty itemProperty(){
		return item;
	}
	
	public void setPriceProperty(double price){
		this.price.set(price);
	}
	public DoubleProperty priceProperty(){
		return price;
	}
	
	@Override
	public String toString(){
		return item.get() + "\t\t $" + price.get();
	}
}
