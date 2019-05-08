package com.giants.admin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.TextView
import com.giants.R
import com.giants.UserActivity
import com.giants.fragments.*
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_admin_home.*
import kotlinx.android.synthetic.main.app_bar_admin_home.*

class AdminHomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var auth: FirebaseAuth
    val fragmentManager = supportFragmentManager
    var fragmentTransaction = fragmentManager.beginTransaction()
    private lateinit var title: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_home)
        setSupportActionBar(toolbar)
        title=toolbarAdmin
        title.text="Services"
        fragmentTransaction.replace(R.id.framelayoutfragmenAdmin, AdminServicesFragment())
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

        auth = FirebaseAuth.getInstance()

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_servicesA -> {
                title.text="Services"

                val fragmentManager = supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.framelayoutfragmenAdmin, AdminServicesFragment())
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }
            R.id.nav_offersA -> {
                title.text="Offers"

                val fragmentManager = supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.framelayoutfragmenAdmin, AdminOffersFragment())
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
                }

            R.id.nav_complaintsA -> {
                title.text="Complaints"

                val fragmentManager = supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.framelayoutfragmenAdmin, AdminComplaintsFragment())
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }

            R.id.nav_logoutA -> {
                signOut()
                val editor = getSharedPreferences("login", Context.MODE_PRIVATE).edit()
                editor.putInt("log", 0)
                editor.apply()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
    private fun signOut() {
        auth.signOut()
        startActivity(Intent(this, UserActivity::class.java))
        this.finish()
    }
}

