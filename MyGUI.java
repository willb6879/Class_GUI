import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class MyGUI extends JFrame implements ActionListener {
    Driver driver = new Driver();
    private JFrame frame = new JFrame("Class Management");
    private JPanel panel = new JPanel();
    private JButton btnGetClass = new JButton("Get Class");
    private JButton btnAddClass = new JButton("Add Class");
    private JButton btnChangeClass = new JButton("Change Class");
    private JLabel lblClassName = new JLabel("Class:");
    private JLabel lblTeacherFirstName = new JLabel("Teacher FN:");
    private JLabel lblTeacherLastName = new JLabel("Teacher LN:");
    private JLabel lblCredits = new JLabel("Credits:");
    private JTextField txtClassName = new JTextField(28);
    private JTextField txtTeacherFirstName = new JTextField(28);
    private JTextField txtTeacherLastName = new JTextField(28);
    private JTextField txtCredits = new JTextField(28);
    private JTextArea txtMenu = new JTextArea(8, 28 );
    private JTextArea txtOutput = new JTextArea(5, 28);
    private JScrollPane outputScrollPane = new JScrollPane( txtOutput );
    private String userMenu = "1. To get the info of a class, enter the class's name\n in the text box labeled 'Class', then press 'Get Class'\n\n" +
            "2. To add a class, fill in all text boxes with the class's info\n then press 'Add Class'\n\n" +
            "3. To change the info of a class, enter the class's name\n in the text box labeled 'Class', then enter the information\n you wish to change in the text box(es)\n\n" +
            "NOTE: All entries are case-sensitive";

    MyGUI() {
        // Sets up textArea, txtMenu, scrollBar, scrollPane and buttons
        txtMenu.setText( userMenu );
        txtMenu.setEditable(false);
        txtMenu.setVisible(true);
        txtOutput.setVisible(true);
        txtOutput.setEditable(false);
        outputScrollPane.setVisible(true);
        btnGetClass.addActionListener(this);
        btnAddClass.addActionListener(this);
        btnChangeClass.addActionListener(this);

        // Sets up JPanel
        panel.setLayout(new MigLayout());
        panel.add(lblClassName); // added labels and text fields
        panel.add(txtClassName, "wrap");
        panel.add(lblTeacherFirstName);
        panel.add(txtTeacherFirstName, "wrap");
        panel.add(lblTeacherLastName);
        panel.add(txtTeacherLastName, "wrap");
        panel.add(lblCredits);
        panel.add(txtCredits, "wrap");

        panel.add(btnGetClass, "skip, split3"); // added buttons
        panel.add(btnAddClass);
        panel.add(btnChangeClass, "wrap");
        panel.add(txtMenu, "cell 1 6");
        panel.add(outputScrollPane, "cell 1 7");

        // Sets up JFrame
        frame.setLayout(new MigLayout());
        frame.setVisible(true);
        frame.setResizable(false);
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        driver.run();
    }


    @Override
    public void actionPerformed(ActionEvent e){
        String className = txtClassName.getText();
        String fn = txtTeacherFirstName.getText();
        String ln = txtTeacherLastName.getText();
        String credits = txtCredits.getText();
        if( e.getSource() == btnGetClass ){
                if( className.isEmpty() ){
                    txtOutput.setText("Please enter the name of the class you want...");
                }else{
                    ArrayList<String> result = driver.sqlSelect( className );
                    if( result == null ){
                        txtOutput.setText("Class not found, please try again...");
                    }else{
                        String output = "";
                        String outputLabels[] = {"Class: ", "Name: ", "Credits: "};
                        output += "Class: " + result.get(1) + "\n"
                                + "Name: " + result.get(2) + "\n"
                                + "Credits: " + result.get(3);

                        txtOutput.setText(output);
                    }
                }
        }else if( e.getSource() == btnAddClass ){
            try{
                if( className.isEmpty() || fn.isEmpty() || ln.isEmpty() || credits.isEmpty() ){ // user doesn't put in values or doesn't put in an integer in "credits" box
                    txtOutput.setText("Please enter all values in boxes above...");
                }else{
                    int insert_result = driver.sqlInsert( className, fn, ln, Integer.parseInt(credits));
                    switch( insert_result ){
                        case -1:
                            txtOutput.setText( String.format("Class '%s' is already in database...", className ));
                            break;
                        case 0:
                            txtOutput.setText("Unexpected error occurred!");
                            break;
                        case 1:
                            txtOutput.setText( String.format("Class %s taught by %s %s with %d credits was successfully added to the database!", className, fn, ln, Integer.parseInt(credits)) );
                            break;
                        default:
                            // will NOT execute because of lines before it
                    }
                }
            }catch( NumberFormatException ex ){
                txtOutput.setText("Please enter an integer for credits...");
            }catch( NullPointerException ex ){
                ex.printStackTrace();
            }
        }else if( e.getSource() == btnChangeClass ){

        }
    }


}
