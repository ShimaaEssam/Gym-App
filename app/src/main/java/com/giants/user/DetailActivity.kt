package com.giants.user

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.bumptech.glide.Glide
import com.giants.R
import com.giants.UserActivity

class DetailActivity : AppCompatActivity() {

    private lateinit var backdrop:ImageView
    private lateinit var motivation:TextView
    private lateinit var descr:TextView
    private lateinit var sname:TextView
    private lateinit var sfees:TextView
    private lateinit var pBtn:Button
    private lateinit var progressBar:ProgressBar
    private lateinit var text:TextView
    private lateinit var text1:TextView
    private lateinit var text2:TextView
    private lateinit var  layout:LinearLayout
    private lateinit var serviceName:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        layout=findViewById(R.id.linear)
        backdrop=findViewById(R.id.backdrop)
        motivation=findViewById(R.id.mot)
        descr=findViewById(R.id.Desc)
        sname=findViewById(R.id.service_title)
        sfees=findViewById(R.id.fees)
        pBtn=findViewById(R.id.participate)
        progressBar=findViewById(R.id.progressBard)
        text=findViewById(R.id.text)
        text1=findViewById(R.id.text1)
        text2=findViewById(R.id.text2)
        initUi()
        pBtn.setOnClickListener {
            fees()
        }
    }

    private fun fees() {
        progressBar.visibility = View.VISIBLE
        val intent = Intent(this@DetailActivity, ParticipateActivity::class.java)
        intent.putExtra("NAME", serviceName)
        startActivity(intent)
        progressBar.visibility = View.GONE
    }

    private fun initUi() {
        if (intent.hasExtra("NAME")) {
             serviceName = intent.extras!!.getString("NAME")
            val serviceMot = intent.extras!!.getString("MOT")
            val serviceDesc = intent.extras!!.getString("DESC")
            val serviceImg = intent.extras!!.getString("IMG")
            val serviceFees = intent.extras!!.getString("fees")


            if(UserActivity.isVendorLogin){
                pBtn.visibility = View.GONE
            } else {
                pBtn.visibility = View.VISIBLE
            }


            Glide.with(this)
                    .load(serviceImg)
                    .into(backdrop)
            sname.text = serviceName
            motivation.text=serviceMot
            descr.text = serviceDesc
            if(!serviceFees.equals("_")) {
                sfees.text = serviceFees
            }
            else{
                sfees.text=""
                pBtn.visibility=View.GONE
                text.visibility=View.GONE
                text1.visibility=View.GONE
                text2.visibility=View.GONE
                layout.visibility=View.GONE
            }

        } else {
            Toast.makeText(this, "No Data Found", Toast.LENGTH_LONG).show()
        }
    }

    override fun onResume() {
        super.onResume()
        if(UserActivity.isVendorLogin){
            pBtn.visibility = View.GONE
        } else {
            pBtn.visibility = View.VISIBLE
        }
    }
}
