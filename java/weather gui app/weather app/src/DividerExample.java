import java.awt.*;
import java.awt.event.*;

public class DividerExample {
    public static void main(String[] args) {
        Frame frame = new Frame("AWT Divider Example");
        frame.setSize(400, 300);

        Panel panel1 = new Panel();
        panel1.setBackground(Color.RED);

        Panel divider = new Panel();
        divider.setBackground(Color.BLACK);

        Panel panel2 = new Panel();
        panel2.setBackground(Color.BLUE);

        frame.setLayout(new GridLayout(1, 3)); // Create a single-row grid layout
        frame.add(panel1);
        frame.add(divider);
        frame.add(panel2);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        frame.setVisible(true);
    }
}
