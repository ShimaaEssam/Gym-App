package com.giants

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.giants.admin.AdminSignInActivity
import com.giants.user.SignInActivity
import kotlinx.android.synthetic.main.activity_user.*


class UserActivity : AppCompatActivity() {
    private lateinit var userBtn: Button
    private lateinit var adminBtn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        userBtn=user
        adminBtn=admin
        userBtn.setOnClickListener {
            var intent = Intent(this@UserActivity, SignInActivity::class.java)
            startActivity(intent)
        }
        adminBtn.setOnClickListener {
            var intent = Intent(this@UserActivity, AdminSignInActivity::class.java)
            startActivity(intent)
        }

       // val dbNode = FirebaseDatabase.getInstance().reference.root.child("Complaints")
       // dbNode.removeValue()

    }

    companion object {
        var isVendorLogin : Boolean = false
    }
}
