package hu.uni.miskolc.recyclerviewpelda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Toast;

public class SensorActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor magnetometer;
    private Sensor thermometer;
    private Sensor gyroscope;
    private SensorEventListener eventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        thermometer = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        sensorManager.registerListener( this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);

        if (gyroscope == null){
            Toast.makeText(this, "Nincs giroszkóp", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Van giroszkóp", Toast.LENGTH_SHORT).show();
            System.out.println("van giroszkop");
        }



    }
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.values[2]>0.5f){
            getWindow().getDecorView().setBackgroundColor(Color.BLUE);
        }
        else if (sensorEvent.values[2]<0.5f){
            getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}