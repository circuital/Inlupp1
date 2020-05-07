package ClimateServer;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class ClimateRepository
{
    private Properties p = new Properties();

    public ClimateRepository()
    {
        try
        {
            p.load(new FileInputStream("C:/Users/elvir/Desktop/SKOLA/Systemintegration/Inlupp1/src/main/java/ClimateServer/Settings.properties"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public Measurement getLastMeasurement()
    {
        ResultSet rs = null;
        Measurement m = new Measurement();
        String query = "select * from climate.measurements " +
                "order by Id desc " +
                "limit 1";

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             PreparedStatement stmt = con.prepareStatement(query))
        {
            rs = stmt.executeQuery();
            while (rs.next())
            {
                int id = rs.getInt("Id");
                float temperature = rs.getFloat("Temperature");
                float humidity = rs.getFloat("Humidity");
                float lighting = rs.getFloat("Lighting");
                float powerConsumption = rs.getFloat("PowerConsumption");
                Date created = rs.getDate("Created");
                Date lastUpdate = rs.getDate("LastUpdate");
                boolean manualPost = rs.getBoolean("ManualPost");
                m = new Measurement(temperature, humidity, lighting, powerConsumption);
                m.setId(id);
                m.setCreated(created);
                m.setLastUpdate(lastUpdate);
                m.setManualPost(manualPost);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return m;
    }

    public Response insertNewMeasurement(Measurement newMeasurement)
    {
        Response r = new Response();
        String query = "insert into climate.measurements (Temperature, Humidity, Lighting, PowerConsumption, ManualPost)" +
                "values (?, ?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             PreparedStatement stmt = con.prepareStatement(query))
        {
            stmt.setFloat(1, newMeasurement.temperature);
            stmt.setFloat(2, newMeasurement.humidity);
            stmt.setFloat(3, newMeasurement.lighting);
            stmt.setFloat(4, newMeasurement.powerConsumption);
            stmt.setBoolean(5, true);
            int numberOfUpdates = stmt.executeUpdate();
            r = new Response(true, "Measurement was inserted successfully!");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return r;
    }

    public Response updateLastMeasurement(Measurement newMeasurement)
    {
        Response r = new Response();
        String query = "update climate.measurements " +
                "set Temperature = ?, Humidity = ?, Lighting = ?, PowerConsumption = ? " +
                "order by Id desc " +
                "limit 1";

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             PreparedStatement stmt = con.prepareStatement(query))
        {
            stmt.setFloat(1, newMeasurement.temperature);
            stmt.setFloat(2, newMeasurement.humidity);
            stmt.setFloat(3, newMeasurement.lighting);
            stmt.setFloat(4, newMeasurement.powerConsumption);
            int numberOfUpdates = stmt.executeUpdate();
            r = new Response(true, "Measurement was updated successfully!");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return r;
    }

    public List<Measurement> getMeasurementReport()
    {
        ResultSet rs = null;
        Measurement m = new Measurement();
        List<Measurement> measurementReport = new ArrayList<>();
        String query = "select * from climate.measurements " +
                "order by Id desc " +
                "limit 7";

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             PreparedStatement stmt = con.prepareStatement(query))
        {
            rs = stmt.executeQuery();
            while (rs.next())
            {
                int id = rs.getInt("Id");
                float temperature = rs.getFloat("Temperature");
                float humidity = rs.getFloat("Humidity");
                float lighting = rs.getFloat("Lighting");
                float powerConsumption = rs.getFloat("PowerConsumption");
                Date created = rs.getDate("Created");
                Date lastUpdate = rs.getDate("LastUpdate");
                boolean manualPost = rs.getBoolean("ManualPost");
                m = new Measurement(temperature, humidity, lighting, powerConsumption);
                m.setId(id);
                m.setCreated(created);
                m.setLastUpdate(lastUpdate);
                m.setManualPost(manualPost);
                measurementReport.add(m);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return measurementReport;
    }
}
