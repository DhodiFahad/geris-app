package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class SensorConnection : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle("GERIS App")
        setContentView(R.layout.activity_sensor_connection)
    }
    /** Called when the user taps the button to connect the sensor */
    fun goToRealTimeMonitoring(view: View) {
        val intent = Intent(applicationContext, RealTimeMonitoring::class.java)
        startActivity(intent)
    }
}