import java.awt.*;
import java.awt.event.*;

public class InfoPanelLayoutWithSuppliedData extends Frame {
    private Panel inputPanel;
    private Panel dataPanel;

    public InfoPanelLayoutWithSuppliedData() {
        setTitle("Info Panel Layout Example");
        setSize(400, 400);
        setLayout(new BorderLayout());

        // Input Panel
        inputPanel = new Panel(new FlowLayout());
        TextField inputTextField = new TextField(20);
        Button fetchButton = new Button("Fetch");
        inputPanel.add(inputTextField);
        inputPanel.add(fetchButton);

        // Data Panel
        dataPanel = new Panel(new GridLayout(4, 2)); // Four rows, two columns

        Label latitudeLabel = new Label("Latitude:");
        Label latitudeData = new Label("48.776");

        Label longitudeLabel = new Label("Longitude:");
        Label longitudeData = new Label("2.345");

        Label weatherLabel = new Label("Weather:");
        Label weatherData = new Label("Clear Sky");

        Label temperatureLabel = new Label("Temperature:");
        Label temperatureData = new Label("22°C");

        dataPanel.add(latitudeLabel);
        dataPanel.add(latitudeData);

        dataPanel.add(longitudeLabel);
        dataPanel.add(longitudeData);

        dataPanel.add(weatherLabel);
        dataPanel.add(weatherData);

        dataPanel.add(temperatureLabel);
        dataPanel.add(temperatureData);

        // Add panels to the frame
        add(inputPanel, BorderLayout.NORTH);
        add(dataPanel, BorderLayout.CENTER);

        // Handle window close event
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            InfoPanelLayoutWithSuppliedData example = new InfoPanelLayoutWithSuppliedData();
            example.setVisible(true);
        });
    }
}
