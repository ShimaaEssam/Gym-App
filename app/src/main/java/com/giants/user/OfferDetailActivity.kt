package com.giants.user

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.giants.R
import com.giants.UserActivity

class OfferDetailActivity : AppCompatActivity() {
    private lateinit var backdrop: ImageView
    private lateinit var motivation: TextView
    private lateinit var descr: TextView
    private lateinit var price: TextView
    private lateinit var pBtn: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var offermMot:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_offer_detail)
        backdrop=findViewById(R.id.backdrop)
        motivation=findViewById(R.id.offer_mot)
        descr=findViewById(R.id.offer_desc)
        price=findViewById(R.id.offer_price)
        pBtn=findViewById(R.id.participateO)
        progressBar=findViewById(R.id.progressBarO)

        initUi()
        pBtn.setOnClickListener {
            fees()
        }
    }

    private fun fees() {
        progressBar.visibility = View.VISIBLE
        val intent = Intent(this@OfferDetailActivity, OfferParticipateActivity::class.java)
        intent.putExtra("NAME", offermMot)
        startActivity(intent)
        progressBar.visibility = View.GONE
    }

    private fun initUi() {
        if (intent.hasExtra("MOT")) {
             offermMot = intent.extras!!.getString("MOT")
            val offerDesc = intent.extras!!.getString("DESC")
            val offerPrice = intent.extras!!.getString("PRICE")


            if(UserActivity.isVendorLogin){
                pBtn.visibility = View.GONE
            } else {
                pBtn.visibility = View.VISIBLE
            }

            motivation.text = offermMot
            descr.text=offerDesc
            price.text = offerPrice

        } else {
            Toast.makeText(this, "No Data Found", Toast.LENGTH_LONG).show()
        }

    }
}

