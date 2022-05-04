import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver {
    ResultSet rs = null;
    ResultSetMetaData num_columns;
    Statement statement = null;
    Scanner sc = new Scanner(System.in);
    int user_input = -1;
    boolean flag; // used for non-integer user inputs during after printMenu() call
    String db;
    String table;
    String sBuffer[];
    String column = "";
    ArrayList o = null;

    Driver(){
        // intentionally empty
    }

    /**
     * Displays user with options to access a database
     */
    void run() {
    try{
        // Connection to database (DriverManager static function establishes connection)
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root", "root", "AdmPas!!!04292021");
        statement = con.createStatement(); // statement object is created SO THAT sql statements can be sent to the database (messenger)
            /*while (true) {
                printMenu(); // prints out menu for user
                try {
                    user_input = sc.nextInt(); // user inputs desired action
                    sc.nextLine(); // clears Scanner buffer

                } catch( InputMismatchException e ){
                    sc.nextLine(); // clears Scanner buffer
                    System.out.println("Please enter a valid integer...");
                    continue;
                } catch( Exception e ){
                    System.out.println("Unexpected error occurred...");
                    continue;
                }
                // 3. execute query based on user input
                switch (user_input) {
                    case 1:
                        if (sqlSelect()) {
                            System.out.println("Successful!");
                        } else {
                            System.out.println("Unsuccessful!");
                        }
                        break;
                    case 2:
                        if (sqlInsert()) {
                            System.out.println("Successful!");
                        } else {
                            System.out.println("Unsuccessful!");
                        }
                        break;
                    case 3:
                        if (sqlAlter()) {
                            System.out.println("Successful!");
                        } else {
                            System.out.println("Unsuccessful!");
                        }
                        break;
                    default:
                        System.out.println("Please enter a valid integer...");
                        continue;
                }
            }
            */

        } catch (Exception e) {
            System.out.println("SQL database connection could not be made. Please try again...");
        }
    }

    /**
     * Performs an SQL Alter statement
     * @return true if successful statement, false if unsuccessful
     */
    boolean sqlAlter( ) {
        return false;
    }

    /**
     * Performs an SQL Insert statement
     * @return true if successful statement, false if unsuccessful
     */
    boolean sqlInsert( String class_name, String first, String last, int credits ) {
        try{
            // executes insert statement
            rs = statement.execute("INSERT INTO school.classes (class_name, teacher, credits) VALUES('" +
                    class_name + "', '" +
                    last + ", " + first + "', '" +
                    credits + ")" );
            if( !statement.execute("INSERT INTO school.classes (class_name, teacher, credits) VALUES('" +
                class_name + "', '" +
                last + ", " + first + "', '" +
                credits + ")" )){
                return false;
            }
        } catch( SQLException e ){
            return false;
        } catch( Exception e ){
            return false;
        }
        return true;
    }

    /**
     * Performs an SQL select statement and prints values to the user
     * @return true if successful SQL query, false if unsuccessful
     */
    private boolean sqlSelect() {
        try{
            System.out.println("Enter database: "); // user inputs info for SQL query
            db = sc.nextLine();
            System.out.println("Enter table: ");
            table = sc.nextLine();
            System.out.println("Enter column(s): <column1> <column2> ... <columnN> OR <enter> for all columns in table ");
            column = sc.nextLine();

            sBuffer = column.split(" "); // sBuffer splits the user input into an array of columns to be selected
            column = String.join(", ", sBuffer); // column is now the correct format for SQL with ", " character

            if( column.isEmpty() ) { // user selects all columns for query
                rs = statement.executeQuery("SELECT * FROM " + db + "." + table );
                num_columns = rs.getMetaData(); // returns integer representing number of columns in the current ResultSet object
                while( rs.next() ){
                    for( int i = 1; i <= num_columns.getColumnCount(); i++ ){
                        if( i == num_columns.getColumnCount() ){
                            System.out.println( String.format("%2s ", rs.getString( i ) ));
                        }else{
                            System.out.print( String.format("%2s | ", rs.getString( i ) ));
                        }
                    }
                }
            }else{ // user specifies columns for query
                rs = statement.executeQuery("SELECT " + column + " FROM " + db + "." + table );
                while( rs.next() ){
                    for( int i = 0; i < sBuffer.length; i++ ){
                        System.out.print( String.format("%2s ", rs.getString( sBuffer[i] ) )); // prints out each column of current row in query
                    }
                    System.out.println(); // skips line and proceeds to next row in database
                }
            }
        }catch( SQLException e ) {
            System.out.println("Invalid query input!");
            return false;
        }catch( Exception e){
            System.out.println("Unexpected error occurred!");
            return false;
        }
        return true;
    }

    private void printMenu(){
        System.out.println("1. SELECT\n2. INSERT\n3. ALTER");
    }

    private String[] spacedInput(){
        return null;
    }
}
