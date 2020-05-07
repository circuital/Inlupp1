package ClimateServer;

import java.io.Serializable;
import java.util.Date;

public class Measurement implements Serializable
{
    protected int id;
    protected float temperature;
    protected float humidity;
    protected float lighting;
    protected float powerConsumption;
    protected Date created;
    protected Date lastUpdate;
    protected boolean manualPost;

    public Measurement(){}

    public Measurement(float temperature, float humidity, float lighting, float powerConsumption)
    {
        this.temperature = temperature;
        this.humidity = humidity;
        this.lighting = lighting;
        this.powerConsumption = powerConsumption;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public float getTemperature()
    {
        return temperature;
    }

    public void setTemperature(float temperature)
    {
        this.temperature = temperature;
    }

    public float getHumidity()
    {
        return humidity;
    }

    public void setHumidity(float humidity)
    {
        this.humidity = humidity;
    }

    public float getLighting()
    {
        return lighting;
    }

    public void setLighting(float lighting)
    {
        this.lighting = lighting;
    }

    public float getPowerConsumption()
    {
        return powerConsumption;
    }

    public void setPowerConsumption(float powerConsumption)
    {
        this.powerConsumption = powerConsumption;
    }

    public Date getCreated()
    {
        return created;
    }

    public void setCreated(Date created)
    {
        this.created = created;
    }

    public Date getLastUpdate()
    {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate)
    {
        this.lastUpdate = lastUpdate;
    }

    public boolean getManualPost()
    {
        return manualPost;
    }

    public void setManualPost(boolean manualPost)
    {
        this.manualPost = manualPost;
    }
}
