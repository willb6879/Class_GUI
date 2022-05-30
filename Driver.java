import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;


public class Driver {
    ResultSet rs = null;
    ResultSetMetaData rsmd;
    Statement statement = null;

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
        } catch (SQLException e) {
            System.out.println("SQL database connection could not be made. Please try again...");
        } catch (Exception e){
        System.out.println("Unexpected error occurred!");
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
     * @return true -1 if Class is already in database, 0 if , 1 if insert statement is successful
     */
    int sqlInsert( String class_name, String first, String last, int credits ) {
        try{
            StringBuffer cn = new StringBuffer(class_name); // converts String to StringBuffer
            StringBuffer fn = new StringBuffer(first);
            StringBuffer ln = new StringBuffer(last);
            // determines if class_name, first name or last name has a ' character in it (will cause error if not checked)
            ArrayList<StringBuffer> text_values = new ArrayList<>(Arrays.asList(cn, fn, ln));
            for( int i = 0; i < 3; i++ ){
                StringBuffer cur_text_val = text_values.get(i);
                int text_len = cur_text_val.length();
                for( int ch = 0; ch < text_len; ch++ ){
                    if( cur_text_val.charAt( ch ) == 39 ) { // ascii code for '
                        cur_text_val.insert(i, "'");
                        ch++;
                        System.out.println(cur_text_val);
                    }
                }
            }
            // executes insert statement
            statement.execute("INSERT INTO school.classes (class_name, teacher, credits) VALUES('" + cn + "', '" + ln + ", " + fn + "', " +
                credits + ")" );
        } catch( SQLIntegrityConstraintViolationException e ){
            return -1;
        } catch( SQLException e ){
            e.printStackTrace();
            System.out.println("Unexpected Error Occurred!");
            return 0;
        }
        return 1;
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
