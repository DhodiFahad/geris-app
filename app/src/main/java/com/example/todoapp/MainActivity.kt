package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle("GERIS App")
        setContentView(R.layout.activity_main)
    }

    fun goToConnection(view: View){
        val intent = Intent(applicationContext, SensorConnection::class.java)
        startActivity(intent)
    }
}