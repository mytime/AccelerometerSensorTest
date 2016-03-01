package com.jikexueyuan.accelerometersensortest;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

/**
 * 使用加速传感器实现摇一摇
 */
public class MainActivity extends AppCompatActivity {

    //传感管理器对象
    private SensorManager sensorManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //注册传感器
        sensorManager.registerListener(listener,sensor,SensorManager.SENSOR_DELAY_NORMAL);

    }

    /**
     * 销毁
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (sensorManager != null){
            sensorManager.unregisterListener(listener);
        }
    }

    /**
     * 传感器事件监听
     */
    private SensorEventListener listener = new SensorEventListener() {
        /**
         * 数值变化
         * @param event
         */
        @Override
        public void onSensorChanged(SensorEvent event) {
            //加速可能出现负值，所以要取绝对值,
            float xValue = Math.abs(event.values[0]);
            float yValue = Math.abs(event.values[1]);
            float zValue = Math.abs(event.values[2]);
            if(xValue > 15 || yValue >15 || zValue >15 ){
                //出发摇一摇逻辑
                Toast.makeText(MainActivity.this,"摇一摇",Toast.LENGTH_SHORT).show();
            }
        }

        /**
         * 精度变化
         * @param sensor
         * @param accuracy
         */
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
}
