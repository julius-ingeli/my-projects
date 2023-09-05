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
        resultArea.setBackground(Color.LIGHT_GRAY);

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

        String reset = "\u001B[0m"; // Reset all attributes
        String red = "\u001B[31m";   // Red text color
        String green = "\u001B[32m"; // Green text color
        String bold = "\u001B[1m";   // Bold text


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
                    System.out.println(response.toString()); //for debug purposes
                    
                    
                    String city, weatherDescription, country, windDir ;
                    double lon, lat, temp, tempFeel,tempMin, tempMax,windSpeed,windDeg;
                    int pressure,humidity, visibility, clouds;
                    //atmo info
                    pressure = rtNd.get("main").get("pressure").asInt();
                    humidity = rtNd.get("main").get("humidity").asInt();
                    visibility = rtNd.get("visibility").asInt();
                    clouds = rtNd.get("clouds").get("all").asInt();
                    //geo info
                    country = rtNd.get("sys").get("country").asText();
                    city = rtNd.get("name").asText();
                    lon = rtNd.get("coord").get("lon").asDouble();
                    lat = rtNd.get("coord").get("lat").asDouble();
                    //temp info
                    temp = rtNd.get("main").get("temp").asDouble();
                    tempFeel = rtNd.get("main").get("feels_like").asDouble();
                    tempMin = rtNd.get("main").get("temp_min").asDouble();
                    tempMax = rtNd.get("main").get("temp_max").asDouble();
                    temp-=273.5; tempFeel-=273.5;tempMin-=273.5;tempMax-=273.5;
                    //weather info
                    weatherDescription = rtNd.get("weather").get(0).get("description").asText();
                    //wind info
                    windSpeed = rtNd.get("wind").get("speed").asDouble();
                    windDeg = rtNd.get("wind").get("deg").asInt();
                    if(windDeg>=337.5&&windDeg<=22.5){ //wind direction
                        windDir = "N";
                    }
                    else if(windDeg>22.5&&windDeg<67.5){
                        windDir = "NE";
                    }
                    else if(windDeg>=67.5&&windDeg<=112.5){
                        windDir = "E";
                    }
                    else if(windDeg>112.5&&windDeg<157.5){
                        windDir = "SE";
                    }
                    else if(windDeg>=157.5&&windDeg<=202.5){
                        windDir = "S";
                    }
                    else if(windDeg>202.5&&windDeg<247.5){
                        windDir = "SW";
                    }
                    else if(windDeg>=247.5&&windDeg<=292.5){
                        windDir = "W";
                    }
                    else{
                        windDir = "NW";
                    }
                    //result
                    String resultText = "GEOGRAPHICAL INFO\nCountry:%s\nCity: %s\nLongitude:%.4f\nLatitude:%.4f\n-------------------------\nWEATHER INFO\nWeather:%s\nTemp:%.1f°C\nFeels like:%.1f°C\nMinimum temperature:%.1f°C\nMaximum temperature:%.1f°C\n-------------------------\nWIND INFO\nWind speed:%.1fm/s\nWind direction:%s\n-------------------------\nATMOSPHERIC INFO\nPressure:%dhPa\nHumidity:%d%%\nVisibilty:%dm\n";
                    String formResultText = String.format(resultText,country,city,lon,lat,weatherDescription,temp,tempFeel,tempMin,tempMax,windSpeed,windDir,pressure,humidity,visibility);
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
