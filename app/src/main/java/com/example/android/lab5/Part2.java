package com.example.android.lab5;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.text.DecimalFormat;
import static java.lang.Math.PI;


public class Part2 extends AppCompatActivity implements SensorEventListener {

    SensorManager mSensorManager;
    Sensor mGyroscope;
    TextView display;
    Button reset;
    private static final float NS2S = 1.0f / 1000000000.0f;
    private float timestamp;
    Float angle;
    double TAU = 2 * PI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part2);

        display = findViewById(R.id.display);
        reset = findViewById(R.id.button);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mGyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        mSensorManager.registerListener(Part2.this, mGyroscope, SensorManager.SENSOR_DELAY_NORMAL);

        angle = 0f;
        timestamp = 0f;

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timestamp = 0f;
                angle = 0f;
            }
        });
    }

    public void onSensorChanged(SensorEvent event) {
        // This time step's delta rotation to be multiplied by the current rotation
        // after computing it from the gyro sample data.
        if (timestamp != 0) {
            final float dT = (event.timestamp - timestamp) * NS2S;
            // Axis of the rotation sample, not normalized yet.
            float angular_speed = event.values[2];
            angle += angular_speed * dT;
            angle %= (float) TAU;
            if (angle < -PI) {
                angle += (float) TAU;
            }
            if (angle > PI) {
                angle -= (float) TAU;
            }

        }
        timestamp = event.timestamp;
        DecimalFormat df = new DecimalFormat("#.###");
        display.setText("Angle:\n" + df.format(angle));

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
