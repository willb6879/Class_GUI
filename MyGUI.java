import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyGUI extends JFrame implements ActionListener {
    Driver driver = new Driver();
    JFrame frame = new JFrame("Class Management");
    JPanel panel = new JPanel();
    JButton btnGetClass = new JButton("Get Class");
    JButton btnAddClass = new JButton("Add Class");
    JButton btnChangeClass = new JButton("Change Class");
    JLabel lblClassName = new JLabel("Class:");
    JLabel lblTeacherFirstName = new JLabel("Teacher FN:");
    JLabel lblTeacherLastName = new JLabel("Teacher LN:");
    JLabel lblCredits = new JLabel("Credits:");
    JTextField txtClassName = new JTextField(28);
    JTextField txtTeacherFirstName = new JTextField(28);
    JTextField txtTeacherLastName = new JTextField(28);
    JTextField txtCredits = new JTextField(28);
    JTextArea txtOutput = new JTextArea();
    JScrollBar scrollBar = new JScrollBar(JScrollBar.VERTICAL);

    JTextField myOutput;
    JButton submit;
    JButton exit;

    MyGUI() {
        // Sets up textArea, scrollBar and buttons
        txtOutput.setColumns(28);
        txtOutput.setRows(20);
        txtOutput.setVisible(true);
        txtOutput.setEditable(false);
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
        panel.add(txtOutput, "skip");
        panel.add(scrollBar);

        // Sets up JFrame
        frame.setLayout(new MigLayout());
        frame.setVisible(true);
        frame.setResizable(false);
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    @Override
    public void actionPerformed(ActionEvent e) throws NumberFormatException {
        if( e.getSource() == btnGetClass ){
            // 1. hand off text fields to driver class
            // 2. Print results or if invalid values
        }else if( e.getSource() == btnAddClass ){
            try{
                if( txtClassName.getText().isEmpty() || txtTeacherFirstName.getText().isEmpty() || txtTeacherLastName.getText().isEmpty() ||
                        txtCredits.getText().isEmpty() ){
                    txtOutput.setText("Please enter all values in boxes above...");
                    // display in area box invalid input
                }else{
                    if( !driver.sqlInsert( txtClassName.getText(), txtTeacherFirstName.getText(), txtTeacherLastName.getText(),
                            Integer.parseInt(txtCredits.getText()) ) ){
                        txtOutput.setText("Unsuccessful!");
                    }else{

                    }
                }
            }catch( NumberFormatException ex ){
                txtOutput.setText("Please enter an integer for credits...");
            }
        }else if( e.getSource() == btnChangeClass ){

        }
    }


}
