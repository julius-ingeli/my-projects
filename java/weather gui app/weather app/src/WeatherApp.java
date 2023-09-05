import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

public class WeatherApp extends JFrame {

    private JTextField cityField;
    private JButton fetchButton;
    private JTextArea resultArea;

    private static final String API_KEY = "3b1de8a725cff7cd6f27c6f97b302b46"; // Replace with your actual API key
    private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather";

    public WeatherApp() {
        setTitle("Weather App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        cityField = new JTextField(20);
        fetchButton = new JButton("Fetch Weather");
        resultArea = new JTextArea();
        resultArea.setEnabled(false);
        resultArea.setDisabledTextColor(Color.BLACK);

        JPanel inputPanel = new JPanel();
        inputPanel.add(cityField);
        inputPanel.add(fetchButton);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        fetchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cityName = cityField.getText().trim();
                if (!cityName.isEmpty()) {
                    fetchWeatherData(cityName);
                }
            }
        });
    }

    private void fetchWeatherData(String cityName) {
        try {
            URL url = new URL(API_URL + "?q=" + cityName + "&appid=" + API_KEY);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                
                try { 
                    String json = response.toString();
                    ObjectMapper objMap = new ObjectMapper();
                    JsonNode rtNd = objMap.readTree(json);

                    String city, weatherDescription ;
                    double temp, feeltemp;
                    city = rtNd.get("name").asText();
                    temp = rtNd.get("main").get("temp").asDouble();
                    feeltemp = rtNd.get("main").get("feels_like").asDouble();
                    temp-=273.5; feeltemp-=273.5; 
                    weatherDescription = rtNd.get("weather").get(0).get("description").asText();
                    String resultText = "City:%s\nWeather:%s\nTemp:%.1f°C\nFeels like:%.1f°C\n";
                    String formResultText = String.format(resultText, city,weatherDescription,temp,feeltemp);
                    resultArea.setText(formResultText);
                    

                }
                catch (JsonProcessingException e) {
                    e.printStackTrace();
                }

                

            } else {
                resultArea.setText("Error fetching weather data");
            }
        } catch (IOException ex) {
            resultArea.setText("An error occurred: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new WeatherApp().setVisible(true);
            }
        });
    }
}
