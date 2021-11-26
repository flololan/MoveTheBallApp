package dom.applibillegravitemaquette;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;

public class Gyroscope {
    SensorManager sensorManager;
    Sensor accelerometre;

    public Gyroscope (MainActivity activity){
        this.sensorManager = (SensorManager)
                activity.getSystemService(Context.SENSOR_SERVICE);

        this.accelerometre =  this.sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        this.sensorManager.registerListener(activity.ecouteurGyroscope, this.accelerometre, SensorManager.SENSOR_DELAY_GAME);
    }

}
