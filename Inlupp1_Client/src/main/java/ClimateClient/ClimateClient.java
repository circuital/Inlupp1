package ClimateClient;

import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ClimateClient
{
    public void getUserInput()
    {
        System.out.println("1. Get last measurement.");
        System.out.println("2. Insert new measurement.");
        System.out.println("3. Update last measurement.");
        System.out.println("4. Get temperature report.");
        System.out.println("5. Get humidity report.");
        System.out.println("6. Get lighting report.");
        System.out.println("7. Get power consumption cost report.");
        System.out.println("Enter choice: ");

        Scanner sc = new Scanner(System.in);

        int enteredChoice;
        if(sc.hasNextInt())
        {
            enteredChoice = sc.nextInt();
            if (enteredChoice == 1)
            {
                getLastMeasurement();
            }
            else if(enteredChoice == 2 || enteredChoice == 3)
            {
                System.out.println("Enter temperature: ");
                float enteredTemperature;
                if (sc.hasNextFloat())
                {
                    enteredTemperature = sc.nextFloat();
                    System.out.println("Enter humidity: ");
                    float enteredHumidity;
                    if (sc.hasNextFloat())
                    {
                        enteredHumidity = sc.nextFloat();
                        System.out.println("Enter lighting: ");
                        float enteredLighting;
                        if (sc.hasNextFloat())
                        {
                            enteredLighting = sc.nextFloat();
                            System.out.println("Enter power consumption: ");
                            float enteredPowerConsumption;
                            if (sc.hasNextFloat())
                            {
                                enteredPowerConsumption = sc.nextFloat();
                                if (enteredChoice == 2)
                                {
                                    insertNewMeasurement(enteredTemperature, enteredHumidity, enteredLighting, enteredPowerConsumption);
                                }
                                else
                                {
                                    updateLastMeasurement(enteredTemperature, enteredHumidity, enteredLighting, enteredPowerConsumption);
                                }
                            }
                        }
                    }
                }
            }
            else if(enteredChoice == 4)
            {
                getTemperatureReport();
            }
            else if(enteredChoice == 5)
            {
                getHumidityReport();
            }
            else if(enteredChoice == 6)
            {
                getLightingReport();
            }
            else if(enteredChoice == 7)
            {
                System.out.println("Enter power cost in kr/kWh: ");
                float enteredPowerCost;
                if(sc.hasNextFloat())
                {
                    enteredPowerCost = sc.nextFloat();
                    getPowerConsumptionCostReport(enteredPowerCost);
                }
            }
        }
    }

    public void getLastMeasurement()
    {
        String url = "http://localhost:8080/getLastMeasurement";
        RestTemplate restTemplate = new RestTemplate();
        Measurement result = restTemplate.getForObject(url, Measurement.class);
        System.out.println("Temperature: " + result.temperature + ", Humidity: " + result.humidity + ", Lighting: " + result.lighting + ", Power consumption: " + result.powerConsumption + ".");
    }

    public void insertNewMeasurement(float enteredTemperature, float enteredHumidity, float enteredLighting, float enteredPowerConsumption)
    {
        String url = "http://localhost:8080/insertNewMeasurement";
        Measurement newMeasurement = new Measurement(enteredTemperature, enteredHumidity, enteredLighting, enteredPowerConsumption);
        RestTemplate restTemplate = new RestTemplate();
        Response result = restTemplate.postForObject(url, newMeasurement, Response.class);
        System.out.println(result.getSuccess() + ", " + result.getMessage());
    }

    public void updateLastMeasurement(float enteredTemperature, float enteredHumidity, float enteredLighting, float enteredPowerConsumption)
    {
        String url = "http://localhost:8080/updateLastMeasurement";
        Measurement newMeasurement = new Measurement(enteredTemperature, enteredHumidity, enteredLighting, enteredPowerConsumption);
        RestTemplate restTemplate = new RestTemplate();
        Response result = restTemplate.postForObject(url, newMeasurement, Response.class);
        System.out.println(result.getSuccess() + ", " + result.getMessage());
    }

    public void getTemperatureReport()
    {
        String url = "http://localhost:8080/getMeasurementReport";
        RestTemplate restTemplate = new RestTemplate();
        Measurement[] resultArray = restTemplate.getForObject(url, Measurement[].class);
        List<Measurement> result = Arrays.asList(resultArray);
        float temperatureSum = 0;
        int temperatureCount = 0;
        for(Measurement m : result)
        {
            LocalDate date = m.created.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            System.out.println("Date: " + date + ", Temperature: " + m.temperature + ".");
            temperatureSum += m.temperature;
            temperatureCount += 1;
        }
        float averageTemperature = temperatureSum / temperatureCount;
        System.out.println("Average temperature: " + averageTemperature);
    }

    public void getHumidityReport()
    {
        String url = "http://localhost:8080/getMeasurementReport";
        RestTemplate restTemplate = new RestTemplate();
        Measurement[] resultArray = restTemplate.getForObject(url, Measurement[].class);
        List<Measurement> result = Arrays.asList(resultArray);
        float humiditySum = 0;
        int humidityCount = 0;
        for(Measurement m : result)
        {
            LocalDate date = m.created.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            System.out.println("Date: " + date + ", Humidity: " + m.humidity + ".");
            humiditySum += m.humidity;
            humidityCount += 1;
        }
        float averageHumidity = humiditySum / humidityCount;
        System.out.println("Average humidity: " + averageHumidity);
    }

    public void getLightingReport()
    {
        String url = "http://localhost:8080/getMeasurementReport";
        RestTemplate restTemplate = new RestTemplate();
        Measurement[] resultArray = restTemplate.getForObject(url, Measurement[].class);
        List<Measurement> result = Arrays.asList(resultArray);
        float lightingSum = 0;
        int lightingCount = 0;
        for(Measurement m : result)
        {
            LocalDate date = m.created.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            System.out.println("Date: " + date + ", Lighting: " + m.lighting + ".");
            lightingSum += m.lighting;
            lightingCount += 1;
        }
        float averageLighting = lightingSum / lightingCount;
        System.out.println("Average lighting: " + averageLighting);
    }

    public void getPowerConsumptionCostReport(float enteredPowerCost)
    {
        String url = "http://localhost:8080/getMeasurementReport";
        RestTemplate restTemplate = new RestTemplate();
        Measurement[] resultArray = restTemplate.getForObject(url, Measurement[].class);
        List<Measurement> result = Arrays.asList(resultArray);
        float powerConsumptionSum = 0;
        for(Measurement m : result)
        {
            powerConsumptionSum += m.powerConsumption;
        }
        float totalPowerCost = enteredPowerCost * powerConsumptionSum;
        System.out.println("Total power cost this week: " + totalPowerCost);
    }

    public static void main(String[] args)
    {
        ClimateClient cc = new ClimateClient();
        while(true)
        {
            cc.getUserInput();
        }
    }
}
