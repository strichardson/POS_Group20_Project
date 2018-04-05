# POS_Group20_Project
Project Title: POS Du Poussin
The Point of Sales system that we created will allow users to input information via buttons to create an order list, e.g. Tuna. It enables them to input as many items as they desire.. With the implementation of a database, we can store a certain amount of item and determine The Point of Sales system that we created will allow users to input information via selection of items to create an order list, e.g. Tuna. It enables them to input as many items as they desire.. With the implementation of a database, we can gain the value of items and determine it once a button is clicked, if that item is added to the list or not. The system is comprised of a combo Box containing 15 items, exit, reset, add, total, and receipt buttons, as well as a database to store items. The total button displays the subtotal, tax amount, and final total. The receipt button opens a receipt pane with the order items and calculations of the total. This software is to be used by management and staff of a very fancy seafood restaurant that allows them to add or adjust pricing of items. It enables them to take orders, perform calculations and provide guests with receipts.

Prerequisites
The required software to build a working example of this project is as follows:
Java IDE 
 Scene Builder
MySql
JDBC Driver
Java FX

Getting Started

Steps
1.Run applications 
2.Select desired item from ComboBox 
3Click add for the item to be added to the Order Table(press add to gain quantity)
 continue adding items as needed
4.You have the option to delete an item by selecting an item and pressing the delete button
5.Reset the order table by selecting Reset
6.To calculate the total click Total
7.To obtain the Receipt click Receipt
8.Exit button to exit 


Description of Methods:

Main Class
public void showReceiptPane(TableView<Item> orders)
Creates and launches the Receipt Pane Application Window

public ObservableList<Item> getOrderData()
Returns the observable list of items


POS Controller Class
private void initialize()
Connects the database and Initializes the Controller class, setting values for the item and price columns of the order table.
Calls the method fillComboBox(Described below). *(In order for connection to the database to work, when file is downloaded, select the database file and copy the file path into this function, following the colon of the jdbc)

public void fillComboBox()
Populates the ComboBox with items and prices from the database.


private void handleTotal()
Called when user presses the total button to calculate the tax, subtotal and Total of the order table.
Alert is set in the event there are no values in the order table to calculate

private void handleAddItem()
When used presses the add button this method is called to add the items selected in the CombBox to the order table. Alert is set in the event no selection of item in the comboBox was made.

private void handleDeleteItem()
When an item from the order table is selected, this method allows the user to press the DELETE BUTTON to delete unwanted items from the order table.
Alert is set if no items is selected and the user presses delete button.

private void handleReset()
When user selects the Reset button, this method allows a new order to be started.
Alert is set to pop up if the order table is already empty.

private void handleReceipt()
Calls the receipt class from the main class when RECEIPT button is pressed and populates it with the order from the order table along with totals. Therefore, it reads the order table.
Alert is set to show if order table is empty and cannot populate the receipt pane.

public void exitProgram()
When user selects the exit button, this method is called to prompts user to confirm if they want to exit 
or not. Once ‘OK’ option is selected application is closed!


Receipt Controller 

public void printOrder(TableView<Item> orders)
Prints orders of the selected items from the order table. Receipt button calls this method.
 An array list is created within the method  to calculate all items tax, subtotal and total gained from the order table.


Authors
Stednisha Richardson
Margaret Kikkert
Micheala Palmer
Jason McNab

