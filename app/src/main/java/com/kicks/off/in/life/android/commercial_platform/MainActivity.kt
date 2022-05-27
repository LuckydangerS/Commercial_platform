package com.kicks.off.`in`.life.android.commercial_platform

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.kicks.off.`in`.life.android.commercial_platform.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var rootElement : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootElement = ActivityMainBinding.inflate(layoutInflater)
        val view = rootElement.root
        setContentView(view)
        init()
    }

    private fun init(){

        val toggle = ActionBarDrawerToggle(this,
            rootElement.drawerLayout,
            rootElement.mainContent.toolbar,
            R.string.open, R.string.close)
        rootElement.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        rootElement.navView.setNavigationItemSelectedListener(this)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId){

            R.id.id_my_ads -> {
                Toast.makeText(this, "Presed id_my_ads", Toast.LENGTH_LONG).show()
            }
            R.id.id_car -> {
                Toast.makeText(this, "Presed id_my_!cvvdfbdf!", Toast.LENGTH_LONG).show()
            }
            R.id.id_pc -> {}
            R.id.id_dm -> {}
            R.id.id_sign_up -> {}
            R.id.id_sign_in -> {}
            R.id.id_sign_out -> {}

        }
        rootElement.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}