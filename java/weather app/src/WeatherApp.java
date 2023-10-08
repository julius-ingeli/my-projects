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

    public static void showErrorPopup(String message) {
        JOptionPane.showMessageDialog(
            null,
            message,
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
    }

    public static JsonNode JSONparse(StringBuilder response){
        try{
                
            String json = response.toString();
            System.out.println(json); //debug purposes
            ObjectMapper objMap = new ObjectMapper();
            JsonNode rtNd = objMap.readTree(json);
            return rtNd;
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
}

    static double kelvinToC(double temp){
        temp -= 273.5;

        return temp;
    }

    static String winDirDetem(double windDeg){
        String windDir;
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
        return windDir;
    }
    private Label inputLabel;
    private JTextField cityField;
    private JButton fetchButton;
    private JTextArea resultArea;


    private Panel dataPanel;
    
    private Label geoInfo;
    private Panel geoPanel;
    private Label countryLabel;
    private Label countryData;
    private Label cityLabel;
    private Label cityData;
    private Label lonLabel;
    private Label lonData;
    private Label latLabel;
    private Label latData;

    private Label weaInfo;
    private Panel weaPanel;
    private Label weaLabel;
    private Label weaData;
    private Label tempLabel;
    private Label tempData;
    private Label feelTempLabel;
    private Label feelTempData;
    private Label minTempLabel;
    private Label minTempData;
    private Label maxTempLabel;
    private Label maxTempData;


    private Label windInfo;
    private Panel windPanel;
    private Label windSpeedLabel;
    private Label windSpeedData;
    private Label windDirLabel;
    private Label windDirData;

    private Label atmoInfo;
    private Panel atmoPanel;
    private Label pressLabel;
    private Label pressData;
    private Label humidLabel;
    private Label humidData;
    private Label visLabel;
    private Label visData;
    private Label cloudLabel;
    private Label cloudData;




    private static final String API_KEY = "3b1de8a725cff7cd6f27c6f97b302b46"; // Replace with your actual API key
    private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather";

    
    public WeatherApp() {
        //init
        setTitle("Weather App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());
        Font sectFont = new Font("Arial", Font.BOLD, 16);

        //input panel
        inputLabel = new Label("Enter City:");
        inputLabel.setFont(sectFont);
        cityField = new JTextField(20);
        fetchButton = new JButton("Fetch Weather");
        JPanel inputPanel = new JPanel();
        inputPanel.add(inputLabel);
        inputPanel.add(cityField);
        inputPanel.add(fetchButton);
        add(inputPanel, BorderLayout.NORTH);
        //data panel
        
        dataPanel = new Panel(new GridLayout(4, 1));
        add(dataPanel);
        //geo info label
        geoInfo = new Label("Geographical Information");
        geoInfo.setFont(sectFont);
        dataPanel.add(geoInfo);
        //geo info panel - data and labels
        geoPanel = new Panel(new GridLayout(5,2));

        //country data
        countryLabel = new Label("Country:");
        countryData = new Label("");
        geoPanel.add(countryLabel);
        geoPanel.add(countryData);

        //city data
        cityLabel = new Label("City:");
        cityData = new Label("");
        geoPanel.add(cityLabel);
        geoPanel.add(cityData); 

        //longitude data
        lonLabel = new Label("Longitude");
        lonData = new Label("");
        geoPanel.add(lonLabel);
        geoPanel.add(lonData);

        //latitude data
        latLabel= new Label("Latitude");
        latData = new Label("");
        geoPanel.add(latLabel);
        geoPanel.add(latData);

        //adding geo panel to data panel
       // dataPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
        dataPanel.add(geoPanel);

  

        //weather info label
        weaInfo = new Label("Weather Information");
        weaInfo.setFont(sectFont);
        dataPanel.add(weaInfo);

        //weather info panel - data and labels
        weaPanel = new Panel(new GridLayout(5,2));
        

        weaLabel = new Label("Weather:");
        weaData = new Label("");
        weaPanel.add(weaLabel);
        weaPanel.add(weaData);

        tempLabel = new Label("Temperature:");
        tempData = new Label("");
        weaPanel.add(tempLabel);
        weaPanel.add(tempData);


        feelTempLabel =  new Label("Feels like:");
        feelTempData = new Label("");
        weaPanel.add(feelTempLabel);
        weaPanel.add(feelTempData);


        minTempLabel = new Label("Min. temperature:");
        minTempData = new Label("");
        weaPanel.add(minTempLabel);
        weaPanel.add(minTempData);


        maxTempLabel = new Label("Max. temperature");
        maxTempData = new Label("");
        weaPanel.add(maxTempLabel);
        weaPanel.add(maxTempData);

       // dataPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
        weaPanel.setBackground(Color.LIGHT_GRAY);
        dataPanel.add(weaPanel);
        
        


        //wind info label
        windInfo = new Label("Wind Information");
        windInfo.setFont(sectFont);
        
        dataPanel.add(windInfo);
       // dataPanel.add(new JSeparator(SwingConstants.HORIZONTAL));

        // wind panel
        windPanel = new Panel(new GridLayout(2,2));

        windSpeedLabel = new Label("Wind Speed:");
        windSpeedData = new Label("");
        windPanel.add(windSpeedLabel);
        windPanel.add(windSpeedData);
        
        windDirLabel = new Label("Wind Direction:");
        windDirData = new Label("");
        windPanel.add(windDirLabel);
        windPanel.add(windDirData);
    
        dataPanel.add(windPanel);
        //dataPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
       

        //atmospheric info label
        atmoInfo = new Label("Atmospheric Information");
        atmoInfo.setFont(sectFont);
        dataPanel.add(atmoInfo);

        //atmospheric panel
        atmoPanel = new Panel(new GridLayout(4,2));

        pressLabel = new Label("Pressure:");
        pressData = new Label("");
        atmoPanel.add(pressLabel);
        atmoPanel.add(pressData);

        humidLabel = new Label("Humidity:");
        humidData = new Label("");
        atmoPanel.add(humidLabel);
        atmoPanel.add(humidData);

        visLabel = new Label("Visibility:");
        visData = new Label("");
        atmoPanel.add(visLabel);
        atmoPanel.add(visData);

        cloudLabel = new Label("Clouds:");
        cloudData = new Label("");
        atmoPanel.add(cloudLabel);
        atmoPanel.add(cloudData);
        
        atmoPanel.setBackground(Color.LIGHT_GRAY);
        dataPanel.add(atmoPanel);
        
        //dataPanel.add(new JSeparator(SwingConstants.HORIZONTAL));


        

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
                    System.out.println(response.toString()); //for debug purposes
                    
                    
                    String pressure, humidity,visibility ;
                    double windSpeed,windDeg, latitude, longitude;
                    //atmo info
                    pressure = rtNd.get("main").get("pressure").asText();
                    pressData.setText(String.format("%s hPa", pressure));
                    humidity = rtNd.get("main").get("humidity").asText();
                    humidData.setText(String.format("%s%%", humidity)); 
                    visibility = rtNd.get("visibility").asText();
                    visData.setText(String.format("%s m",visibility ));
                    cloudData.setText(rtNd.get("clouds").get("all").asText());
                    //geo info
                    countryData.setText(rtNd.get("sys").get("country").asText());
                    cityData.setText(rtNd.get("name").asText());
                    longitude = rtNd.get("coord").get("lon").asDouble();
                    if(longitude>0){
                          lonData.setText(String.format("%.4f E",longitude));  
                    }
                    else{
                        longitude*=-1;
                        lonData.setText(String.format("%.4f W",longitude));
                    }

                    latitude = rtNd.get("coord").get("lat").asDouble();
                    if(latitude>0){
                            latData.setText(String.format("%.4f N", latitude));
                    }
                    else{
                        latitude*=-1;
                        latData.setText(String.format("%.4f S", latitude));
                    }
                    
                    //temp info
                    double tempC, tempFeelC, tempMinC, tempMaxC;
                    tempC = kelvinToC(rtNd.get("main").get("temp").asDouble());
                    tempData.setText(String.format("%.1f °C",tempC));
                    tempFeelC = kelvinToC(rtNd.get("main").get("feels_like").asDouble());
                    feelTempData.setText(String.format("%.1f °C",tempFeelC));    
                    tempMinC = kelvinToC(rtNd.get("main").get("temp_min").asDouble());
                    minTempData.setText(String.format("%.1f °C",tempMinC));
                    tempMaxC = kelvinToC(rtNd.get("main").get("temp_max").asDouble());
                    maxTempData.setText(String.format("%.1f °C",tempMaxC));
                    //weather info
                    weaData.setText(rtNd.get("weather").get(0).get("description").asText());
                    //wind info
                    windSpeed = rtNd.get("wind").get("speed").asDouble();
                    windSpeedData.setText(String.format("%.2fm/s",windSpeed));
                    windDeg = rtNd.get("wind").get("deg").asInt();
                    windDirData.setText(winDirDetem(windDeg));
                    //result
                    /*
                    String resultText = "GEOGRAPHICAL INFO\nCountry:%s\nCity: %s\nLongitude:%.4f\nLatitude:%.4f\n-------------------------\nWEATHER INFO\nWeather:%s\nTemp:%.1f°C\nFeels like:%.1f°C\nMinimum temperature:%.1f°C\nMaximum temperature:%.1f°C\n-------------------------\nWIND INFO\nWind speed:%.1fm/s\nWind direction:%s\n-------------------------\nATMOSPHERIC INFO\nPressure:%dhPa\nHumidity:%d%%\nVisibilty:%dm\nClouds:%d\n";
                    String formResultText = String.format(resultText,country,city,lon,lat,weatherDescription,temp,tempFeel,tempMin,tempMax,windSpeed,windDir,pressure,humidity,visibility,clouds);
                    resultArea.setText(formResultText);
                     */
                    

                }
                catch (JsonProcessingException e) {
                    e.printStackTrace();
                }

                

            } else {
                //resultArea.setText("Error fetching weather data");
                showErrorPopup("Error fetching data.");
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
