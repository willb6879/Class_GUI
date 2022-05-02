import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Driver driver = new Driver();

        JFrame frame = new JFrame();
        frame.setSize(420, 420);
        frame.setVisible( true );

        driver.run();
    }
}
