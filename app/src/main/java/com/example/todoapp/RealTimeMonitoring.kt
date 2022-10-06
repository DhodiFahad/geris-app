package com.example.todoapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView



class RealTimeMonitoring : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener  {
    companion object {
        var lowTreshold = 20
        var highTreshold = 24
    }

    var handler: Handler = Handler()
    var runnable: Runnable? = null
    var delay = 4000
    private var toolbar: Toolbar? = null
    private var drawerLayout: DrawerLayout? = null
    private var navigationView: NavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_real_time_monitoring)
        setTitle("GERIS Agri-solutions")
        configureToolBar()
        configureDrawerLayout()
        configureNavigationView()
    }

    /** I change the temperature using a random to show how the real-time monitoring will work*/
    override fun onResume() {
        val realTimeText = findViewById<TextView>(R.id.realTimeTemp)
        handler.postDelayed(Runnable {
            handler.postDelayed(runnable!!, delay.toLong())
            val randomTemp = (15..40).random()
            var textToDisplay = "$randomTemp°"
            realTimeText.setText(textToDisplay);
            if (randomTemp < lowTreshold) {
                realTimeText.setTextColor(Color.parseColor("#55B0F1"))
            }
            else if (randomTemp > highTreshold) {
                realTimeText.setTextColor(Color.parseColor("#F92727"))
            }
            else {
                realTimeText.setTextColor(Color.parseColor("#5DB075"))
            }
        }.also { runnable = it }, delay.toLong())
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable!!) //stop handler when activity not visible super.onPause();
    }

    /** Called when the user taps the button to define the treshold */
    fun goToTresholdDefiner(view: View) {
        val intent = Intent(applicationContext, TresholdDefiner::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
        // 5 - Handle back click to close menu
        if (drawerLayout!!.isDrawerOpen(GravityCompat.START)) {
            drawerLayout!!.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        // 4 - Handle Navigation Item Click
        val id: Int = item.getItemId()
        when (id) {
            R.id.activity_main_drawer_treshold -> {
                val intent = Intent(applicationContext, TresholdDefiner::class.java)
                startActivity(intent)
            }
            R.id.activity_main_drawer_profile -> {
            }
            else -> {
            }
        }
        drawerLayout!!.closeDrawer(GravityCompat.START)
        return true
    }

    // ---------------------
    // CONFIGURATION
    // ---------------------
    // 1 - Configure Toolbar
    private fun configureToolBar() {
        toolbar = findViewById<View>(R.id.activity_main_toolbar) as Toolbar
        setSupportActionBar(toolbar)
    }

    // 2 - Configure Drawer Layout
    private fun configureDrawerLayout() {
        drawerLayout = findViewById<View>(R.id.activity_main_drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout!!.addDrawerListener(toggle)
        toggle.syncState()
    }

    // 3 - Configure NavigationView
    private fun configureNavigationView() {
        this.navigationView = findViewById<View>(R.id.activity_main_nav_view) as NavigationView
        navigationView!!.setNavigationItemSelectedListener(this)
    }
}