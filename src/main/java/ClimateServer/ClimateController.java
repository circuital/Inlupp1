package ClimateServer;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class ClimateController
{
    ClimateRepository r = new ClimateRepository();

    public ClimateController()
    {
    }

    @RequestMapping(value = "/getLastMeasurement", produces = "application/xml")
    public Measurement getLastMeasurement()
    {
        r = new ClimateRepository();
        return r.getLastMeasurement();
    }

    @PostMapping("insertNewMeasurement")
    public Response insertNewMeasurement(@RequestBody Measurement newMeasurement)
    {
        r = new ClimateRepository();
        return r.insertNewMeasurement(newMeasurement);
    }

    @PostMapping("updateLastMeasurement")
    public Response updateLastMeasurement(@RequestBody Measurement newMeasurement)
    {
        r = new ClimateRepository();
        return r.updateLastMeasurement(newMeasurement);
    }

    @RequestMapping(value = "/getMeasurementReport", produces = "application/xml")
    public List<Measurement> getMeasurementReport()
    {
        r = new ClimateRepository();
        return r.getMeasurementReport();
    }
}
