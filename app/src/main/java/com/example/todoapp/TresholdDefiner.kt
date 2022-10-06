package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import android.widget.AdapterView.OnItemSelectedListener


class TresholdDefiner : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var textView1: TextView? = null
    private var seekBar1: SeekBar? = null

    private var textView2: TextView? = null
    private var seekBar2: SeekBar? = null

    private var textView3: TextView? = null
    private var textView5: TextView? = null

    private var progress1: Int? =  null
    private var progress2: Int? =  null

    private var toolbar: Toolbar? = null
    private var drawerLayout: DrawerLayout? = null
    private var navigationView: NavigationView? = null

    private var dropdown: Spinner? = null

    //create a list of items for the spinner.
    private val items = arrayOf("Maize", "Rice", "Soybean")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle("GERIS Agri-solutions")
        setContentView(R.layout.activity_treshold_definer)
        configureToolBar()
        configureDrawerLayout()
        configureNavigationView()

        //get the spinner from the xml.
        dropdown = findViewById(R.id.spinner1)
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)
        //set the spinners adapter to the previously created one.
        dropdown!!.adapter = adapter


        textView1 = findViewById<View>(R.id.textView1) as TextView
        textView3 = findViewById<View>(R.id.textView3) as TextView
        seekBar1 = findViewById<View>(R.id.seekBar1) as SeekBar
        progress1 = 20
        seekBar1!!.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (progress2!! > progress) {
                    progress1 = progress
                    textView1!!.text = "$progress°"
                    textView3!!.text = "$progress°"
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        textView2 = findViewById<View>(R.id.textView2) as TextView
        textView5 = findViewById<View>(R.id.textView5) as TextView
        seekBar2 = findViewById<View>(R.id.seekBar2) as SeekBar
        progress2 = 24
        seekBar2!!.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (progress > progress1!!) {
                    progress2 = progress
                    textView2!!.text = "$progress°"
                    textView5!!.text = "$progress°"
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
    }

    /** Called when the user taps the button to define the treshold */
    fun confirmTreshold(view: View) {
        val intent = Intent(applicationContext, RealTimeMonitoring::class.java)
        startActivity(intent)
        RealTimeMonitoring.lowTreshold = progress1 as Int
        RealTimeMonitoring.highTreshold = progress2 as Int
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
            R.id.activity_main_drawer_realtime -> {
                val intent = Intent(applicationContext, RealTimeMonitoring::class.java)
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

    override fun onStart() {
        super.onStart()
        var selection = ""
        dropdown!!.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View,
                position: Int,
                id: Long
            ) {
                selection = items[position]
                if (selection.equals("Maize")) {
                    textView1!!.text = "20°"
                    textView3!!.text = "20°"
                    textView2!!.text = "24°"
                    textView5!!.text = "24°"
                }
                else if (selection.equals("Rice")) {
                    textView1!!.text = "23°"
                    textView3!!.text = "23°"
                    textView2!!.text = "27°"
                    textView5!!.text = "27°"
                }
                else if (selection.equals("Soybean")) {
                    textView1!!.text = "18°"
                    textView3!!.text = "18°"
                    textView2!!.text = "23°"
                    textView5!!.text = "23°"
                }
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // your code here
            }
        })
    }
}




