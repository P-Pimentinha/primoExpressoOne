package com.example.primoexpresso

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer)

        toggle = ActionBarDrawerToggle(
            this@MainActivity,
            drawerLayout,
            R.string.open,
            R.string.close
        )

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_layout_1, wocheUmsatz())
            commit()
        }


        // functionality for the nav drawer items
        val navVIew: NavigationView = findViewById(R.id.nav_view)
        navVIew.setNavigationItemSelectedListener {
            it.isChecked = true
            when (it.itemId) {

                R.id.wocheUmsatz -> {
                    replaceFragment(wocheUmsatz(), it.title.toString())
                }

                R.id.drinksOrder -> {
                    replaceFragment(drinksOrder(), it.title.toString())
                }

            }
            true
        }
    }


    private fun replaceFragment(fragment: Fragment, title: String) {
        val fragmentManager = supportFragmentManager
        val fragTrans = fragmentManager.beginTransaction()
        fragTrans.replace(R.id.frame_layout_1, fragment)
        fragTrans.commit()
        drawerLayout.closeDrawers()
        setTitle(title)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    }