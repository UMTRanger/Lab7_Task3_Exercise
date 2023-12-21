package com.example.lab7_task3_exercise;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager senseMan;

    Sensor accSensor, gyroSensor, magnetSensor;

    TextView acctext, gyrotext, magnettext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Mapping textview to Textview resources
        acctext = (TextView) findViewById(R.id.acctext);
        gyrotext = (TextView) findViewById(R.id.gyrotext);
        magnettext = (TextView) findViewById(R.id.magnettext);

        //Mapping sensor manager to system service
        senseMan = (SensorManager) getSystemService(SENSOR_SERVICE);

        //Mapping sensor to their sensors
        accSensor = senseMan.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyroSensor = senseMan.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        magnetSensor = senseMan.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        //Register accelerometer listener
        //Check if accelerometer sensor is in the system
        if(accSensor != null){
            Toast.makeText(this, "Accelerometer Sensor Found", Toast.LENGTH_LONG).show();
            senseMan.registerListener((SensorEventListener) this, accSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        else{
            Toast.makeText(this, "Accelerometer Sensor NOT Found", Toast.LENGTH_LONG).show();
        }

        //Register gyroscop listener
        //Check if gyroscope sensor is in the system
        if (gyroSensor != null){
            Toast.makeText(this, "Gyroscope Sensor Found", Toast.LENGTH_LONG).show();
            senseMan.registerListener((SensorEventListener) this, gyroSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        else{
            Toast.makeText(this, "Gyroscope Sensor NOT Found", Toast.LENGTH_LONG).show();
        }

        //Register magnetometer listener
        //Check if magnetometer sensor is in the system
        if (magnetSensor != null){
            Toast.makeText(this, "Magnet Sensor Found", Toast.LENGTH_LONG).show();
            senseMan.registerListener((SensorEventListener) this, magnetSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        else{
            Toast.makeText(this, "Magnet Sensor NOT Found", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        // Check the sensor type and update the corresponding TextView
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            acctext.setText("X: " + Float.toString(event.values[0]));

        } else if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            gyrotext.setText("Y: " + Float.toString(event.values[0]));

        } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            magnettext.setText("Z: " + Float.toString(event.values[0]));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume(){
        super.onResume();
        senseMan.registerListener(this, accSensor, SensorManager.SENSOR_DELAY_NORMAL);
        senseMan.registerListener(this, gyroSensor, SensorManager.SENSOR_DELAY_NORMAL);
        senseMan.registerListener(this, magnetSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause(){
        super.onPause();
        senseMan.unregisterListener(this);
    }
}