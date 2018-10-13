package com.example.android.lab5;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Part3 extends AppCompatActivity implements SensorEventListener {

    SensorManager mSensorManager;
    Sensor mMagnetometer;
    TextView display;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part3);

        display = findViewById(R.id.display);
        view = findViewById(R.id.view1);
        view.setOnTouchListener(new OnSwipeTouchListener(Part3.this) {
//            @Override
//            public void onSwipeLeft() {
//                Intent intent = new Intent(Part3.this, Part4.class);
//                finish();
//                startActivity(intent);
//            }
            @Override
            public void onSwipeRight() {
                Intent intent = new Intent(Part3.this, Part2.class);
                finish();
                startActivity(intent);
            }
        });

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mMagnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        mSensorManager.registerListener(Part3.this, mMagnetometer, SensorManager.SENSOR_DELAY_NORMAL);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        DecimalFormat df = new DecimalFormat("#.#####");
        if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            display.setText("Gyroscope\nX: " + df.format(event.values[0]) +
                    "\nY: " + df.format(event.values[1]) +
                    "\nZ: " + df.format(event.values[2]));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
