package com.giants.admin

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.giants.R
import com.giants.UserActivity
import com.giants.utils.Validation

class AdminSignInActivity : AppCompatActivity() {

    private lateinit var edtMail: EditText
    private  lateinit var edtPass: EditText
    private lateinit var btnSignIn: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var vaild: Validation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_sign_in)


        edtMail=findViewById(R.id.email_in_admin)
            edtPass=findViewById(R.id.pass_in_admin)
            btnSignIn=findViewById(R.id.sign_in_admin)
            progressBar = findViewById(R.id.progressBarin_admin)
            vaild= Validation()
        val prefs = this.getSharedPreferences("login", Context.MODE_PRIVATE)
        val restoredText = prefs.getInt("log", 0)
        if(restoredText==1){
            val intent = Intent(this@AdminSignInActivity, AdminHomeActivity::class.java)
            startActivity(intent)
            finish()
        }

            btnSignIn.setOnClickListener OnClickListener@{

                var email:String=edtMail.text.toString()
                var password:String=edtPass.text.toString()
                progressBar.visibility = View.VISIBLE
                val checker =  email.isEmpty()|| password.isEmpty()||!vaild.isValidEmail(email) || !vaild.isValidPass(password)
                if (checker) {
                    progressBar.visibility = View.GONE
                    if( email.isEmpty())
                        edtMail.error="Empty Email"
                    else if(!vaild.isValidEmail(email))
                        edtMail.error="Email is not valid"
                    else if(password.isEmpty())
                        edtPass.error="Empty Password"
                    else if(!vaild.isValidPass(password))
                        edtPass.error="Password is not valid"
                }
                else{
                    if(email != "admin@giants.com" || password != "adminG1")
                    {
                        Toast.makeText(this@AdminSignInActivity, "Wrong Admin Account !", Toast.LENGTH_LONG).show()
                    progressBar.visibility=View.GONE
                    }
                    else if(email == "admin@giants.com" || password == "adminG1"){
                        val editor = getSharedPreferences("login", Context.MODE_PRIVATE).edit()
                        editor.putInt("log", 1)
                        editor.apply()
                        val intent = Intent(this@AdminSignInActivity, AdminHomeActivity::class.java)
                        progressBar.visibility=View.GONE
                        UserActivity.isVendorLogin = true
                        startActivity(intent)
                        this.finish()
                    }
                }
                return@OnClickListener

                }
            }
    override fun onPause() {
        super.onPause()
        edtMail.error = null
        edtPass.error = null
    }
}


