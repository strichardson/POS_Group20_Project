package application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbMain {
	    public static void main(String[]args){
        try{
            /*accepts connection string and returns a connection instance. How do I know that? DOCUMENTATION! :)

            If this database did not exist, the Driver Manager will create one for us.
             */
            Connection conn = DriverManager.getConnection("jdbc:sqlite:D:\\Documents\\degree 2018\\cois2240\\projects\\Poisson\\databases\\poisson.db");
            /* This is a statement object, recall that Java is an OOP language therefore we created an object
            statement and we will execute this instance of statement we have created with a .execute method.
             */

            Statement statement = conn.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS poisson_stock (productID INTEGER, product TEXT, price REAL, quantity INTEGER)");
            statement.execute("INSERT INTO poisson_stock (productID, product, price, quantity) VALUES ('1', 'jellied eels', '50', '10')");
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


