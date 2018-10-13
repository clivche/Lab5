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

public class Part1 extends AppCompatActivity implements SensorEventListener {

    SensorManager mSensorManager;
    Sensor mGyroscope;
    TextView display;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part1);

        display = findViewById(R.id.display);
        view = findViewById(R.id.view1);
        view.setOnTouchListener(new OnSwipeTouchListener(Part1.this) {
            @Override
            public void onSwipeLeft() {
                Intent intent = new Intent(Part1.this, Part2.class);
                finish();
                startActivity(intent);
            }
        });

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mGyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        mSensorManager.registerListener(Part1.this, mGyroscope, SensorManager.SENSOR_DELAY_NORMAL);
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
