package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {
	public static void main(String[]args){
        try{
            /*accepts connection string and returns a connection instance. How do I know that? DOCUMENTATION! :)

           
            If this database did not exist, the Driver Manager will create one for us.
             */
            Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\smart\\git\\Final\\POS_Group20_Project\\POS_Group20_Project\\databases.db");
            /* This is a statement object, recall that Java is an OOP language therefore we created an object
            statement and we will execute this instance of statement we have created with a .execute method.
             */

            Statement statement = conn.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS poisson_price (productID INTEGER, productDescr TEXT, price REAL)");
            statement.execute("INSERT INTO poisson_price (productID, productDescr, productPrice) VALUES ('101', 'Jellied eels', '50.00')");
            statement.execute("INSERT INTO poisson_price (productID, productDescr, productPrice) VALUES ('102', 'Monkfish', '60.00')");
            statement.execute("INSERT INTO poisson_price (productID, productDescr, productPrice) VALUES ('103', 'Deep fried pomfret', '20.00')");
            statement.execute("INSERT INTO poisson_price (productID, productDescr, productPrice) VALUES ('104', 'Marinated swordfish', '30.00')");
            statement.execute("INSERT INTO poisson_price (productID, productDescr, productPrice) VALUES ('105', 'Tuna', '10.00')");
            statement.execute("INSERT INTO poisson_price (productID, productDescr, productPrice) VALUES ('106', 'Salmon roe', '30.00')");
            statement.execute("INSERT INTO poisson_price (productID, productDescr, productPrice) VALUES ('107', 'Haddock', '10.00')");
            statement.execute("INSERT INTO poisson_price (productID, productDescr, productPrice) VALUES ('108', 'Snapper', '12.00')");
            statement.execute("INSERT INTO poisson_price (productID, productDescr, productPrice) VALUES ('109', 'Crab cake', '12.00')");
            statement.execute("INSERT INTO poisson_price (productID, productDescr, productPrice) VALUES ('110', 'Lobster bisque', '15.00')");
            statement.execute("INSERT INTO poisson_price (productID, productDescr, productPrice) VALUES ('111', 'Deep fried calamari', '18.00')");
            statement.execute("INSERT INTO poisson_price (productID, productDescr, productPrice) VALUES ('112', 'Takoyaki', '25.00')");
            statement.execute("INSERT INTO poisson_price (productID, productDescr, productPrice) VALUES ('113', 'Scallops', '20.00')");
            statement.execute("INSERT INTO poisson_price (productID, productDescr, productPrice) VALUES ('114', 'Sea cucumbers', '9.00')");
            statement.execute("INSERT INTO poisson_price (productID, productDescr, productPrice) VALUES ('115', 'Oyster Rockefeller', '9.00')");
         
            
            /* we must close the statement and the connection as well. Does anyone know why we do that?
                If you close the connection first you will get an error.
             */
            statement.close();
            conn.close();

        }catch (SQLException e){

            System.out.println("Something went wrong: " + e.getMessage());
        }//end catch bracket
    }//end main method bracket

}//end main class bracket

