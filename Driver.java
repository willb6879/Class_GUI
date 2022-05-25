import java.sql.*;
import java.util.ArrayList;


public class Driver {
    ResultSet rs = null;
    ResultSetMetaData rsmd;
    Statement statement = null;
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
            if( statement.execute("INSERT INTO school.classes (class_name, teacher, credits) VALUES('" + class_name + "', '" + last + ", " + first + "', " +
                credits + ")" )){
                return false;
            }
        } catch( SQLException e ){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Performs an SQL select statement and prints values to the user
     * @return the String of data retrieved from a class, null if the class doesn't exist or error occurs
     */
    ArrayList<String> sqlSelect( String class_name ) {
        ArrayList<String> class_info = null;
        try{
            rs = statement.executeQuery("SELECT * FROM school.classes WHERE class_name = '" + class_name + "'" ); // holds row info from query
            rsmd = rs.getMetaData();
            int num_columns = rsmd.getColumnCount();
            class_info = new ArrayList( num_columns );
            while( rs.next() ){
                String column_info = null;
                for( int col = 1; col <= num_columns; col++ ){
                    column_info = rs.getString(col);
                    class_info.add(col-1, column_info ); // adds current column resultSet metadata to class_info
                }
            }
            if( class_info.size() == 0 ){ // class not found in database, null is returned
                return null;
            }
            return class_info; // class was found in database, arraylist of data is returned
        }catch( SQLException e ) {
            e.printStackTrace();
            System.out.println("SQL Exception!");
            return null;
        }catch( Exception e ){
            System.out.println("Unexpected error occurred!");
            return null;
        }

    }
}
