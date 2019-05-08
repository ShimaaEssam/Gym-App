package com.giants.user

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.giants.R
import com.giants.fragments.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*



class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var auth: FirebaseAuth
    private lateinit var name:TextView
    val fragmentManager = supportFragmentManager
    var fragmentTransaction = fragmentManager.beginTransaction()
    private lateinit var title:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        title=titletoolbar
        title.text="Home"
        fragmentTransaction.replace(R.id.framelayoutfragment, HomeFragment())
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

        auth = FirebaseAuth.getInstance()
        var navigationView :NavigationView=  findViewById(R.id.nav_view);
        var hView:View =  navigationView.getHeaderView(0);
        name=hView.findViewById(R.id.userNamefire)

        var database: FirebaseDatabase = FirebaseDatabase.getInstance()
        var uID:String=FirebaseAuth.getInstance().currentUser!!.uid
        Log.i("hehehe",uID)

        val event=object:ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {

                var userName:String= p0.child("userName").getValue(String::class.java)!!
                Log.i("hehehe",userName)
                name.text=userName
            }

        }
        database.getReference("/User/User/"+uID.toString()).addListenerForSingleValueEvent(event)


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
            R.id.nav_home -> {
                title.text="Home"
                val fragmentManager = supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.framelayoutfragment, HomeFragment())
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }
            R.id.nav_services -> {
                title.text="Services"
                val fragmentManager = supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.framelayoutfragment, ServicesFragment())
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }
            R.id.nav_offers -> {
                title.text="Offers"
                val fragmentManager = supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.framelayoutfragment, OffersFragment())
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()            }
            R.id.nav_complaints -> {
                title.text="Complaint"
                val fragmentManager = supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.framelayoutfragment, ComplaintsFragment())
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }
            R.id.nav_logout -> {
                signOut()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
    private fun signOut() {
        auth.signOut()
        startActivity(Intent(this, SignInActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        this.finish()
    }
}
