import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyGUI extends JFrame implements ActionListener {
    JFrame frame = new JFrame("Class Management");
    JPanel panel = new JPanel();
    JButton btnGetClass = new JButton("Get Class");
    JButton btnAddClass = new JButton("Add Class");
    JButton btnChangeClass = new JButton("Change Class");
    JLabel lblClassName = new JLabel("Class:");
    JLabel lblTeacherName = new JLabel("Teacher:");
    JLabel lblCredits = new JLabel("Credits:");
    JTextField txtClassName = new JTextField(28);
    JTextField txtTeacher = new JTextField(28);
    JTextField txtCredits = new JTextField(28);
    JTextArea txtOutput = new JTextArea();
    JScrollBar scrollBar = new JScrollBar(JScrollBar.VERTICAL);

    JTextField myOutput;
    JButton submit;
    JButton exit;

    MyGUI() {
        // Sets up textArea and scrollBar
        txtOutput.setColumns(28);
        txtOutput.setRows(20);
        txtOutput.setVisible(true);

        // Sets up JPanel
        panel.setLayout(new MigLayout());
        panel.add(lblClassName); // added labels and text fields
        panel.add(txtClassName, "wrap");
        panel.add(lblTeacherName);
        panel.add(txtTeacher, "wrap");
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
    public void actionPerformed(ActionEvent e) {

    }
}
